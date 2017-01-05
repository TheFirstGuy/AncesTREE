package datatree;


import java.util.ArrayList;
import java.util.Date;

/**
 * Created by TheFirstGuy on 1/3/2017.
 */
public class DataTree {

    // List containing all persons
    private ArrayList<Person> family = new ArrayList<Person>();

    // List of relationships
    private ArrayList<Relationship> relationships = new ArrayList<Relationship>();

    // Finds person in list which fully matches given name
    // Returns null if no match found
    public Person find(String firstName, ArrayList<String> middleNames, String lastNames){
        Person match = null;
        for(int i = 0; i < family.size() && match == null ; i++){
            if(Person.isEqual(firstName, middleNames, lastNames, family.get(i))) {
                match = family.get(i);
            }
        }
        return match;
    }

    // Finds people who match first and last name
    // Returns a list of people who match the first and last name
    public ArrayList<Person> find(String firstName, String lastName){
        ArrayList<Person> people = new ArrayList<Person>();
        for(int i = 0; i < family.size(); i++){
            if(Person.partialEqual(firstName, lastName, family.get(i))){
                people.add(family.get(i));
            }
        }
        return people;
    }

    // Finds if Person is contained in family
    public boolean inFamily(Person person){
        return family.contains(person);
    }

    // Adds person to the family
    public void addPerson(String firstName, String lastName, Date birthdate){
        Person person = new Person(firstName, lastName, birthdate);
        family.add(person);
    }

    // Adds a relationship
    public boolean addRelationship(Person partner1, Person partner2, Date startDate, Date stopDate){
        // Check that partners are not the same
        if(partner1 == partner2){
            return false;
        }
        Relationship existingRelationship = null;
        // Check to see if relationship is found
        for(int i = 0; i < relationships.size() && existingRelationship == null; i++){
            if(Relationship.isEqual(partner1,partner2, relationships.get(i))){
                existingRelationship = relationships.get(i);
            }
        }
        // If an existing relationship exists, modify the start and end date
        if(existingRelationship != null) {
            existingRelationship.startDate = startDate;
            existingRelationship.stopDate = stopDate;
        }
        // Create a new relationship and add to list
        else{
            Relationship relationship = new Relationship(partner1, partner2, startDate, stopDate);
            relationships.add(relationship);
        }
        return true;
    }


}
