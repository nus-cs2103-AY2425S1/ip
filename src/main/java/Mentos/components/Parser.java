package Mentos.components;

import Mentos.Commands.*;
import Mentos.MentosException.MentosException;
import Mentos.task.Deadline;
import Mentos.task.Event;
import Mentos.task.Task;
import Mentos.task.ToDo;

import java.time.DateTimeException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final String MARKED = "mark";
    private final String UNMARKED = "unmark";
    private final String TODO = "todo";
    private final String DEADLINE = "deadline";
    private final String EVENT = "event";
    private final String DELETE = "delete";
    private final String FIND = "find";
    private final String LIST = "list";
    private final String UPDATE = "update";



    /**
     * Handles input commands and returns the corresponding Command object.
     * This method processes user inputs such as listing tasks, marking/unmarking tasks,
     * deleting tasks, and adding different types of tasks (to-dos, deadlines, events).
     *
     * @param input The input command string from the user. It should follow a specific format.
     * @return A Command object based on the input, or an InvalidCommand if the input doesn't match any valid command.
     * @throws Exception If an invalid input is provided or the command cannot be processed.
     */
    public Command taskHandler(String input) throws Exception {
        if (input.equals(LIST)) {
            return new ListCommand();
        } else if (input.startsWith(MARKED)) {
            return new MarkCommand(input);
        } else if (input.startsWith(UNMARKED)){
            return new UnmarkCommand(input);
        } else if (input.startsWith(DELETE)) {
            return new DeleteCommand(input);
        } else if (input.startsWith(TODO)) {
            return new ToDoCommand(input);
        } else if (input.startsWith(DEADLINE)) {
            return new DeadlineCommand(input);
        } else if (input.startsWith(EVENT)) {
            return new EventCommand(input);
        } else if (input.startsWith(FIND)) {
            return new FindCommand(input);
        } else if (input.startsWith(UPDATE)) {
            return new UpdateCommand(input);
        }
        return new InvalidCommand();
    }
}
