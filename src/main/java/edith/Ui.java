package edith;

import java.util.Objects;

import edith.exception.InvalidCommandException;
import edith.exception.InvalidTaskNumberException;
import edith.exception.MissingDeadlineException;
import edith.exception.MissingEventDurationException;
import edith.exception.MissingKeywordException;
import edith.exception.MissingTaskNameException;
import edith.exception.MissingTaskNumberException;
import edith.task.DeadlineTask;
import edith.task.EventTask;
import edith.task.ToDoTask;

/**
 * This class handles all user inputs.
 */
public class Ui {
    private static final String HORIZONTAL = "______________________________________________________"
            + "_________________________________________________";
    private static final String GREETING = "heyyy im edith!\nwhat can I do for you?";
    private static final String FAREWELL = "bye!! see you soon love <3";
    private static final String LINEBREAK = "\n";

    /**
     * Handles user input accordingly. List of commands: list, mark, unmark, todo, deadline, event, delete. Prints
     * error message if user input is an invalid command.
     * @param userInput User input.
     * @param toDoList User's to-do list.
     * @return Chatbot response.
     */
    public static String handleUserInput(String userInput, ToDoList toDoList) {
        String command = Parser.getCommand(userInput);

        if (Objects.equals(command, "list")) { // check if user wants the todo list
            System.out.println("tasks in your todo list!" + LINEBREAK + toDoList);
            return "tasks in your todo list!" + LINEBREAK + toDoList;
        } else if (Objects.equals(command, "mark") || Objects.equals(command, "unmark")) {
            return changeTaskStatus(userInput, toDoList);
        } else if (Objects.equals(command, "todo") || Objects.equals(command, "deadline")
                || Objects.equals(command, "event")) {
            return addTask(userInput, toDoList);
        } else if (Objects.equals(command, "delete")) {
            return delete(userInput, toDoList);
        } else if (Objects.equals(command, "find")) {
            return find(userInput, toDoList);
        } else if (Objects.equals(command, "bye")) {
            return bidFarewell();
        } else {
            try {
                otherCommand();
            } catch (InvalidCommandException e) {
                showError(e.getMessage());
                return e.getMessage();
            }
        }
        return null;
    }

    /**
     * Greets user when Edith first starts up.
     * @return Greeting when Edith first starts up.
     */
    public static String greetUser() {
        System.out.println(HORIZONTAL + LINEBREAK + GREETING + LINEBREAK + HORIZONTAL);
        return GREETING;
    }

    /**
     * Bids farewell when user inputs "bye".
     * @return Farewell message when user inputs "bye".
     */
    public static String bidFarewell() {
        showLine();
        System.out.println(FAREWELL + LINEBREAK);
        showLine();
        return FAREWELL;
    }

    /**
     * Changes the task status of a task. Prints error message when task number is missing in user input or task number
     * is invalid.
     * @param userInput User input.
     * @param toDoList User's to-do list.
     * @return Chatbot's reply indicating success or error.
     */
    public static String changeTaskStatus(String userInput, ToDoList toDoList) {
        String returnStatement;
        try {
            int taskNumber = Parser.getTaskNumber(userInput);
            String command = Parser.getCommand(userInput);
            assert command.equals("mark") || command.equals("unmark");
            if (Objects.equals(command, "mark")) { // check if user wants to mark a task
                toDoList.markTaskAsCompleted(taskNumber); // may throw Edith.InvalidTaskNumberException
                System.out.println(" " + "yay! i've marked this task as done #productive:" + LINEBREAK
                        + "   " + toDoList.getTask(taskNumber) + LINEBREAK);
                returnStatement = "yay! i've marked this task as done #productive:" + LINEBREAK
                        + "   " + toDoList.getTask(taskNumber) + LINEBREAK;
            } else { // unmarking a task
                toDoList.unmarkTaskAsCompleted(taskNumber); // may throw Edith.InvalidTaskNumberException
                System.out.println(" " + "aw, i've marked this task as undone:" + LINEBREAK
                        + "   " + toDoList.getTask(taskNumber) + LINEBREAK);
                returnStatement = "aw, i've marked this task as undone:" + LINEBREAK
                        + "   " + toDoList.getTask(taskNumber) + LINEBREAK;
            }

        } catch (MissingTaskNumberException | InvalidTaskNumberException e) { // if there is a missing task number
            showError(e.getMessage());
            return e.getMessage();
        }

        return returnStatement;
    }

