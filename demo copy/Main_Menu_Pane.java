package com.example.demo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.CheckBox;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main_Menu_Pane extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Home Menu");

        Button button1 = new Button("Calendar");
        Calendar_Pane cal = new Calendar_Pane();
        button1.setOnAction(e -> {
            try {
                cal.start(primaryStage);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        Button button2 = new Button("Add Task");
        Add_Task_Pane add = new Add_Task_Pane();
        button2.setOnAction(e -> {
            add.start(primaryStage);
        });

        Button button3 = new Button("Pomodoro Timer");
        Pomodoro_Timer_Pane pt = new Pomodoro_Timer_Pane();
        button3.setOnAction(e -> {
            try {
                pt.start(primaryStage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button button4 = new Button("Notepad");
        Notepad_Pane np = new Notepad_Pane();
        button4.setOnAction(e -> {
            try {
                np.start(primaryStage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button button5 = new Button("Feature 3");

        Text textToDoToday = new Text("To Do Today");

        Text textTask = new Text("Task");
        Text textTask1 = new Text("Task 1");
        Text textTask2 = new Text("Task 2");
        Text textTask3 = new Text("Task 3");

        Text textWeekTask = new Text("Task");
        Text textWeekTask1 = new Text("Task 1");
        Text textWeekTask2 = new Text("Task 2");
        Text textWeekTask3 = new Text("Task 3");

        Text textPriority = new Text("Priority");
        Text textHighPriority = new Text("High");
        Text textMediumPriority = new Text("Medium");
        Text textLowPriority = new Text("Low");

        Text textWeekPriority = new Text("Priority");
        Text textWeekHighPriority = new Text("High");
        Text textWeekMediumPriority = new Text("Medium");
        Text textWeekLowPriority = new Text("Low");

        Text textTag = new Text("Tag");
        Text textWorkTag = new Text("Work");
        Text textSchoolTag = new Text("School");
        Text textPersonalTag = new Text("Personal");

        Text textWeekTag = new Text("Tag");
        Text textWeekWorkTag = new Text("Work");
        Text textWeekSchoolTag = new Text("School");
        Text textWeekPersonalTag = new Text("Personal");

        Text textDone = new Text("Done");

        CheckBox checkBox1 = new CheckBox("Completed");
        CheckBox checkBox2 = new CheckBox("Completed");
        CheckBox checkBox3 = new CheckBox("Completed");

        Text textWeekOverview = new Text("Next 7 Days");
        Text textDueDate = new Text("Due Date");
        Text textDueDate1 = new Text("01/01/2022");
        Text textDueDate2 = new Text("01/02/2022");
        Text textDueDate3 = new Text("01/03/2022");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(button1, 0, 0, 1, 1);
        gridPane.add(button2, 2, 0, 1, 1);
        gridPane.add(button3, 0, 16, 1, 1);
        gridPane.add(button4, 2, 16, 1, 1);
        gridPane.add(button5, 4, 16, 1, 1);

        gridPane.add(textToDoToday, 1, 2, 1, 1);

        gridPane.add(textTask, 0,3,1,1);
        gridPane.add(textTask1, 0, 4,1,1);
        gridPane.add(textTask2, 0,5,1,1);
        gridPane.add(textTask3,0,6,1,1);

        gridPane.add(textPriority,1,3,1,1);
        gridPane.add(textHighPriority,1,4,1,1);
        gridPane.add(textMediumPriority,1,5,1,1);
        gridPane.add(textLowPriority,1,6,1,1);

        gridPane.add(textTag,2,3,1,1);
        gridPane.add(textWorkTag,2,4,1,1);
        gridPane.add(textSchoolTag,2,5,1,1);
        gridPane.add(textPersonalTag,2,6,1,1);

        gridPane.add(textDone,3,3,1,1);
        gridPane.add(checkBox1,3,4,1,1);
        gridPane.add(checkBox2,3,5,1,1);
        gridPane.add(checkBox3,3,6,1,1);

        gridPane.add(textWeekOverview,1,10,1,1);
        gridPane.add(textWeekTask,0,11,1,1);
        gridPane.add(textWeekTask1,0,12,1,1);
        gridPane.add(textWeekTask2,0,13,1,1);
        gridPane.add(textWeekTask3,0,14,1,1);

        gridPane.add(textWeekPriority,1,11,1,1);
        gridPane.add(textWeekHighPriority,1,12,1,1);
        gridPane.add(textWeekMediumPriority,1,13,1,1);
        gridPane.add(textWeekLowPriority,1,14,1,1);

        gridPane.add(textWeekTag,2,11,1,1);
        gridPane.add(textWeekWorkTag,2,12,1,1);
        gridPane.add(textWeekSchoolTag,2,13,1,1);
        gridPane.add(textWeekPersonalTag,2,14,1,1);

        gridPane.add(textDueDate,3,11,1,1);
        gridPane.add(textDueDate1,3,12,1,1);
        gridPane.add(textDueDate2,3,13,1,1);
        gridPane.add(textDueDate3,3,14,1,1);

        Scene scene = new Scene(gridPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
