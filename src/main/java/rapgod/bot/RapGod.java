package rapgod.bot;

import java.util.ArrayList;
import java.util.Arrays;

import rapgod.exceptions.InvalidDateTimeException;
import rapgod.exceptions.NoInputException;
import rapgod.exceptions.RudeInputException;
import rapgod.storage.DataManager;

/**
 * The {@code RapGod} class is the main controller for the Rap Bot application.
 * It handles user commands related to task management, such as adding, deleting,
 * marking, and snoozing tasks. The class uses a {@link DataManager} to store
 * and retrieve tasks.
 *
 * <p>Key features include:</p>
 * <ul>
 *     <li>Processing commands like {@code ADD}, {@code LIST}, {@code FIND},
 *         {@code MARK}, {@code UNMARK}, and {@code DELETE}.</li>
 *     <li>Providing a list of commands through {@link #getInitialMessage()}.</li>
 *     <li>Validating user input to avoid empty or rude responses.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 *     RapGod rapBot = new RapGod();
 *     String response = rapBot.getResponse("ADD New Task");
 * </pre>
 *
 * <p>Rude words are stored in a list, and the bot will respond to rude input
 * with a specific message.</p>
 *
 * @see DataManager
 * @see rapgod.exceptions.NoInputException
 * @see rapgod.exceptions.RudeInputException
 * @see rapgod.exceptions.InvalidDateTimeException
 */
public class RapGod {
    public static final ArrayList<String> RUDE_WORDS = new ArrayList<>(Arrays.asList(
            "damn", "hell", "shit", "fuck",
            "bitch", "asshole", "dickhead",
            "idiot", "moron", "stupid",
            "loser", "jerk", "creep"
    ));
    private DataManager dataManager = new DataManager("data/rapgod.txt");
    public static void main(String[] args) {
    }

