package com.example.demo;

import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Main_Menu_Pane menu = new Main_Menu_Pane();
        menu.start(stage);
    }
}
