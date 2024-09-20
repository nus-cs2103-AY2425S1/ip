package yoda.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import yoda.TaskList;
import yoda.exceptions.YodaException;
import yoda.tasks.Event;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {
    private TaskList taskList;
    private String input;

    /**
     * Constructs an EventCommand with the specified task list and input.
     *
     * @param taskList Task list that the event will be added to.
     * @param input User input containing the description, starting and ending time.
     */
    public EventCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    /**
     * Executes the command to add a new event task to the task list.
     *
     * @return Yoda's response as a string
     * @throws YodaException if the input or date format is invalid.
     */
    public String run() throws YodaException {
        checkFormat();
        return handleInput();
    }

    /**
     * Handles input to produce Yoda's response.
     *
     * @return Yoda's response as a string.
     * @throws YodaException
     */
    public String handleInput() throws YodaException {
        String[] splitInput = input.split(" ", 2);
        String task = splitInput[1];
        String[] splitTask = task.split(" /from ", 2);
        String[] times = splitTask[1].split(" /to ", 2);

        Event newTask = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String message = null;
        try {
            LocalDateTime from = LocalDateTime.parse(times[0], formatter);
            LocalDateTime to = LocalDateTime.parse(times[1], formatter);
            newTask = new Event(splitTask[0], from, to);
            taskList.add(newTask);
            message = "Added task:\n" + newTask + "\n"
                    + String.format("Now you have %d tasks in the list", taskList.getLength());
        } catch (DateTimeParseException e) {
            throw new YodaException("Dates and times must be in format: yyyy-mm-dd HHmm");
        }
        return message;
    }

    /**
     * Checks if the input format is valid.
     *
     * @throws YodaException if format is invalid
     */

    public void checkFormat() throws YodaException {
        String[] splitInput = input.split(" ", 2);
        boolean hasValidFormat = false;
        if (splitInput.length == 2) {
            String[] splitTask = splitInput[1].split("/from ", 2);
            if (splitTask.length == 2) {
                String[] times = splitTask[1].split("/to ", 2);
                hasValidFormat = times.length == 2;
            } else {
                hasValidFormat = false;
            }
        } else {
            hasValidFormat = false;
        }
        if (!hasValidFormat) {
            throw new YodaException("An event must have a description, start time and end time, no...?"
                    + "\n" + "Command should be in format: event [name] /from [yyyy-mm-dd HHmm]"
                    + "/to [yyyy-mm-dd HHmm]");
        }
    }


}
