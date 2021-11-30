import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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

    Calendar cal = Calendar.getInstance();

    //Event myEvent = new MyEvent();


    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    static LocalDateTime now = LocalDateTime.now();

    ArrayList<Task> display = cal.readArray(dtf.format(now));
    public ArrayList<Task> todayList;

    public static ArrayList<Task> testarr = new ArrayList<Task>();
    public static ArrayList<Task> testarr2 = new ArrayList<Task>();
    public static ArrayList<Task> sevenDays = new ArrayList<Task>();

    int task = cal.addTask(new Task("Do homework", "11/29/21", "High", "School"));

    private TableView<Task> dayTable = new TableView();
    public ObservableList<Task> dayData =

            FXCollections.observableArrayList(
                    todayList = cal.readArray(dtf.format(now))
            );

    private TableView<Task> weekTable = new TableView();
    public final ObservableList<Task> weekData =

            FXCollections.observableArrayList(
                    sevenDays
            );

    public static int parseDay(){
        int[] dayarr = new int[3];
        String date = dtf.format(now);
        String[] stringarr = date.split("/");
        for(int k = 0; k < dayarr.length; k++){
            dayarr[k] = Integer.valueOf(stringarr[k]);
            System.out.println(dayarr[k]);
        }
        int day = dayarr[1];
        return day;
    }

    public static void main(String[] args) {
        testarr.add(new Task("Do homework", "11/29/21", "High", "School"));
        testarr.add(new Task("Walk dog", "11/29/21", "medium", "personal"));
        testarr.add(new Task("eat food", "11/30/21", "low", "personal"));
        testarr.add(new Task("eat food", "11/31/21", "low", "personal"));
        testarr.add(new Task("eat food", "12/01/21", "low", "personal"));

        int day = parseDay();
        int i = 0;
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
//        Calendar cal = new Calendar();
        Scene scene = new Scene(new Group());

        primaryStage.setTitle("Home Menu");
        primaryStage.setWidth(1350);
        primaryStage.setHeight(800);

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
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        //System.out.println(dtf.format(now));
        //System.out.println(display.toString());

        //---------------
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

                Calendar cal = Calendar.getInstance();
                Task newTask = new Task(taskNameField.getText(), date.getEditor().getText(), priorityField.getText() , taskTagField.getText());

                if(cal.addTask(newTask) == 1){
                    System.out.println("Task Added");
                }
                else{
                    System.out.println("Task did not add");
                }
                todayList = cal.readArray(dtf.format(now));
                dayData = FXCollections.observableArrayList(
                        todayList = cal.readArray(dtf.format(now))
                );

                dayTable.setItems(dayData);
                taskNameField.clear();
                priorityField.clear();
                taskNameField.clear();
                taskTagField.clear();
                date.getEditor().clear();
                //primaryStage.getScene().getWindow().setWidth(primaryStage.getScene().getWidth() + 100);
            }

        });


        grid.add(btn, 0, 6);
        //--------------

        HBox hboxLabels = new HBox();
        hboxLabels.getChildren().addAll(dayLabel, weekLabel);
        hboxLabels.setSpacing(500);
        hboxLabels.setAlignment(Pos.TOP_CENTER);

        HBox hboxData = new HBox();
        hboxData.getChildren().addAll(dayTable, weekTable);
        hboxData.setSpacing(100);
        hboxData.setPadding(new Insets(10,10,10,10));
        hboxData.setAlignment(Pos.CENTER);

        HBox hboxButtons = new HBox();
        hboxButtons.getChildren().addAll(button1,button2,button3,button4);
        hboxButtons.setSpacing(300);
        hboxButtons.setAlignment(Pos.BOTTOM_CENTER);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(hboxLabels, hboxData, hboxButtons, grid);

        Label label1 = new Label("\"There are two ways of spreading light: to be the candle or mirror that reflects it.\" -Edith Wharton");

        HBox topbuttons = new HBox();
        topbuttons.getChildren().addAll(button1, button3, label1, button4, button2);
        topbuttons.setAlignment(Pos.CENTER);
        topbuttons.setPadding(new Insets(10,10,10,10));
        topbuttons.setSpacing(100);

//        HBox botbuttons = new HBox();
//        botbuttons.getChildren().addAll(button3, button4);
//        botbuttons.setAlignment(Pos.CENTER);
//        botbuttons.setPadding(new Insets(10,10,10,10));
//        botbuttons.setSpacing(400);

        Label spacing = new Label();
        VBox labels_and_data = new VBox();
        labels_and_data.getChildren().addAll(spacing, hboxLabels, hboxData);

        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(grid);
        borderPane.setCenter(labels_and_data);
        borderPane.setTop(topbuttons);
        borderPane.setPadding(new Insets(10,10,10,10));

        ((Group) scene.getRoot()).getChildren().addAll(borderPane);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
