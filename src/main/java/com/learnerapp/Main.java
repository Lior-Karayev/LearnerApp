package com.learnerapp;

import com.learnerapp.view.LoadingScreen;
import com.learnerapp.view.UserDashboard;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {

    //    Application.launch(LoadingScreen.class, args);
        Application.launch(UserDashboard.class, args);

    }
}