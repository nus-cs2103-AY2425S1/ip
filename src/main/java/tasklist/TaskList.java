package tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import exceptions.ErrorMessages;
import exceptions.TheOrangeRatchetCatException;
import parser.Parser;
import tasks.Task;

/**
 * TaskList class is a class that contains all the methods that are used to manipulate the list of tasks.
 */
public class TaskList {
    /**
     * Prints out all tasks currently in the list
     * @param tasks represents the list of task
     * @return returns the string of all tasks in the list
     */
    public static String checkListOfTasks(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        String result = Parser.printAllTasks(tasks);
        return result;
    }

    /**
     * Marks a particular task with that specific index as done
     * @param input represents the index of the task to mark
     * @param tasks represents the list of task
     * @return returns the task string that has been marked as done
     */
    public static String markTaskAsDone(String input, List<Task> tasks) {
        try {
            return Parser.markTaskAsDone(tasks, Integer.parseInt(input) - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ErrorMessages.ARRAY_OUT_OF_BOUNDS);
            return ErrorMessages.ARRAY_OUT_OF_BOUNDS;
        } catch (NumberFormatException e) {
            System.out.println(ErrorMessages.INCORRECT_ASCII_VALUE_INPUT);
            return ErrorMessages.INCORRECT_ASCII_VALUE_INPUT;
        }
    }

    /**
     * Unmarks a particular task with that specific index as done
     * @param input represents the index of the task to unmark
     * @param tasks represents the list of task
     * @return returns the task string that has been unmarked as done
     */
    public static String unmarkTaskAsDone(String input, List<Task> tasks) {
        try {
            return Parser.unmarkTaskAsDone(tasks, Integer.parseInt(input) - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ErrorMessages.ARRAY_OUT_OF_BOUNDS);
            return ErrorMessages.ARRAY_OUT_OF_BOUNDS;
        } catch (NumberFormatException e) {
            System.out.println(ErrorMessages.INCORRECT_ASCII_VALUE_INPUT);
            return ErrorMessages.INCORRECT_ASCII_VALUE_INPUT;
        }
    }

    /**
     * Deletes a particular task with that specific index as done
     * @param index represents the index of the task to delete
     * @param tasks represents the list of task
     * @return returns the string that shows that the task has been deleted
     */
    public static String deleteTask(int index, List<Task> tasks) {
        try {
            return Parser.deleteTasks(tasks, index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ErrorMessages.ARRAY_OUT_OF_BOUNDS);
            return ErrorMessages.ARRAY_OUT_OF_BOUNDS;
        }
    }

    /**
     * Adds a new task of type ToDo
     *
     * @param input represents the taskDescription of task
     * @param tasks represents the list of task
     * @return returns the string that shows a new todo task has been added
     */
    public static String addNewTodoTask(String input, List<Task> tasks) throws TheOrangeRatchetCatException {
        if (input.isEmpty()) {
            return ErrorMessages.INCORRECT_FORMAT_FOR_ADDING_TODO_COMMAND;
        }
        return Parser.addingToDoTaskToList(input, tasks);
    }

