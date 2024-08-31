package orion.chatbot;

import orion.commands.AddTaskCommand;
import orion.commands.Command;
import orion.commands.DeleteTaskCommand;
import orion.commands.ExitCommand;
import orion.commands.FindCommand;
import orion.commands.MarkTaskCommand;
import orion.commands.PrintTasksCommand;
import orion.commands.UnmarkTaskCommand;

import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;

import orion.tasks.Deadline;
import orion.tasks.Event;
import orion.tasks.Todo;

import java.util.Arrays;

public class Parser {

    public Parser() {

    }

    private static String removeFirstWordFromString(String str) {
        return str.split(" ", 2)[1];
    }

    protected static Command parse(String input) throws OrionException {
        String[] inputArray = input.split(" ");
        String command = inputArray[0].toLowerCase();
        // For use by deadline and event commands
        String[] parsed = input.split("/");
        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new PrintTasksCommand();
            case "mark":
                if (inputArray.length != 2) {
                    throw new OrionInputException("Correct syntax: mark <task number>");
                } else {
                    try {
                        int taskNo = Integer.parseInt(inputArray[1]);
                        return new MarkTaskCommand(taskNo);
                    } catch (NumberFormatException e) {
                        throw new OrionInputException("Correct syntax: mark <task number>");
                    }
                }
            case "unmark":
                if (inputArray.length != 2) {
                    throw new OrionInputException("Correct syntax: unmark <task number>");
                } else {
                    try {
                        int taskNo = Integer.parseInt(inputArray[1]);
                        return new UnmarkTaskCommand(taskNo);
                    } catch (NumberFormatException e) {
                        throw new OrionInputException("Correct syntax: unmark <task number>");
                    }
                }
            case "todo":
                if (inputArray.length < 2) {
                    throw new OrionInputException("Correct syntax: todo <task>");
                } else {
                    Todo todo = new Todo(Parser.removeFirstWordFromString(input).trim());
                    return new AddTaskCommand(todo);
                }
            case "deadline":
                if (parsed.length != 2 || !parsed[1].matches("^by.*$")) {
                    throw new OrionInputException("Correct syntax: deadline <task> /by <yyyy-mm-dd>");
                } else {
                    // Removes "deadline" and "by" keywords from input
                    String[] mapped = Arrays.stream(parsed)
                            .map(Parser::removeFirstWordFromString)
                            .toArray(String[]::new);
                    Deadline deadline = new Deadline(mapped[0].trim(), mapped[1].trim());
                    return new AddTaskCommand(deadline);
                }
            case "event":
                if (parsed.length != 3 || !parsed[1].matches("^from.*$") || !parsed[2].matches("^to.*$")) {
                    throw new OrionInputException("Correct syntax: event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
                } else {
                    // Removes "event", "from" and "to" keywords from input
                    String[] mapped = Arrays.stream(parsed)
                            .map(Parser::removeFirstWordFromString)
                            .toArray(String[]::new);
                    Event event = new Event(mapped[0].trim(), mapped[1].trim(), mapped[2].trim());
                    return new AddTaskCommand(event);
                }
            case "delete":
                if (inputArray.length != 2) {
                    throw new OrionInputException("Correct syntax: delete <task number>");
                } else {
                    try {
                        int taskNo = Integer.parseInt(inputArray[1]);
                        return new DeleteTaskCommand(taskNo);
                    } catch (NumberFormatException e) {
                        throw new OrionInputException("Correct syntax: delete <task number>");
                    }
                }
            case "find":
                String[] findQuery = input.split(" ", 2);
                if (inputArray.length < 2) {
                    throw new OrionInputException("Correct syntax: find <search query>");
                } else {
                    return new FindCommand(findQuery[1].trim());
                }
            default:
                throw new OrionInputException("Please provide a supported command!");
        }
    }
}
