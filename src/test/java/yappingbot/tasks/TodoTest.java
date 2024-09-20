package yappingbot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotInvalidSaveFileException;
import yappingbot.tasks.tasklist.TaskTypes;

class TodoTest {

    @Test
    void getTaskTypeTest() {
        Todo t = new Todo();
        assertEquals(TaskTypes.TODO, t.getTaskType());
    }

    @Test
    void setTaskTypeTest() {
        Todo t = new Todo();
        t.setTaskType(TaskTypes.EVENT);
        assertEquals(TaskTypes.EVENT, t.getTaskType());
    }

    @Test
    void setTaskDoneTest() {
        Todo t = new Todo("", false);
        assertFalse(t.isTaskDone());

        t.setTaskDone(true);
        assertTrue(t.isTaskDone());

        t.setTaskDone(false);
        assertFalse(t.isTaskDone());
    }

    @Test
    void setTaskNameTest() {
        Todo t = new Todo("", false);
        String expected = "asdasd";
        t.setTaskName(expected);
        assertEquals(expected, t.getTaskName());
    }

    @Test
    void isTaskDoneTest() {
        Todo t = new Todo("", false);
        assertFalse(t.isTaskDone());

        t = new Todo("", true);
        assertTrue(t.isTaskDone());
    }

    @Test
    void getTaskNameTest() {
        String expected = "asdasd";
        Todo t = new Todo(expected, false);
        assertEquals(expected, t.getTaskName());
    }

    @Test
    void getTaskDoneCheckmarkTest() {
        Todo t = new Todo("", false);
        assertEquals(" ", t.getTaskDoneCheckmark());

        t.setTaskDone(true);
        assertEquals("X", t.getTaskDoneCheckmark());

        t.setTaskDone(false);
        assertEquals(" ", t.getTaskDoneCheckmark());
    }

    @Test
    void testToStringTest() {
        String n = "asd asd 123";
        Todo t = new Todo(n, false);

        String expected = n;
        assertEquals(expected, t.toString());
    }

    @Test
    void serializeTest() {
        String n = "asd asd 123";
        Todo t = new Todo(n, false);

        String expected1 = "TODO:" + n + ":false";
        assertEquals(expected1, t.serialize());

        t.setTaskDone(true);
        String expected2 = "TODO:" + n + ":true";
        assertEquals(expected2, t.serialize());
    }

    @Test
    void deserializeTest() {
        Todo t = new Todo();
        String input1 = "TODO:a:false";
        t.deserialize(input1.split(":"));
        assertEquals("a", t.getTaskName());
        assertFalse(t.isTaskDone());

        String input2 = "TODO:b:true";
        t.deserialize(input2.split(":"));
        assertEquals("b", t.getTaskName());
        assertTrue(t.isTaskDone());

        String input3 = "TODO:c:true:extra:data";
        t.deserialize(input3.split(":"));
        assertEquals("c", t.getTaskName());
        assertTrue(t.isTaskDone());

        String input4 = "asdlkjdlkdja:c:true:extra:data";
        YappingBotException e1 = assertThrowsExactly(YappingBotInvalidSaveFileException.class,
                                                    () -> t.deserialize(input4.split(":")));
        String expectedErrorMessage1 = "Error Reading save file! The following error was "
                                       + "encountered: No enum constant yappingbot.tasks."
                                       + "tasklist.TaskTypes." + input4.split(":")[0];
        assertEquals(expectedErrorMessage1, e1.getErrorMessage());

        String input5 = "TODO:";
        YappingBotException e2 = assertThrowsExactly(YappingBotInvalidSaveFileException.class,
                                                    () -> t.deserialize(input5.split(":")));
        String expectedErrorMessage2 = "Error Reading save file! The following error was "
                                       + "encountered: Missing Data Values";
        assertEquals(expectedErrorMessage2, e2.getErrorMessage());
    }

    @Test
    void isStringFoundInTaskTest() {
        Todo t = new Todo("abc 123", false);
        assertTrue(t.isStringFoundInTask("abc 123"));
        assertTrue(t.isStringFoundInTask("c 1"));
        assertTrue(t.isStringFoundInTask("abc"));
        assertTrue(t.isStringFoundInTask("123"));
        assertTrue(t.isStringFoundInTask(" "));
        assertFalse(t.isStringFoundInTask("123 abc"));
        assertFalse(t.isStringFoundInTask(""));
        assertFalse(t.isStringFoundInTask("111111111111"));
        assertFalse(t.isStringFoundInTask("  "));
    }

    @Test
    void getTaskTypeSymbolTest() {
        Todo t = new Todo();
        assertEquals("T", t.getTaskTypeSymbol());
    }
}