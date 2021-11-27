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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.IOException;
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
        Button pauseBtn = new Button("Pause Timer");
        Button resetBtn = new Button("Reset Timer");
        bottomMenu.getChildren().addAll(startBtn,pauseBtn,resetBtn);
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
            rotations.getAndDecrement();
            num_rotations.setText(rotations.toString());
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
                    //play sound
                    minutes = 5;
                    seconds = 0;
                    complete_cycle = true;
                    ddSeconds = decimalFormat.format(seconds);
                    ddMinutes = decimalFormat.format(minutes);
                    timer_label.setText(ddMinutes + ":" + ddSeconds);
                }
                else if(minutes == 0 && seconds == 0 && complete_cycle == true){
                    //play sound
                    minutes = 25;
                    seconds = 0;
                    rotations.getAndDecrement();
                    complete_cycle = false;
                    ddSeconds = decimalFormat.format(seconds);
                    ddMinutes = decimalFormat.format(minutes);
                    timer_label.setText(ddMinutes + ":" + ddSeconds);
                }

                if(minutes == 0 && seconds == 0 && rotations.get() == 0){
                    ddSeconds = decimalFormat.format(seconds);
                    ddMinutes = decimalFormat.format(minutes);
                    timer_label.setText(ddMinutes + ":" + ddSeconds);
                    timer.cancel();
                }
            }
        };

        AtomicBoolean paused = new AtomicBoolean(false);
        startBtn.setOnAction(e -> {
                    if (paused.get() == true) {
                        timer.notify();
                    } else {
                        timer.schedule(timerTask, 1000);
                    }
                }
        );
        pauseBtn.setOnAction(e -> {
            try {
                timer.wait();
                paused.set(true);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        resetBtn.setOnAction(e -> {
            timer.cancel();
            timer_label.setText("00:00");
        });
    }
}