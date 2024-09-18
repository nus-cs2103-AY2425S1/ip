package commands;

import org.junit.jupiter.api.Test;
import storage.Storage;
import tasks.DeadlineTask;
import tasks.TaskList;
import tasks.TodoTask;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {
    @Test
    public void execute_todoTask_correctString() {
        TodoTask todoTask = new TodoTask("Task");
        TodoCommand todoCommand= new TodoCommand(todoTask);
        StringBuilder output = new StringBuilder();
        output.append("New todo task! Grrr\n");
        output.append(todoTask).append("\n");
        output.append(String.format("Now you have %d tasks to PUNCH!%n", 1));
        assertEquals(output.toString(), todoCommand.execute(new TaskList(), new Ui(), new Storage("doesnt matter")));
    }
}
