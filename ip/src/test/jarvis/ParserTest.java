package jarvis;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void parser_wrongInput_noTasksAdded() {
        // Arrange
        TaskList taskList = new TaskListStub();
        String invalidInput = "wrong input format"; // Invalid input

        Parser.parse(invalidInput, taskList);

        assertEquals(0, taskList.getNumTasks(), "Task list should be empty for invalid input");
    }
    @Test
    void parser_correctInput_oneTodoAdded(){
        TaskList tasklist = new TaskList();
        String correctInput = "todo hello";

        Parser.parse(correctInput, tasklist);
        assertEquals(1,tasklist.getNumTasks());
    }
}