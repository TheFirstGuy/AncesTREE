package gui;

import datatree.DataTree;
import datatree.Person;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * Created by Urs on 1/28/2017.
 * The Card Manager class manages the cards that are
 * displayed for the ancestry tree.
 */
public class CardManager {

    /**
     *  The GraphicsContext of the ancestry tree canvas
     */
    private GraphicsContext graphicsContext_;

    /**
     *  The Canvas of the ancestry tree
     */
    private Canvas canvas_;

    /**
     *  The cards formed.
     */
    private ArrayList<Card> cards_;

    /**
     *  The zone which cards are drawn, any cards whos coordinates
     *  are outside of this zone will not be drawn onto the canvas.
     */
    private Rectangle drawZone_;

    /**
     *  The data tree which ancestry tree data is stored
     */
    private DataTree dataTree_;

    /**
     *  CardManager constructor.
     *  @param graphicsContext The graphical context of the ancestry canvas
     *  @param canvas The canvas of the ancestry tree
     *  @param dataTree The data tree which ancestry tree data is stored
     */
    public CardManager(GraphicsContext graphicsContext, Canvas canvas, DataTree dataTree){
        graphicsContext_ = graphicsContext;
        canvas_ = canvas;
        dataTree_ = dataTree;

    }

    /**
     * Initializes the cards using the data tree.
     * @param firstPerson The person to be centered on when cards are initially drawn
     */
    public void initCards(Person firstPerson){
        Card card = new Card(firstPerson);
        centerCard(card);
        card.drawCard(graphicsContext_);
    }

    /**
     * Gets the center x coordinate of the canvas
     * @return center x coordinate of the canvas
     */
    private double getCenterXOfCanvas(){
        return canvas_.getWidth() / 2;
    }

    /**
     * Gets the center y coordinate of the canvas
     * @return center y coordinate of the canvas
     */
    private double getCenterYOfCanvas(){
        return canvas_.getHeight() / 2;
    }

    /**
     * Centers the given card in the center of the canvas
     * @param card the card to be centered
     */
    private void centerCard(Card card){
        int x = (int)(getCenterXOfCanvas() - card.getHalfWidth());
        int y = (int)(getCenterYOfCanvas() - card.getHalfHeight());
        card.setX(x);
        card.setY(y);
    }
}
