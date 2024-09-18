package yapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import yapper.components.Deadline;
import yapper.exceptions.YapperException;

public class DeadlineTest {

    @Test
    public void getDesc() {
        assertEquals("| D |   | return book | 06:00 PM", new Deadline("return book", "1800").getDesc());
        assertEquals("| D |   | this | DEC 2 2019", new Deadline("this", "2/12/2019").getDesc());
        assertEquals("| D |   | that | DEC 2 2019", new Deadline("that", "2019/12/2").getDesc());
        assertEquals("| D |   | make thing | DEC 2 2019 06:00 PM",
                new Deadline("make thing", "2/12/2019 1800").getDesc());
        assertEquals("| D |   | fight | hello", new Deadline("fight", "hello").getDesc());
    }

    @Test
    public void getDesc_emptyString_exceptionThrown() {
        try {
            assertEquals("| D |   |   | by", new Deadline("", "by").getDesc());
            System.out.println("Fail");
        } catch (YapperException e) {
            assertEquals("Please provide a description", e.getMessage());
        }

        try {
            assertEquals("| D |   | desc | ", new Deadline("desc", "").getDesc());
            System.out.println("Fail");
        } catch (YapperException e) {
            assertEquals("Please provide a description", e.getMessage());
        }

        try {
            assertEquals("| D |   |   | ", new Deadline("", "").getDesc());
            System.out.println("Fail");
        } catch (YapperException e) {
            assertEquals("Please provide a description", e.getMessage());
        }
    }
}
