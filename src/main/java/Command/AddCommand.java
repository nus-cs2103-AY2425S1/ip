package Command;

import Tools.Storage;
import Tools.TaskList;
import Exception.MissingDateException;
import Exception.EmptyDescriptionException;
import Tools.Ui;

public class AddCommand extends Command {

    public AddCommand(TaskList tasks, Storage storage, String command) {

        super(tasks, storage, command);
    }

    private void execute() {
        try {
            if (command.startsWith("delete")) {
                tasks.deleteTask(Integer.parseInt(command.substring(7)) - 1);
            } else if (command.startsWith("todo")) {
                tasks.addTodoTask(command);
            } else if (command.startsWith("deadline")) {
                tasks.addDeadlineTask(command);
            }
        } catch (EmptyDescriptionException | MissingDateException e) {
            System.out.println(e.getMessage());
        }
    }
}

