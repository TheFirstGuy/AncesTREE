package gui;


import datatree.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by TheFirstGuy on 1/5/2017.
 */

// Card represents a graphical person in the ancesTree
public class Card extends Pane {


    @FXML private Pane pane;

    private Person person_;
    private String lifeSpan_;

    private Ellipse pictureFrame_;
    private Label nameLabel_;
    private Label lifeSpanLabel_;

    private Node view_;

    static final int PADDING = 5;
    static final double FRAME_WIDTH = 150;
    static final double FRAME_HEIGHT = 175;
    static final double PIC_DIAMETER = 75;
    static final Color FRAME_FILL = Color.WHITESMOKE;
    static final Color FRAME_STROKE = Color.BLACK;
    static final int FRAME_ROUNDNESS = 10;
    static final int STROKE_WIDTH = 2;
    static final Font FONT = Font.font("Sans-Serif", 16);
    static final String DATE_FORMAT = "MMM d y";

    /**
     * Card constructor.
     * @param person The person the card represents.
     */
    public Card( Person person ){
        super();
        person_ = person;
        lifeSpan_ = "";
        // Load layout from fxml data
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try{
            fxmlLoader.load();
        }catch (IOException exception){
            throw new RuntimeException(exception);
        }

        // Get components from fxml
        pictureFrame_ = (Ellipse) this.lookup("#pictureFrame");
        nameLabel_ = (Label) this.lookup("#nameLabel");
        lifeSpanLabel_ = (Label) this.lookup("#lifeSpanLabel");

        initLifeSpanStr();

        // Initialize labels
        if(nameLabel_ != null) {
            nameLabel_.setText(person_.getFirstName() + " " + person_.getLastName());

            lifeSpanLabel_.setText(lifeSpan_);
            lifeSpanLabel_.autosize();
        }

        // Initialize image
        String imageUrl;
        if(person_.getSex() == Person.SEX.MALE){
            imageUrl = "data/images/default_male.png";
        }
        else{
            imageUrl = "data/images/default_female.png";
        }
        Image image = new Image(new File(imageUrl).toURI().toString());
        ImagePattern pattern = new ImagePattern(image);
        pictureFrame_.setFill(pattern);
        System.out.print("card: " + this.getWidth());
        setView(this);
    }



    /**
     * Creates the life span string
     */
    private void initLifeSpanStr(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        // Check if birthdate is known
        if(person_.getBirthDate() == null){
            lifeSpan_ = "?";
        }
        else{
            lifeSpan_ = simpleDateFormat.format(person_.getBirthDate().getTime());
        }
        // Check if death date is known
        if(!person_.isAlive()){
            if(person_.getDeathDate() == null){
                lifeSpan_ += " - ?";
            }
            else{
                lifeSpan_ += " - " + simpleDateFormat.format(person_.getDeathDate().getTime());
            }
        }
    }



    /**
     * Sets the view Node
     * @param view
     */
    public void setView(Node view){
        view_ = view;
    }

    public Node getView(){
        return view_;
    }

    public Person getPerson(){
        return person_;
    }
}
