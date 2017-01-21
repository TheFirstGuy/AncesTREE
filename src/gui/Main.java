package gui;

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

            // Create Card for canvas
            Card card = new Card((int)((canvas.getWidth()/2) - Card.FRAME_WIDTH/2), (int)((canvas.getHeight()/2) - Card.FRAME_HEIGHT/2));
            // Create graphics context
            GraphicsContext gc = canvas.getGraphicsContext2D();
            card.drawCard(gc);



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
