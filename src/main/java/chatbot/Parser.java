package chatbot;

import java.time.LocalDate;

import exceptions.InvalidCommandException;
import exceptions.InvalidNumberException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

public class Parser {

    public static String answer(String response, TaskList taskList)
            throws InvalidCommandException, InvalidNumberException {
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
                return taskList.add(new Todo(name), false);
            case "deadline":
                name = response.substring(response.indexOf(' ') + 1, response.indexOf('/') - 1);
                endTime = response.substring(response.indexOf("/by") + 4);
                return taskList.add(new Deadline(name, LocalDate.parse(endTime)), false);
            case "event":
                name = response.substring(response.indexOf(' ') + 1, response.indexOf('/') - 1);
                startTime = response.substring(response.indexOf("/from") + 6, response.indexOf("/to") - 1);
                endTime = response.substring(response.indexOf("/to") + 4);
                return taskList.add(new Event(name, LocalDate.parse(startTime),
                        LocalDate.parse(endTime)), false);
            case "mark":
                index = Integer.parseInt(response.substring(response.indexOf(' ') + 1,
                        response.indexOf(' ') + 2)) - 1;
                return taskList.markTask(index);
            case "unmark":
                index = Integer.parseInt(response.substring(response.indexOf(' ') + 1,
                        response.indexOf(' ') + 2)) - 1;
                return taskList.unmarkTask(index);
            case "delete":
                index = Integer.parseInt(response.substring(response.indexOf(' ') + 1,
                        response.indexOf(' ') + 2)) - 1;
                return taskList.delete(index);
            case "list":
                return taskList.listOut();
            case "find":
                String keyword = response.substring(response.indexOf(' ') + 1);
                TaskList filtered = new TaskList(taskList.filterByWord(keyword));
                return filtered.listOut();
            default:
                throw new InvalidCommandException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
    }
}
