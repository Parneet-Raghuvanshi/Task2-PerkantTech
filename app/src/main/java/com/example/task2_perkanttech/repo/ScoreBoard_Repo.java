package com.example.task2_perkanttech.repo;

import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.task2_perkanttech.helpers.CustomSnacks;
import com.example.task2_perkanttech.models.StudentModel;
import com.example.task2_perkanttech.retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreBoard_Repo {
    private final Call<ResponseBody> scoreBoardCall;

    public ScoreBoard_Repo() {
        scoreBoardCall = RetrofitClient.getInstance().getApi().scoreBoardApi();
    }

    public MutableLiveData<List<StudentModel>> getScoreBoard() {
        MutableLiveData<List<StudentModel>> scoreBoard = new MutableLiveData<>();
        scoreBoardCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    JSONArray mainArray = null;
                    List<StudentModel> list = new ArrayList<>();

                    try {
                        mainArray = new JSONArray(response.body().string());
                        for (int i=0;i<mainArray.length();i++){
                            StudentModel model = new StudentModel();
                            JSONObject temp = mainArray.optJSONObject(i);
                            model.setName(temp.optString("name"));
                            model.setScore(temp.optString("score"));
                            model.setCode(temp.optString("code"));
                            list.add(model);
                        }

                        //---( Sorting by Code )---//
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            list.sort((o1, o2) -> Integer.parseInt(o1.getCode().substring(1))-Integer.parseInt(o2.getCode().substring(1)));
                        }

                        //---( Sort List Accordingly )---//
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            list.sort((o1, o2) -> Integer.parseInt(o2.getScore())-Integer.parseInt(o1.getScore()));
                        }

                        //---( Updating Live Data )---//
                        scoreBoard.postValue(list);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        Log.i("ERROR"," === "+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                call.cancel();

            }
        });
        return scoreBoard;
    }
}
