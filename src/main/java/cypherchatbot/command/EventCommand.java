package cypherchatbot.command;

import cypherchatbot.task.Event;
import cypherchatbot.task.Task;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a command to create a new Event task
 * and add it to the task list in the Cypher chat bot application.
 */
public class EventCommand extends Command {

    String[] command;


    /**
     * Instantiates a Event command with a specific command.
     *
     * @param s The command argument, where the index 0 contains the description
     *          of the Event task to be created,index 1 contains the from time for
     *          this task and index 2 contains the to time for the task.
     */
    public EventCommand(String[] s) {
        this.command = s;
    }

    /**
     * Executes the Event command, creating a new Event task, adding it to the TaskList,
     * outputting the result to the user via the Ui output method, and then finally
     * saving the task to storage.
     *
     * @param tasks The TaskList to which the new Event task should be added.
     * @param ui The Ui interface used to interact with the user.
     * @param storage The Storage file where the task data will be saved.
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        try {
            LocalDateTime from = LocalDateTime.parse(command[1].trim(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime to = LocalDateTime.parse(command[2].trim(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Task task = new Event(command[0], from, to);
            String output = tasks.addToList(task);
            ui.output(output);
            storage.addToStorage(task.toStringinFile());
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
