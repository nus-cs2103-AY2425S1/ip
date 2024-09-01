package ahmad;

import java.util.Arrays;
import java.util.List;

import ahmad.exceptions.CommandInvalidException;
import ahmad.processor.Exit;
import ahmad.processor.Processor;
import ahmad.processor.task.Add;
import ahmad.processor.task.Delete;
import ahmad.processor.task.Mark;
import ahmad.processor.task.Unmark;

/**
 * A command parser class.
 */
public class Parser {
    /**
     * Returns the appropriate processor for given command.
     *
     * @param prompt User prompt.
     * @return The correct processor.
     * @throws CommandInvalidException If command/prompt given is invalid.
     */
    public static Processor parse(String prompt) throws CommandInvalidException {

        final List<String> prompts = Arrays.asList(prompt.split(" "));

        switch (prompts.get(0)) {
        case "mark":
            return Mark::process;
        case "unmark":
            return Unmark::process;
        case "list":
            return ahmad.processor.task.List::process;
        case "bye":
            return Exit::process;
        case "todo":
            return Add::todo;
        case "deadline":
            return Add::deadline;
        case "event":
            return Add::event;
        case "delete":
            return Delete::process;
        default:
            throw new CommandInvalidException();
        }
    }
}
