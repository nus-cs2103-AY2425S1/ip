package Bwead;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserTest {
    @Test
    public void testGetEventEndTimeSuccess() {
        assertEquals(LocalTime.of(18,00),
                new Parser("event project meeting /from 2019-10-15 1700 /to 2019-10-15 1800").getEventEndTime());
        assertEquals(LocalTime.of(9,00),
                new Parser("event project meeting /from 2019-10-15 1700 /to 2020-09-16 0900").getEventEndTime());
        assertEquals(LocalTime.of(10,38),
                new Parser("event project meeting /from 2019-10-15 1700 /to 2019-10-15 1038").getEventEndTime());
    }

    @Test
    public void testGetEventEndTimeFailure() {
        assertThrows(BweadException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Parser("event project meeting /from 2019-10-15 2100 /to 2019-10-15 18000").getEventEndTime();
            }
        });
        assertThrows(BweadException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Parser("event project meeting /from 2019-10-15 1700 /to 2019-09-17 1280").getEventEndTime();
            }
        });
        assertThrows(BweadException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Parser("event project meeting /from 2019-10-15 1700 /to 2019-10-15 2500").getEventEndTime();
            }
        });
    }

    @Test
    public void testDeadlineDateSuccess() {
        assertEquals(LocalDate.of(2020, 12, 13),
                new Parser("deadline project meeting /by 2020-12-13 1700").getDeadlineDate());
        assertEquals(LocalDate.of(2021, 1, 2),
                new Parser("deadline return book /by 2021-01-02 2100").getDeadlineDate());
        assertEquals(LocalDate.of(2022, 5, 12),
                new Parser("deadline project meeting /by 2022-05-12 1900").getDeadlineDate());
    }

    @Test
    public void testGetDeadlineDateFailure() {
        assertThrows(BweadException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Parser("deadline return book /by 2020-15-12 1000").getDeadlineDate();
            }
        });

        assertThrows(BweadException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Parser("deadline return book /by 2020-10-40 1000").getDeadlineDate();
            }
        });
    }


}
