package chatbot;

import java.time.LocalDate;

import exceptions.InvalidCommandException;
import exceptions.InvalidNumberException;
import exceptions.NoTasksException;
import exceptions.NonExistentTaskException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

/**
 * Represents a class for Parser to parse user input.
 */

public class Parser {

    /**
     * Responds according to user input and return a bot response.
     *
     * @param response User response.
     * @param taskList Tasklist to store tasks.
     * @return Bot response.
     * @throws InvalidCommandException If invalid command is given.
     * @throws InvalidNumberException If invalid number is given.
     * @throws NoTasksException If tasklist is empty when trying to list or operate on specific tasks.
     * @throws NonExistentTaskException If specified task does not exist.
     */
    public static String answer(String response, TaskList taskList)
            throws InvalidCommandException, InvalidNumberException, NoTasksException, NonExistentTaskException {
        String command = response.contains(" ")
                ? response.substring(0, response.indexOf(' '))
                : response;
        String name;
        String startTime;
        String endTime;
        int index;
        try {
            switch (command) {
            case "todo":
                name = response.substring(response.indexOf(' ') + 1);
                return taskList.addTask(new Todo(name), false);
            case "deadline":
                name = response.substring(response.indexOf(' ') + 1, response.indexOf('/') - 1);
                endTime = response.substring(response.indexOf("/by") + 4);
                return taskList.addTask(new Deadline(name, LocalDate.parse(endTime)), false);
            case "event":
                name = response.substring(response.indexOf(' ') + 1, response.indexOf('/') - 1);
                startTime = response.substring(response.indexOf("/from") + 6, response.indexOf("/to") - 1);
                endTime = response.substring(response.indexOf("/to") + 4);
                return taskList.addTask(new Event(name, LocalDate.parse(startTime),
                        LocalDate.parse(endTime)), false);
            case "mark":
                index = Integer.parseInt(response.substring(response.indexOf(' ') + 1)) - 1;
                return taskList.markTask(index);
            case "unmark":
                index = Integer.parseInt(response.substring(response.indexOf(' ') + 1)) - 1;
                return taskList.unmarkTask(index);
            case "delete":
                index = Integer.parseInt(response.substring(response.indexOf(' ') + 1)) - 1;
                return taskList.deleteTask(index);
            case "list":
                return taskList.listOut();
            case "find":
                String keyword = response.substring(response.indexOf(' ') + 1);
                TaskList filtered = new TaskList(taskList.filterByWord(keyword));
                return filtered.listOut();
            case "help":
                return Parser.showCommands();
            default:
                return "type 'help' to see list of commands";
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        } catch (IndexOutOfBoundsException e) {
            throw new NonExistentTaskException();
        }
    }

    /**
     * Returns a help list.
     *
     * @return a string of available commands.
     */
    public static String showCommands() {
        return "todo {taskName}: add a new Todo\n"
                + "deadline {taskName} /by {deadline}: add a new Deadline\n"
                + "event {taskName} /from {startDate in YYYY-MM-DD} /to {endDate in YYYY-MM-DD}: add a new Event\n"
                + "mark {taskIndex}: mark that task as Done\n"
                + "unmark {taskIndex}: mark that task as not Done\n"
                + "delete {taskIndex}: delete that task\n"
                + "list: list out all tasks\n"
                + "find {keyword}: filter list by keyword\n";
    }
}
