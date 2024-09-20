package yappingbot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotInvalidSaveFileException;
import yappingbot.exceptions.YappingBotInvalidSaveFileException;
import yappingbot.tasks.tasklist.TaskTypes;

class DeadlineTest {

    @Test
    void getTaskTypeTest() {
        Deadline t = new Deadline();
        assertEquals(TaskTypes.DEADLINE, t.getTaskType());
    }

    @Test
    void setTaskTypeTest() {
        Deadline t = new Deadline();
        t.setTaskType(TaskTypes.EVENT);
        assertEquals(TaskTypes.EVENT, t.getTaskType());
    }

    @Test
    void setTaskDoneTest() {
        Deadline t = new Deadline("", false);
        assertFalse(t.isTaskDone());

        t.setTaskDone(true);
        assertTrue(t.isTaskDone());

        t.setTaskDone(false);
        assertFalse(t.isTaskDone());
    }

    @Test
    void setTaskNameTest() {
        Deadline t = new Deadline("", false);
        String expected = "asdasd";
        t.setTaskName(expected);
        assertEquals(expected, t.getTaskName());
    }

    @Test
    void isTaskDoneTest() {
        Deadline t = new Deadline("", false);
        assertFalse(t.isTaskDone());

        t = new Deadline("", true);
        assertTrue(t.isTaskDone());
    }

    @Test
    void getTaskNameTest() {
        String expected = "asdasd";
        Deadline t = new Deadline(expected, false);
        assertEquals(expected, t.getTaskName());
    }

    @Test
    void getTaskDoneCheckmarkTest() {
        Deadline t = new Deadline("", false);
        assertEquals(" ", t.getTaskDoneCheckmark());

        t.setTaskDone(true);
        assertEquals("X", t.getTaskDoneCheckmark());

        t.setTaskDone(false);
        assertEquals(" ", t.getTaskDoneCheckmark());
    }

    @Test
    void testToStringTest() {
        String n = "asd asd 123";
        String d = "2024-12-21";
        Deadline t = new Deadline(n, false, d);

        String expected = n + " (by: Dec 21 2024)";
        assertEquals(expected, t.toString());
    }

    @Test
    void serializeTest() {
        String n = "asd asd 123";
        String d = "2023-12-23";
        Deadline t = new Deadline(n, false, d);

        String expected1 = "DEADLINE:" + n + ":false:" + d;
        assertEquals(expected1, t.serialize());

        t.setTaskDone(true);
        String expected2 = "DEADLINE:" + n + ":true:" + d;
        assertEquals(expected2, t.serialize());
    }

    @Test
    void deserializeTest() {
        Deadline t = new Deadline();
        String input1 = "DEADLINE:a:false:2023-12-11";
        t.deserialize(input1.split(":"));
        assertEquals("a", t.getTaskName());
        assertFalse(t.isTaskDone());
        assertEquals("2023-12-11", t.getDeadline());

        String input2 = "DEADLINE:b:true:2011-12-23";
        t.deserialize(input2.split(":"));
        assertEquals("b", t.getTaskName());
        assertTrue(t.isTaskDone());
        assertEquals("2011-12-23", t.getDeadline());

        String input3 = "DEADLINE:c:true:2011-12-23:extra:data";
        t.deserialize(input3.split(":"));
        assertEquals("c", t.getTaskName());
        assertTrue(t.isTaskDone());

        String input4 = "asdlkjdlkdja:c:true:extra:data:12323123";
        YappingBotException e1 = assertThrowsExactly(YappingBotInvalidSaveFileException.class,
                                                     () -> t.deserialize(input4.split(":")));
        String expectedErrorMessage1 = "Error Reading save file! The following error was "
                                       + "encountered: No enum constant yappingbot.tasks."
                                       + "tasklist.TaskTypes." + input4.split(":")[0];
        assertEquals(expectedErrorMessage1, e1.getErrorMessage());

        String input5 = "DEADLINE:";
        YappingBotException e2 = assertThrowsExactly(YappingBotInvalidSaveFileException.class,
                                                     () -> t.deserialize(input5.split(":")));
        String expectedErrorMessage2 = "Error Reading save file! The following error was "
                                       + "encountered: Missing Data Values";
        assertEquals(expectedErrorMessage2, e2.getErrorMessage());

        String input6 = "DEADLINE:f:true:INVALID-date";
        YappingBotException e3 = assertThrowsExactly(YappingBotInvalidSaveFileException.class,
                                                     () -> t.deserialize(input6.split(":")));
        String expectedErrorMessage3 = "Error Reading save file! The following error was "
                                       + "encountered: I'm sorry, I do not understand what "
                                       + "'INVALID-date' is!\nTime value must be given in any "
                                       + "valid date-time format!";
        assertEquals(expectedErrorMessage3, e3.getErrorMessage());
    }

    @Test
    void isStringFoundInTaskTest() {
        Deadline t = new Deadline("abc 123", false, "2023-12-11");
        assertTrue(t.isStringFoundInTask("abc 123"));
        assertTrue(t.isStringFoundInTask("c 1"));
        assertTrue(t.isStringFoundInTask("abc"));
        assertTrue(t.isStringFoundInTask("123"));
        assertTrue(t.isStringFoundInTask(" "));
        assertTrue(t.isStringFoundInTask("2023"));
        assertTrue(t.isStringFoundInTask("2023-12"));
        assertTrue(t.isStringFoundInTask("12-11"));
        assertFalse(t.isStringFoundInTask("123 abc"));
        assertFalse(t.isStringFoundInTask(""));
        assertFalse(t.isStringFoundInTask("111111111111"));
        assertFalse(t.isStringFoundInTask("  "));
        assertFalse(t.isStringFoundInTask("2023 12"));
        assertFalse(t.isStringFoundInTask("2023-0-11"));
        assertFalse(t.isStringFoundInTask("12- 11"));
    }

    @Test
    void getTaskTypeSymbolTest() {
        Deadline t = new Deadline();
        assertEquals("D", t.getTaskTypeSymbol());
    }

    @Test
    void getDeadlineTest() {
        String d = "2024-12-21";
        Deadline t = new Deadline("", false, d);
        assertEquals(d, t.getDeadline());

    }

    @Test
    void setDeadlineTest() {
        Deadline t = new Deadline();
        String d = "2024-12-21";
        t.setDeadline(d);
        assertEquals(d, t.getDeadline());
    }
}