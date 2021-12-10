package com.example.task2_perkanttech.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.task2_perkanttech.R;
import com.example.task2_perkanttech.adapters.ScoreBoard_Adapter;
import com.example.task2_perkanttech.helpers.CustomSnacks;
import com.example.task2_perkanttech.models.StudentModel;
import com.example.task2_perkanttech.viewmodels.ScoreBoard_ViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScoreBoard_Adapter scoreBoard_adapter;
    private List<StudentModel> studentModelList = new ArrayList<>();
    private ScoreBoard_ViewModel scoreBoard_viewModel;
    private RelativeLayout progressLayout;
    private ImageView mainMenu;
    private String ID;
    boolean doubleBackToExitPressedOnce = false;
    private CustomSnacks customSnacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ID = getIntent().getStringExtra("ID");
        customSnacks = new CustomSnacks();

        recyclerView = findViewById(R.id.rv_scoreboard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scoreBoard_adapter = new ScoreBoard_Adapter(this,studentModelList);
        recyclerView.setAdapter(scoreBoard_adapter);
        progressLayout = findViewById(R.id.progress_layout);
        progressLayout.setVisibility(View.VISIBLE);
        mainMenu = findViewById(R.id.menu_btn);

        scoreBoard_viewModel = new ViewModelProvider(this).get(ScoreBoard_ViewModel.class);
        scoreBoard_viewModel.getStudentsList().observe(this, new Observer<List<StudentModel>>() {
            @Override
            public void onChanged(List<StudentModel> studentModels) {
                if (studentModels != null) {
                    progressLayout.setVisibility(View.GONE);
                    studentModelList.clear();
                    studentModelList.addAll(studentModels);
                    scoreBoard_adapter.notifyDataSetChanged();
                }
            }
        });

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Dashboard.this,R.style.BottomSheetDialogTheme);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
                bottomSheetDialog.setCanceledOnTouchOutside(true);

                //---( Bottom Sheet Variables )---//
                TextView userName = bottomSheetDialog.findViewById(R.id.user_name);
                ImageView userImage = bottomSheetDialog.findViewById(R.id.user_image);
                Button logoutButton = bottomSheetDialog.findViewById(R.id.logout_btn);

                if (ID.equals("harsh")){
                    userName.setText("Harsh");
                    userImage.setImageDrawable(getResources().getDrawable(R.drawable.profile));
                }
                else if (ID.equals("nikita")){
                    userName.setText("Nikita");
                    userImage.setImageDrawable(getResources().getDrawable(R.drawable.woman));
                }

                logoutButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Dashboard.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                bottomSheetDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        View view = findViewById(android.R.id.content);
        if (doubleBackToExitPressedOnce) {
            finish();
        }

        this.doubleBackToExitPressedOnce = true;
        customSnacks.infoSnack(view,"Press back again to exit!");

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}