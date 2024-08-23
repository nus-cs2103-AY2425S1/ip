package matcha.command;
import matcha.tasklist.TaskList;
import matcha.ui.Ui;
import matcha.storage.Storage;
import matcha.exception.MatchaException;

public class UpdateTaskCommand extends Command{
    private String commandType;
    private String[] inputWords;
    public UpdateTaskCommand(String[] inputWords, String commandType) {
        this.commandType = commandType;
        this.inputWords = inputWords;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MatchaException {
        if (inputWords.length != 2) {
            throw new MatchaException("Please enter the task number of the task you want to " + commandType + ".");
        }

        switch(commandType) {
        case "mark":
            markTask(tasks);
            //save tasks to file
            storage.saveTasks(tasks.getTasks());
            break;
        case "unmark":
            unmarkTask(tasks);
            //save tasks to file
            storage.saveTasks(tasks.getTasks());
            break;
        default:
            throw new MatchaException("Invalid command to update tasks!");
        }
    }

    private void markTask(TaskList tasks) throws MatchaException {
        int taskNum = 0;

        try {
            taskNum = Integer.parseInt(inputWords[1]) - 1;
        } catch (NumberFormatException e) {
            throw new MatchaException("Please enter the task number of the task you want to\nmark as done.");
        }

        if (taskNum < 0 || taskNum >= tasks.getSize()) {
            throw new MatchaException("This task does not exist!");
        }

        tasks.getTask(taskNum).markDone();

        System.out.println("I have successfully marked this task as done:");
        System.out.println(tasks.getTask(taskNum).toString());
    }

    private void unmarkTask(TaskList tasks) throws MatchaException {
        int taskNum = 0;

        try {
            taskNum = Integer.parseInt(inputWords[1]) - 1;
        } catch (NumberFormatException e) {
            throw new MatchaException("Please enter the task number of the task you want to\nmark as not done.");
        }

        if (taskNum < 0 || taskNum >= tasks.getSize()) {
            throw new MatchaException("This task does not exist!");
        }

        tasks.getTask(taskNum).markNotDone();

        System.out.println("Alright, I have marked this task as not done yet:");
        System.out.println(tasks.getTask(taskNum).toString());
    }
}
