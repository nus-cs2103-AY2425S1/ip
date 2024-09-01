package blue.task;

import blue.Exceptions.EmptyDescriptionException;
import blue.Exceptions.InputErrorException;
import blue.Exceptions.WrongNumberOfItemException;
import blue.Storage;
import blue.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Manages a list of tasks, allowing tasks to be added, marked as done, unmarked, deleted, and listed.
 */
public class TaskList {
    /** List of tasks. */
    private ArrayList<Task> myList;

    /** The number of tasks in the list. */
    private int noOfTask;

    /**
     * Constructs a TaskList, loading tasks from storage if available.
     */
    public TaskList() {
        this.myList = new ArrayList<>();
        Storage.loadFromFile(myList);
        this.noOfTask = myList.size();
    }

    /**
     * Adds a task to the list based on the user's input.
     *
     * @param input The user's input specifying the task type and details.
     * @throws EmptyDescriptionException If the task description is empty.
     * @throws InputErrorException If the input format is incorrect.
     */
    public void addToList(String input) throws EmptyDescriptionException, InputErrorException {
        Task task;
        String[] inputParts = input.split(" ", 2);
        String taskType = inputParts[0].trim(); // todo, deadline, or event

        if (taskType.equalsIgnoreCase("todo")) {
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new EmptyDescriptionException("The description of a todo cannot be empty.");
            }
            task = new ToDoTask(inputParts[1].trim());
        } else if (taskType.equalsIgnoreCase("deadline")) {
            String[] descParts = inputParts[1].split(" /by ", 2);
            if (descParts.length < 2) {
                throw new InputErrorException("The deadline must be specified in the format: 'deadline <description> /by <date>'");
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime deadline = LocalDateTime.parse(descParts[1].trim(), formatter);
                task = new DeadlineTask(descParts[0].trim(), deadline);
            } catch (DateTimeParseException e) {
                throw new InputErrorException("The date format must be 'd/M/yyyy HHmm'. Example: '2/12/2019 1800'");
            }
        } else if (taskType.equalsIgnoreCase("event")) {
            String[] descParts = inputParts[1].split(" /from ", 2);
            if (descParts.length < 2 || !descParts[1].contains(" /to ")) {
                throw new InputErrorException("The event must be specified in the format: 'event <description> /from <start_time> /to <end_time>'");
            }
            String[] timeParts = descParts[1].split(" /to ", 2);
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime fromTime = LocalDateTime.parse(timeParts[0].trim(), formatter);
                LocalDateTime toTime = LocalDateTime.parse(timeParts[1].trim(), formatter);
                task = new EventTask(descParts[0].trim(), fromTime.toString(), toTime.toString());
            } catch (DateTimeParseException e) {
                throw new InputErrorException("The date format must be 'd/M/yyyy HHmm'. Example: '2/12/2019 1800'");
            }
        } else {
            throw new InputErrorException("Unknown task type.");
        }

        myList.add(task);
        noOfTask++;
        Storage.saveToFile(this.myList); // Save to file after adding a task
        System.out.println(task.toString());
    }

    /**
     * Marks the specified task as done.
     *
     * @param number The task number to mark as done.
     * @throws WrongNumberOfItemException If the task number is invalid.
     */
    public void mark(int number) throws WrongNumberOfItemException {
        if (number <= 0 || number > noOfTask) {
            throw new WrongNumberOfItemException(number);
        }

        Task currTask = myList.get(number - 1);
        currTask.markAsDone();
        UI.displayAfterMark(currTask);
        Storage.saveToFile(this.myList);
    }

    /**
     * Marks the specified task as not done.
     *
     * @param number The task number to unmark.
     * @throws WrongNumberOfItemException If the task number is invalid.
     */
    public void unmark(int number) throws WrongNumberOfItemException {
        if (number <= 0 || number > noOfTask) {
            throw new WrongNumberOfItemException(number);
        }

        Task currTask = myList.get(number - 1);
        currTask.markAsUnDone();
        UI.displayAfterUnMark(currTask);
        Storage.saveToFile(this.myList);
    }

    /**
     * Deletes the specified task from the list.
     *
     * @param number The task number to delete.
     * @throws WrongNumberOfItemException If the task number is invalid.
     */
    public void delete(int number) throws WrongNumberOfItemException {
        if (number <= 0 || number > noOfTask) {
            throw new WrongNumberOfItemException(number);
        }
        Task removedTask = myList.get(number - 1);
        this.noOfTask--;
        UI.displayAfterDelete(removedTask, noOfTask);
        myList.remove(number - 1);
        Storage.saveToFile(this.myList); // Save to file after deleting a task
    }

    /**
     * Prints the current list of tasks.
     */
    public void printList() {
        UI.displayList(myList, noOfTask);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getNumberOfTask() {
        return this.noOfTask;
    }
}
