package com.example.demo;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.MenuItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Notepad_Pane extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MenuBar menubar = new MenuBar();

        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu format = new Menu("Format");
        Menu view = new Menu("View");
        Menu help = new Menu("Help");

        MenuItem New = new MenuItem("New");
        MenuItem Open = new MenuItem("Open");
        MenuItem Save = new MenuItem("Save");
        MenuItem SaveAs = new MenuItem("Save As");

        file.getItems().addAll(New, Open, Save, SaveAs);

        menubar.getMenus().addAll(file, edit, format, view, help);

        TextArea textArea = new TextArea();
        textArea.setMaxWidth(600);
        textArea.setMaxHeight(400);

        Save.setOnAction(ActionEvent-> {
            FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("txt files", "*.txt");
            File saveFile = fc.showSaveDialog(null);
            try {
                FileWriter fw = new FileWriter(saveFile);
                fw.write(textArea.getText());
                fw.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        });


        GridPane gp = new GridPane();
        gp.add(menubar, 0, 0);
        gp.add(textArea, 0, 1);

        Group gr = new Group();
        gr.getChildren().add(gp);
        Scene sc = new Scene(gr);
        Stage st = new Stage();
        st.setScene(sc);
        st.setMaxWidth(600);
        st.setMaxHeight(500);
        st.show();

    }
}
