package gui;

import datatree.DataTree;
import datatree.Person;
import javafx.scene.shape.Rectangle;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Urs on 1/28/2017.
 * The Card Manager class manages the cards that are
 * displayed for the ancestry tree.
 */
public class CardManager {

    /**
     *  The Pane of the ancestry tree
     */
    private ZoomableScrollPane scrollPane_;

    /**
     *  The pane which holds the cards
     */
    private CardLayer cardLayer_;
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
     *  @param scrollPane The pane which the cards are drawn
     *  @param dataTree The data tree which ancestry tree data is stored
     */
    public CardManager( ZoomableScrollPane scrollPane, DataTree dataTree){
        scrollPane_ = scrollPane;
        dataTree_ = dataTree;

    }

    /**
     * Initializes cards
     */
    public void initCards(){
        HashSet<Person> family = dataTree_.getFamily();
        for(Person person : family){
            Card card = new Card(person);
            cards_.put(person,card);
        }
    }

    public HashMap<Person, Card> getCards_(){
        return cards_;
    }
}
