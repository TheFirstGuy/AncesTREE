package gui;

import datatree.DataTree;
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
     */
    private void initCards(){

    }

}
