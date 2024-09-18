package rapgod.bot;

import rapgod.exceptions.NoInputException;
import rapgod.exceptions.RudeInputException;
import rapgod.storage.DataManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RapGod {
    DataManager dataManager = new DataManager("data/rapgod.txt");
    public static void main(String[] args) {
        System.out.println("Hello!");
    }
    public static final ArrayList<String> RUDE_WORDS = new ArrayList<>(Arrays.asList(
            "damn", "hell", "shit", "fuck",
            "bitch", "asshole", "dickhead",
            "idiot", "moron", "stupid",
            "loser", "jerk", "creep"
    ));

    public static String getInitialMessage() {
        String initialise = """
                Initialising List Bot...
                Special commands:
                
                'LIST'                    Show full list
                'FIND abc, def'           Filters tasks with abc or def
                
                'MARK n'                  Marks nth task as complete
                'UNMARK n'                Marks nth task as incomplete
                'DELETE n'                Deletes the nth task
                
                '/BY z'                   Used to specify a deadline z
                '/FROM x /TO y'           Used to specify bounds of an event from x to y
                'SNOOZE n /by x'          Changes deadline of Deadline Task
                'SNOOZE n /from x /to y'  Changes deadline of Event Task
                
                Time format               dd/MM/yyyy OR dd/MM/yyyy HHHH
                """;
        return initialise;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String response = "";

        try {

            assert input != null : "Input should not be null";  // Input should not be null
            assert !input.trim().isEmpty() : "Input should not be empty";

            if (input == null || input.trim().isEmpty()) {
                throw new NoInputException();
            } else if (RUDE_WORDS.contains(input)) {
                throw new RudeInputException();
            }

            CommandType command = CommandType.getCommand(input);

            assert command != null : "Command type should not be null";

            switch (command) {
                case LIST:
                    response = dataManager.getTaskList().taskString();
                    break;

                case FIND:
                    String[] keywords = CommandType.extractKeywords(input);
                    response = dataManager.getTaskList().filteredTask(keywords);
                    break;

                case MARK:
                    int markIndex = CommandType.extractIndex(input, command);
                    assert markIndex >= 0 : "Mark index should be non-negative";
                    response = dataManager.getTaskList().markTaskByIndex(markIndex);
                    break;

                case UNMARK:
                    int unmarkIndex = CommandType.extractIndex(input, command);
                    assert unmarkIndex >= 0 : "Unmark index should be non-negative";
                    response = dataManager.getTaskList().unmarkTaskByIndex(unmarkIndex);
                    break;

                case DELETE:
                    int deleteIndex = CommandType.extractIndex(input, command);
                    assert deleteIndex >= 0 : "Delete index should be non-negative";
                    response = dataManager.getTaskList().deleteTaskByIndex(deleteIndex);
                    break;

                case DEADLINE:
                    String deadlineDesc = input.substring(0, input.toLowerCase().indexOf("/by"));
                    String due = input.substring(input.toLowerCase().indexOf("/by") + 4);
                    assert !deadlineDesc.isEmpty() : "Deadline description should not be empty";
                    assert !due.isEmpty() : "Due date should not be empty";
                    response = dataManager.getTaskList().addDeadlineTask(deadlineDesc, due);
                    break;

                case EVENT:
                    String eventDesc = input.substring(0, input.toLowerCase().indexOf("/from"));
                    String from = input.substring(input.toLowerCase().indexOf("/from") + 6, input.toLowerCase().indexOf("/to") - 1);
                    String to = input.substring(input.toLowerCase().indexOf("/to") + 4);
                    assert !eventDesc.isEmpty() : "Event description should not be empty";
                    assert !from.isEmpty() : "'From' date should not be empty";
                    assert !to.isEmpty() : "'To' date should not be empty";
                    response = dataManager.getTaskList().addEventTask(eventDesc, from, to);
                    break;

                case TODO:
                    response = dataManager.getTaskList().addToDoTask(input);
                    break;

                case SNOOZE_DEADLINE:
                    int snoozeDeadlineIndex = CommandType.extractIndex(input, command);
                    String snoozeDueField = input.substring(input.toLowerCase().indexOf("/by") + 4);
                    response = dataManager.getTaskList().snoozeDeadline(snoozeDeadlineIndex, snoozeDueField);
                    break;

                case SNOOZE_EVENT:
                    int snoozeEventIndex = CommandType.extractIndex(input, command);
                    String snoozeFromField = input.substring(input.toLowerCase().indexOf("/from") + 6, input.toLowerCase().indexOf("/to") - 1);
                    String snoozeToField = input.substring(input.toLowerCase().indexOf("/to") + 4);
                    response = dataManager.getTaskList().snoozeEvent(snoozeEventIndex, snoozeFromField, snoozeToField);
                    break;

                case BYE:
                    response = "Bye! Hope to see you again soon!";
                    break;

                default:
                    response = "Unknown command. Please try again.";
                    break;
            }

            dataManager.updateMemory();

        } catch (NumberFormatException exc) {
            response = "Enter a valid number after 'Mark ', 'Unmark ', or 'Delete '. Eg. Mark 4";
        } catch (IndexOutOfBoundsException exc) {
            response = "No such task exists.";
        } catch (NoInputException | RudeInputException exc) {
            response = "RapGod:\n" + exc.getMessage();
        } catch(IllegalArgumentException exc) {
            response = exc.getMessage();
        } finally {
            return response;
        }
    }

    /**
     * Enum representing the various command types that can be used for task management in the ListBot Bot.
     * Commands include listing tasks, marking/unmarking tasks, deleting tasks, and adding deadlines, events, or to-dos.
     */
    public enum CommandType {
        LIST, FIND, MARK, UNMARK, DELETE, BYE, EVENT, DEADLINE, TODO, SNOOZE_DEADLINE, SNOOZE_EVENT;

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
            }else if (input.toLowerCase().startsWith("mark ")) {
                return MARK;
            } else if (input.toLowerCase().startsWith("unmark ")) {
                return UNMARK;
            } else if (input.toLowerCase().startsWith("delete ")) {
                return DELETE;
            } else if (input.toLowerCase().startsWith("snooze ")
                    && input.toLowerCase().contains("/by")) {
                return SNOOZE_DEADLINE;
            } else if (input.toLowerCase().startsWith("snooze ")
                        && input.toLowerCase().contains("/from")
                        && input.toLowerCase().contains("/to")) {
                return SNOOZE_EVENT;
            } else if (input.toLowerCase().contains("/by")) {
                return DEADLINE;
            } else if (input.toLowerCase().contains("/from") && input.toLowerCase().contains("/to")) {
                return EVENT;
            } else if (input.equalsIgnoreCase("bye")) {
                return BYE;
            } else {
                return TODO;
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

        public static String[] extractKeywords(String input) {
            String substring = input.substring(5);
            return substring.split(",\\s*");
        }
    }
}