package gui;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Scale;

/**
 * Created by Urs on 2/25/2017.
 * Modifed from Roland http://stackoverflow.com/questions/30679025/graph-visualisation-like-yfiles-in-javafx
 */
public class ZoomableScrollPane extends ScrollPane {
    private Group zoomGroup_;
    private Scale scaleTransform_;
    private Node content_;
    private double scaleValue_ = 1.0;
    private double delta_ = 0.1;

    /**
     * Contructor
     * @param content Node to be added the Pane?
     */
    public ZoomableScrollPane(Node content){
        content_ = content;
        Group contentGroup = new Group();
        zoomGroup_ = new Group();
        contentGroup.getChildren().add(zoomGroup_);
        zoomGroup_.getChildren().add(content_);
        setContent(contentGroup);
        scaleTransform_ = new Scale(scaleValue_, scaleValue_, 0, 0);
        zoomGroup_.getTransforms().add(scaleTransform_);
        zoomGroup_.setOnScroll(new ZoomHandler());
    }

    /**
     * Zooms to a set value
     * @param scaleValue value to scale too
     */
    public void zoomTo(double scaleValue) {

        this.scaleValue_ = scaleValue;

        scaleTransform_.setX(scaleValue);
        scaleTransform_.setY(scaleValue);

    }

    /**
     * Returns the scaleValue
     * @return scaleValue
     */
    public double getScaleValue() {
        return scaleValue_;
    }

    /**
     * Resets zoom to default scale
     */
    public void zoomToActual() {
        zoomTo(1.0);
    }

    /**
     * Decreases the zoom by the delta value
     */
    public void zoomOut() {
        scaleValue_ -= delta_;

        // Prevent zooming less than 0.1
        if (Double.compare(scaleValue_, 0.1) < 0) {
            scaleValue_ = 0.1;
        }

        zoomTo(scaleValue_);
    }

    /**
     * Increases the zoom by the delta value
     */
    public void zoomIn() {

        scaleValue_ += delta_;

        // Prevents zooming out to more than 10
        if (Double.compare(scaleValue_, 10) > 0) {
            scaleValue_ = 10;
        }

        zoomTo(scaleValue_);

    }
    /**
     * Handles zooming for the panel
     */
    private class ZoomHandler implements EventHandler<ScrollEvent> {

        @Override
        public void handle(ScrollEvent scrollEvent) {
            // if (scrollEvent.isControlDown())
            {

                if (scrollEvent.getDeltaY() < 0) {
                    scaleValue_ -= delta_;
                } else {
                    scaleValue_ += delta_;
                }

                zoomTo(scaleValue_);

                scrollEvent.consume();
            }
        }
    }
}
