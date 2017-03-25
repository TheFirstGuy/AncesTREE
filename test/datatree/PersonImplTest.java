package datatree;

import org.junit.Assert;
import org.junit.Before;
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
        this.female = new PersonImpl("Jane", "Smith", new GregorianCalendar(1, 1, 1) {
        }, PersonImpl.SEX.FEMALE, true);
        this.male= new PersonImpl("John", "Smith", new GregorianCalendar(1,2,3), Person.SEX.MALE, true);
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





}