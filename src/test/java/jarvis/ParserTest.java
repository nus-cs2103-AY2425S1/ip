package jarvis;

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

    @Test
    void parser_listCommand_noTasksListed() {
        // Arrange
        TaskList taskList = new TaskListStub();
        String listCommand = "list"; // Command to list tasks

        Parser.parse(listCommand, taskList);

        assertEquals(0, taskList.getNumTasks(), "Task list should still be empty");
    }

    @Test
    void parser_listCommand_tasksListed() {
        // Arrange
        TaskList taskList = new TaskListStub();
        taskList.add("todo test");
        String listCommand = "list"; // Command to list tasks

        Parser.parse(listCommand, taskList);

        // Expecting no tasks to be removed/added, just listed.
        assertEquals(1, taskList.getNumTasks(), "Task list should contain one task");
    }

    @Test
    void parser_deleteCommand_taskDeleted() {
        // Arrange
        TaskList taskList = new TaskListStub();
        taskList.add("todo test");
        String deleteCommand = "delete 1"; // Command to delete the first task

        Parser.parse(deleteCommand, taskList);

        assertEquals(0, taskList.getNumTasks(), "Task list should be empty after deletion");
    }

//    @Test
//    void parser_markCommand_taskMarked() {
//        // Arrange
//        TaskList taskList = new TaskListStub();
//        taskList.add("todo test");
//        String markCommand = "mark 1"; // Command to mark the first task
//
//        Parser.parse(markCommand, taskList);
//
//        assertEquals(true, taskList.isTaskMarked(0), "Task should be marked as done");
//    }
//
    @Test
    void parser_invalidIndex_throwsException() {
        // Arrange
        TaskList taskList = new TaskListStub();
        taskList.add("todo test");
        String deleteCommand = "delete 100"; // Command with an invalid index

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            Parser.parse(deleteCommand, taskList);
        }, "Should throw IndexOutOfBoundsException for invalid index");
    }

    @Test
    void parser_emptyInput_noTasksAdded() {
        // Arrange
        TaskList taskList = new TaskListStub();
        String emptyInput = ""; // Empty input

        Parser.parse(emptyInput, taskList);

        assertEquals(0, taskList.getNumTasks(), "Task list should be empty for empty input");
    }

    @Test
    void parser_byeCommand_noTasksAdded() {
        // Arrange
        TaskList taskList = new TaskListStub();
        String byeCommand = "bye"; // Command to exit

        Parser.parse(byeCommand, taskList);

        assertEquals(0, taskList.getNumTasks(), "Task list should remain unchanged");
    }

    @Test
    void parser_invalidCommand_noTasksAdded() {
        // Arrange
        TaskList taskList = new TaskListStub();
        String invalidCommand = "invalid command"; // Invalid command

        Parser.parse(invalidCommand, taskList);

        assertEquals(0, taskList.getNumTasks(), "Task list should remain unchanged for invalid command");
    }
}
