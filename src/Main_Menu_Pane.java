import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimeZone;

public class Main_Menu_Pane extends Application  {

    Calendar cal = Calendar.getInstance(TimeZone.getDefault());

    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    static LocalDateTime now = LocalDateTime.now();

    ArrayList<Task> display = cal.readArray(dtf.format(now));
    //cal.readArray();

    public static ArrayList<Task> testarr = new ArrayList<Task>();
    public static ArrayList<Task> testarr2 = new ArrayList<Task>();
    public static ArrayList<Task> sevenDays = new ArrayList<Task>();

    private TableView<Task> dayTable = new TableView<Task>();
    private final ObservableList<Task> dayData =

            FXCollections.observableArrayList(
                    testarr
            );

    private TableView<Task> weekTable = new TableView<Task>();
    private final ObservableList<Task> weekData =

            FXCollections.observableArrayList(
                    sevenDays
            );


    public static void main(String[] args) {
        testarr.add(new Task("Do homework", "11/29/21", "High", "School"));
        testarr.add(new Task("Walk dog", "11/29/21", "medium", "personal"));
        testarr.add(new Task("eat food", "11/30/21", "low", "personal"));
        testarr.add(new Task("eat food", "11/31/21", "low", "personal"));
        testarr.add(new Task("eat food", "12/01/21", "low", "personal"));

        int[] dayarr = new int[3];
        String date = dtf.format(now);
        String[] stringarr = date.split("/");
        for(int k = 0; k < dayarr.length; k++){
            dayarr[k] = Integer.valueOf(stringarr[k]);
            System.out.println(dayarr[k]);
        }

        int i = 0;
        int day = dayarr[1];
        while(i < 7){
            if(day > 32){
                break;
            }
            else{
                sevenDays.add(testarr.get(i));
            }
            i++;
            day++;
        }

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Calendar cal = new Calendar();
        Scene scene = new Scene(new Group());

        primaryStage.setTitle("Home Menu");
        primaryStage.setWidth(1350);
        primaryStage.setHeight(700);

        Label dayLabel = new Label("To Do Today");
        dayLabel.setFont(new Font("Arial", 20));

        Label weekLabel = new Label("To Do In The Next 7 Days Of Current Month");
        weekLabel.setFont(new Font("Arial", 20));

        dayTable.setEditable(true);
        weekTable.setEditable(true);

        TableColumn taskCol = new TableColumn("Task");
        taskCol.setMinWidth(100);
        taskCol.setCellValueFactory(
                new PropertyValueFactory<Task, String>("name"));

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<Task, String>("date"));

        TableColumn priorityCol = new TableColumn("Priority");
        priorityCol.setMinWidth(200);
        priorityCol.setCellValueFactory(
                new PropertyValueFactory<Task, String>("priority"));

        TableColumn tagCol = new TableColumn("Tag");
        tagCol.setMinWidth(200);
        tagCol.setCellValueFactory(
                new PropertyValueFactory<Task, String>("tag"));

        dayTable.setItems(dayData);
        dayTable.getColumns().addAll(taskCol, dateCol, priorityCol, tagCol);

        TableColumn weekTask = new TableColumn("Task");
        weekTask.setMinWidth(100);
        weekTask.setCellValueFactory(
                new PropertyValueFactory<Task, String>("name"));

        TableColumn weekDate = new TableColumn("Date");
        weekDate.setMinWidth(100);
        weekDate.setCellValueFactory(
                new PropertyValueFactory<Task, String>("date"));

        TableColumn weekPriority = new TableColumn("Priority");
        weekPriority.setMinWidth(200);
        weekPriority.setCellValueFactory(
                new PropertyValueFactory<Task, String>("priority"));

        TableColumn weekTag = new TableColumn("Tag");
        weekTag.setMinWidth(200);
        weekTag.setCellValueFactory(
                new PropertyValueFactory<Task, String>("tag"));

        weekTable.setItems(weekData);
        weekTable.getColumns().addAll(weekTask, weekDate, weekPriority, weekTag);

        Button button1 = new Button("Calendar");
        /*CalendarPane cal = new CalendarPane();
        button1.setOnAction(e -> {
            try {
                cal.main();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });*/

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

        //System.out.println(dtf.format(now));
        //System.out.println(display.toString());

        HBox hboxLabels = new HBox();
        hboxLabels.getChildren().addAll(dayLabel, weekLabel);
        hboxLabels.setSpacing(500);
        hboxLabels.setAlignment(Pos.TOP_CENTER);

        HBox hboxData = new HBox();
        hboxData.getChildren().addAll(dayTable, weekTable);
        hboxData.setSpacing(100);

        HBox hboxButtons = new HBox();
        hboxButtons.getChildren().addAll(button1,button2,button3,button4);
        hboxButtons.setSpacing(300);
        hboxButtons.setAlignment(Pos.BOTTOM_CENTER);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(hboxLabels, hboxData, hboxButtons);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
