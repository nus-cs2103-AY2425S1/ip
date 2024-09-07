package echo.parser;

import echo.exception.EchoException;
import echo.task.Deadline;
import echo.task.Event;
import echo.task.Task;
import echo.task.ToDo;

/**
 * Represents a parser to parse inputs given by users
 *
 * @author Ernest Lim
 */
public class Parser {
    /**
     * Splits the input string into and array of 2 Strings containing command and description
     *
     * @param input input provided by user
     * @return String array containing command and description e.g. [command, description]
     */
    public String[] parseInput(String input) {
        return input.split(" ", 2);
    }

    /**
     * Returns the description of the todo task
     *
     * @param taskDescription string of description provided after parsing the input
     * @return string of task description of todo task
     * @throws EchoException if there is no task description
     */
    public String parseToDos(String taskDescription) throws EchoException {
        if (taskDescription.isEmpty()) {
            throw new EchoException("Sorry! Please include a description of what to do.");
        }
        return taskDescription;
    }

    /**
     * Returns the String Array of the deadline task containing task description and deadline
     * e.g. [task description, deadline]
     *
     * @param taskDescription string of description provided after parsing the input.
     * @return string array of deadline task containing task description and deadline.
     * @throws EchoException if there is no task description and
     *                       if there is no deadline include for the task.
     */
    public String[] parseDeadlines(String taskDescription) throws EchoException {
        if (taskDescription.isEmpty()) {
            throw new EchoException("Sorry! Please include a description of what to do.");
        }

        String[] deadlineArray = taskDescription.split(" /by ");

        if (deadlineArray.length == 1) {
            throw new EchoException("Sorry! Please include a deadline for the task.");
        }
        return deadlineArray;
    }

    /**
     * Returns the String Array of the events task containing task description,
     * start time of event and end time of event.
     * e.g.[task description, start time, end time]
     *
     * @param taskDescription string of description provided after parsing the input.
     * @return string array of event containing task description, start and end time of event.
     * @throws EchoException if there is no task description and there is no start or end time.
     */
    public String[] parseEvents(String taskDescription) throws EchoException {
        if (taskDescription.isEmpty()) {
            throw new EchoException("Sorry! Please include a description of what to do.");
        }

        String[] eventArray = taskDescription.split(" /from | /to ");

        if (eventArray.length < 3) {
            throw new EchoException("Sorry! Please include a start and end time for the event.");
        }
        return eventArray;
    }

    /**
     * Marks Task objects if the task status is equal to 1
     *
     * @param taskStatus 1 if the task is marked and 0 if the task is unmark
     * @param taskToMark Task object to be marked
     */
    public void markTask(String taskStatus, Task taskToMark) {
        if (taskStatus.equals("1")) {
            taskToMark.mark();
        }
    }

    /**
     * Parses input provided from text file with the format
     * e.g. Deadline | 1 | work | /by 12-12-2345 2314
     * Creates the respective Task object determined after parsing and returns the Task objects
     *
     * @param task String input provided from text file
     * @return Task object determined after parsing and finding the command
     * @throws EchoException if the command in the text file is not "todo", "deadline" or "event"
     */
    public Task parseInputFromTextFile(String task) throws EchoException {
        String[] textArray = task.split(" \\| ");
        String command = textArray[0].toLowerCase();
        String taskStatus = textArray[1];
        String taskDescription = textArray[2];

        switch (command) {
        case "todo":
            ToDo toDoTask = new ToDo(taskDescription);

            markTask(taskStatus, toDoTask);
            return toDoTask;
        case "deadline":
            String deadline = textArray[3];
            taskDescription = taskDescription + " " + deadline;

            String[] deadlineArray = parseDeadlines(taskDescription);
            String deadlineDescription = deadlineArray[0];
            String deadlineDate = deadlineArray[1];
            Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);

            markTask(taskStatus, deadlineTask);
            return deadlineTask;
        case "event":
            String startToEndTime = textArray[3];
            taskDescription = taskDescription + " " + startToEndTime;

            String[] eventArray = parseEvents(taskDescription);
            String eventDescription = eventArray[0];
            String eventStartTime = eventArray[1];
            String eventEndTime = eventArray[2];
            Event eventTask = new Event(eventDescription, eventStartTime, eventEndTime);

            markTask(taskStatus, eventTask);
            return eventTask;
        default:
            throw new EchoException("File has been corrupted, invalid syntax stored");
        }
    }
}


