package cypherchatbot.command;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cypherchatbot.task.Deadline;
import cypherchatbot.task.Task;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;


/**
 * The Deadline class represents a command to create a new Deadline task
 * and add it to the task list in the Cypher chat bot application.
 */
public class DeadlineCommand extends Command {
    private String[] command;

    /**
     * Instantiates a Deadline command with a specific command.
     *
     * @param s The command argument, where the index 0 contains the description
     *          of the Deadline task to be created and index 1 contains the deadline for
     *          this task.
     */
    public DeadlineCommand(String[] s) {
        this.command = s;
    }

    /**
     * Executes the Deadline command, creating a new Deadline task, adding it to the TaskList,
     * outputting the result to the user via the Ui output method, and then finally
     * saving the task to storage.
     *
     * @param tasks The TaskList to which the new Deadline task should be added.
     * @param ui The Ui interface used to interact with the user.
     * @param storage The Storage file where the task data will be saved.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            assert this.command.length == 2 : "Command error checking not done properly";
            LocalDateTime by = LocalDateTime.parse(command[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Task deadline = new Deadline(command[0], by);
            String output = tasks.addToList(deadline);
            ui.output(output);
            storage.addToStorage(deadline.toStringinFile());

        } catch (DateTimeException e) {
            ui.showError("Enter a valid date and time in the format of yyyy-MM-dd HH:mm");
        }
    };

    /**
     * Returns false indicating that this command does not cause the application to exit.
     */
    public boolean isExit() {
        return false;
    }
}
