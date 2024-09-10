package cypherchatbot.command;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cypherchatbot.CypherException;
import cypherchatbot.task.Event;
import cypherchatbot.task.Task;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

/**
 * The Event class represents a command to create a new Event task
 * and add it to the task list in the Cypher chatbot application.
 */
public class EventCommand extends Command {

    private String[] command;


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
     * @param tasks   The TaskList to which the new Event task should be added.
     * @param ui      The Ui interface used to interact with the user.
     * @param storage The Storage file where the task data will be saved.
     * @return String returns the dialog message to be displayed to the User
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CypherException{
        try {

            String[] eventSplit = command[1].split("/from|/to ", 3);

            if (eventSplit[0].isEmpty()) {
                throw new CypherException("No task is given. The format of the event command is:"
                        + "\n event <Description of task> /from yyyy-MM-dd HH:mm> /to yyyy-MM-dd HH:mm");
            } else if (eventSplit.length != 3 || eventSplit[1].trim().isEmpty() || eventSplit[2].trim().isEmpty()) {
                throw new CypherException("To/from is not given properly. The format of the deadline command is:"
                        + "\n event <Description of task> /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm");
            }
            assert eventSplit.length == 3 : "Command error checking not done properly";
            LocalDateTime from = LocalDateTime.parse(command[1].trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime to = LocalDateTime.parse(command[2].trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            Task event = new Event(command[0], from, to);
            tasks.addToList(event);

            storage.addToStorage(event.toStringInFile());
            return ui.showAddMessage(event,tasks.size());

        } catch (DateTimeException e) {
            return ui.showError("Enter a valid date and time in the format of yyyy-MM-dd HH:mm");
        }
    };

    /**
     * Returns false indicating that this command does not cause the application to exit.
     */
    public boolean showExitStatus() {
        return false;
    }
}
