import javax.swing.plaf.InsetsUIResource;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

        Label taskName = new Label("Task Name: ");
        grid.add(taskName, 0, 1);
        TextField taskNameField = new TextField();
        grid.add(taskNameField, 1, 1);

        Label dateLabel = new Label("Date: ");
        grid.add(dateLabel, 0, 2);
        DatePicker date = new DatePicker();
        grid.add(date, 1, 2);

        Label priorityLabel = new Label("Priority: ");
        grid.add(priorityLabel, 0, 3);
        TextField priorityField = new TextField();
        grid.add(priorityField, 1, 3);

        Label taskTagLabel = new Label("Task Tag: ");
        grid.add(taskTagLabel, 0, 4);
        TextField taskTagField = new TextField();
        grid.add(taskTagField, 1, 4);

        Button btn = new Button();
        btn.setText("Add Task");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
        @Override
             public void handle(ActionEvent event) {
                
                //Task newTask = new Task(taskNameField.getText(), date.getEditor().getText(), Integer.parseInt(priorityField.getText()) , "True");
                /*
                if(cal.addTask(newTask) == 1){
                    System.out.println("Task Added");
                }
                else{
                    System.out.println("Task did not add");
                }
                */
                
             }
        });
        
        grid.add(btn, 0, 6);
        Scene scene = new Scene(grid, 350, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
 public static void main(String[] args) {
        launch(args);
    }
}