package util;

import exceptions.PrinceException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

/**
 * Parser handles user input and executes commands based on them.
 */
public class Parser {

    /**
     * Returns a boolean that is used to determine if program terminates
     * Parses user input and executes related commands.
     * @param input    Input by the user.
     * @param taskList List of tasks.
     * @param storage  Storage that was inialised in main.
     * @param ui       Ui that was initialised in main.
     * @return Boolean.
     */
    public static boolean parse(String input, TaskList taskList, Storage storage, Ui ui) {
        try {
            if (input.equals("bye")) {
                ui.showBye();
                return true;
            } else if (input.equals("list")) {
                ui.showTaskList(taskList);
            } else if (input.contains("unmark")) {
                unmark(input, taskList, storage);
                ui.showLine();
            } else if (input.contains("mark")) {
                mark(input, taskList, storage);
                ui.showLine();
            } else if (input.contains("delete")) {
                delete(input, taskList, storage);
                ui.showLine();
            } else if (input.equals("todo") || input.equals("deadline")
                    || input.equals("event")) {
                throw new PrinceException("    Please describe your '" + input
                        + "' task in more detail!");
            } else if (input.contains("todo") || input.contains("deadline")
                    || input.contains("event")) {
                if (input.contains("todo")) {
                    handleTodo(input, taskList, storage);
                }
                if (input.contains("deadline")) {
                    handleDeadline(input, taskList, storage);
                }
                if (input.contains("event")) {
                    handleEvent(input, taskList, storage);
                }
                ui.showLine();
            } else {
                throw new PrinceException("    Sorry, I am not sure what '" + input
                        + "' means. Please try again!");
            }
        } catch (PrinceException err) {
            System.out.println(err.toString());
            ui.showLine();
        }
        return false;
    }

    /**
     * Returns the string "todo".
     * @param input Input of the user.
     * @return "todo".
     */
    private static String getTodo(String input) {
        String[] arr = input.split("todo");
        String todo = arr[1].trim();
        return todo;
    }

    /**
     * Returns the string "deadline".
     * @param input Input of the user.
     * @return "deadline".
     */
    private static String getDeadline(String input) {
        String[] arr = input.split("deadline|/by");
        String deadline = arr[1].trim();
        return deadline;
    }

    /**
     * Returns a String representing the date of the deadline.
     * @parsm input Input of the user.
     * @return Date of deadline.
     */
    private static String getBy(String input) {
        String[] arr = input.split("/by");
        String by = arr[1].trim();
        return by;
    }

    /**
     * Returns the string "event"
     * @param input Input of the user.
     * @return "event".
     */
    private static String getEvent(String input) {
        String[] arr = input.split("event|/from|/to");
        String event = arr[1].trim();
        return event;
    }

    /**
     * Returns a String representing the start of the event.
     * @param input Input of the user.
     * @return Date of start of event.
     */
    private static String getFrom(String input) {
        String[] arr = input.split("/from|/to");
        String from = arr[1].trim();
        return from;
    }

    /**
     * Returns a String representing the end of the event.
     * @param input Input of the user.
     * @return Date of end of event.
     */
    private static String getTo(String input) {
        String[] arr = input.split("/from|/to");
        String to = arr[2].trim();
        return to;
    }

    /**
     * Handles everything to do with creating a Todo task.
     * Creates a Todo task.
     * Adds it to the list of tasks.
     * Saves task to storage.
     * Display output for user.
     * @param input    Input by the user.
     * @param taskList List of tasks.
     * @param storage  Storage.
     */
    private static void handleTodo(String input, TaskList taskList, Storage storage) {
        System.out.println("    Got it. I've added this task:");
        String desc = getTodo(input);
        Todo todo = new Todo(desc);
        taskList.add(todo);
        System.out.println("      " + todo.toString());
        System.out.println("    Now you have " + taskList.size()
                + " tasks in the list.");

        storage.saveToFile(todo, taskList);
    }

