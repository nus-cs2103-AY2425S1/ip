package phenex.util;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import phenex.command.Command;
import phenex.command.CommandWithIndex;
import phenex.command.CreateTaskCommand;
import phenex.command.DateCheckCommand;
import phenex.command.DeadlineCommand;
import phenex.command.DeleteCommand;
import phenex.command.EventCommand;
import phenex.command.FindCommand;
import phenex.command.ListCommand;
import phenex.command.MarkCommand;
import phenex.command.TerminatingCommand;
import phenex.command.TodoCommand;
import phenex.command.UnmarkCommand;
import phenex.exception.PhenexException;
import phenex.task.Deadline;
import phenex.task.Event;
import phenex.task.Task;


/**
 * Parser class encapsulating the parser which parses inputs for the Phenex.
 */
public class Parser {

    /**
     * Encapsulates the different RegexFormats for each of the Phenex
     * commands
     */
    public enum RegexFormat {
        REGEX_TERMINATING("(?i)bye\\s*$", new TerminatingCommand()),
        REGEX_LIST("(?i)list\\s*$", new ListCommand()),
        REGEX_MARK("^mark \\d+\\s*$", new MarkCommand()),
        REGEX_UNMARK("^unmark \\d+\\s*$", new UnmarkCommand()),
        REGEX_DELETE("^delete \\d+\\s*$", new DeleteCommand()),
        REGEX_DATECHECK("^missions on (.+)$", new DateCheckCommand()),
        REGEX_FIND("^(?i)find (.+)$", new FindCommand()),
        REGEX_TODO("^(?i)todo (.+)$", new TodoCommand()),
        REGEX_DEADLINE("^(?i)deadline (.+) /by (.+)$", new DeadlineCommand()),
        REGEX_EVENT("^(?i)event (.+) /from (.+) /to (.+)$", new EventCommand());

        private final String regex;
        private final Command command;

        RegexFormat(String regex, Command command) {
            this.regex = regex;
            this.command = command;
        }

        /**
         * Detects if a line matches the regex format.
         *
         * @param line the line to check.
         * @return a boolean indicating whether a match is detected.
         */
        public boolean detectMatch(String line) {
            Pattern pattern = Pattern.compile(this.regex);
            Matcher matcher = pattern.matcher(line);
            return matcher.matches();
        }
    }

    /**
     * Parses local date from a line.
     *
     * @param line the line to check.
     * @return a LocalDate object representing the local date.
     * @throws PhenexException if parsing error occurs.
     */
    public LocalDate parseLocalDateFromLine(String line) throws PhenexException {
        try {
            return LocalDate.parse(line.substring(12));
        } catch (DateTimeParseException e) {
            throw new PhenexException(e.getMessage());
        }
    }

    /**
     * Finds a format which matches the line.
     * @param line to find a format which matches.
     * @return the matching format.
     */
    public RegexFormat findMatchingFormat(String line) {
        for (RegexFormat format : RegexFormat.values()) {
            if (format.detectMatch(line)) {
                return format;
            }
        }
        return null;
    }

    /**
     * Updates a command based on user input.
     * @param command the command to be updated.
     * @param matcher the matcher of the command.
     * @param line the user input.
     */
    public void updateCommand(Command command, Matcher matcher, String line) throws PhenexException {
        if (command instanceof CommandWithIndex) {
            CommandWithIndex commandWithIndex = (CommandWithIndex) command;
            commandWithIndex.setIndex(this.getIndexOfTask(line, command));
        } else if (command instanceof DateCheckCommand) {
            DateCheckCommand dateCheckCommand = (DateCheckCommand) command;
            dateCheckCommand.setLocalDate(this.parseLocalDateFromLine(line));
        } else if (command instanceof CreateTaskCommand) {

            if (command instanceof DeadlineCommand) {
                String deadlineBy = matcher.group(2);
                LocalDate localDate = LocalDate.parse(deadlineBy);
                DeadlineCommand deadlineCommand = (DeadlineCommand) command;
                deadlineCommand.setDate(localDate);
            } else if (command instanceof EventCommand) {
                LocalDate fromDate = LocalDate.parse(matcher.group(2));
                LocalDate toDate = LocalDate.parse(matcher.group(3));
                EventCommand eventCommand = (EventCommand) command;
                eventCommand.setDates(fromDate, toDate);
            }
            String name = matcher.group(1);
            CreateTaskCommand createTaskCommand = (CreateTaskCommand) command;
            createTaskCommand.setName(name);
        } else if (command instanceof FindCommand) {
            String name = matcher.group(1);
            FindCommand findCommand = (FindCommand) command;
            findCommand.setName(name);
        }
    }

    /**
     * Parses command from a line.
     *
     * @param line the line to check.
     * @return a Command object representing the command parsed.
     * @throws PhenexException if parsing error.
     */
    public Command parseCommandFromLine(String line) throws PhenexException {
        RegexFormat format = findMatchingFormat(line);
        if (format == null) {
            throw new PhenexException("Invalid user input!");
        }
        Command command = format.command;
        Pattern pattern = Pattern.compile(format.regex);
        Matcher matcher = pattern.matcher(line);
        matcher.matches();
        updateCommand(command, matcher, line);
        return command;
    }

    public int getIndexOfTask(String line, Command command) throws PhenexException {
        if (command instanceof MarkCommand) {
            int indexOfResult = 5;
            return Integer.parseInt(line.substring(indexOfResult)) - 1;
        } else if ((command instanceof UnmarkCommand) || (command instanceof DeleteCommand)) {
            int indexOfResult = 7;
            return Integer.parseInt(line.substring(indexOfResult)) - 1;
        } else {
            throw new PhenexException("Unknown command type! Aborting");
        }
    }

    /**
     * Returns a formatted string representation of a task for storage in memory.
     *
     * @param task task to format string representation.
     * @return formatted string representation of task.
     */
    public static String parseTaskInfo(Task task) {
        String localDateString = "";
        String completedSymbol = "1, ";
        String incompleteSymbol = "0, ";
        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            localDateString = deadlineTask.getDeadlineDate().toString() + ", ";
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            localDateString = eventTask.getEventStartDate().toString()
                    + ", " + eventTask.getEventEndDate().toString() + ", ";
        }
        return task.getSymbol() + ", "
                + (task.isCompleted() ? completedSymbol : incompleteSymbol)
                + task.getName() + ", "
                + localDateString
                + "\n";
    }


}
