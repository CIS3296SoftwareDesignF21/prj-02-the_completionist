import java.net.SecureCacheResponse;

import javax.swing.plaf.InsetsUIResource;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
 
public class addTaskFrame extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Add Task");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        // Button btn = new Button();
        // btn.setText("Say 'Hello World'");
        // btn.setOnAction(new EventHandler<ActionEvent>() {
 
        //     @Override
        //     public void handle(ActionEvent event) {
        //         System.out.println("Hello World!");
        //     }
        // });
        
        // StackPane root = new StackPane();
        // root.getChildren().add(btn);

        Scene scene = new Scene(grid, 300, 250);

        primaryStage.setScene(scene);
         primaryStage.show();
    }
 public static void main(String[] args) {
        launch(args);
    }
}