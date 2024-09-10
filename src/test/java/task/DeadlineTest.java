package task;

import ai.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void deadline_toStringNotDone_success() throws Exception {
        assertEquals("[D][ ] buy gift (Feb 2 2000)",
                new Deadline("buy gift", "02/02/2000 1000").toString());
    }

    @Test
    public void deadline_toStringDone_success() {
        assertEquals("[D][X] buy gift (Feb 2 2000)",
                new Deadline("buy gift", "2000-02-02", true).toString());
    }

    @Test
    public void deadline_stringDataNotDone_success() throws Exception {
        assertEquals("D | 0 | buy gifts for ai | 2000-02-02",
                new Deadline("buy gifts for ai", "2000-02-02").stringData());
    }

    @Test
    public void deadline_stringDataDone_success() {
        assertEquals("D | 1 | buy gifts for ai | 2000-02-02",
                new Deadline("buy gifts for ai", "2000-02-02", true).stringData());
    }

    @Test
    public void deadline_wrongDateTimeFormat_exceptionThrown() {
        try {
            new Deadline("eat sushi with Ai", "this sunday");
            fail();
        } catch (Exception e) {
            assertEquals("Awww shucksss, your date time format should either be d/M/yyyy HHmm or YYYY-MM-DD\n",
                    e.getMessage());
        }
    }

    @Test
    public void deadline_incorrectFormatDate_exceptionThrown() {
        try {
            new Deadline("eat sushi with Ai", "30-08-2001");
            fail();
        } catch (Exception e) {
            assertEquals("Awww shucksss, your date time format should either be d/M/yyyy HHmm or YYYY-MM-DD\n",
                    e.getMessage());
        }
    }

    @Test
    public void deadline_incorrectFormatTime_exceptionThrown() {
        try {
            new Deadline("eat sushi with Ai", "03-12-2024 1999");
            fail();
        } catch (Exception e) {
            assertEquals("Awww shucksss, your date time format should either be d/M/yyyy HHmm or YYYY-MM-DD\n",
                    e.getMessage());
        }
    }
}
