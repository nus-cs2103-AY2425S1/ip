package Commands;

import Default.Storage;
import Default.TaskList;
import Exceptions.NedException;
import Tasks.Task;
import Tasks.ToDo;
import Default.Ui;
import java.util.ArrayList;

public class AddToDoCommand implements Command{
    private final String REGEX = "^todo.*";

    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) throws NedException {
        String[] parsed_inputs = userInput.split("todo", 2);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create a todo task with no description" + uiInstance.getCommandMessage());
        }
        Task newTask = ToDo.createToDo(parsed_inputs[1].strip(), false);
        taskList.addTask(newTask, uiInstance);
        storageInstance.save(taskList);
    };
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
