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
    public void testMarkUnmark() {
        try {
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
        } catch (InvalidPeriodException e) {
            fail();
        }
    }

    @Test
    public void occurringOn_date_returnsFalse() {
        try {
            Period period = new Period(this.description, this.period);
            assertFalse(period.occursOn(LocalDate.parse("2024-08-24")));
        } catch (InvalidPeriodException e) {
            fail();
        }
    }

    @Test
    public void hasKeyword() {
        try {
            Period periodWithoutWhiteSpace = new Period(this.description, this.period);
            Period periodWithWhiteSpace = new Period(this.description + " " + this.description, this.period);

            assertTrue(periodWithoutWhiteSpace.hasKeyword("dummy"));
            assertTrue(periodWithoutWhiteSpace.hasKeyword("Dum"));
            assertTrue(periodWithWhiteSpace.hasKeyword(" "));

            assertFalse(periodWithoutWhiteSpace.hasKeyword("dummies"));
            assertFalse(periodWithoutWhiteSpace.hasKeyword("?"));
            assertFalse(periodWithoutWhiteSpace.hasKeyword("dummy "));
            assertFalse(periodWithoutWhiteSpace.hasKeyword(" "));
        } catch (InvalidPeriodException e) {
            fail();
        }
    }

    @Test
    public void testStringUI() {
        try {
            Period period = new Period(this.description, this.period);
            assertEquals("[P][ ] " + this.description + " (needs " + this.period + " hours)",
                    period.stringUI());
        } catch (InvalidPeriodException e) {
            fail();
        }
    }

    @Test
    public void testStringStorage() {
        try {
            Period period = new Period(this.description, this.period);
            period.mark();
            assertEquals("P | X | " + this.description + " | " + this.period, period.stringStorage());
        } catch (InvalidPeriodException e) {
            fail();
        }
    }
}
