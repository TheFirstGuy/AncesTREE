package gui;

import datatree.DataTree;
import datatree.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class Main extends Application {

    private Stage primaryStage;
    private VBox rootLayout;

    @Override
    public  void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AncesTREE");

        initRootLayout();

    }

    public static void main(String[] args) {

        launch(args);

    }

    // Initializes the root layout
    private void initRootLayout(){
        try{
            // Load the root layout from the fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("main.fxml"));
            this.rootLayout = (VBox) loader.load();

            // Test geting canvas
            SplitPane temp =  (SplitPane) rootLayout.lookup("#mainVBox").lookup("#splitPane");
            Canvas canvas = (Canvas) temp.getItems().get(1).lookup("#baseCanvas");

            // Create person for card
            Person person = new Person("Eddard", "Stark", new Date(-1700, 1, 12), new Date(-1650, 6, 31), false);

            GraphicsContext gc = canvas.getGraphicsContext2D();

            DataTree dataTree = new DataTree();

            CardManager cardManager = new CardManager(gc, canvas, dataTree);

            cardManager.initCards(person);



            // Show the scene
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
