package chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.TaskList;

public class ParserTest {

    @Test
    public void invalidCommandTest() {
        TaskList taskList = new TaskList();
        try {
            Parser.answer("random", taskList);
        } catch (Exception e) {
            assertEquals("Let's go inputting valid commands only \nLet's go type 'help' for valid commands",
                    e.getMessage());
        }
    }

    @Test
    public void invalidNumberTest() {
        TaskList taskList = new TaskList();
        try {
            Parser.answer("todo hello", taskList);
            Parser.answer("mark 2", taskList);
        } catch (Exception e) {
            assertEquals("Task does not exist", e.getMessage());
        }
    }
}