    public static String getInitialMessage() {
        String initialise =
                """
                Yo, Rap Bot's kickin off! Here's the lowdown:\n
                'ADD abc'                 - Adds a new task 'abc'\n
                'LIST'                    - This shows the full list.
                'FIND abc, def'           - Searching for tasks with 'abc' or 'def'? This filters 'em out.
                'MARK n'                  - Mark the nth task as done. Easy peasy.
                'UNMARK n'                - This marks the nth task as not done.
                'DELETE n'                - Get rid of the nth task. Poof, it's gone.\n
                '/BY z'                   - Got a deadline? Specify it with '/BY z'.
                '/FROM x /TO y'              - Set up an event from x to y with this.
                'RESCHEDULE n /by x'         - Snooze that deadline on the nth task.
                'RESCHEDULE n /from x /to y' - Change the schedule on the nth event task.
                 Time Format:             - Use dd/MM/yyyy or dd/MM/yyyy HHmm to keep things in check.
                """;

        return initialise;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            assert input != null : "Yo! Input should not be null";
            assert !input.trim().isEmpty() : "Yo! Input should not be empty";

            if (input == null || input.trim().isEmpty()) {
                throw new NoInputException();
            } else if (RUDE_WORDS.contains(input)) {
                throw new RudeInputException();
            }

            CommandType command = CommandType.getCommand(input);
            assert command != null : "Yo! Command type should not be null";
            response = executeCommand(input, command);
            dataManager.updateMemory();

        } catch (NumberFormatException exc) {
            response = "Enter a valid number after 'Mark ', 'Unmark ', or 'Delete '. Eg. Mark 4";
        } catch (IndexOutOfBoundsException exc) {
            response = "No such task exists man!";
        } catch (NoInputException | RudeInputException exc) {
            response = "RapGod:\n" + exc.getMessage();
        } catch (IllegalArgumentException exc) {
            response = exc.getMessage();
        } catch (InvalidDateTimeException exc) {
            response = exc.getMessage();
        } finally {
            return response;
        }
    }

    private String executeCommand(String input, CommandType command) {
        String response = "";
        switch (command) {
        case LIST:
            response = dataManager.getTaskList().taskString();
            break;
        case FIND:
            response = executeFind(input);
            break;
        case MARK:
            response = executeMark(input, command);
            break;
        case UNMARK:
            response = executeUnmark(input, command);
            break;
        case DELETE:
            response = executeDelete(input, command);
            break;
        case DEADLINE:
            response = loadDeadline(input);
            break;
        case EVENT:
            response = loadEvent(input);
            break;
        case TODO:
            response = loadToDo(input);
            break;
        case SNOOZE_DEADLINE:
            response = executeSnoozeDeadline(input, command);
            break;
        case SNOOZE_EVENT:
            response = executeSnoozeEvent(input, command);
            break;
        case BYE:
            response = "Peace out! Catch you on the flip side!";
            break;
        default:
            response = "What's this? I ain't never seen a command like that before, man!";
            break;
        }
        return response;
    }

    /**
     * Executes the 'find' command by extracting keywords from the input and filtering tasks based on those keywords.
     *
     * @param input the input string containing the command and keywords
     * @return a filtered list of tasks matching the keywords
     */
    private String executeFind(String input) {
        String[] keywords = CommandType.extractKeywords(input);
        return dataManager.getTaskList().filteredTask(keywords);
    }
    /**
     * Executes the 'mark' command by marking the task at the specified index as done.
     *
     * @param input the input string containing the command and task index
     * @param command the CommandType representing the 'mark' command
     * @return the result of marking the task as done
     * @throws AssertionError if the extracted index is negative
     */
    private String executeMark(String input, CommandType command) {
        int markIndex = CommandType.extractIndex(input, command);
        assert markIndex >= 0 : "Yo! Mark index should be non-negative";
        return dataManager.getTaskList().markTaskByIndex(markIndex);
    }
    /**
     * Executes the 'unmark' command by unmarking the task at the specified index as not done.
     *
     * @param input the input string containing the command and task index
     * @param command the CommandType representing the 'unmark' command
     * @return the result of unmarking the task as not done
     * @throws AssertionError if the extracted index is negative
     */
    private String executeUnmark(String input, CommandType command) {
        int unmarkIndex = CommandType.extractIndex(input, command);
        assert unmarkIndex >= 0 : "Yo! Unmark index should be non-negative";
        return dataManager.getTaskList().unmarkTaskByIndex(unmarkIndex);
    }
    /**
     * Executes the 'snooze' command to snooze a deadline task, updating its due date.
     *
     * @param input the input string containing the command and snooze details
     * @param command the CommandType representing the 'snooze' command for deadlines
     * @return the result of snoozing the deadline
     */
    private String executeSnoozeDeadline(String input, CommandType command) {
        int snoozeDeadlineIndex = CommandType.extractIndex(input, command);
        String snoozeDueField = input.substring(input.toLowerCase().indexOf("/by") + 4);
        return dataManager.getTaskList().snoozeDeadline(snoozeDeadlineIndex, snoozeDueField);
    }
    /**
     * Executes the 'snooze' command to update the schedule for an event task.
     *
     * @param input the input string containing the command and snooze details
     * @param command the CommandType representing the 'snooze' command for events
     * @return the result of snoozing the event
     */
    private String executeSnoozeEvent(String input, CommandType command) {
        int snoozeEventIndex = CommandType.extractIndex(input, command);
        String snoozeFromField = input.substring(input.toLowerCase().indexOf("/from") + 6,
                input.toLowerCase().indexOf("/to") - 1);
        String snoozeToField = input.substring(input.toLowerCase().indexOf("/to") + 4);
        return dataManager.getTaskList().snoozeEvent(snoozeEventIndex, snoozeFromField, snoozeToField);
    }
    /**
     * Executes the 'delete' command by deleting the task at the specified index.
     *
     * @param input the input string containing the command and task index
     * @param command the CommandType representing the 'delete' command
     * @return the result of deleting the task
     * @throws AssertionError if the extracted index is negative
     */
    private String executeDelete(String input, CommandType command) {
        int deleteIndex = CommandType.extractIndex(input, command);
        assert deleteIndex >= 0 : "Yo! Delete index should be non-negative";
        return dataManager.getTaskList().deleteTaskByIndex(deleteIndex);
    }
    /**
     * Loads a new to-do task from the input and adds it to the task list.
     *
     * @param input the input string containing the to-do task description
     * @return the result of adding the to-do task
     */
    private String loadToDo(String input) {
        return dataManager.getTaskList().addToDoTask(input.substring(4));
    }
    /**
     * Loads a new deadline task from the input and adds it to the task list.
     *
     * @param input the input string containing the deadline task description and due date
     * @return the result of adding the deadline task
     * @throws AssertionError if the description or due date is empty
     */
    private String loadDeadline(String input) {
        String deadlineDesc = input.substring(4, input.toLowerCase().indexOf("/by"));
        String due = input.substring(input.toLowerCase().indexOf("/by") + 4);
        assert !deadlineDesc.isEmpty() : "Yo! Deadline description should not be empty";
        assert !due.isEmpty() : "Yo! Due date should not be empty";
        return dataManager.getTaskList().addDeadlineTask(deadlineDesc, due);
    }
    /**
     * Loads a new event task from the input and adds it to the task list.
     *
     * @param input the input string containing the event description, start date, and end date
     * @return the result of adding the event task
     * @throws AssertionError if the description, start date, or end date is empty
     */
    private String loadEvent(String input) {
        String eventDesc = input.substring(4, input.toLowerCase().indexOf("/from"));
        String from = input.substring(input.toLowerCase().indexOf("/from") + 6,
                input.toLowerCase().indexOf("/to") - 1);
        String to = input.substring(input.toLowerCase().indexOf("/to") + 4);
        assert !eventDesc.isEmpty() : "Yo! Event description should not be empty";
        assert !from.isEmpty() : "Yo! 'From' date should not be empty";
        assert !to.isEmpty() : "Yo! 'To' date should not be empty";
        return dataManager.getTaskList().addEventTask(eventDesc, from, to);
    }

    /**
     * Enum representing the various command types that can be used for task management in the ListBot Bot.
     * Commands include listing tasks, marking/unmarking tasks, deleting tasks, and adding deadlines, events, or to-dos.
     */
    public enum CommandType {
        LIST, FIND, MARK, UNMARK, DELETE, BYE, EVENT, DEADLINE, TODO, SNOOZE_DEADLINE, SNOOZE_EVENT, UNKNOWN;

        /**
         * Determines the {@link CommandType} based on the provided user input string.
         * The method analyzes the input to identify and return the appropriate command type.
         *
         * @param input The user input string to be analyzed.
         * @return The corresponding {@link CommandType} based on the input.
         */
        public static CommandType getCommand(String input) {
            if (input.equalsIgnoreCase("list")) {
                return LIST;
            } else if (input.toLowerCase().startsWith("find ")) {
                return FIND;
            } else if (input.toLowerCase().startsWith("mark ")) {
                return MARK;
            } else if (input.toLowerCase().startsWith("unmark ")) {
                return UNMARK;
            } else if (input.toLowerCase().startsWith("delete ")) {
                return DELETE;
            } else if (input.toLowerCase().startsWith("snooze ")
                    && input.toLowerCase().contains("/by")) {
                return SNOOZE_DEADLINE;
            } else if (input.toLowerCase().startsWith("reschedule ")
                        && input.toLowerCase().contains("/from")
                        && input.toLowerCase().contains("/to")) {
                return SNOOZE_EVENT;
            } else if (input.toLowerCase().startsWith("add ")
                        && input.toLowerCase().contains("/by")) {
                return DEADLINE;
            } else if (input.toLowerCase().startsWith("add ")
                    && input.toLowerCase().contains("/from") && input.toLowerCase().contains("/to")) {
                return EVENT;
            } else if (input.toLowerCase().startsWith("add ")) {
                return TODO;
            } else if (input.equalsIgnoreCase("bye")) {
                return BYE;
            } else {
                return UNKNOWN;
            }
        }

        /**
         * Extracts and returns the index from a command string based on the given {@link CommandType}.
         * The index is parsed from the input string following the command keyword.
         *
         * @param input The user input string containing the index.
         * @param command The {@link CommandType} that specifies the type of command requiring an index.
         * @return The extracted index as an integer.
         * @throws NumberFormatException If the substring following the command keyword cannot be parsed as an integer.
         * @throws IllegalArgumentException If the {@link CommandType} does not require an index.
         */
        public static int extractIndex(String input, CommandType command) throws NumberFormatException {
            switch (command) {
            case SNOOZE_DEADLINE:
            case SNOOZE_EVENT:
                return Integer.parseInt(input.substring(7, input.indexOf('/')).trim());
            case MARK:
                return Integer.parseInt(input.substring(5).trim());
            case UNMARK:
                return Integer.parseInt(input.substring(7).trim());
            case DELETE:
                return Integer.parseInt(input.substring(7).trim());
            default:
                throw new IllegalArgumentException("Command does not require an index.");
            }
        }

        private static String[] extractKeywords(String input) {
            String substring = input.substring(5);
            return substring.split(",\\s*");
        }
    }
}
