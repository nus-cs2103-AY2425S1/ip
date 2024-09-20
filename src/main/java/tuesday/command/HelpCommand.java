package tuesday.command;

import tuesday.task.Task;
import tuesday.util.Storage;
import tuesday.util.Ui;

import java.io.*;

public class HelpCommand extends Command {
    private String responseMessage;

    /**
     * Constructor for DeleteCommand
     *
     * @param command Description of the command
     */
    public HelpCommand(String command) {
        super(command);
    }

    /**
     * Deletes the task and prints the message
     *
     * @param task The Task object
     * @param ui The UI object
     * @param storage The Storage object
     */
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        this.responseMessage = "Here is a list of commands:\nlist - list out all the task(s) you have" +
                "\nmark - change a task to be finished (Usage: mark {task number})" +
                "\nunmark - change a task to be unfinished (Usage: unmark {task number})" +
                "\ntodo - create a simple task (Usage: todo {task})" +
                "\ndeadline - create a task with a deadline (Usage: deadline {task} /by {time})" +
                "\nevent - create a task with a time period (Usage: event {task} /from {time} /to {time})" +
                "\ndelete - delete a task from the list (Usage: delete {task number})" +
                "\nfind - find a task (Usage: find {anything})" +
                "\nedit - edit a task (Usage: edit {task number} /priority {H/M/L})" +
                "\nbye - exit program (Usage: bye)";
    }

    public String getString() {
        assert this.responseMessage != null : "The execute() method must be called first";
        return this.responseMessage;
    }

    /**
     * Use to exit the program
     *
     * @return false and do not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
