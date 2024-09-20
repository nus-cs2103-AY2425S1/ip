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

class EventTest {

    @Test
    void getTaskTypeTest() {
        Event t = new Event();
        assertEquals(TaskTypes.EVENT, t.getTaskType());
    }

    @Test
    void setTaskTypeTest() {
        Event t = new Event();
        t.setTaskType(TaskTypes.TODO);
        assertEquals(TaskTypes.TODO, t.getTaskType());
    }

    @Test
    void setTaskDoneTest() {
        Event t = new Event("", false);
        assertFalse(t.isTaskDone());

        t.setTaskDone(true);
        assertTrue(t.isTaskDone());

        t.setTaskDone(false);
        assertFalse(t.isTaskDone());
    }

    @Test
    void setTaskNameTest() {
        Event t = new Event("", false);
        String expected = "asdasd";
        t.setTaskName(expected);
        assertEquals(expected, t.getTaskName());
    }

    @Test
    void isTaskDoneTest() {
        Event t = new Event("", false);
        assertFalse(t.isTaskDone());

        t = new Event("", true);
        assertTrue(t.isTaskDone());
    }

    @Test
    void getTaskNameTest() {
        String expected = "asdasd";
        Event t = new Event(expected, false);
        assertEquals(expected, t.getTaskName());
    }

    @Test
    void getTaskDoneCheckmarkTest() {
        Event t = new Event("", false);
        assertEquals(" ", t.getTaskDoneCheckmark());

        t.setTaskDone(true);
        assertEquals("X", t.getTaskDoneCheckmark());

        t.setTaskDone(false);
        assertEquals(" ", t.getTaskDoneCheckmark());
    }

    @Test
    void testToStringTest() {
        String n = "asd asd 123";
        String d1 = "2024-12-21";
        String d2 = "2026-11-05";
        Event t = new Event(n, false, d1, d2);

        String expected = n + " (from: Dec 21 2024 to: Nov 5 2026)";
        assertEquals(expected, t.toString());
    }

    @Test
    void serializeTest() {
        String n = "asd asd 123";
        String d1 = "2024-12-21";
        String d2 = "2026-11-05";
        Event t = new Event(n, false, d1, d2);

        String expected1 = "EVENT:" + n + ":false:" + d1 + ":" + d2;
        assertEquals(expected1, t.serialize());

        t.setTaskDone(true);
        String expected2 = "EVENT:" + n + ":true:" + d1 + ":" + d2;
        assertEquals(expected2, t.serialize());
    }

    @Test
    void deserializeTest() {
        Event t = new Event();
        String input1 = "EVENT:a:false:2023-12-11:2024-12-23";
        t.deserialize(input1.split(":"));
        assertEquals("a", t.getTaskName());
        assertFalse(t.isTaskDone());
        assertEquals("2023-12-11", t.getStartTime());
        assertEquals("2024-12-23", t.getEndTime());

        String input2 = "EVENT:b:true:2003-12-11:2014-12-23";
        t.deserialize(input2.split(":"));
        assertEquals("b", t.getTaskName());
        assertTrue(t.isTaskDone());
        assertEquals("2003-12-11", t.getStartTime());
        assertEquals("2014-12-23", t.getEndTime());

        String input3 = "EVENT:c:true:2011-12-23:2023-12-22:extra:data";
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

        String input5 = "EVENT:";
        YappingBotException e2 = assertThrowsExactly(YappingBotInvalidSaveFileException.class,
                                                     () -> t.deserialize(input5.split(":")));
        String expectedErrorMessage2 = "Error Reading save file! The following error was "
                                       + "encountered: Missing Data Values";
        assertEquals(expectedErrorMessage2, e2.getErrorMessage());

        String input6 = "EVENT:f:true:INVALID-date:INVALID-date";
        YappingBotException e3 = assertThrowsExactly(YappingBotInvalidSaveFileException.class,
                                                     () -> t.deserialize(input6.split(":")));
        String expectedErrorMessage3 = "Error Reading save file! The following error was "
                                       + "encountered: I'm sorry, I do not understand what "
                                       + "'INVALID-date' is!\nTime value must be given in any "
                                       + "valid date-time format!";

        assertEquals(expectedErrorMessage3, e3.getErrorMessage());

        String input7 = "EVENT:f:true:2023-12-12:INVALID-date";
        YappingBotException e4 = assertThrowsExactly(YappingBotInvalidSaveFileException.class,
                                                     () -> t.deserialize(input7.split(":")));
        String expectedErrorMessage4 = "Error Reading save file! The following error was "
                                       + "encountered: I'm sorry, I do not understand what "
                                       + "'INVALID-date' is!\nTime value must be given in any "
                                       + "valid date-time format!";
        assertEquals(expectedErrorMessage4, e4.getErrorMessage());
    }

    @Test
    void isStringFoundInTaskTest() {
        String d1 = "2024-12-21";
        String d2 = "2026-11-05";
        Event t = new Event("abc 123", false, d1, d2);
        assertTrue(t.isStringFoundInTask("abc 123"));
        assertTrue(t.isStringFoundInTask("c 1"));
        assertTrue(t.isStringFoundInTask("abc"));
        assertTrue(t.isStringFoundInTask("123"));
        assertTrue(t.isStringFoundInTask(" "));
        assertTrue(t.isStringFoundInTask("2024"));
        assertTrue(t.isStringFoundInTask("2024-12"));
        assertTrue(t.isStringFoundInTask("12-21"));
        assertTrue(t.isStringFoundInTask("2026-11-05"));
        assertTrue(t.isStringFoundInTask("2024-12-21"));

        assertFalse(t.isStringFoundInTask("123 abc"));
        assertFalse(t.isStringFoundInTask(""));
        assertFalse(t.isStringFoundInTask("111111111111"));
        assertFalse(t.isStringFoundInTask("  "));
        assertFalse(t.isStringFoundInTask("2024 12"));
        assertFalse(t.isStringFoundInTask("2026-0-11"));
        assertFalse(t.isStringFoundInTask("12- 11"));
        assertFalse(t.isStringFoundInTask("2024-12-21 2026-11-05"));
    }

    @Test
    void getTaskTypeSymbolTest() {
        Event t = new Event();
        assertEquals("E", t.getTaskTypeSymbol());
    }

    @Test
    void getStartTimeTest() {
        String d1 = "2024-12-21";
        String d2 = "2026-11-05";
        Event t = new Event("", false, d1, d2);
        assertEquals(d1, t.getStartTime());

    }

    @Test
    void setStartTimeTest() {
        Event t = new Event();
        String d = "2024-12-21";
        t.setStartTime(d);
        assertEquals(d, t.getStartTime());
    }

    @Test
    void getEndTimeTest() {
        String d1 = "2024-12-21";
        String d2 = "2026-11-05";
        Event t = new Event("", false, d1, d2);
        assertEquals(d2, t.getEndTime());

    }

    @Test
    void setEndTimeTest() {
        Event t = new Event();
        String d = "2024-12-21";
        t.setEndTime(d);
        assertEquals(d, t.getEndTime());
    }
}