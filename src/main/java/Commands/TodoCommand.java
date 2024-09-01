package Commands;

import Exceptions.BrockException;
import Storage.Storage;
import Tasks.Task;
import Tasks.TaskList;
import Tasks.ToDos;
import Ui.Ui;

public class TodoCommand extends Command {
    public TodoCommand(String command) {
        super(command);
    }

    private Task createTodo() throws BrockException {
        String command = super.getCommand();
        String[] commandWords = command.split(" ");
        int commandLength = commandWords.length;

        StringBuilder description = new StringBuilder();
        for (int i = 1; i < commandLength; i++) {
                description.append(commandWords[i])
                        .append(" ");
        }

        if (description.isEmpty()) {
            throw new BrockException("Description is missing!");
        }
        return new ToDos(description.toString());
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws BrockException {
        Task todoTask = createTodo();
        tasks.addToList(todoTask);
        ui.displayResponse("Got it. I've added this task:\n"
                + "  "
                + tasks.getTaskDetails(todoTask)
                + '\n'
                + tasks.getTasksSummary());

        storage.writeToFile(tasks.numTasks()
                        + ". "
                        + tasks.getTaskDetails(todoTask)
                        + '\n'
                , true);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
