package gui.Layout;

import datatree.DataTree;
import datatree.Person;
import gui.Card;
import gui.CardManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Urs on 3/3/2017.
 */
public class FullTreeLayout implements Layout {

    private DataTree dataTree_;
    private CardManager cardManager_;
    private ArrayList<ArrayList<Card>> cards_;

    public FullTreeLayout(DataTree dataTree, CardManager cardManager){
        dataTree_ = dataTree;
        cardManager_ = cardManager;
    }

    @Override
    public void execute() {
        dataTree_.calcGenerations();
        cards_ = new ArrayList<ArrayList<Card>>(dataTree_.getNumGenerations());
    }

    private void setUpGenerations(){
        ArrayList<Person> firstGeneration = dataTree_.getGeneration(0);

        // Get cards
        for(Person person: firstGeneration){
            cards_.get(0).add(cardManager_.getCards_().get(person));
        }


    }


}
