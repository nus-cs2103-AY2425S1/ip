package rapgod.ui;

import rapgod.RapGod;

import rapgod.exceptions.RudeInputException;
import rapgod.exceptions.NoInputException;

import rapgod.storage.DataManager;

import java.util.Scanner;

/**
 * A class that provides a command-line interface for managing tasks with the ListBot Bot.
 * It processes user commands to perform various task management operations, including:
 * - Listing all tasks
 * - Marking tasks as completed or incomplete
 * - Deleting tasks
 * - Adding new deadlines, events, or to-dos
 */
public class ListBot {

    /**
     * Starts the ListBot Bot, which provides a command-line interface for managing tasks.
     * It initializes the bot, displays available commands, and enters a loop to continuously process user input.
     * The method handles commands for listing tasks, marking tasks as done or undone, deleting tasks,
     * and adding new deadlines, events, or to-dos. It also processes special commands and handles errors
     * such as invalid input or rude commands.
     */
    public static void run() {

        String initialise = """
                -----------------------------------------------
                Initialising ListBot Bot...
                Special commands:
                'LIST' -> Show full list
                'FIND abc' -> Filters tasks with abc
                'MARK n' -> Marks nth task as complete
                'UNMARK n' -> Marks nth task as incomplete
                'DELETE n' -> Deletes the nth task
                '/BY z' -> Used to specify a deadline z
                '/FROM x /TO y' -> Used to specify bounds of an event from x to y
                Time format: dd/MM/yyyy OR dd/MM/yyyy HHHH
                -----------------------------------------------
                """;

        System.out.print(initialise);

        Scanner scanner = RapGod.scanner;
        String input;
        DataManager dataManager = new DataManager("data/rapgod.txt");

        while (true) {
            System.out.print("Task:\n");
            input = scanner.nextLine();

            try {
                if (input == null || input.trim().isEmpty()) {
                    throw new NoInputException();
                } else if (RapGod.RUDE_WORDS.contains(input)) {
                    throw new RudeInputException();
                }

                CommandType command = CommandType.getCommand(input);

                switch (command) {
                case LIST:
                    dataManager.getTaskList().showList();
                    break;

                case FIND:
                    String keyword = CommandType.extractKeyword(input);
                    dataManager.getTaskList().filterAndShowList(keyword);
                    break;

                case MARK:
                    int markIndex = CommandType.extractIndex(input, command);
                    dataManager.getTaskList().markTaskByIndex(markIndex);
                    break;

                case UNMARK:
                    int unmarkIndex = CommandType.extractIndex(input, command);
                    dataManager.getTaskList().unmarkTaskByIndex(unmarkIndex);
                    break;

                case DELETE:
                    int deleteIndex = CommandType.extractIndex(input, command);
                    dataManager.getTaskList().deleteTaskByIndex(deleteIndex);
                    break;

                case DEADLINE:
                    String deadlineDesc = input.substring(0, input.toLowerCase().indexOf("/by"));
                    String due = input.substring(input.toLowerCase().indexOf("/by") + 4);
                    dataManager.getTaskList().addDeadlineTask(deadlineDesc, due);
                    break;

                case EVENT:
                    String eventDesc = input.substring(0, input.toLowerCase().indexOf("/from"));
                    String from = input.substring(input.toLowerCase().indexOf("/from") + 6, input.toLowerCase().indexOf("/to") - 1);
                    String to = input.substring(input.toLowerCase().indexOf("/to") + 4);
                    dataManager.getTaskList().addEventTask(eventDesc, from, to);
                    break;

                case TODO:
                    dataManager.getTaskList().addToDoTask(input);
                    break;

                case BYE:
                    System.out.println("-----------------------------------------------");
                    System.out.println("Bye! Hope to see you again soon!");
                    System.out.println("-----------------------------------------------");
                    return;

                default:
                    System.out.println("Unknown command. Please try again.");
                    break;
                }

                dataManager.updateMemory();

            } catch (NumberFormatException exc) {
                System.out.println("Enter a valid number after 'Mark ', 'Unmark ', or 'Delete '. Eg. Mark 4");
            } catch (IndexOutOfBoundsException exc) {
                System.out.println("No such task exists.");
            } catch (NoInputException | RudeInputException exc) {
                System.out.println("-----------------------------------------------");
                System.out.println("RapGod:\n" + exc.getMessage());
                System.out.println("-----------------------------------------------");
            } catch(IllegalArgumentException exc) {
                System.out.println(exc.getMessage());
            }
        }
    }

    /**
     * Enum representing the various command types that can be used for task management in the ListBot Bot.
     * Commands include listing tasks, marking/unmarking tasks, deleting tasks, and adding deadlines, events, or to-dos.
     */
    public enum CommandType {
        LIST, FIND, MARK, UNMARK, DELETE, BYE, EVENT, DEADLINE, TODO;

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

        public static String extractKeyword(String input) {
            return input.substring(5).trim();
        }
    }

}
