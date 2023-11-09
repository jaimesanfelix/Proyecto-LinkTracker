package com.example.practicafinal;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import linktracker.model.WebPage;
import linktracker.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;

public class LinkController {

    public MenuItem mi_loadFile;
    public ListView lv_name;
    public ListView lv_url;
    private ArrayList<WebPage> webList = new ArrayList<>();

    public void onLoadFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        webList = (ArrayList<WebPage>) FileUtils.loadPages(selectedFile.toPath());

        for (WebPage web:webList) {
            lv_name.getItems().add(web.getWebName());
        }

        for(WebPage url:webList){
            lv_url.getItems().add(url.getUrl());
        }

    }
}