    /**
     * Adds task to user's to-do list. Prints error message if user input does not specify a task name.
     * @param userInput User input.
     * @param toDoList User's to-do list.
     * @return Chatbot's reply indicating success or error.
     */
    public static String addTask(String userInput, ToDoList toDoList) {
        String taskType = Parser.getCommand(userInput);
        assert taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event");
        try {
            String taskDetails = Parser.getTaskDetails(userInput, taskType);
            if (Objects.equals(taskType, "todo")) {
                return addToDoTask(taskDetails, toDoList);
            }
            if (Objects.equals(taskType, "deadline")) {
                return addDeadlineTask(taskDetails, taskType, toDoList);
            }
            return addEventTask(taskDetails, taskType, toDoList);
        } catch (MissingTaskNameException e) {
            showError(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Adds task of type ToDoTask to user's to-do list.
     * @param taskName Task's name.
     * @param toDoList User's to-do list.
     * @return Chatbot's reply indicating success or error.
     */
    public static String addToDoTask(String taskName, ToDoList toDoList) {
        ToDoTask task = new ToDoTask(taskName);
        toDoList.add(task);
        System.out.println(" nice! i've added this task:" + LINEBREAK + " " + task + LINEBREAK
                + " there are currently " + toDoList.getNumberOfTasks() + " tasks in your todo list." + LINEBREAK);
        return "nice! i've added this task:" + LINEBREAK + " " + task + LINEBREAK
                + "there are currently " + toDoList.getNumberOfTasks() + " tasks in your todo list.";
    }

    /**
     * Adds task of type DeadlineTask to user's to-do list.
     * @param taskDetails Task's details including task name and deadline.
     * @param taskType Task's type: "deadline".
     * @param toDoList User's to-do list.
     * @return Chatbot's reply indicating success or error.
     */
    public static String addDeadlineTask(String taskDetails, String taskType, ToDoList toDoList) {
        try {
            String taskName = Parser.getTaskName(taskDetails, taskType);
            String taskDeadline = Parser.getTaskDeadline(taskDetails);
            DeadlineTask task = new DeadlineTask(taskName, taskDeadline);
            toDoList.add(task);
            System.out.println(" " + "nice! i've added this task:" + LINEBREAK + " " + task + LINEBREAK
                    + " there are currently " + toDoList.getNumberOfTasks() + " tasks in your todo list." + LINEBREAK);
            return "nice! i've added this task:" + LINEBREAK + " " + task + LINEBREAK
                    + "there are currently " + toDoList.getNumberOfTasks() + " tasks in your todo list.";
        } catch (MissingDeadlineException e) {
            showError(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Adds task of type EventTask to user's to-do list.
     * @param taskDetails Task's details including task name and duration.
     * @param taskType Task's type: "duration".
     * @param toDoList User's to-do list.
     * @return Chatbot's reply indicating success or error.
     */
    public static String addEventTask(String taskDetails, String taskType, ToDoList toDoList) {
        try {
            String taskName = Parser.getTaskName(taskDetails, taskType);
            String taskDuration = Parser.getTaskDuration(taskDetails);
            String taskStart = Parser.getTaskStart(taskDuration);
            String taskEnd = Parser.getTaskEnd(taskDuration);
            EventTask task = new EventTask(taskName, taskStart, taskEnd);
            toDoList.add(task);
            System.out.println(" " + "nice! i've added this task:" + LINEBREAK + " " + task + LINEBREAK
                    + " there are currently " + toDoList.getNumberOfTasks() + " tasks in your todo list." + LINEBREAK);
            return "nice! i've added this task:" + LINEBREAK + " " + task + LINEBREAK
                    + "there are currently " + toDoList.getNumberOfTasks() + " tasks in your todo list.";
        } catch (MissingEventDurationException e) {
            showError(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Handles input of invalid commands.
     * @throws InvalidCommandException When user input cannot be recognised.
     */
    public static void otherCommand() throws InvalidCommandException {
        throw new InvalidCommandException();
    }

    /**
     * Deletes task from user's to-do list.
     * @param userInput User input.
     * @param toDoList User's to-do list.
     * @return Chatbot's reply indicating success or error.
     */
    public static String delete(String userInput, ToDoList toDoList) {
        try {
            int taskNumber = Parser.getTaskNumber(userInput);
            System.out.println(" okay! i've deleted this task:" + LINEBREAK
                    + "   " + toDoList.getTask(taskNumber) + LINEBREAK
                    + " you currently have " + (toDoList.getNumberOfTasks() - 1) + " tasks in your todo list"
                    + LINEBREAK);
            String returnStatement = "okay! i've deleted this task:" + LINEBREAK + "   " + toDoList.getTask(taskNumber)
                    + LINEBREAK + "you currently have " + (toDoList.getNumberOfTasks() - 1)
                    + " tasks in your todo list";
            toDoList.delete(taskNumber);
            return returnStatement;
        } catch (MissingTaskNumberException | InvalidTaskNumberException e) {
            showError(e.getMessage());
            return e.getMessage();
        }
    }


    /**
     * Finds tasks that match keyword(s) provided by user. Prints error if user does not input any keywords.
     * @param userInput User input.
     * @param toDoList List of user's current tasks.
     * @return Chatbot's reply indicating success or error.
     */
    public static String find(String userInput, ToDoList toDoList) {
        try {
            String keyword = Parser.getKeyword(userInput);
            ToDoList tasksMatchingKeyword = toDoList.getListOfTasksMatchingKeyword(keyword);
            if (tasksMatchingKeyword.getNumberOfTasks() == 0) {
                System.out.println(" there are no tasks matching the description you gave: " + keyword + " :(\n");
                return "there are no tasks matching the description you gave: \" + keyword";
            } else {
                System.out.println(" here is a list of tasks matching the description you gave: " + keyword + LINEBREAK
                        + tasksMatchingKeyword + LINEBREAK);
                return "here is a list of tasks matching the description you gave: " + keyword
                        + LINEBREAK + tasksMatchingKeyword;
            }
        } catch (MissingKeywordException e) {
            showError(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Prints a horizontal line with a linebreak at the end.
     */
    public static void showLine() {
        System.out.println(HORIZONTAL + LINEBREAK);
    }

    /**
     * Prints error message from exception thrown.
     * @param errorMessage Error message from exception thrown.
     */
    public static void showError(String errorMessage) {
        System.out.println(errorMessage + "\n");
    }
}
