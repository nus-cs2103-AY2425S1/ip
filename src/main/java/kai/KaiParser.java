package kai;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import kai.commands.Command;
import kai.commands.CreateDeadlineCommand;
import kai.commands.CreateEventCommand;
import kai.commands.CreateToDoCommand;
import kai.commands.DeleteCommand;
import kai.commands.ExitCommand;
import kai.commands.InvalidCommand;
import kai.commands.ListCommand;
import kai.commands.MarkCommand;
import kai.commands.UnmarkCommand;
import kai.tasks.Deadline;
import kai.tasks.Event;
import kai.tasks.Task;
import kai.tasks.ToDo;

/**
 * Parser handles the logic of parsing input in one place
 */
public class KaiParser {
    private static final DateTimeFormatter inputFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter storageFormatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Parses a string representing a Task and transforms it into an actual Task
     *
     * @param state the string representation of the Task
     * @return the Task corresponding to that representation
     * @throws IllegalArgumentException if the stored Task state is invalid
     * @throws StringIndexOutOfBoundsException if the stored Task state is invalid
     * @throws DateTimeParseException if the stored Task state is invalid
     */
    public Task parseStoredTask(String state)
            throws IllegalArgumentException, StringIndexOutOfBoundsException,
            DateTimeParseException {
        String type = state.substring(0, state.indexOf(" | ") + 3);
        state = state.substring(state.indexOf(" | ") + 3);

        boolean isDone = (state.substring(0, state.indexOf(" | ")).equals("1"));
        state = state.substring(state.indexOf(" | ") + 3);

        String desc;
        Task res;
        switch (type) {
        case "T | " -> {
            desc = state;
            res = new ToDo(desc);
        }
        case "D | " -> {
            desc = state.substring(0, state.indexOf(" | "));
            state = state.substring(state.indexOf(" | ") + 3);

            LocalDate deadline = LocalDate.parse(state, storageFormatter);
            res = new Deadline(desc, deadline);
        }
        case "E | " -> {
            desc = state.substring(0, state.indexOf(" | "));
            state = state.substring(state.indexOf(" | ") + 3);

            LocalDate from = LocalDate.parse(
                    state.substring(0, state.indexOf(" | ")), storageFormatter);
            state = state.substring(state.indexOf(" | ") + 3);

            LocalDate to = LocalDate.parse(state, storageFormatter);
            res = new Event(desc, from, to);
        }
        default -> throw new IllegalArgumentException();
        }

        if (isDone) {
            res.markComplete();
        } else {
            res.markIncomplete();
        }

        return res;
    }

    /**
     * Parses the command given and takes the appropriate response thereof
     *
     * @param input the string corresponding to the command in question
     * @param taskList the TaskList some of these commands work on
     * @param sc the Scanner that may need to closed
     * @return the Command to be invoked later on
     */
    public Command parseCommand(String input, TaskList taskList, Scanner sc) {
        if (input.equals("bye")) {
            return new ExitCommand(sc);
        }
        else if (input.equals("list")) {
            return new ListCommand(taskList);
        } else if (input.startsWith("mark ")) {
            try {
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index >= taskList.size() || index < 0) {
                    return new InvalidCommand("\t There is no task corresponding to" +
                            " the number you entered, please try again.");
                } else {
                    return new MarkCommand(taskList.getTask(index));
                }
            } catch (IllegalArgumentException e) {
                return new InvalidCommand("\t The task number specified is invalid, please retry.");
            }
        } else if (input.startsWith("unmark ")) {
            try {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= taskList.size() || index < 0) {
                    return new InvalidCommand("\t There is no task corresponding to" +
                            " the number you entered, please try again.");
                }
                return new UnmarkCommand(taskList.getTask(index));
            } catch (IllegalArgumentException e) {
                return new InvalidCommand("\t The task number specified is invalid, please retry.");
            }
        } else if (input.startsWith("delete ")) {
            try {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= taskList.size() || index < 0) {
                    return new InvalidCommand("\t There is no task corresponding to" +
                            " the number you entered, please try again.");
                }
                return new DeleteCommand(taskList, index);
            } catch (IllegalArgumentException e) {
                return new InvalidCommand("\t The task number specified is invalid, please retry.");
            }
        } else if (!input.isEmpty()) {
            if (input.startsWith("todo ")) {
                    if (input.length() < 6) {
                        return new InvalidCommand("\t The todo command must have a name for the task.");
                    }
                    return new CreateToDoCommand(taskList, input.substring(5));
                } else if (input.startsWith("deadline ")) {
                    if (!input.contains(" /by ")) {
                        return new InvalidCommand("\t The deadline command requires ' /by '" +
                                " without the quotation marks " + "and then the deadline to work properly.");
                    }
                    String desc = input.substring(9, input.indexOf(" /by "));
                    if (desc.isEmpty()) {
                        return new InvalidCommand("\t The deadline command must have a name for the task.");
                    }
                    try {
                        LocalDate deadline = LocalDate.parse(
                                input.substring(input.indexOf(" /by ") + 5), inputFormatter);
                        return new CreateDeadlineCommand(taskList, desc, deadline);
                    } catch (DateTimeParseException e) {
                        return new InvalidCommand("\t The input dates could not be parsed:" + System.lineSeparator() +
                                "\t Did you follow the format of yyyy-MM-dd and pad 0s in front (eg: 2019-09-15)?");
                    }
                } else if (input.startsWith("event ")) {
                if (!(input.contains(" /from ") && input.contains(" /to ")) ||
                        input.indexOf(" /from ") >= input.indexOf(" /to ")) {
                    return new InvalidCommand("\t The event command requires ' /from '" +
                            " without the quotation marks, the first date," + System.lineSeparator() +
                            "\t and then ' /to ' without the quotation marks " +
                            "followed by the second date to work properly.");
                }
                String desc = input.substring(6, input.indexOf(" /from "));
                if (desc.isEmpty()) {
                    return new InvalidCommand("\t The event command must have a name for the task.");
                }

                try {
                    LocalDate from = LocalDate.parse(
                            input.substring(input.indexOf(" /from ") + 7, input.indexOf(" /to ")),
                            inputFormatter);
                    LocalDate to = LocalDate.parse(
                            input.substring(input.indexOf(" /to ") + 5), inputFormatter);
                    if (from.isAfter(to)) {
                        return new InvalidCommand("\t The start date of the event needs to be before or equal to the end date!");
                    }
                    return new CreateEventCommand(taskList, desc, from, to);
                } catch (DateTimeParseException e) {
                    return new InvalidCommand("\t The input dates could not be parsed:" + System.lineSeparator() +
                            "\t Did you follow the format of yyyy-MM-dd and pad 0s in front (eg: 2019-09-15)?");
                }

            } else {
                return new InvalidCommand("\t I'm sorry, I don't recognise your command, " +
                        "the currently supported (case-sensitive, without the quotation marks) commands are:" +
                        System.lineSeparator() + "\t " +
                        "'mark', 'unmark', 'delete', 'list', 'todo', 'deadline', and 'event'." +
                        System.lineSeparator() + "\t " +
                        "Did you forget to add a space at the end of the commands to input arguments if applicable?");

            }
        }
        return new InvalidCommand("\t I would love to help you, " +
                "but could you please give me more to work with?");
    }
}
