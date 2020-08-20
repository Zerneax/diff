package com.diff.controller;

import com.diff.process.FileProcess;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.GenericStyledArea;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MainController {

    @FXML
    private GridPane files;



    @FXML
    private TextFlow leftFile;

    @FXML
    private TextFlow rightFile;

    private FileProcess fileProcess;

    public MainController() {
        this.fileProcess = new FileProcess();
    }

    @FXML
    protected void openFiles() {

        List<File> files = this.chooseFiles();

        List<String> contentLeftFile = this.fileProcess.convertFileToArray(Paths.get(files.get(0).getPath()));
        List<String> contentRightFile = this.fileProcess.convertFileToArray(Paths.get(files.get(1).getPath()));

        this.displayFile(this.leftFile, contentLeftFile);
        this.displayFile(this.rightFile, contentRightFile);
    }

    protected List<File> chooseFiles() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text files", "*.txt")
        );
        List<File> files = fileChooser.showOpenMultipleDialog(Stage.getWindows().get(0));
        return files;
    }

    protected void displayFile(TextFlow textArea, List<String> contentOfFile) {
        if(!contentOfFile.isEmpty()) {
            contentOfFile.stream().forEach(line -> {
                Text text = new Text(line + "\n");
                //Label text = new Label(line + "\n");
                if(text.getText().equals("test1\n")) {
                    text.getStyleClass().add("highlight-error");
                }
                textArea.getChildren().add(text);
            });
        }
    }
}
