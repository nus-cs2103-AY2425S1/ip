package parser;

import tasks.Task;

import org.junit.jupiter.api.Test;
import tasks.DeadlineTask;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testParseDeadlineTask() {
        try {
            String s = new Parser().parseDeadlineTask("Kiss Meeks!/by 2020-02-02 1800").toString();
            DeadlineTask deadlineTask = new DeadlineTask("Kiss Meeks!", false, "2020-02-02 1800");
            assertEquals(deadlineTask.toString(), s);
        } catch (DateTimeParseException e) {
            System.out.print("Incorrect formatting of date");
            fail();
        }

    }
}
