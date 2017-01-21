package gui;


import datatree.Person;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Created by TheFirstGuy on 1/5/2017.
 */

// Card represents a graphical person in the ancesTree
public class Card {

    private Rectangle frame_;
    private int x_;
    private int y_;


    static final double FRAME_WIDTH = 100;
    static final double FRAME_HEIGHT = 200;
    static final Color FRAME_FILL = Color.WHITESMOKE;
    static final Color FRAME_STROKE = Color.BLACK;
    static final int FRAME_ROUNDNESS = 10;
    static final int STROKE_WIDTH = 2;

    // Full Constructor
    Card(Rectangle frame, int x, int y){
        this.frame_ = frame;
    }

    // Default Constructor
    Card(int x, int y){
        this.frame_ = new Rectangle(FRAME_WIDTH, FRAME_HEIGHT);
        this.x_ = x;
        this.y_ = y;
    }

    // Draws the card on the canvas
    public void drawCard(GraphicsContext gc){
        gc.setFill(FRAME_FILL);
        //gc.setStroke(FRAME_STROKE);
        //gc.setLineWidth(STROKE_WIDTH);
        gc.fillRoundRect(x_,y_, frame_.getWidth(), frame_.getHeight(), FRAME_ROUNDNESS, FRAME_ROUNDNESS);
        drawDropShadow(gc, Color.BLACK, 10);
        gc.fillRoundRect(100, 100, frame_.getWidth(), frame_.getHeight(), FRAME_ROUNDNESS, FRAME_ROUNDNESS);

        //gc.strokeRoundRect(x_,y_, frame_.getWidth(), frame_.getHeight(), FRAME_ROUNDNESS, FRAME_ROUNDNESS);
    }

    // Draws drop shadow around the card
    private void drawDropShadow(GraphicsContext gc, Color color, int shadowLength){
        gc.applyEffect(new DropShadow(shadowLength, color));
    }





}
