package gui;

import datatree.DataTree;
import datatree.PersonImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.GregorianCalendar;


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
            ZoomableScrollPane zoomableScrollPane = (ZoomableScrollPane) temp.getItems().get(1).lookup("#zoomPane");
            //TextField textField = (TextField) temp.getItems().get(1).lookup("#searchBox");


            // Create person for card
            PersonImpl eddard = new PersonImpl("Eddard", "Stark", PersonImpl.SEX.MALE ,new GregorianCalendar(-1637, 1, 12), new GregorianCalendar(-1601, 6, 31), false);
            PersonImpl catelyn = new PersonImpl("Catelyn", "Tully", PersonImpl.SEX.FEMALE, new GregorianCalendar(-1636, 4, 24), new GregorianCalendar(-1601, 8, 23), false);
            PersonImpl robb = new PersonImpl("Robb", "Stark", PersonImpl.SEX.MALE, new GregorianCalendar(-1617, 2, 10), new GregorianCalendar(-1601, 3, 10), false);
            PersonImpl sansa = new PersonImpl("Sansa", "Stark", PersonImpl.SEX.FEMALE, new GregorianCalendar(-1614, 3, 12), null, true);
            PersonImpl arya = new PersonImpl("Arya", "Stark", PersonImpl.SEX.FEMALE, new GregorianCalendar(-1611, 3, 12), null, true);
            PersonImpl bran = new PersonImpl("Bran", "Stark", PersonImpl.SEX.MALE, new GregorianCalendar(-1610, 5, 2), null, true);
            PersonImpl rickon = new PersonImpl("Rickon", "Stark", PersonImpl.SEX.MALE, new GregorianCalendar(-1605, 7, 23), null, true);
            robb.setFather(eddard);
            sansa.setFather(eddard);
            arya.setFather(eddard);
            bran.setFather(eddard);
            rickon.setFather(eddard);
            robb.setMother(catelyn);
            sansa.setMother(catelyn);
            arya.setMother(catelyn);
            bran.setMother(catelyn);
            rickon.setMother(catelyn);


            DataTree dataTree = new DataTree();

            Card card = new Card(eddard);
            card.relocate(100,100);
            zoomableScrollPane.getZoomGroup().getChildren().add(card);


//            CardManager cardManager = new CardManager(gc, canvas, dataTree);
//
//            cardManager.initCards(eddard);



            // Show the scene
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println(zoomableScrollPane.getWidth());
            System.out.println(card.getWidth());
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
