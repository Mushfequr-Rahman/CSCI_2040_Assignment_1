package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


// Created By Mohammmad Mushfequr Rahman Student ID:100659376
//Created By Terry Darmawan Hosea Student ID: 100670290
public class Assignment_1_Q1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();

        // Generate three numbers randomly

        for (int i= 0 ;i <3;i++) {
            int image = (int) (Math.random() * 53) + 1;
            System.out.println("Image :" + i +" source " + image );
            ImageView card = new ImageView(String.format(image + ".png"));


            pane.add(card, i, 0);


        }

        //Set Scene
        Scene scene = new Scene(pane,220,100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question 1");
        primaryStage.show();

    }
}
