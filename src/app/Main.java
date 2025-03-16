package app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends javafx.application.Application{

    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Calculadora.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            primaryStage.setTitle("Calculadora JavaFX");
            primaryStage.setScene(scene);

            primaryStage.setResizable(false);
            primaryStage.setMaximized(false);

            primaryStage.show();
        }catch(IOException e){
            e.printStackTrace();
        }



    }

    public static void main(String[] args){
        launch(args);
    }
}