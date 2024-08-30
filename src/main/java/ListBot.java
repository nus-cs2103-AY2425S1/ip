import java.util.Scanner;

/**
 * A class that handles user commands for task management using the ListBot Bot.
 * It supports commands to list, mark, unmark, delete tasks, and add deadlines, events, or todos.
 */
public class ListBot {

    /**
     * Runs the ListBot Bot, which processes user commands to manage tasks.
     * It initializes the bot, processes user input in a loop, and handles various commands to manage tasks.
     */
    public static void run() {

        String initialise = """
                -----------------------------------------------
                Initialising ListBot Bot...
                Special commands:
                'LIST' -> Show full list
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
        String input = "";
        java.util.List<Task> list = DataManager.readMemory();

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
                        System.out.println("-----------------------------------------------");
                        System.out.println("Displaying ListBot:");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.printf("%d. %s\n", i + 1, list.get(i));
                        }
                        System.out.println("-----------------------------------------------");
                        break;

                    case MARK:
                        int markIndex = CommandType.extractIndex(input, command);
                        list.get(markIndex - 1).setIsDone(true);
                        System.out.println("-----------------------------------------------");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(markIndex - 1));
                        System.out.println("-----------------------------------------------");
                        break;

                    case UNMARK:
                        int unmarkIndex = CommandType.extractIndex(input, command);
                        list.get(unmarkIndex - 1).setIsDone(false);
                        System.out.println("-----------------------------------------------");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(list.get(unmarkIndex - 1));
                        System.out.println("-----------------------------------------------");
                        break;

                    case DELETE:
                        int deleteIndex = CommandType.extractIndex(input, command);
                        System.out.println("-----------------------------------------------");
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(list.get(deleteIndex - 1));
                        list.remove(deleteIndex - 1);
                        System.out.printf("Now you have %d tasks in the list\n", list.size());
                        System.out.println("-----------------------------------------------");
                        break;

                    case DEADLINE:
                        String deadlineDesc = input.substring(0, input.toLowerCase().indexOf("/by"));
                        String due = input.substring(input.toLowerCase().indexOf("/by") + 4);
                        list.add(new Deadline(deadlineDesc, due));
                        System.out.println("-----------------------------------------------");
                        System.out.printf("Got it. I've added this task: \n%s\n", list.get(list.size() - 1));
                        System.out.printf("Now you have %d tasks in the list\n", list.size());
                        System.out.println("-----------------------------------------------");
                        break;

                    case EVENT:
                        String eventDesc = input.substring(0, input.toLowerCase().indexOf("/from"));
                        String from = input.substring(input.toLowerCase().indexOf("/from") + 6, input.toLowerCase().indexOf("/to") - 1);
                        String to = input.substring(input.toLowerCase().indexOf("/to") + 4);
                        list.add(new Event(eventDesc, from, to));
                        System.out.println("-----------------------------------------------");
                        System.out.printf("Got it. I've added this event: \n%s\n", list.get(list.size() - 1));
                        System.out.printf("Now you have %d tasks in the list\n", list.size());
                        System.out.println("-----------------------------------------------");
                        break;

                    case TODO:
                        String todoDesc = input;
                        list.add(new ToDo(todoDesc));
                        System.out.println("-----------------------------------------------");
                        System.out.printf("Got it. I've added this task: \n%s\n", list.get(list.size() - 1));
                        System.out.printf("Now you have %d tasks in the list\n", list.size());
                        System.out.println("-----------------------------------------------");
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

                DataManager.updateMemory(list);

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
     * Enum representing the types of commands that can be issued to manage tasks.
     */
    public enum CommandType {
        LIST, MARK, UNMARK, DELETE, BYE, EVENT, DEADLINE, TODO;

        /**
         * Determines the command type based on user input.
         *
         * @param input The user input string.
         * @return The corresponding CommandType.
         */
        public static CommandType getCommand(String input) {
            if (input.equalsIgnoreCase("list")) {
                return LIST;
            } else if (input.toLowerCase().startsWith("mark ")) {
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
         * Extracts the index from a command string.
         *
         * @param input The user input string.
         * @param command The CommandType.
         * @return The extracted index as an integer.
         * @throws NumberFormatException If the input is not a valid number.
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
    }

}
