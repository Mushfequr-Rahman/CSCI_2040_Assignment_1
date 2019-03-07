import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import java.text.DecimalFormat;

// Created By Mohammmad Mushfequr Rahman Student ID:100659376
//Created By Terry Darmawan Hosea Student ID: 100670290

public class Assignment_1_Q2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //creates a grid pane for the labels, text fields and the button
        GridPane pane = new GridPane();
        pane.setVgap(5);
        pane.setHgap(15);
        pane.setPadding(new Insets(5,10,10,10));

        //Text field and label for investment amount
        TextField IA = new TextField();
        pane.add(new Label("Investment Amount "), 0,0);
        pane.add(IA,1,0);

        //Text field and label for years
        TextField years = new TextField();
        pane.add(new Label("Years "), 0,1);
        pane.add(years,1,1);

        //Text field and label for annual interest rate
        TextField AIR = new TextField();
        pane.add(new Label("Annual Interest Rate "), 0,2);
        pane.add(AIR,1,2);

        //Text field and label for future value
        TextField FV = new TextField();
        pane.add(new Label("Future value "), 0,3);
        pane.add(FV,1,3);
        FV.setEditable(false);

        //Button for calculate future value
        Button calculate = new Button("Calculate");
        pane.setHalignment(calculate, HPos.RIGHT);
        pane.add(calculate,1,4);

        //calculation when button is clicked
        calculate.setOnAction((e) ->{
            double IA_val = Double.parseDouble(IA.getText());
            double years_val = Double.parseDouble(years.getText());
            double AIR_val = Double.parseDouble(AIR.getText());
            double future = IA_val*(Math.pow(1+(AIR_val/1200.0),years_val*12.0));

            FV.setText(new DecimalFormat("##.##").format(future));
        });

        //Set scene
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Question 2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
