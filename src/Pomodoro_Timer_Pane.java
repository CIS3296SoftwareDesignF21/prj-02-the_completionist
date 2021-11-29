package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Pomodoro_Timer_Pane extends Application {

    Stage window;

    @Override
    public void start(Stage stage) throws IOException {

        AtomicInteger rotations = new AtomicInteger(1);

        window = stage;
        window.setTitle("Pomodoro Timer");

        HBox bottomMenu = new HBox();
        bottomMenu.setSpacing(20);
        Button startBtn = new Button("Start Timer");
        //Button pauseBtn = new Button("Pause Timer");
        Button resetBtn = new Button("Reset Timer");
        //bottomMenu.getChildren().addAll(startBtn,pauseBtn,resetBtn);
        bottomMenu.getChildren().addAll(startBtn,resetBtn);
        bottomMenu.setAlignment(Pos.CENTER);

        Label timer_label = new Label("00:00");
        timer_label.setFont(new Font(80));
        timer_label.setAlignment(Pos.CENTER);

        VBox topMenu = new VBox();
        topMenu.setSpacing(10);
        Label cycles = new Label("Timer Cycles");
        cycles.setFont(new Font(15));
        HBox topMenuOptions = new HBox();
        topMenuOptions.setSpacing(10);
        Label num_rotations = new Label(rotations.toString());
        Button minusBtn = new Button("-");
        minusBtn.setOnAction(e -> {
            if(rotations.get() == 1){
                num_rotations.setText(rotations.toString());
            }
            else {
                rotations.getAndDecrement();
                num_rotations.setText(rotations.toString());
            }
        });
        Button plusBtn = new Button("+");
        plusBtn.setOnAction(e -> {
            rotations.getAndIncrement();
            num_rotations.setText(rotations.toString());
        });
        topMenuOptions.getChildren().addAll(minusBtn,num_rotations,plusBtn);
        topMenuOptions.setAlignment(Pos.CENTER);
        topMenu.getChildren().addAll(cycles, topMenuOptions);
        topMenu.setAlignment(Pos.CENTER);


        BorderPane layout = new BorderPane();
        layout.setBottom(bottomMenu);
        layout.setCenter(timer_label);
        layout.setTop(topMenu);
        layout.setPadding(new Insets(20,20,20,20));

        Scene scene = new Scene(layout, 400, 300);
        Stage st = new Stage();
        st.setScene(scene);
        st.show();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int minutes = 25;
            int seconds = 0;
            boolean complete_cycle = false;
            String ddSeconds, ddMinutes;
            DecimalFormat decimalFormat = new DecimalFormat("00");

            @Override
            public void run() {
                Platform.runLater(() -> {
                    seconds--;
                    ddSeconds = decimalFormat.format(seconds);
                    ddMinutes = decimalFormat.format(minutes);
                    timer_label.setText(ddMinutes + ":" + ddSeconds);

                    if(seconds == -1){
                        seconds = 59;
                        minutes--;
                        ddSeconds = decimalFormat.format(seconds);
                        ddMinutes = decimalFormat.format(minutes);
                        timer_label.setText(ddMinutes + ":" + ddSeconds);
                    }

                    if(minutes == 0 && seconds == 0 && complete_cycle == false){
                        try {
                            playSound();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (UnsupportedAudioFileException e) {
                            e.printStackTrace();
                        } catch (LineUnavailableException e) {
                            e.printStackTrace();
                        }
                        minutes = 5;
                        seconds = 0;
                        complete_cycle = true;
                        ddSeconds = decimalFormat.format(seconds);
                        ddMinutes = decimalFormat.format(minutes);
                        timer_label.setText(ddMinutes + ":" + ddSeconds);
                    }
                    else if(minutes == 0 && seconds == 0 && complete_cycle == true){
                        try {
                            playSound();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (UnsupportedAudioFileException e) {
                            e.printStackTrace();
                        } catch (LineUnavailableException e) {
                            e.printStackTrace();
                        }
                        minutes = 25;
                        seconds = 0;
                        rotations.getAndDecrement();
                        System.out.println(rotations.get());
                        complete_cycle = false;
                        ddSeconds = decimalFormat.format(seconds);
                        ddMinutes = decimalFormat.format(minutes);
                        timer_label.setText(ddMinutes + ":" + ddSeconds);
                    }

                    if(rotations.get() == 0){
                        timer_label.setText("00:00");
                        timer.cancel();
                        timer.purge();
                    }
                });
            }
        };

        AtomicBoolean paused = new AtomicBoolean(false);
        startBtn.setOnAction(e -> {
                if (paused.get() == true) {
                    synchronized (timer) {
                        timer.notify();
                    }
                    paused.set(false);
                } else {
                    timer.schedule(timerTask, 1, 1000);
                }
            }
        );
        /*
        pauseBtn.setOnAction(e -> {
            try {
                synchronized (timer) {
                    timer.wait();
                }
                paused.set(true);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
         */
        resetBtn.setOnAction(e -> {
            timer.cancel();
            timer.purge();
            timer_label.setText("00:00");
        });
    }

    void playSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File f = new File("../prj-02-the_completionist/mixkit-happy-bells-notification-937.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }
}
