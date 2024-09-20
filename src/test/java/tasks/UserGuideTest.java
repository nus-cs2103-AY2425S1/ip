package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duck.Duck;

public class UserGuideTest {
    @Test
    public void userGuide_quickstart_success() throws Exception {
        Duck duck = new Duck();

        assertEquals("Got it. I've added this task:\n" +
                "  [T][ ] read book\n" +
                "Now you have 1 tasks in the list.",
                duck.getResponse("todo read book"));

        assertEquals("Got it. I've added this task:\n" +
                "  [D][ ] turn in homework (by: 20 SEPTEMBER 2024)\n" +
                "Now you have 2 tasks in the list.",
                duck.getResponse("deadline turn in homework /by 2024-09-20"));

        assertEquals("Got it. I've added this task:\n" +
                "  [E][ ] open house (from: 20 SEPTEMBER 2024 to: 21 SEPTEMBER 2024)\n" +
                "Now you have 3 tasks in the list.",
                duck.getResponse("event open house /from 2024-09-20 /to 2024-09-21"));

        assertEquals("Got it. I've added this task:\n" +
                "  [A][ ] clean up (after: 21 SEPTEMBER 2024)\n" +
                "Now you have 4 tasks in the list.",
                duck.getResponse("doafter clean up /after 2024-09-21"));

        assertEquals("Here are the tasks in your list:\n" +
                "    1. [T][ ] read book\n" +
                "    2. [D][ ] turn in homework (by: 20 SEPTEMBER 2024)\n" +
                "    3. [E][ ] open house (from: 20 SEPTEMBER 2024 to: 21 SEPTEMBER 2024)\n" +
                "    4. [A][ ] clean up (after: 21 SEPTEMBER 2024)",
                duck.getResponse("list"));

        assertEquals("Nice! I've marked this task as done:\n" +
                "  [T][X] read book",
                duck.getResponse("mark 1"));

        assertEquals("OK, I've marked this task as not done yet:\n" +
                "  [T][ ] read book",
                duck.getResponse("unmark 1"));

        assertEquals("Noted. I've removed this task:\n" +
                "  [T][ ] read book\n" +
                "Now you have 3 tasks in the list.",
                duck.getResponse("delete 1"));
    }

}