    /**
     * Adds a new task of type Deadline
     *
     * @param input represents the taskDescription of Deadline
     * @param tasks represents the list of task
     * @return returns the string that shows a new deadline task has been added
     */
    public static String addNewDeadlineTask(String input, List<Task> tasks) throws TheOrangeRatchetCatException {
        // Split the input string by "/by"
        String[] parts = input.split("/by");
        // The description is the first part after removing the word "deadline"
        String taskDescription = parts[0].replace("deadline", "").trim();
        if (taskDescription.isEmpty()) {
            return ErrorMessages.INCORRECT_FORMAT_FOR_ADDING_DEADLINE_COMMAND;
        }
        // The "by" part is the second part, if it exists
        String date = parts.length > 1 ? parts[1].trim() : "";
        LocalDate dateBy;
        try { // Utilises LocalDate static method to parse input
            dateBy = LocalDate.parse(date);
            assert dateBy != null : "Parsed date should not be null"; // Assert that the date is correctly parsed
        } catch (DateTimeParseException e) {
            System.out.println(ErrorMessages.INCORRECT_FORMAT_FOR_ADDING_DEADLINE_COMMAND);
            return ErrorMessages.INCORRECT_FORMAT_FOR_ADDING_DEADLINE_COMMAND;
        }
        if (date.isEmpty()) {
            return ErrorMessages.INCORRECT_FORMAT_FOR_ADDING_DEADLINE_COMMAND;
        }
        assert !taskDescription.isEmpty() : "Task description should not be empty";
        return Parser.addingDeadlineTaskToList(taskDescription, dateBy, tasks);
    }
    /**
     * Adds a new task of type Event
     *
     * @param input represents the taskDescription of Event
     * @param tasks represents the list of task
     * @return returns the string that shows a new event task has been added
     */
    public static String addNewEventTask(String input, List<Task> tasks) throws TheOrangeRatchetCatException {
        String[] parts = input.split("/from"); // Split the input string by "/from"
        String taskDescription = parts[0].replace("event", "").trim();
        if (taskDescription.isEmpty()) {
            throw new TheOrangeRatchetCatException(ErrorMessages.INCORRECT_FORMAT_FOR_ADDING_EVENT_COMMAND);
        }
        String[] dateParts = parts[1].split("/to"); // Further split the remaining part by "/to"
        String fromDate = dateParts[0].trim(); // The "fromDate" is the first part
        String toDate = dateParts.length > 1 ? dateParts[1].trim() : ""; // The "toDate" is the second part
        LocalDate toLocalDate;
        LocalDate fromLocalDate;
        try { // Utilises LocalDate static method to parse input
            toLocalDate = LocalDate.parse(toDate);
            fromLocalDate = LocalDate.parse(fromDate);
        } catch (DateTimeParseException e) {
            System.out.println(ErrorMessages.INCORRECT_FORMAT_FOR_ADDING_EVENT_COMMAND);
            return ErrorMessages.INCORRECT_FORMAT_FOR_ADDING_EVENT_COMMAND;
        }
        if (fromLocalDate.isAfter(toLocalDate)) {
            throw new TheOrangeRatchetCatException("Your start date cannot be later than your end date");
        }
        assert !fromLocalDate.isAfter(toLocalDate) : "Start date must be before or on the end date.";
        if (toDate.isEmpty()) {
            throw new TheOrangeRatchetCatException(ErrorMessages.INCORRECT_FORMAT_FOR_ADDING_EVENT_COMMAND);
        }
        validateEventInputs(taskDescription, fromDate, toDate);
        return Parser.addingEventToTaskList(taskDescription, fromLocalDate, toLocalDate, tasks);
    }
    /**
     * To validate that the taskDescription, fromDate and toDate are not empty before creating a new Event instance
     * @param taskDescription the description of the task
     * @param fromDate the starting date of the event
     * @param toDate the ending date of the event
     */
    private static void validateEventInputs(String taskDescription, String fromDate, String toDate) {
        assert !taskDescription.isEmpty() : "TaskDescription cannot be empty";
        assert !fromDate.isEmpty() : "From date must not be empty.";
        assert !toDate.isEmpty() : "To date must not be empty.";
    }
    /**
     * Prints out all activities that are still relevant in regard to the input date
     *
     * @param date represents the date of interest
     * @param tasks represents the list of task
     * @return returns the string that shows all tasks that are relevant to the input date
     */
    public static String printTasksOnRelevantDate(LocalDate date, List<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks that occur at this date: " + date.toString());
        if (date.toString().isEmpty()) {
            return ErrorMessages.INCORRECT_FORMAT_FOR_ON_COMMAND;
        }
        StringBuilder sbr = new StringBuilder("Here are the tasks that occur at this date: " + date + "\n");
        int startingTaskIndex = 1;
        int totalNumberOfTasksRelatedToDate = Parser.printTasksIfDateCorresponds(tasks, date, sbr, startingTaskIndex);
        if (totalNumberOfTasksRelatedToDate == startingTaskIndex) {
            System.out.println(ErrorMessages.NO_TASKS_ARE_RELEVANT_TO_INPUT_DATE);
            return ErrorMessages.NO_TASKS_ARE_RELEVANT_TO_INPUT_DATE;
        }
        System.out.println("____________________________________________________________");
        return sbr.toString();
    }

    /**
     * Prints out all tasks related to the input that is present in the list of tasks
     *
     * @param input represents the taskDescription of Deadline
     * @param tasks represents the list of task
     * @return returns the string that shows all tasks which input is contained in the taskDescription
     */
    public static String printTasksContainingKeyword(String input, List<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        StringBuilder sbr = new StringBuilder("Here are the matching tasks in your list:\n");
        int index = 1;
        for (Task task : tasks) {
            if (task.getDes().contains(input)) {
                System.out.println(index + "." + task);
                sbr.append(index + "." + task + "\n");
                index++;
            }
        }
        if (index == 1) {
            return printsMessageWhenNoTasksFound(input);
        }
        System.out.println("____________________________________________________________");
        return sbr.toString();
    }

    /**
     * A special case where the keyword does not match any task description and returns this print
     * @return returns the string that shows that the input does not match any task description
     */
    private static String printsMessageWhenNoTasksFound(String input) {
        System.out.println("Looks Like there's no task with taskDescription that contains " + "'" + input + "'");
        System.out.println("Try Looking for something else!");
        return "Looks like there's no task with taskDescription that contains '" + input + "'\n"
                + "Try looking for something else!";
    }

    /**
     * Changes the priorityLevel for various task instances
     * @param input represents the taskDescription of Deadline
     * @param tasks represents the list of task
     * @return returns the string that shows the priorityLevel of the task has been changed
     */
    public static String changePriorityForSpecificTask(String input, List<Task> tasks) {
        String[] result = input.trim().split("\\s+");
        if (result.length != 3) {
            return "Correct command format is...";
        }
        int severity = Integer.valueOf(result[2].trim());
        if (severity > 4 || severity < 1) {
            return "Choose only 1 to 4";
        }
        Task task;
        try {
            task = tasks.stream()
                    .skip(Integer.valueOf(result[1].trim()) - 1) //Skip to the task at the given index(1-based index)
                    .findFirst() //Get the first task if present
                    .orElseThrow(() -> new IllegalArgumentException("Task index out of bounds."));
        } catch (IllegalArgumentException e) {
            return "You seme to have reached too high. Try reaching for something else!";
        }
        return printsOutputStringWhenChangingPriorityForTasks(task, severity);
    }

    /**
     * Aids in printing the output string when the user changes priority for specific tasks
     * @param task the task to be modified
     * @param priority the new priority level
     * @return returns the string that shows the priorityLevel of the task has been changed
     */
    private static String printsOutputStringWhenChangingPriorityForTasks(Task task, int priority) {
        String previousPriority = Task.getPriority(task).toString();
        Task.changePriority(task, priority);
        String newPriority = Task.getPriority(task).toString();
        return "Got it. I've changed the priority of this task:\n"
                + task + "\n"
                + "from " + previousPriority + " to " + newPriority + "\n";
    }
}
