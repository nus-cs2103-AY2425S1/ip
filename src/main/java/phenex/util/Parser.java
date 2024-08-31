package phenex.util;

import phenex.command.*;
import phenex.exception.PhenexException;
import phenex.task.Deadline;
import phenex.task.Event;
import phenex.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

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

        /** detects if a line matches the regex format
         *
         * @param line, the line to check
         * @return a boolean indicating whether a match is detected
         */
        public boolean detectMatch(String line) {
            Pattern pattern = Pattern.compile(this.regex);
            Matcher matcher = pattern.matcher(line);
            return matcher.matches();
        }
    }

    /** parses local date from a line
     *
     * @param line, the line to check
     * @return a LocalDate object representing the local date
     * @throws PhenexException, if parsing error occurs
     */
    public LocalDate parseLocalDateFromLine(String line) throws PhenexException {
        try {
            return LocalDate.parse(line.substring(12));
        } catch (DateTimeParseException e) {
            throw new PhenexException(e.getMessage());
        }
    }

    /** parses command from a line
     *
     * @param line, the line to check
     * @return a Command object representing the command parsed
     * @throws PhenexException, if parsing error
     */
    public Command parseCommandFromLine(String line) throws PhenexException {
        try {
            for (RegexFormat format : RegexFormat.values()) {
                if (format.detectMatch(line)) {
                    Command command = format.command;
                    Pattern pattern = Pattern.compile(format.regex);
                    Matcher matcher = pattern.matcher(line);
                    matcher.matches();

                    if (command instanceof CommandWithIndex) {
                        ((CommandWithIndex) command).setIndex(this.getIndexOfTask(line, command));
                    } else if (command instanceof DateCheckCommand) {
                        ((DateCheckCommand) command).setLocalDate(this.parseLocalDateFromLine(line));
                    } else if (command instanceof CreateTaskCommand) {

                        if (command instanceof DeadlineCommand) {
                            String deadlineBy = matcher.group(2);
                            LocalDate localDate = LocalDate.parse(deadlineBy);
                            ((DeadlineCommand) command).setDate(localDate);
                        } else if (command instanceof EventCommand) {
                            LocalDate fromDate = LocalDate.parse(matcher.group(2));
                            LocalDate toDate = LocalDate.parse(matcher.group(3));
                            ((EventCommand) command).setDates(fromDate, toDate);
                        }
                        String name = matcher.group(1);
                        ((CreateTaskCommand) command).setName(name);
                    } else if (command instanceof FindCommand) {
                        String name = matcher.group(1);
                        ((FindCommand) command).setName(name);
                    }

                    return command;
                }
            }
            throw new PhenexException("Invalid input!");
        } catch (Exception e) {
            throw new PhenexException(e.getMessage());
        }
    }

    public int getIndexOfTask(String line, Command command) throws PhenexException {
        int indexOfResult = -1;
        if (command instanceof MarkCommand) {
            indexOfResult = 5;
        } else if ((command instanceof UnmarkCommand) || (command instanceof DeleteCommand)) {
            indexOfResult = 7;
        } else {
            throw new PhenexException("Unknown command type! Aborting");
        }
        return Integer.parseInt(line.substring(indexOfResult)) - 1;
    }

    /**
     * Returns a string representation of a task name
     *
     * @param line, line to parse from
     * @return name of task in string representation
     */
    public String getNameOfTask(String line) {
        return line.substring(5);
    }

    /**
     * Returns a formatted string representation of a task for storage in memory
     *
     * @param task, task to format string representation
     * @return formatted string representation of task
     */
    public static String parseTaskInfo(Task task) {
        String localDateString = "";
        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            localDateString = deadlineTask.getDeadlineDate().toString() + ", ";
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            localDateString = eventTask.getEventStartDate().toString()
                    + ", " + eventTask.getEventEndDate().toString() + ", ";
        }
        return task.getSymbol() + ", "
                + (task.isCompleted() ? "1, " : "0, ")
                + task.getName() + ", "
                + localDateString
                + "\n";
    }


}
