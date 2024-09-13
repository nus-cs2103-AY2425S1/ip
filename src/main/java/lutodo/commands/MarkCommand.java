package lutodo.commands;

import lutodo.parser.Parser;
import lutodo.tasklist.TaskList;
import lutodo.storage.Storage;

import static java.lang.Integer.parseInt;

/**
 * Represents the command of mark/unmark a task in the task list.
 */
public class MarkCommand extends Command{

    private String fullCommand;
    private boolean isDone;
    private int index;

    /**
     * Constructs an MarkCommand object with the index of task that needs mark/unmark.
     *
     * @param fullCommand The command message sent by user.
     */
    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Marks the certain task as done / not done.
     *
     * @param tasks   The TaskList the method interacts with.
     * @param storage The Storage object used to save the new task list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (Parser.splitTaskInfo(fullCommand).length <= 1) {
            System.out.println("You are not telling me which task should I mark/unmark :-(");
        }
        this.index = parseInt(Parser.splitTaskInfo(fullCommand)[1]) - 1;
        assert Parser.splitTaskInfo(fullCommand)[0].equalsIgnoreCase("mark") ||
                Parser.splitTaskInfo(fullCommand)[0].equalsIgnoreCase("unmark") :
                "the command should be mark/unmark.";

        if (Parser.splitTaskInfo(fullCommand)[0].equalsIgnoreCase("mark")) {
            this.isDone = true;
        } else if (Parser.splitTaskInfo(fullCommand)[0].equalsIgnoreCase("unmark")) {
            this.isDone = false;
        } else {
            System.out.println("Task type not match, try again.");
        }

        try {
            if (isDone) {
                tasks.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n"
                        + tasks.get(index));
                storage.save(tasks);
            } else {
                tasks.get(index).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n"
                        + tasks.get(index));
                storage.save(tasks);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task you want to mark/unmark is not in task list, please try again.");
        }
    }

    /**
     * Marks the certain task as done / not done.
     *
     * @param tasks   The TaskList the method interacts with.
     * @param storage The Storage object used to save the new task list.
     */
    @Override
    public String executeAndRespond(TaskList tasks, Storage storage) {
        if (Parser.splitTaskInfo(fullCommand).length <= 1) {
            return "You are not telling me which task should I mark/unmark :-(";
        }
        this.index = parseInt(Parser.splitTaskInfo(fullCommand)[1]) - 1;
        assert Parser.splitTaskInfo(fullCommand)[0].equalsIgnoreCase("mark") ||
                Parser.splitTaskInfo(fullCommand)[0].equalsIgnoreCase("unmark") :
                "the command should be mark/unmark.";

        if (Parser.splitTaskInfo(fullCommand)[0].equalsIgnoreCase("mark")) {
            this.isDone = true;
        } else if (Parser.splitTaskInfo(fullCommand)[0].equalsIgnoreCase("unmark")) {
            this.isDone = false;
        } else {
            System.out.println("Task type not match, try again.");
        }

        try {
            if (isDone) {
                tasks.get(index).markAsDone();
                storage.save(tasks);
                return "Nice! I've marked this task as done:\n"
                        + tasks.get(index);
            } else {
                tasks.get(index).markAsNotDone();
                storage.save(tasks);
                return "OK, I've marked this task as not done yet:\n"
                        + tasks.get(index);
            }
        } catch (IndexOutOfBoundsException e) {
            return "The task you want to mark/unmark is not in task list, please try again.";
        }

    }

    /**
     * Returns false since this type of command is not exit command.
     *
     * @return whether this is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Represents the command with a String containing basic information of this command.
     *
     * @return a String containing basic information of this command.
     */
    @Override
    public String toString() {
        return "MarkCommand: " + isDone;
    }
}
