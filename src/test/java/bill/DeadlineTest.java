package bill;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void dateFormatting_yyyyMMdd_success() {
        // Deadline climb 2024-12-10 is [D][ ] climb (by: Dec 10 2024) task
        assertEquals("[D][ ] climb (by: Dec 10 2024)", new Deadline("climb", "2024-12-10").toString());
    }

    @Test
    public void dateFormatting_monthDayYear_success() {
        // Deadline climb Dec 10 2024 is [D][ ] climb (by: Dec 10 2024) task
        assertEquals("[D][ ] climb (by: Dec 10 2024)", new Deadline("climb", "Dec 10 2024").toString());
    }
}
