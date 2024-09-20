package yapper;

import java.time.format.DateTimeParseException;

/**
 * Represents a Parser, which takes in a command in String, parses it, and calls the respective command.
 */
public class Parser {
    private final TaskList taskList;

    /**
     * Creates an instance of Parser.
     *
     * @param taskList Tasklist to call after parsing commands.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Reads the command given by user.
     *
     * @param command Command given by user.
     */
    public String readCommand(String command) {
        try {
            if (command.equals("list")) {
                return this.taskList.returnList();
            } else if (command.startsWith("mark")) {
                return this.taskList.mark(command);
            } else if (command.startsWith("unmark")) {
                return this.taskList.unmark(command);
            } else if (command.startsWith("todo")) {
                return this.taskList.addToDo(command);
            } else if (command.startsWith("deadline")) {
                return this.taskList.addDeadline(command);
            } else if (command.startsWith("event")) {
                return this.taskList.addEvent(command);
            } else if (command.startsWith("delete")) {
                return this.taskList.deleteTask(command);
            } else if (command.startsWith("find")) {
                return this.taskList.findTask(command);
            } else if (command.startsWith("postpone")) {
                return this.taskList.postpone(command);
            } else {
                throw new YapperException("Yapper don't know this command :(");
            }
        } catch (YapperException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        } catch (NumberFormatException | DateTimeParseException e) {
            String toReturn = "Invalid Date Time Format!" + "\n" + "Correct Format is YYYY/MM/DD (HHMM)";
            System.out.println(toReturn);
            return toReturn;
        }

    }
}
