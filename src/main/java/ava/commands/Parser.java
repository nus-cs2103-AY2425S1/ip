package ava.commands;

import ava.task.TaskManager;

import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parses the text command and maps it to one of the available commands
     *
     *
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
        String description = arguments[0].trim();
        String time = arguments[1].trim();
        try {
            taskManager.addTask(description, time);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use the format yyyy-mm-ddThh:mm:ss");
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
        String description = arguments[0].trim();
        String startTime = arguments[1].substring(FROM_LENGTH).trim();
        String endTime = arguments[2].substring(TO_LENGTH).trim();

        try {
            taskManager.addTask(description, startTime, endTime);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use the format yyyy-mm-ddThh:mm:ss");
        }
        return description + " from " + startTime + " to " + endTime;
    }
    public static void parseMark(String command){

    }

    public static void parseUnmark(String command){

    }

    public static void parseDelete(String command){

    }

    public static void parseFind(String command){

    }
}
