package gui;


import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import datatree.Person;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TheFirstGuy on 1/5/2017.
 */

// Card represents a graphical person in the ancesTree
public class Card extends Pane {

    private Rectangle frame_;
    private int x_;
    private int y_;

    private Person person_;
    private Rectangle firstNameFrame_;
    private Rectangle lastNameFrame_;
    private Rectangle lifeSpanFrame_;
    private String lifeSpan_;

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
        this.person_ = person;
        this.x_ = 100;
        this.y_ = 100;
        this.frame_  = new Rectangle(FRAME_WIDTH, FRAME_HEIGHT);
        this.firstNameFrame_ = new Rectangle(0,0);
        this.lastNameFrame_ = new Rectangle(0,0);
        this.lifeSpanFrame_ = new Rectangle(0,0);
        this.lifeSpan_ = "";
        initLifeSpanStr();
        calcFrameSize();

        frame_.setStroke(FRAME_STROKE);
        frame_.setFill(FRAME_FILL);

        setView(frame_);
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
            lifeSpan_ = simpleDateFormat.format(person_.getBirthDate());
        }
        // Check if death date is known
        if(!person_.isAlive()){
            if(person_.getDeathDate() == null){
                lifeSpan_ += " - ?";
            }
            else{
                lifeSpan_ += " - " + simpleDateFormat.format(person_.getDeathDate());
            }
        }
    }
    /**
     *  Calculates the frame size of the card based on the profile picture size and text sizes.
     */
    private void calcFrameSize(){
        // Create Text objects for each string
        Text firstName = new Text(person_.getFirstName());
        Text lastName = new Text(person_.getLastName());
        Text lifeSpan = new Text(lifeSpan_);
        firstName.applyCss();
        lastName.applyCss();
        lifeSpan.applyCss();

        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        // Calculate text dimensions
        firstNameFrame_.setWidth( fontLoader.computeStringWidth(person_.getFirstName(), FONT)+ (PADDING * 2));
        lastNameFrame_.setWidth(fontLoader.computeStringWidth(person_.getLastName(), FONT) + (PADDING * 2));
        lifeSpanFrame_.setWidth(fontLoader.computeStringWidth(lifeSpan_, FONT) + (PADDING * 2));
        firstNameFrame_.setHeight(firstName.getLayoutBounds().getHeight() + PADDING);
        lastNameFrame_.setHeight(lastName.getLayoutBounds().getHeight() + PADDING);
        lifeSpanFrame_.setHeight(lifeSpan.getLayoutBounds().getHeight() + (PADDING * 2));

        // Check if first name requires more space
        if(frame_.getWidth() < firstNameFrame_.getWidth()){
            frame_.setWidth(firstNameFrame_.getWidth());
        }

        // Check if last name requires more space
        if(frame_.getWidth() < lastNameFrame_.getWidth()){
            frame_.setWidth(lastNameFrame_.getWidth());
        }

        double width = Toolkit.getToolkit().getFontLoader().computeStringWidth( lifeSpan_, FONT);
        // Check if lifespan name requires more space
        if(frame_.getWidth() < lifeSpanFrame_.getWidth()){
            frame_.setWidth(lifeSpanFrame_.getWidth());
        }

        // Calculate frame height
        double height = PIC_DIAMETER + PADDING;
        height += firstNameFrame_.getHeight();
        height += lastNameFrame_.getHeight();
        height += lifeSpanFrame_.getHeight();

        frame_.setHeight(height);
    }

    // Draws the card on the canvas
    public void drawCard(GraphicsContext gc){
        gc.setFill(FRAME_FILL);
        //gc.setStroke(FRAME_STROKE);
        //gc.setLineWidth(STROKE_WIDTH);
        gc.fillRoundRect(x_,y_, frame_.getWidth(), frame_.getHeight(), FRAME_ROUNDNESS, FRAME_ROUNDNESS);
        gc.strokeOval( x_+ (frame_.getWidth()/2) - PIC_DIAMETER/2, y_+ PADDING, PIC_DIAMETER, PIC_DIAMETER);
        gc.setFont(FONT);
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.CENTER);

        gc.fillText(person_.getFirstName(), x_ + frame_.getWidth()/2, y_ + (PADDING * 2) + PIC_DIAMETER + firstNameFrame_.getHeight()/2);
        gc.fillText(person_.getLastName(), x_ + frame_.getWidth()/2, y_ + PADDING + PIC_DIAMETER + firstNameFrame_.getHeight()*1.5);
        gc.fillText(lifeSpan_, x_ + frame_.getWidth()/2, y_ + PADDING + firstNameFrame_.getHeight()*1.5 + PIC_DIAMETER + lastNameFrame_.getHeight());

        gc.strokeRoundRect(x_,y_, frame_.getWidth(), frame_.getHeight(), FRAME_ROUNDNESS, FRAME_ROUNDNESS);
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

    public Rectangle getFrame(){
        return frame_;
    }

    public double getCenterX(){
        return x_ + frame_.getWidth()/2;
    }

    public double getCenterY(){
        return y_ + frame_.getHeight()/2;
    }

    public double getHalfWidth(){
        return frame_.getWidth()/2;
    }

    public double getHalfHeight(){
        return frame_.getHeight()/2;
    }

    public int getX() {
        return x_;
    }

    public int getY(){
        return y_;
    }

    public void setX( int x ){
        x_ = x;
    }

    public void setY( int y ){
        y_ = y;
    }

    public Person getPerson(){
        return person_;
    }
}
