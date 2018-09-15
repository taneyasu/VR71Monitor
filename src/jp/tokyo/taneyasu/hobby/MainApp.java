/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.tokyo.taneyasu.hobby;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author tanef
 */
public class MainApp extends Application {
    
    private Stage primaryStage;
    private AnchorPane layout;

    @Override
    public void start(Stage aPrimaryStage) {
        primaryStage = aPrimaryStage;
        primaryStage.setTitle("VR71 Monitor");
        initRootLayout();
    }
    
    public void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Scene.fxml"));
            layout = (AnchorPane) loader.load();
            Scene scene = new Scene(layout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}
