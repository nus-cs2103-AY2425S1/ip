package yoda.commands;

import yoda.TaskList;
import yoda.exceptions.InvalidInputException;
import yoda.tasks.Event;
import yoda.tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
     * @throws InvalidInputException if the input or date format is invalid.
     */
    public void run() throws InvalidInputException {
        if (!checkValidEvent(input)) {
            throw new InvalidInputException("An event must have a description, start time and end time, no...?");
        }
        String[] splitInput = input.split(" ", 2);
        String task = splitInput[1];
        String[] splitTask = task.split(" /from ", 2);
        String[] times = splitTask[1].split(" /to ", 2);

        Event newTask = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            LocalDateTime from = LocalDateTime.parse(times[0], formatter);
            LocalDateTime to = LocalDateTime.parse(times[1], formatter);
            newTask = new Event(splitTask[0], from, to);
            taskList.add(newTask);
            System.out.println("Added task:\n" + newTask + "\n"
                    + String.format("Now you have %d tasks in the list", taskList.getLength()));
        } catch (DateTimeParseException e) {
            System.out.println("Dates and times must be in format: yyyy-mm-dd HHmm");
        }

    }

    /**
     * Checks if the input and date format is valid.
     *
     * @param input User input to be validated.
     * @return true if the input and date format is valid.
     */
    public static boolean checkValidEvent(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 2) {
            String[] splitTask = splitInput[1].split("/from ", 2);
            if (splitTask.length == 2) {
                String[] times = splitTask[1].split("/to ", 2);
                return times.length == 2;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


}
