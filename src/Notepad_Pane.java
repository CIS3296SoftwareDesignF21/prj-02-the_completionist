import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import sun.tools.jstat.Alignment;

import java.io.File;
import java.io.FileWriter;
import java.io.*;
import java.net.URL;

public class Notepad_Pane extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        MenuBar menubar = new MenuBar();
        Button saveChanges = new Button("Save Changes");
        Menu file = new Menu("File");
//        Menu edit = new Menu("Edit");
//        Menu format = new Menu("Format");
//        Menu view = new Menu("View");
//        Menu help = new Menu("Help");

//        MenuItem new = new MenuItem("New");
//        MenuItem open = new MenuItem("Open");
        //MenuItem saveChanges = new MenuItem("Save Changes");
//        MenuItem saveAs = new MenuItem("Save As");

        //file.getItems().add(saveChanges);

        menubar.getMenus().add(file);

        TextArea textArea = new TextArea();
        //textArea.setMaxWidth(600);
        //textArea.setMaxHeight(500);
        textArea.setPrefHeight(300);
        textArea.setPrefWidth(300);
        textArea.setWrapText(true);

        // solution for jar file read error. jar files cannot access reference files for read/write purposes.
        String filePath = new File("").getAbsolutePath();
        String newFilePath = filePath + "/src/notepad.txt";
        int lengthOfFilePath = filePath.length();
        String slicedFilePath = newFilePath.substring(0, (lengthOfFilePath - 36)); //86  before
        String finalFilePath =  slicedFilePath + "/completionistNotepad.txt";
        System.out.println(filePath);
        System.out.println(newFilePath);
        System.out.println(slicedFilePath);
        System.out.println(finalFilePath);

        // IDE solution
//        FileReader fr = new FileReader (
//                "../prj-02-the_completionist/src/notepad.txt");

        System.out.println();

        FileReader fr = new FileReader (
                finalFilePath);

        int i;
        StringBuilder notepadContent = new StringBuilder("");

        while ((i = fr.read()) != -1) {
            notepadContent.append((char)i);
        }

        textArea.setText(notepadContent.toString());

//        for (int i = 0; i < 5; i++) {
//            textArea.setText("Hello " + i);
//        }

        saveChanges.setOnAction(ActionEvent-> {
//                    FileChooser fc = new FileChooser();
//                    FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("txt files", "*.txt");
//                    File saveFile = fc.showSaveDialog(null);

            //IDE SOLN
//            File fold = new File ("../prj-02-the_completionist/src/notepad.txt");
//            fold.delete();
//            File fnew = new File ("../prj-02-the_completionist/src/notepad.txt");

            File fold = new File (finalFilePath);
            fold.delete();
            File fnew = new File (finalFilePath);

            try {
                FileWriter fw = new FileWriter(fnew);
                fw.write(textArea.getText());
                fw.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        });

        VBox menu = new VBox();
        menu.setPadding(new Insets(5,5,5,5));
        menu.setSpacing(5);
        menu.getChildren().addAll(saveChanges, textArea);

//        GridPane gp = new GridPane();
//        gp.add(saveChanges, 0, 0);
//        gp.add(textArea, 0, 1);
//
//        Group gr = new Group();
//        gr.getChildren().add(gp);
        Scene sc = new Scene(menu, 400, 325);
        Stage st = new Stage();
        st.setScene(sc);
        st.show();
        Region region = (Region) textArea.lookup(".content");
        region.setBackground(new Background(new BackgroundFill( Color.KHAKI, CornerRadii.EMPTY, Insets.EMPTY )));
    }
}