    /**
     * Handles everything to do with creating a Deadline task.
     * Creates a Deadline task.
     * Adds it to the list of tasks.
     * Saves task to storage.
     * Display output for user.
     * @param input    Input by the user.
     * @param taskList List of tasks.
     * @param storage  Storage.
     */
    private static void handleDeadline(String input, TaskList taskList, Storage storage) {
        System.out.println("    Got it. I've added this task:");
        String desc = getDeadline(input);
        String by = getBy(input);
        Deadline deadlineTask = new Deadline(desc, by);
        taskList.add(deadlineTask);
        System.out.println("      " + deadlineTask.toString());
        System.out.println("    Now you have " + taskList.size()
                + " tasks in the list.");
        storage.saveToFile(deadlineTask, taskList);
    }

    /**
     * Handles everything to do with creating an Event task.
     * Creates an Event task.
     * Adds it to the list of tasks.
     * Saves task to storage.
     * Display output for user.
     * @param input    Input by the user.
     * @param taskList List of tasks.
     * @param storage  Storage.
     */
    private static void handleEvent(String input, TaskList taskList, Storage storage) {
        System.out.println("    Got it. I've added this task:");
        String desc = getEvent(input);
        String from = getFrom(input);
        String to = getTo(input);
        Event event = new Event(desc, from, to);
        taskList.add(event);
        System.out.println("      " + event.toString());
        System.out.println("    Now you have " + taskList.size()
                + " tasks in the list.");
        storage.saveToFile(event, taskList);
    }

    /**
     * Marks task as incomplete.
     * Update storage with correct boolean status.
     * Displays input for user.
     * @param input    Input by the user.
     * @param taskList List of tasks.
     * @param storage  Storage.
     */
    private static void unmark(String input, TaskList taskList, Storage storage) {
        // extra check to make sure the start of input is "unmark"
        String checkUnmark = input.substring(0, 6);
        if (checkUnmark.equals("unmark")) {
            int index = getIndex(input);
            Task task = taskList.get(index);
            task.markAsNotDone();
            storage.updateFile(input, taskList);
            System.out.println("      " + task.toString());
        }
    }

    /**
     * Marks task as complete.
     * Update storage with correct boolean status.
     * Displays input for user.
     * @param input    Input by the user.
     * @param taskList List of tasks.
     * @param storage  Storage.
     */
    private static void mark(String input, TaskList taskList, Storage storage) {
        // extra check to make sure the start of input is "mark"
        String checkMark = input.substring(0, 4);
        if (checkMark.equals("mark")) {
            int index = getIndex(input);
            Task task = taskList.get(index);
            task.markAsDone();
            storage.updateFile(input, taskList);
            System.out.println("      " + task.toString());
        }
    }

    /**
     * Deletes tasks from taskList and storage.
     * Displays input for user.
     * @param input    Input by the user.
     * @param taskList List of tasks.
     * @param storage  Storage.
     */
    private static void delete(String input, TaskList taskList, Storage storage) {

        // extra check to make sure the start of input is "delete"
        String checkDelete = input.substring(0, 6);
        if (checkDelete.equals("delete")) {
            // remove the task from storage.txt
            storage.deleteFromFile(input, taskList);
            int index = getIndex(input);
            Task task = taskList.get(index);
            task.delete(); // prints "the noted i removed this task" string
            taskList.remove(index);
            System.out.println("      " + task.toString());
            System.out.println("    Now you have " + taskList.size()
                    + " tasks in the list.");
        }
    }

    /**
     * Returns an integer representing the index of a task in an array.
     * @param input Input by the user.
     * @return Index of the task.
     */
    private static int getIndex(String input) {
        if (input.contains("unmark")) {
            // get character value of index in the input
            String indexAsString = input.substring(7);
            // convert to arr index
            int index = Integer.valueOf(indexAsString) - 1;
            return index;
        } else if (input.contains("mark")) {
            // get character value of index in input
            String indexAsString = input.substring(5);
            // convert to arr index
            int index = Integer.valueOf(indexAsString) - 1;
            return index;
        } else if (input.contains("delete")) {
            // get character value of index in the input
            String indexAsString = input.substring(7);
            // convert to arr index
            int index = Integer.valueOf(indexAsString) - 1;
            return index;
        } else {
            // should not reach here
            return -1;
        }
    }

}
