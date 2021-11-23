import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Pomodoro_Timer_Pane extends Application {

    Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        window.setTitle("Pomodoro Timer");

        HBox bottomMenu = new HBox();
        bottomMenu.setSpacing(10);
        Button startBtn = new Button("Start Timer");
        Button pauseBtn = new Button("Pause Timer");
        Button stopBtn = new Button("Stop Timer");
        Button resetBtn = new Button("Reset Timer");
        bottomMenu.getChildren().addAll(startBtn,pauseBtn,stopBtn,resetBtn);
        bottomMenu.setAlignment(Pos.CENTER);

        Label timer = new Label("00:00");
        timer.setFont(new Font(70));

        BorderPane layout = new BorderPane();
        layout.setBottom(bottomMenu);
        layout.setCenter(timer);
        layout.setPadding(new Insets(10,10,10,10));

        Scene scene = new Scene(layout, 400, 200);
        Stage st = new Stage();
        st.setScene(scene);
        st.show();
    }
}