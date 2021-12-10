package com.example.task2_perkanttech.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.task2_perkanttech.models.StudentModel;
import com.example.task2_perkanttech.repo.ScoreBoard_Repo;

import java.util.List;

public class ScoreBoard_ViewModel extends ViewModel {
    private MutableLiveData<List<StudentModel>> studentsList;
    private ScoreBoard_Repo scoreBoard_repo;

    public ScoreBoard_ViewModel() {
        scoreBoard_repo = new ScoreBoard_Repo();
        studentsList = scoreBoard_repo.getScoreBoard();
    }

    public MutableLiveData<List<StudentModel>> getStudentsList() {
        return studentsList;
    }
}
