package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.EmptyTodoDescriptionException;
import duke.exceptions.UnknownMessageException;
import duke.tasks.Task;

public class AddTaskCommand extends Command {
    String userInput;
    public AddTaskCommand(String userInput) {
        super();
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTask(this.userInput);
        } catch (UnknownMessageException | EmptyTodoDescriptionException e) {
            System.out.println(Ui.formatOutputMessage("Please enter a valid task!"));
        }
    }
}
