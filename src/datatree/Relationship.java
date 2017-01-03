package datatree;

import java.util.Date;

/**
 * Created by TheFirstGuy on 1/3/2017.
 */

// Stores a pair of people who are or were partners. Used for marriages, unions, and other relationships
public class Relationship {

    public Person partner1;
    public Person partner2;

    public Date startDate;
    public Date stopDate;
}