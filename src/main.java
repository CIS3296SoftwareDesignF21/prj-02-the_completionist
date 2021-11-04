import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;

public class Main extends Application implements EventHandler<ActionEvent>{
    
    Button button;
    
    public static void main(String[] args) {
      
    launch(args);
    
    }

    @Override
    public void start(Stage stage) {
        button = new Button();
        button.setText("Click Me");
        button.setOnAction(e -> closeProgram());
        
        stage.setTitle("nice title");
        
        stage.setOnCloseRequest(e ->
        {
           e.consume();
           closeProgram();
        });
        
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        
        Scene scene = new Scene(layout, 500, 500);
        
        stage.setScene(scene);
        
        stage.show();
    }
  
    @Override
    public void handle(ActionEvent event){
        if(event.getSource() == button){
            System.out.println("you clicked the button");
        }
    }
    
    private void closeProgram(){
        Boolean answer = ConfirmBox.display("Title", "Are you sure you want to exit?");
    }
}