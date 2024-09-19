package jarvis.logic;

import java.lang.reflect.InvocationTargetException;

/**
 * The {@code Parser} class is responsible for parsing user input and executing
 * the corresponding commands on the {@link TaskList}.
 */
public class Parser {

    /**
     * Parses the user input and executes the corresponding command on the given {@code TaskList}.
     *
     * @param input    the user input string to be parsed.
     * @param tasklist the {@code TaskList} object on which the commands will be executed.
     */
    public static void parse(String input, TaskList tasklist) {
        input = input.trim().toLowerCase();  // Normalize input to lowercase and remove leading/trailing spaces

        if (input.equals("bye")) {
            return;  // Early exit if input is "bye"
        }

        if (input.startsWith("mark") || input.startsWith("unmark")
                || input.startsWith("delete")) {
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                return;  // Early exit if the input doesn't have two parts
            }

            try {
                int taskIndex = Integer.parseInt(parts[1]);
                if (input.startsWith("mark")) {
                    tasklist.mark(taskIndex);
                } else if (input.startsWith("unmark")) {
                    tasklist.unmark(taskIndex);
                } else if (input.startsWith("delete")) {
                    tasklist.handleDelete(taskIndex);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("index error!");
            } catch(NumberFormatException e) {
                System.out.println("wrong input format!\n");
            }

            return;  // Early exit after handling mark, unmark, or delete
        }

        if (input.startsWith("find")) {
            String toFind = input.substring(5);
            tasklist.find(toFind);
        }
    }
    /**
     * Parses the input command and returns the corresponding result as a dialog string.
     *
     * @param input    the user input string.
     * @param tasklist the TaskList to operate on.
     * @return the dialog string corresponding to the input command.
     */
    public static String parseDialog(String input, TaskList tasklist) {
        StringBuilder result = new StringBuilder();

        input = input.trim().toLowerCase();  // Normalize input: trim whitespace and convert to lowercase

        if (input.equals("list")) {
            result.append(tasklist.list());
            return result.toString();
        }

        if (input.equals("bye")) {
            return result.toString();  // Early exit if input is "bye"
        }

        if (input.startsWith("mark") || input.startsWith("unmark")
                || input.startsWith("delete")) {
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                return result.append("Invalid input format\n").toString();
            }

            try {
                int taskIndex = Integer.parseInt(parts[1]);  // Parse task index
                if (input.startsWith("mark")) {
                    result.append(tasklist.markDialog(taskIndex));
                } else if (input.startsWith("unmark")) {
                    result.append(tasklist.unmarkDialog(taskIndex));
                } else if (input.startsWith("delete")) {
                    result.append(tasklist.deleteDialog(taskIndex));
                    tasklist.handleDelete(taskIndex);
                }
            } catch (IndexOutOfBoundsException e) {
                result.append("index error!\n");
            } catch(NumberFormatException e) {
                result.append("wrong input format!\n");
            }

            return result.toString();  // Return after processing mark, unmark, or delete
        }

        if (input.startsWith("find")) {
            String toFind = input.substring(4).trim();
            toFind = toFind.trim();// Extract the search query after "find"
            result.append(tasklist.find(toFind));
            return result.toString();
        }

        // Process add operation
        try {
            result.append(tasklist.add(input)).append("\n");
        } catch(NullPointerException e){
            result.append("You are missing one or more fields!");
        } catch (ArrayIndexOutOfBoundsException e) {
            result.append("Im sorry, I don't understand this!\n ").append(input).append("\n");
        } catch (IndexOutOfBoundsException e) {
            result.append("Im sorry, I don't understand this!\n ").append(input).append("\n");
        } catch (IllegalArgumentException e) {
            result.append("This input is not quite right!.\n");
        } catch (Exception e) {
            result.append("error\n");
        }
        return result.toString();
    }
}
