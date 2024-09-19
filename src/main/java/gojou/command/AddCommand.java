package gojou.command;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import gojou.GojouException;
import gojou.Parser;
import gojou.Storage;
import gojou.Ui;
import gojou.task.Priority;
import gojou.task.Task;
import gojou.task.TaskList;

/**
 * Represents the command by user to add a Task to Tasklist.
 */
public class AddCommand extends Command {
    private Scanner lineScanner;
    private String response;

    /**
     * Constructs an {@code AddCommand} instance with the provided {@code Scanner} and command response.
     *
     * @param lineScanner A {@code Scanner} object used to parse the user's input.
     * @param response A {@code String} representing the user's command response (e.g., "todo", "deadline", "event").
     */
    public AddCommand(Scanner lineScanner, String response) {
        this.lineScanner = lineScanner;
        this.response = response;
    }

    /**
     * {@inheritDoc}
     *
     * Creates the Task object specified by user, save it to file and informs user that a new Task
     * is added to Tasklist.
     *
     * @param tasks Tasklist that holds the list of Tasks.
     * @param ui Ui object that displays messages to user based on action taken by chatbot.
     * @param storage Storage object that saves changes to file.
     * @throws GojouException If date and time are not in correct format or if there are issues creating
     *     the Task object such as if no description is given.
     * @throws IOException If there are issues saving changes to file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GojouException, IOException {
        ArrayList<String> arrOfStr = new ArrayList<>();
        Task task = new Task("", false, Priority.NONE);

        if (this.response.equals("todo")) {
            task = Parser.makeTodoTask(lineScanner, arrOfStr, false);
        } else if (this.response.equals("deadline")) {
            task = getDeadlineTask(arrOfStr);
        } else if (this.response.equals("event")) {
            task = getEventTask(arrOfStr);
        }
        tasks.add(task, storage);
        return ui.showMessage("Got it. I've added this task: ", task, tasks.getSize());
    }

    private Task getEventTask(ArrayList<String> arrOfStr) throws GojouException {
        Task task;
        try {
            task = Parser.makeEventTask(lineScanner, arrOfStr, false);
        } catch (DateTimeParseException e) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just "
                    + "part of getting stronger. Let's try that again, shall we? Please provide the start and "
                    + "end date and time in yyyy-mm-dd HHMM format e.g. 2024-05-19 1800");
        }
        return task;
    }

    private Task getDeadlineTask(ArrayList<String> arrOfStr) throws GojouException {
        Task task;
        try {
            task = Parser.makeDeadlineTask(lineScanner, arrOfStr, false);
        } catch (DateTimeParseException e) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just "
                    + "part of getting stronger. Let’s try that again, shall we? Please provide the deadline "
                    + "in yyyy-mm-dd HHMM format e.g. 2024-05-19 1800");
        }
        return task;
    }

    @Override
    public String getCommandType() {
        return "AddCommand";
    }
}
