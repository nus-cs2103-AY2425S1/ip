package meeju;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskTest {
    //CHECKSTYLE.OFF: MethodName
    @Test
    public void create_new_todoTask_Success() {
        assertEquals("[T][ ] Test Todo", new Todo("Test Todo").toString());
    }

    @Test
    public void create_new_deadlineTask_Success() {
        try {
            assertEquals("[D][ ] Test Deadline (by: Dec 20 2023 18:00HRS)",
                    new Deadline("Test Deadline", "20/12/2023 1800").toString());
        } catch (MeejuException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void create_new_eventTask_Success() {
        try {
            assertEquals("[E][ ] Test Event (from: Dec 20 2023 18:00HRS to: Dec 20 2023 19:00HRS)",
                    new Event("Test Event", "20/12/2023 1800", "20/12/2023 1900").toString());
        } catch (MeejuException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void create_new_eventTask_invalidDate_exceptionThrow() {
        try {
            assertEquals("[E][ ] Test Event (from: Dec 20 2023 18:00HRS to: Dec 40 2023 19:00HRS)",
                    new Event("Test Event", "20/12/2023 1800", "40/12/2023 1900").toString());
            fail();
        } catch (MeejuException e) {
            String message = "Meow! Please recheck date and time you have entered \n"
                    + "The Correct format is -> event <desc> /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM";
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void create_new_deadlineTask_invalidTime_exceptionThrow() {
        try {
            assertEquals("[D][ ] Test Deadline (by: Feb 35 2023 18:00HRS)",
                    new Deadline("Test Deadline", "35/02/2023 1800").toString());
            fail();
        } catch (MeejuException e) {
            String message = "I'm having a bit of trouble understanding the task.\n"
                    + "Could you please explain it using the correct format?\n"
                    + "The Correct format is -> deadline <desc> /by DD/MM/YYYY HHMM";
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void create_new_deadlineTask_invalidParameter_exceptionThrow() {
        try {
            assertEquals("[D][ ] Test Deadline (by: abcde)",
                    new Deadline("Test Deadline", "abcde").toString());
            fail();
        } catch (MeejuException e) {
            String message = "I'm having a bit of trouble understanding the task.\n"
                    + "Could you please explain it using the correct format?\n"
                    + "The Correct format is -> deadline <desc> /by DD/MM/YYYY HHMM";
            assertEquals(message, e.getMessage());
        }
    }
    //CHECKSTYLE.ON: MethodName
}

