import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class Main extends Application implements EventHandler<ActionEvent>{
    
    Button button;
    
    public static void main(String[] args) {
      
    launch(args);
    
    }

    @Override
    public void start(Stage stage) {
        button = new Button();
        button.setText("Click Me");
        
        
        stage.setTitle("nice title");
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        
        
        Label labelOne = new Label("Monday");
        GridPane.setConstraints(labelOne, 0, 0);
        
        Button buttonOne = new Button("1");
        GridPane.setConstraints(buttonOne, 0, 1);
        
        Label labelTwo = new Label("Tuesday");
        GridPane.setConstraints(labelTwo, 1, 0);
        
        Button buttonTwo = new Button("2");
        GridPane.setConstraints(buttonTwo, 1, 1);
        
        grid.getChildren().addAll(labelOne, labelTwo, buttonOne, buttonTwo);
        
        Scene scene = new Scene(grid, 500, 500);
        
        stage.setScene(scene);
        
        stage.show();
    }
  
    @Override
    public void handle(ActionEvent event){
        if(event.getSource() == button){
            System.out.println("you clicked the button");
        }
    }
}