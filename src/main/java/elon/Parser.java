package elon;

import elon.command.AddCommand;
import elon.command.Command;
import elon.command.DeleteCommand;
import elon.command.ExitCommand;
import elon.command.FindCommand;
import elon.command.ListCommand;
import elon.command.MarkCommand;
import elon.command.UnmarkCommand;
import elon.task.Deadline;
import elon.task.Event;
import elon.task.ToDo;
import java.time.LocalDate;
import java.util.Arrays;



/**
 * Parses user input commands and interacts with the UI, TaskList, and Storage.
 */
public class Parser {
    public static Command parse(String[] inputArr) throws ElonException{
        String commandPhrase = inputArr[0];
        switch (commandPhrase) {
            case "list":
                return new ListCommand();
            case "mark":
                int indexToMark = Integer.parseInt(inputArr[1]) - 1;
                return new MarkCommand(indexToMark);
            case "unmark":
                int indexToUnmark = Integer.parseInt(inputArr[1]) - 1;
                return new UnmarkCommand(indexToUnmark);
            case "delete":
                int indexToDelete = Integer.parseInt(inputArr[1]) - 1;
                return new DeleteCommand(indexToDelete);
            case "bye":
                return new ExitCommand();
            case "find":
                String searchWord = String.join(" ", Arrays.copyOfRange(inputArr, 1, inputArr.length));
                return new FindCommand(searchWord);
            case "todo":
                if (inputArr.length <= 1) {
                    throw new ElonException("Error. Description for ToDo task not specified.");
                }
                String todoTask = "";
                for (int i = 1; i < inputArr.length; i++) {
                    todoTask += inputArr[i] + " ";
                }
                todoTask = todoTask.strip();
                ToDo todo = new ToDo(todoTask, false);
                return new AddCommand(todo);
            case "deadline":
                if (inputArr.length <= 1) {
                    throw new ElonException("Error. Description and By date for Deadline task not specified.");
                }
                int j = 1;
                String deadlineTask = "";
                while (!inputArr[j].equals("/by")) {
                    deadlineTask += inputArr[j] + " ";
                    j++;
                }
                deadlineTask = deadlineTask.strip();
                String by = "";
                if (inputArr.length <= j+1) {
                    throw new ElonException("Error. By date for Deadline task not specified.");
                }
                for (int k = j+1; k < inputArr.length; k++) {
                    by += inputArr[k] + " ";
                }
                by = by.strip();
                LocalDate byDate = LocalDate.parse(by);
                Deadline deadline = new Deadline(deadlineTask, false, byDate);
                return new AddCommand(deadline);
            case "event: ":
                if (inputArr.length <= 1) {
                    throw new ElonException("Error. Description, From and To date for Event task not specified.");
                }
                int x = 1;
                String eventTask = "";
                while (!inputArr[x].equals("/from")) {
                    eventTask += inputArr[x] + " ";
                    x++;
                }
                eventTask = eventTask.strip();
                x++;
                if (inputArr.length <= x) {
                    throw new ElonException("Error. From date for Event task not specified.");
                }
                String from = "";
                while (!inputArr[x].equals("/to")) {
                    from += inputArr[x] + " ";
                    x++;
                }
                from = from.strip();
                LocalDate fromDate = LocalDate.parse(from);
                x++;
                if (inputArr.length <= x) {
                    throw new ElonException("Error. To date for Event task not specified.");
                }
                String to = "";
                for (int y = x; y < inputArr.length; y++) {
                    to += inputArr[y] + " ";
                }
                to = to.strip();
                LocalDate toDate = LocalDate.parse(to);
                Event event = new Event(eventTask, false, fromDate, toDate);
                return new AddCommand(event);
            default:
                throw new ElonException("Invalid Command: " + commandPhrase);
        }
    }
}
