import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.shape.*;
import java.io.IOException;
import javafx.scene.input.KeyCode;
import java.util.*;

// Created By Mohammmad Mushfequr Rahman Student ID:100659376
//Created By Terry Darmawan Hosea Student ID: 100670290

public class Assignment_1_Q4 extends Application {
    protected String cap_letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    protected int counter[] = new int[26];
    protected String allword = "";
    protected Rectangle[] bar = new Rectangle[26];
    protected Label[] labels = new Label[26];

    public static void main(String[] args) {
        launch(args);
    }

    //Count occurrence of letter from a string by changing values of counter array
    void CountLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            int ascii = (int) character;
            if (ascii >= 65 && ascii <= 90) counter[ascii - 65]++;
        }
    }
    void Draw(TextField textfield, HBox pane) {
        Arrays.fill(counter, 0);
        allword = "";
        java.io.File inputfile = new java.io.File(textfield.getText());
        try {
            Scanner filein = new Scanner(inputfile);
            while (filein.hasNext()) {
                String word = filein.next();
                allword += word.toUpperCase();
            }
        } catch (IOException er) {
            System.out.println("File error");
            System.exit(0);
        }
        CountLetter(allword);
        pane.getChildren().clear();
        for (int i = 0; i < 26; i++) {
            bar[i] = new Rectangle(12, counter[i]);
            bar[i].setFill(Color.WHITE);
            bar[i].setStroke(Color.BLACK);
            labels[i] = new Label(cap_letter.charAt(i) + "", bar[i]);
            labels[i].setStyle("-fx-border-color: white");
            labels[i].setContentDisplay(ContentDisplay.TOP);
            pane.getChildren().add(labels[i]);
        }
    }
    public void start(Stage primaryStage) {

        //Create borderpane
        BorderPane mainpane = new BorderPane();
        mainpane.setPadding(new Insets(10, 20, 10, 10));

        //HBox for file name label, file text field and view button
        HBox filebox = new HBox(10);
        filebox.setPrefWidth(420);
        filebox.setAlignment(Pos.CENTER);

        //File text field
        TextField filenamefield = new TextField();
        filenamefield.setPrefWidth(filebox.getPrefWidth() - 200);

        //file name label
        Label flnamelbl = new Label("Filename", filenamefield);
        flnamelbl.setContentDisplay(ContentDisplay.RIGHT);

        //View button
        Button viewfilebutton = new Button("View");
        filebox.getChildren().addAll(flnamelbl, viewfilebutton);

        //HBox for chart
        HBox chartbox = new HBox();
        chartbox.setAlignment(Pos.BOTTOM_CENTER);

        //Initialize bar chart to 0
        for (int i = 0; i < 26; i++) {
            //Here we make a label and set rectangles as it's content
            bar[i] = new Rectangle(12, 0);
            bar[i].setFill(Color.WHITE);
            bar[i].setStroke(Color.BLACK);
            labels[i] = new Label(cap_letter.charAt(i) + "", bar[i]);
            labels[i].setStyle("-fx-border-color: white");
            labels[i].setContentDisplay(ContentDisplay.TOP);
            chartbox.getChildren().add(labels[i]);
        }

        //Set center and bottom of mainpane
        mainpane.setCenter(chartbox);
        mainpane.setBottom(filebox);

        //Set scene
        Scene scene = new Scene(mainpane, 430, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question 4");
        primaryStage.show();

        //Action when enter key is pressed on file text field
        filenamefield.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                Draw(filenamefield, chartbox);
            }
        });

        //Action when view button is pressed
        viewfilebutton.setOnAction(e -> {
            Draw(filenamefield, chartbox);
        });
    }
}
