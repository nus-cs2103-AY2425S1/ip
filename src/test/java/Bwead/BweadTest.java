package Bwead;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BweadTest {

    @Test
    public void testGetResponseValid() {
        Bwead bweadTotest = new Bwead("./src/main/java/Bwead/testFile");
        assertEquals(bweadTotest.getResponse("todo return book"),
                "Got it. I've added this task: [T][ ] return book\n" + "Now you have 1 tasks in the list.");
        assertEquals(bweadTotest.getResponse("deadline return book /by 2020-10-01 1900"),
                "Got it. I've added this task: [D][ ] return book (by: Oct 1 2020, 19.00)\n"
                        + "Now you have 2 tasks in the list.");
        assertEquals(bweadTotest.getResponse("delete 1"),
                "Noted. I've removed this task: [T][ ] return book\n" + "Now you have 1 tasks in the list.");
        assertEquals(bweadTotest.getResponse("delete 1"),
                "Noted. I've removed this task: [D][ ] return book (by: Oct 1 2020, 19.00)\n"
                        + "Now you have 0 tasks in the list.");
    }

    @Test
    public void testGetResponseInvalidInput() {
        Bwead bweadTotest = new Bwead("./src/main/java/Bwead/testFile");
        assertEquals(bweadTotest.getResponse("deadline return book /by 2020-25-01 1900"),
                "invalid date");
        assertEquals(bweadTotest.getResponse("list"),
                "no tasks in list yet!");
    }
}
