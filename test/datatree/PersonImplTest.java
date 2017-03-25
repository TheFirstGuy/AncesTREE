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





}