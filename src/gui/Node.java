package gui;


import datatree.Person;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

/**
 * Created by TheFirstGuy on 1/5/2017.
 */

// Node represents a graphical person in the ancesTree
public class Node {

    private VBox frame;
    private Ellipse profilePic;
    private Text name;
    private Text lifetime;
    private Person person;

    // Default values
    private static final int VBOX_SPACING = 8;
    private static final int RADIUS_X = 29;
    private static final int RADIUS_Y = 39;
    private static final Color PROFILE_PIC_FILL = Color.BLUE;
    private static final String NAME = "John Doe";
    private static final String LIFE_TIME = "Jan 1 1900 - Jan 1 2000";

    // Full Constructor
    Node(VBox frame, Ellipse profilePic, Text name, Text lifetime, Person person){
        this.frame = frame;
        this.profilePic = profilePic;
        this.name = name;
        this.lifetime = lifetime;
        this.person = person;

        this.frame.getChildren().add(this.profilePic);
        this.frame.getChildren().add(this.name);
        this.frame.getChildren().add(this.lifetime);
    }

    // Default Constructor
    Node(){
        this.frame = new VBox(VBOX_SPACING);
        this.profilePic = new Ellipse(RADIUS_X, RADIUS_Y);
        this.profilePic.setFill(PROFILE_PIC_FILL);
        this.name = new Text(NAME);
        this.lifetime = new Text(LIFE_TIME);

        this.frame.getChildren().add(this.profilePic);
        this.frame.getChildren().add(this.name);
        this.frame.getChildren().add(this.lifetime);
    }

    public VBox getFrame(){
        return frame;
    }


}
