package bill;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void dateFormattingTest_yyyyMMdd_success() {
        // Deadline climb 2024-12-10 is [D][ ] climb (by: Dec 10 2024)
        assertEquals("[D][ ] climb (by: Dec 10 2024)",
                    new Deadline("climb", "2024-12-10").toString());
    }

    @Test
    public void dateFormattingTest_monthDayYear_success() {
        // Deadline climb Dec 10 2024 is [D][ ] climb (by: Dec 10 2024)
        assertEquals("[D][ ] climb (by: Dec 10 2024)",
                    new Deadline("climb", "Dec 10 2024").toString());
    }

    @Test
    public void descriptionTest_noDescription_throwsError() {
        // No description of deadline throws an assertion error
        assertThrows(AssertionError.class, () -> new Deadline("", "2024-12-10"));
    }

    @Test
    public void dateTest_noDate_throwsError() {
        // No date of deadline throws an assertion error
        assertThrows(AssertionError.class, () -> new Deadline("climb", ""));
    }


    @Test
    public void maxDateTest_largestDate_success() {
        // Deadline climb 9999-12-31 is [D][ ] climb (by: Dec 31 9999)
        assertEquals("[D][ ] climb (by: Dec 31 9999)",
                    new Deadline("climb", "9999-12-31").toString());
    }

    @Test
    public void leapYearTest_leapYearDate_success() {
        // Deadline climb 2028-02-29 is [D][ ] climb (by: Feb 29 2028)
        assertEquals("[D][ ] climb (by: Feb 29 2028)",
                    new Deadline("climb", "2028-02-29").toString());
    }
}
