package datatree;

import java.util.Date;

/**
 * Created by TheFirstGuy on 1/3/2017.
 */

// Stores a pair of people who are or were partners. Used for marriages, unions, and other relationships
public class Relationship {

    public PersonImpl partner1;
    public PersonImpl partner2;

    public Date startDate;
    public Date stopDate;

    // Constructor
    Relationship(PersonImpl partner1, PersonImpl partner2, Date startDate, Date stopDate){
        this.partner1 = partner1;
        this.partner2 = partner2;
        this.startDate = startDate;
        this.stopDate = stopDate;
    }

    // Checks for equality between two relationships
    public static boolean isEqual(Relationship relationship1, Relationship relationship2 ){
        boolean isEqual = false;
        if( relationship1.partner1 == relationship2.partner1 && relationship1.partner2 == relationship2.partner2){
            isEqual = true;
        }
        else if(relationship1.partner1 == relationship2.partner2 && relationship1.partner2 == relationship2.partner1){
            isEqual = true;
        }
        return isEqual;
    }

    // Check for equality between two people and a relationship
    public static boolean isEqual(PersonImpl partner1, PersonImpl partner2, Relationship relationship){
        boolean isEqual = false;
        if(relationship.partner1 == partner1 && relationship.partner2 == partner2){
            isEqual = true;
        }
        else if(relationship.partner2 == partner1 && relationship.partner1 == partner2){
            isEqual = true;
        }
        return isEqual;
    }


}
