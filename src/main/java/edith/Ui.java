package edith;

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
        Parser.Command command = Parser.getCommand(userInput);
        switch (command) {
        case LIST:
            return printList(toDoList);
        case MARK:
        case UNMARK:
            return changeTaskStatus(userInput, toDoList);
        case TODO:
        case DEADLINE:
        case EVENT:
            return addTask(userInput, toDoList);
        case DELETE:
            return delete(userInput, toDoList);
        case FIND:
            return find(userInput, toDoList);
        case BYE:
            return bidFarewell();
        case INVALID:
            try {
                otherCommand();
            } catch (InvalidCommandException e) {
                showError(e.getMessage());
                return e.getMessage();
            }
            break;
        default:
        }
        return userInput;
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
     * Prints and returns to do list.
     * @param toDoList To-do list.
     * @return To-do list.
     */
    public static String printList(ToDoList toDoList) {
        System.out.println("tasks in your todo list!" + LINEBREAK + toDoList);
        return "tasks in your todo list!" + LINEBREAK + toDoList;
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
            Parser.Command command = Parser.getCommand(userInput);
            assert command.equals(Parser.Command.MARK) || command.equals(Parser.Command.UNMARK);
            if (command.equals(Parser.Command.MARK)) { // check if user wants to mark a task
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
        Parser.Command taskType = Parser.getCommand(userInput);
        assert taskType.equals(Parser.Command.TODO) || taskType.equals(Parser.Command.DEADLINE)
                || taskType.equals(Parser.Command.EVENT);
        try {
            String taskDetails = Parser.getTaskDetails(userInput, taskType);
            if (taskType.equals(Parser.Command.TODO)) {
                return addToDoTask(taskDetails, toDoList);
            }
            if (taskType.equals(Parser.Command.DEADLINE)) {
                return addDeadlineTask(taskDetails, toDoList);
            }
            return addEventTask(taskDetails, toDoList);
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
     * @param toDoList User's to-do list.
     * @return Chatbot's reply indicating success or error.
     */
    public static String addDeadlineTask(String taskDetails, ToDoList toDoList) {
        try {
            String taskName = Parser.getTaskName(taskDetails, "deadline");
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
     * @param toDoList User's to-do list.
     * @return Chatbot's reply indicating success or error.
     */
    public static String addEventTask(String taskDetails, ToDoList toDoList) {
        try {
            String taskName = Parser.getTaskName(taskDetails, "event");
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
