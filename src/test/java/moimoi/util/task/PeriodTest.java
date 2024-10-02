package moimoi.util.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import moimoi.util.exception.InvalidPeriodException;

public class PeriodTest {
    private String description = "dummy";
    private double period = 17;

    @Test
    public void period_invalidPeriod_invalidPeriodExceptionThrown() {

        try {
            Period period = new Period(this.description, 0);
            fail();
        } catch (InvalidPeriodException e) {
            assertEquals(new InvalidPeriodException().getMessage(), e.getMessage());
        }

        try {
            Period period = new Period(this.description, -this.period);
            fail();
        } catch (InvalidPeriodException e) {
            assertEquals(new InvalidPeriodException().getMessage(), e.getMessage());
        }

    }

    @Test
    public void testMarkUnmark() throws Exception {

        Period period = new Period(this.description, this.period);
        assertEquals(" ", period.getStatusIcon());

        period.mark();
        assertEquals("X", period.getStatusIcon());

        period.mark();
        assertEquals("X", period.getStatusIcon());

        period.unmark();
        assertEquals(" ", period.getStatusIcon());

        period.unmark();
        assertEquals(" ", period.getStatusIcon());

    }

    @Test
    public void occurringOn_date_returnsFalse() throws Exception {
        Period period = new Period(this.description, this.period);
        assertFalse(period.occursOn(LocalDate.parse("2024-08-24")));
    }

    @Test
    public void hasKeyword() throws Exception {

        Period periodWithoutWhiteSpace = new Period(this.description, this.period);
        Period periodWithWhiteSpace = new Period(this.description + " " + this.description, this.period);

        assertTrue(periodWithoutWhiteSpace.hasKeyword("dummy"));
        assertTrue(periodWithoutWhiteSpace.hasKeyword("Dum"));
        assertTrue(periodWithWhiteSpace.hasKeyword(" "));

        assertFalse(periodWithoutWhiteSpace.hasKeyword("dummies"));
        assertFalse(periodWithoutWhiteSpace.hasKeyword("?"));
        assertFalse(periodWithoutWhiteSpace.hasKeyword("dummy "));
        assertFalse(periodWithoutWhiteSpace.hasKeyword(" "));

    }

    @Test
    public void testStringUI() throws Exception {
        Period period = new Period(this.description, this.period);
        assertEquals("[P][ ] " + this.description + " (needs " + this.period + " hours)",
                    period.stringUI());
    }

    @Test
    public void testStringStorage() throws Exception {
        Period period = new Period(this.description, this.period);
        period.mark();
        assertEquals("P | X | " + this.description + " | " + this.period, period.stringStorage());
    }
}
