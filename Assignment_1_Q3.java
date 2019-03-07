package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// Created By Mohammmad Mushfequr Rahman Student ID:100659376
//Created By Terry Darmawan Hosea Student ID: 100670290

public class Assignment_1_Q3 extends Application {



    public static void main(String[] args) {
        launch(args);
    }


    //Function to generate new angle from the mouse coordinates and the old centre
    // replaced with atan2 in updates
    double get_new_theta(double initial_x,double initial_y, double old_centre,double bias)
    {


        double length_x = Math.sqrt(Math.pow(initial_x,2)+Math.pow(old_centre,2));
        double lenght_y = Math.sqrt(Math.pow(initial_y,2)+Math.pow(old_centre,2));

        double new_theta = Math.atan(lenght_y/length_x);
        return new_theta+bias;
    }


    //Function to get length from of the line passed as argument
    double get_Length(Line l1)
    {
        return Math.sqrt((Math.pow((l1.getEndX()-l1.getStartX()),2))+(Math.pow((l1.getEndY()-l1.getStartY()),2)));
    }

    //Function that gets te angle between three lines using the cosine rule
    double get_Angle(Line x, Line y,Line  z)
    {
        double a = get_Length(x);
        double b = get_Length(y);
        double c = get_Length(z);
        return Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
    }

    final double WIDTH = 500.0;
    final double HEIGHT = 500.0;
    final double RADIUS = 120.0;

    @Override
    public void start(Stage primaryStage) {



        //smaller Circle which act as nodes
        Circle[] points = new Circle[3];
        //Three lines drawn between the points
        Line[] lines = new Line[3];
        //Three text labels to display the angles.
        Text[] labels = new Text[3];
        Pane pane = new Pane();
        Circle circle = new Circle(WIDTH / 2, HEIGHT / 2, RADIUS);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);


        for (int i = 0; i < 3; i++) {
            Circle point = new Circle(RADIUS / 20);
            double randomAngle = Math.random() * 2 * Math.PI;
            double x = circle.getCenterX() + RADIUS * Math.cos(randomAngle);
            double y = circle.getCenterY() + RADIUS * Math.sin(randomAngle);
            point.setCenterX(x);
            point.setCenterY(y);
            point.setFill(Color.ORANGE);
            point.setOnMouseDragged(e -> {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    //Event Handling for mouse drag, gets new angle from mouse positions and the original centre of the main circle
                    double angle = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());
                    Circle c = (Circle)e.getSource();
                    // Sets the new centre of the node using polar equations y = rSin(theata) and x = rCos(0)
                    c.setCenterX(circle.getCenterX() + RADIUS * Math.cos(angle));
                    c.setCenterY(circle.getCenterY() + RADIUS * Math.sin(angle));

                    //Computes the angles between the lines using the get angle function
                    // and assigns the value to them
                    angle = get_Angle(lines[1], lines[2], lines[0]);
                    labels[0].setText(String.format("%.2f", angle));

                    angle = get_Angle(lines[2], lines[1], lines[0]);
                    labels[1].setText(String.format("%.2f", angle));
                    angle = get_Angle(lines[0], lines[2], lines[1]);
                    labels[2].setText(String.format("%.2f", angle));

                }
            });
            points[i] = point;
        }

        for (int i = 0; i < 3; i++) {
            int next = i == 2 ? 0 : i + 1; //Boolean condition to make sure that i+1 exists

            Line line = new Line();
            //Section to bind the lines to the binds of the adjoning nodes.
            line.startXProperty().bind(points[i].centerXProperty());
            line.startYProperty().bind(points[i].centerYProperty());
            line.endXProperty().bind(points[next].centerXProperty());
            line.endYProperty().bind(points[next].centerYProperty());
            lines[i] = line;
        }

        // create three Text objects to display the angles
        for (int i = 0; i < 3; i++) {
            Text text = new Text();
            text.xProperty().bind(points[i].centerXProperty().subtract(-RADIUS/20));
            text.yProperty().bind(points[i].centerYProperty().subtract(+RADIUS/20));
            labels[i] = text;
        }

        pane.getChildren().addAll(points[0], points[1], points[2],
                lines[0], lines[1], lines[2], labels[0], labels[1], labels[2]);


        //Setting the stage
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        primaryStage.setTitle("Assignment 1 Question 3");
        primaryStage.setScene(scene);
        primaryStage.show();







    }
}
