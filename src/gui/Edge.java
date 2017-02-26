package gui;

import javafx.scene.Group;
import javafx.scene.shape.Line;

/**
 * Created by Urs on 2/25/2017.
 * Modified from Roland http://stackoverflow.com/questions/30679025/graph-visualisation-like-yfiles-in-javafx
 */
public class Edge extends Group{

    protected Card source_;
    protected Card target_;

    private Line line_;

    /**
     * Constructor
     * @param source Source Card to draw edge from
     * @param target Target Card to draw edge to
     */
    public Edge(Card source, Card target){
        source_ = source;
        target_ = target;

        line_ = new Line();

        line_.startXProperty().bind(source_.layoutXProperty().add(source_.getBoundsInParent().getWidth() / 2.0 ));
        line_.startYProperty().bind(source_.layoutYProperty().add(source_.getBoundsInParent().getHeight() / 2.0 ));

        line_.endXProperty().bind(target_.layoutXProperty().add(target_.getBoundsInParent().getWidth() / 2.0));
        line_.endYProperty().bind(target_.layoutYProperty().add(target_.getBoundsInParent().getHeight() / 2.0));

        getChildren().add(line_);
    }

    public Card getSource(){
        return source_;
    }

    public Card getTarget(){
        return target_;
    }

    
}
