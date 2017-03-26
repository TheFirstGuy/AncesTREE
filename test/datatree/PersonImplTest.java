package datatree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by Urs on 3/24/2017.
 */
public class PersonImplTest {
    protected PersonImpl female;
    protected PersonImpl male;

    @Before
    public void setUp() throws Exception {
        this.female = new PersonImpl("Jane", "Smith",
                new GregorianCalendar(2000, 1, 1), PersonImpl.SEX.FEMALE, true);
        this.male= new PersonImpl("John", "Smith",
                new GregorianCalendar(2000,2,3), Person.SEX.MALE, true);
    }

    @Test
    public void tesEqualsNominal(){
        PersonImpl targetFemale = this.female;
        PersonImpl targetMale = this.male;
        PersonImpl sameNameFemale = new PersonImpl("Jane", "Smith", new GregorianCalendar(1,2,3), Person.SEX.FEMALE, true);

        assertTrue(targetFemale.equals(this.female));
        assertTrue(this.female.equals(targetFemale));
        assertTrue(targetMale.equals(this.male));
        assertTrue(this.male.equals(targetMale));
        assertFalse(targetFemale.equals(targetMale));
        assertFalse(sameNameFemale.equals(this.female));
    }

    @Test
    public void testEqualsNullObjects(){
        assertFalse(male.equals(null));
        assertFalse(male.equals(7));
        assertFalse(male.equals("Test String"));
    }

    @Test
    public void testSetFatherNominal(){
        Person targetFather = new PersonImpl("Dad", "Smith",
                new GregorianCalendar(1900,2,3), Person.SEX.MALE, true);

        this.female.setFather(targetFather);
        assertEquals(targetFather, this.female.getFather());
        assertEquals(targetFather.getChildren()[0], this.female);
    }

    @Test
    public void testSetYoungFather(){

        Person youngFather = new PersonImpl("Dad", "Smith",
                new GregorianCalendar(2010,3,1), Person.SEX.MALE, true);

        this.female.setFather(youngFather);
        assertNotEquals(this.female.getFather(), youngFather);
    }

    @Test
    public void testSetDeadFather(){
        Person deadFather = new PersonImpl("Dad", "Smith",
                new GregorianCalendar(1970,2,3), Person.SEX.MALE, false);
        this.female.setFather(deadFather);
        assertEquals(this.female.getFather(), deadFather);
    }

    @Test
    public void testSetFemaleFather(){
        Person femaleFather = new PersonImpl("Dad", "Smith",
                new GregorianCalendar( 1960,2,3), Person.SEX.FEMALE, true);
        this.female.setFather(femaleFather);
        assertNotEquals(this.female.getFather(), femaleFather);
    }

    @Test
    public void testSetMotherNominal(){
        Person targetMother = new PersonImpl("Mom", "Smith",
                new GregorianCalendar(1900,2,3), Person.SEX.FEMALE, true);
        this.female.setMother(targetMother);
        assertEquals(this.female.getMother(), targetMother);
    }

    @Test
    public void testSetMaleMother(){
        Person maleMother = new PersonImpl("Mom", "Smith",
                new GregorianCalendar( 1960,2,3), Person.SEX.MALE, true);
        this.female.setMother(maleMother);
        assertNotEquals(this.female.getMother(), maleMother);
    }

    @Test
    public void testSetYoungerMother(){
        Person youngerMother = new PersonImpl("Mom", "Smith",
                new GregorianCalendar( 2010,2,3), Person.SEX.FEMALE, true);
        this.female.setMother(youngerMother);
        assertNotEquals(this.female.getMother(), youngerMother);
    }

    @Test
    public void testDeadMother(){
        Person deadMother = new PersonImpl("Mom", "Smith",
                new GregorianCalendar( 1960,2,3), Person.SEX.FEMALE, false);
        this.female.setMother(deadMother);
        assertEquals(this.female.getMother(), deadMother);
    }

    @Test
    public void testAddChildNominal(){
        Person femaleChild = new PersonImpl("Child", "Smith",
                new GregorianCalendar(2010, 2, 3), Person.SEX.FEMALE, true);
        Person maleChild = new PersonImpl("Child", "Smith",
                new GregorianCalendar(2011, 2,3), Person.SEX.MALE, false);


        this.female.addChild(femaleChild);
        this.male.addChild(femaleChild);
        this.female.addChild(maleChild);
        this.male.addChild(maleChild);

        assertEquals(this.female.getChildren()[0], femaleChild);
        assertEquals(this.male.getChildren()[0], femaleChild);
        assertEquals(femaleChild.getMother(), this.female);
        assertEquals(femaleChild.getFather(), this.male);
        assertEquals(this.female.getChildren()[1], maleChild);
        assertEquals(this.male.getChildren()[1], maleChild);
        assertEquals(maleChild.getFather(), this.male);
        assertEquals(femaleChild.getMother(), this.female);
    }

    @Test
    public void testAddOlderChild(){
        Person olderChild = new PersonImpl("Child","Smith",
                new GregorianCalendar(1920,2,1), Person.SEX.FEMALE, true);

        this.female.addChild(olderChild);
        assertEquals(this.female.getChildren().length, 0);
        assertNull(olderChild.getMother());
    }

