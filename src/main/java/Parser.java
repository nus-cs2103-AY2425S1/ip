import exceptions.InvalidCommandException;
import exceptions.InvalidNumberException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

import java.time.LocalDate;

public class Parser {

    /**
     * Processes the user input
     *
     * @param response Response to be processed.
     * @param taskList Task List to be tracked.
     * @throws InvalidCommandException If invalid command is given.
     * @throws InvalidNumberException If requested mark/unmark command is out of bounds.
     */
    public static void answer(String response, TaskList taskList) throws InvalidCommandException, InvalidNumberException {
        String command = response.contains(" ")
                ? response.substring(0, response.indexOf(' '))
                : response;
        String name;
        String startTime;
        String endTime;
        int index;
        switch (command) {
            case "todo":
                try {
                    name = response.substring(response.indexOf(' ') + 1);
                    taskList.add(new Todo(name), false);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                }
                break;
            case "deadline":
                try {
                    name = response.substring(response.indexOf(' ') + 1, response.indexOf('/') - 1);
                    endTime = response.substring(response.indexOf("/by") + 4);
                    taskList.add(new Deadline(name, LocalDate.parse(endTime)), false);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                }
                break;
            case "event":
                try {
                    name = response.substring(response.indexOf(' ') + 1, response.indexOf('/') - 1);
                    startTime = response.substring(response.indexOf("/from") + 6, response.indexOf("/to") - 1);
                    endTime = response.substring(response.indexOf("/to") + 4);
                    taskList.add(new Event(name, LocalDate.parse(startTime),
                            LocalDate.parse(endTime)), false);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                }
                break;
            case "mark":
                try {
                    index = Integer.parseInt(response.substring(response.indexOf(' ') + 1,
                            response.indexOf(' ') + 2)) - 1;
                    taskList.markTask(index);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                } catch (NumberFormatException e) {
                    throw new InvalidNumberException();
                }
                break;
            case "unmark":
                try {
                    index = Integer.parseInt(response.substring(response.indexOf(' ') + 1,
                            response.indexOf(' ') + 2)) - 1;
                    taskList.unmarkTask(index);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                } catch (NumberFormatException e) {
                    throw new InvalidNumberException();
                }
                break;
            case "delete":
                try {
                    index = Integer.parseInt(response.substring(response.indexOf(' ') + 1,
                            response.indexOf(' ') + 2)) - 1;
                    taskList.delete(index);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                } catch (NumberFormatException e) {
                    throw new InvalidNumberException();
                }
                break;
            case "list":
                taskList.listOut();
                break;
            default:
                throw new InvalidCommandException();
        }
    }
}
