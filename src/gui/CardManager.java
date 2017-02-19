package gui;

import datatree.DataTree;
import datatree.Person;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sun.misc.Queue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
    private HashMap<Person, Card> cards_ = new HashMap<Person, Card>();

    /**
     *  The zone which cards are drawn, any cards whos coordinates
     *  are outside of this zone will not be drawn onto the canvas.
     */
    private Rectangle drawZone_;

    /**
     *  The data tree which ancestry tree data is stored
     */
    private DataTree dataTree_;

    private final static int X_PADDING = 50;
    private final static int Y_PADDING = 50;

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
        // Add to card list
        cards_.put(firstPerson, card);
        positionOtherParent(card);
        // draw all cards
        for(Card c: cards_.values()){
            c.drawCard(graphicsContext_);
        }
        //card.drawCard(graphicsContext_);
        drawDropShadow(graphicsContext_, Color.BLACK, 12);
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

    // Draws drop shadow around the card
    private void drawDropShadow(GraphicsContext gc, Color color, int shadowLength){
        DropShadow dropShadow = new DropShadow(shadowLength, color);
        dropShadow.setOffsetX(2.0);
        dropShadow.setOffsetY(2.0);
        gc.applyEffect(dropShadow);
    }

    /**
     * Crawls through the ancestry graph to determine which cards can/need to be drawn.
     * Uses a form of breadth first search.
     * @param firstPerson Person to begin search from
     */
    private void crawlTree(Person firstPerson){
        Card card;
        HashSet<Person> explored = new HashSet<Person>();
        Queue<Person> fringe = new Queue<Person>();
        Person currentPerson = firstPerson;
        // Check if first person has been initialized
        if(!cards_.containsKey(firstPerson)){
            card = new Card(firstPerson);
            centerCard(card);
        }
        // Explore graph
        // Get parents
        Person father = currentPerson.getFather();
        Person mother = currentPerson.getMother();

    }

    /**
     * Gets Siblings and positions them
     */

    /**
     * Positions other parent.
     * @param cardOfInterest Person who is one of the parents
     */
    private void positionOtherParent(Card cardOfInterest){
        HashSet<Person> partners = dataTree_.getPartners(cardOfInterest.getPerson());
        Card newCard;
        Card referenceCard = cardOfInterest;
        boolean positionRight = true;
        if(partners != null) {
            for (Person partner : partners) {
                // Try to create new card
                if (!cards_.containsKey(partner)) {
                    newCard = new Card(partner);
                    cards_.put(partner, newCard);
                    if (positionRight) {
                        positionRightOf(referenceCard, newCard);
                        positionRight = false;
                    } else {
                        positionLeftOf(referenceCard, newCard);
                    }
                }
            }
        }
    }
    /**
     * Gets children and positions them in the tree.
     * @param firstPerson Person whos children are to be drawn
     */
    private void positionChildren(Person firstPerson){
        
    }

    /**
     * Positions the target card to the right of the origin card
     * @param origin the reference card
     * @param target the card to be put to the right of the origin
     */
    private void positionRightOf(Card origin, Card target){
        // Set up coordinates
        target.setY(origin.getY());
        int x = (int)(origin.getX() + (2*origin.getHalfWidth()) + X_PADDING);
        target.setX(x);
    }

    /**
     * Positions the target card to the left of the origin card
     * @param origin the reference card
     * @param target the card to be put to the right of the origin
     */
    private void positionLeftOf(Card origin, Card target){
        target.setY(origin.getY());
        int x = (int)(origin.getX() - (2*target.getHalfWidth()) - X_PADDING);
        target.setX(x);
    }
}