    @Test
    public void testAddNullChild(){
        this.female.addChild(null);

        assertEquals(this.female.getChildren().length, 0);
    }

    @Test
    public void testAddingSameChild(){
        Person child = new PersonImpl("Child", "Smith",
                new GregorianCalendar(2010, 2, 3), Person.SEX.FEMALE, true);
        this.female.addChild(child);
        this.female.addChild(child);
        assertEquals(this.female.getChildren().length, 1);
    }

    @Test
    public void testRemoveChild(){
        Person femaleChild = new PersonImpl("Child", "Smith",
                new GregorianCalendar(2010, 2, 3), Person.SEX.FEMALE, true);
        Person maleChild = new PersonImpl("Child", "Smith",
                new GregorianCalendar(2011, 2,3), Person.SEX.MALE, false);

        this.female.addChild(femaleChild);
        this.female.addChild(maleChild);
        this.female.removeChild(femaleChild);

        assertEquals(this.female.getChildren().length, 1);
        assertEquals(this.female.getChildren()[0], maleChild);
        assertNull(femaleChild.getMother());
    }

    @Test
    public void testRemoveNullChild(){
        Person child = new PersonImpl("Child", "Smith",
                new GregorianCalendar(2010, 2, 3), Person.SEX.FEMALE, true);

        this.female.addChild(child);
        this.female.removeChild(null);

        assertEquals(this.female.getChildren().length, 1);
        assertEquals(this.female.getChildren()[0], child);
    }

    @Test
    public void testRemoveFather(){
        Person targetFather = new PersonImpl("Dad", "Smith",
                new GregorianCalendar(1900,2,3), Person.SEX.MALE, true);

        this.female.setFather(targetFather);
        this.female.removeFather();

        assertNull(this.female.getFather());
        assertEquals(targetFather.getChildren().length, 0);
    }

    @Test
    public void testRemoveMother(){
        Person targetMother = new PersonImpl("Mom", "Smith",
                new GregorianCalendar(1900,2,3), Person.SEX.FEMALE, true);

        this.female.setMother(targetMother);
        this.female.removeMother();

        assertNull(this.female.getMother());
        assertEquals(targetMother.getChildren().length, 0);
    }

    @Test
    public void testSetBirthdateNominal(){
        GregorianCalendar nominalBirthday = new GregorianCalendar(1950, 1,1);

        this.female.setBirthDate(nominalBirthday);

        assertEquals(this.female.getBirthDate(), nominalBirthday);
    }

    @Test
    public void testSetBirthAfterDeath(){
        GregorianCalendar bornAfterDeath = new GregorianCalendar(2050,3,2);
        GregorianCalendar death = new GregorianCalendar(2000, 3,4);
        GregorianCalendar currentBirthDate = (GregorianCalendar)this.female.getBirthDate();

        this.female.setDeathDate(death);
        this.female.setBirthDate(bornAfterDeath);

        assertNotEquals(this.female.getBirthDate(), bornAfterDeath);
        assertEquals(this.female.getBirthDate(), currentBirthDate);
    }

    @Test
    public void testSetBirthBeforeParents(){
        Person targetMother = new PersonImpl("Mom", "Smith",
                new GregorianCalendar(1900,2,3), Person.SEX.FEMALE, true);
        Person targetFather = new PersonImpl("Dad", "Smith",
                new GregorianCalendar(1900,2,3), Person.SEX.MALE, true);
        GregorianCalendar birthDate = new GregorianCalendar(1850,3,4);
        GregorianCalendar currentBirthDate = (GregorianCalendar)this.female.getBirthDate();

        this.female.setMother(targetMother);
        this.female.setFather(targetFather);
        this.female.setBirthDate(birthDate);

        assertNotEquals(this.female.getBirthDate(), birthDate);
        assertEquals(this.female.getBirthDate(), currentBirthDate);
    }

    @Test
    public void testSetBirthAfterChildren(){
        Person femaleChild = new PersonImpl("Child", "Smith",
                new GregorianCalendar(2010, 2, 3), Person.SEX.FEMALE, true);
        Person maleChild = new PersonImpl("Child", "Smith",
                new GregorianCalendar(2011, 2,3), Person.SEX.MALE, false);
        GregorianCalendar birthDate = new GregorianCalendar(2050,3,2);

        this.female.addChild(femaleChild);
        this.female.addChild(maleChild);
        this.female.setBirthDate(birthDate);

        assertNotEquals(this.female.getBirthDate(), birthDate);

    }

    @Test
    public void testSetDeathDateNominal(){
        GregorianCalendar deathDate = new GregorianCalendar(2015,6,2);

        this.female.setDeathDate(deathDate);

        assertEquals(this.female.getDeathDate(), deathDate);
    }

    @Test
    public void testSetDeathBeforeBirth(){
        GregorianCalendar deathBeforeBirth = new GregorianCalendar(1830,6,3);

        this.female.setDeathDate(deathBeforeBirth);

        assertNotEquals(this.female.getDeathDate(), deathBeforeBirth);
        assertNull(this.female.getDeathDate());
    }


}