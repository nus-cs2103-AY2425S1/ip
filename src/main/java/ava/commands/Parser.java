package ava.commands;

import ava.task.TaskManager;

import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parses the text command and maps it to one of the available commands
     *
     * <br>
     * This command helps Ava interpret text commands.
     * @param command Command entered by user as text
     * @return Command corresponding to user's input.
     */
    public static Command parseCommand(String command){
        if(command.equals("list")){
            return Command.LIST;
        } else if(command.startsWith("mark")){
            return Command.MARK;
        } else if(command.startsWith("unmark")) {
            return Command.UNMARK;
        } else if(command.startsWith("delete")){
            return Command.DELETE;
        } else if (command.startsWith("todo")){
            return Command.TODO;
        } else if (command.startsWith("deadline")){
            return Command.DEADLINE;
        } else if (command.startsWith("event")){
            return Command.EVENT;
        } else if (command.startsWith("find")){
            return Command.FIND;
        } else {
            throw new IllegalArgumentException("Unsupported Command");
        }
    }

    /**
     * Parses the To-Do command and executes it
     *
     * <br>
     * Stores a To-Do task in the task list
     * @param command a To - Do task
     * @param taskManager Task Manager to store the task
     * @return description of the To-Do task
     */
    public static String parseToDo(String command, TaskManager taskManager){
        final int TODO_LENGTH = 5;
        String todo = command.substring(TODO_LENGTH);

        if(todo.isEmpty()){
            System.out.println("The description of a todo cannot be empty.");
            throw new IllegalArgumentException("Empty todo description");
        }

        taskManager.addTask(todo);
        return todo;
    }

    /**
     * Parses the Deadline command and executes it
     *
     * <br>
     * Stores a Deadline task in the task list
     * @param command a Deadline task
     * @param taskManager Task Manager to store the task
     */
    public static String parseDeadline(String command, TaskManager taskManager){
        final int DEADLINE_LENGTH = 9;
        String deadline = command.substring(DEADLINE_LENGTH);
        String[] arguments = deadline.split("/by");
        if (arguments.length != 2) {
            System.out.println("Invalid deadline format. Please use /by to separate the description and time");
            throw new IllegalArgumentException("Invalid deadline format");
        }

        String description = arguments[0].trim();
        if(description.isEmpty()){
            System.out.println("The description of a deadline cannot be empty.");
            throw new IllegalArgumentException("Empty deadline description");
        }

        String time = arguments[1].trim();
        if(time.isEmpty()){
            System.out.println("You need to provide a time for the task.");
            throw new IllegalArgumentException("No time provided");
        }


        try {
            taskManager.addTask(description, time);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use the format yyyy-mm-ddThh:mm:ss");
            throw new IllegalArgumentException("Invalid deadline format");
        }
        return description + " by " + time;
    }

    /**
     * Parses the Event command and executes it
     *
     * <br>
     * Stores an Event task in the task list
     * @param command an Event task
     * @param taskManager Task Manager to store the task
     */
    public static String parseEvent(String command, TaskManager taskManager){
        final int EVENT_LENGTH = 6;
        final int FROM_LENGTH = 5;
        final int TO_LENGTH = 3;

        String event = command.substring(EVENT_LENGTH);
        String[] arguments = event.split("/");

        if (arguments.length != 2) {
            System.out.println("Invalid deadline format. Please use /by to separate the description and time");
            throw new IllegalArgumentException("Invalid deadline format");
        }

        String description = arguments[0].trim();
        if(description.isEmpty()){
            System.out.println("The description of an event cannot be empty.");
            throw new IllegalArgumentException("Empty event description");
        }

        String startTime = arguments[1].substring(FROM_LENGTH).trim();
        if(startTime.isEmpty()){
            System.out.println("You need to provide a start time for the task.");
            throw new IllegalArgumentException("No time provided");
        }

        String endTime = arguments[2].substring(TO_LENGTH).trim();
        if(endTime.isEmpty()){
            System.out.println("You need to provide an endTime for the task.");
            throw new IllegalArgumentException("No time provided");
        }

        try {
            taskManager.addTask(description, startTime, endTime);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use the format yyyy-mm-ddThh:mm:ss");
            throw new IllegalArgumentException("Invalid deadline format");
        }
        return description + " from " + startTime + " to " + endTime;
    }

    /**
     * Parses the mark command and returns the task id
     *
     * @param command the mark command
     * @return the task id of the task to be marked
     */
    public static int parseMark(String command){
        final int MARK_LENGTH = 5;
        int taskId;
        try {
            taskId = Integer.parseInt(command.substring(MARK_LENGTH));
        } catch (NumberFormatException e) {
            System.out.println("I am sorry, but you need to provide me a task id to mark something.");
            throw new IllegalArgumentException("No task id provided");
        }
        return taskId;
    }

    /**
     * Parses the unmark command and returns the task id
     *
     * @param command the unmark command
     * @return the task id of the task to be unmarked
     */
    public static int parseUnmark(String command){
        final int UNMARK_LENGTH = 7;
        int taskId;
        try {
            taskId = Integer.parseInt(command.substring(UNMARK_LENGTH));
        } catch (NumberFormatException e) {
            System.out.println("I am sorry, but you need to provide me a task id to unmark something.");
            throw new IllegalArgumentException("No task id provided");
        }
        return taskId;
    }

    /**
     * Parses the delete command and returns the task id
     *
     * @param command the delete command
     * @return the task id of the task to be deleted
     */
    public static int parseDelete(String command){
        final int DELETE_LENGTH = 7;
        int taskId;
        try {
            taskId = Integer.parseInt(command.substring(DELETE_LENGTH));
        } catch (NumberFormatException e) {
            System.out.println("I am sorry, but you need to provide me a task id to delete something.");
            throw new IllegalArgumentException("No task id provided");
        }
        return taskId;
    }

    public static void parseFind(String command){

    }
}
