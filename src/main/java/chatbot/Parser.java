package chatbot;

import java.time.LocalDate;

import exceptions.InvalidCommandException;
import exceptions.InvalidNumberException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

public class Parser {

    public static void answer(String response, TaskList taskList)
            throws InvalidCommandException, InvalidNumberException {
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
        case "find":
            try {
                String keyword = response.substring(response.indexOf(' ') + 1);
                TaskList filtered = new TaskList(taskList.filterByWord(keyword));
                filtered.listOut();
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidCommandException();
            }
            break;
        default:
            throw new InvalidCommandException();
        }
    }
}
