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
     */
    public static void parseToDo(String command, TaskManager taskManager){
        final int TODO_LENGTH = 5;
        String todo = command.substring(TODO_LENGTH);
        taskManager.addTask(todo);
    }

    /**
     * Parses the Deadline command and executes it
     *
     * <br>
     * Stores a Deadline task in the task list
     * @param command a Deadline task
     * @param taskManager Task Manager to store the task
     */
    public static void parseDeadline(String command, TaskManager taskManager){
        final int DEADLINE_LENGTH = 9;
        String deadline = command.substring(DEADLINE_LENGTH);
        String[] arguments = deadline.split("/by");
        String description = arguments[0].trim();
        String time = arguments[1].trim();
        try {
            taskManager.addTask(description, time);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use the format yyyy-mm-dd");
        }
    }

    /**
     * Parses the Event command and executes it
     *
     * <br>
     * Stores an Event task in the task list
     * @param command a Deadline task
     * @param taskManager Task Manager to store the task
     */
    public static void parseEvent(String command, TaskManager taskManager){
        final int EVENT_LENGTH = 6;
        String event = command.substring(EVENT_LENGTH);



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
