package jarvis.logic;

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
        if (!input.equals("bye")) {
            // Only print if it's not "bye"
            if (input.startsWith("mark") || input.startsWith("unmark") ||
                    input.startsWith("delete")) {
                String[] parts = input.split(" ");
                try {
                    if (parts.length == 2) {
                        int taskIndex = Integer.parseInt(parts[1]);
                        if (input.startsWith("mark")) {
                            tasklist.mark(taskIndex);
                        } else if (input.startsWith("unmark")) {
                            tasklist.unmark(taskIndex);
                        } else if (input.startsWith("delete")) {
                            tasklist.handleDelete(taskIndex);
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("index error!");
                }

            } else if (input.startsWith("find")) {
                String toFind = input.substring(5);
                tasklist.find(toFind);
            }
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

        if (input.equals("list")) {
            result.append(tasklist.list());
        } else if (!input.equals("bye")) {
            // Only return if it's not "bye"
            if (input.startsWith("mark") || input.startsWith("unmark") ||
                    input.startsWith("delete")) {
                String[] parts = input.split(" ");
                try {
                    if (parts.length == 2) {
                        int taskIndex = Integer.parseInt(parts[1]);
                        if (input.startsWith("mark")) {
                            result.append(tasklist.markDialog(taskIndex));
                        } else if (input.startsWith("unmark")) {
                            result.append(tasklist.unmarkDialog(taskIndex));
                        } else if (input.startsWith("delete")) {
                            result.append(tasklist.deleteDialog(taskIndex));
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    result.append("index error!\n");
                }

            } else if (input.startsWith("find")) {
                String toFind = input.substring(5).trim();
                result.append(tasklist.find(toFind));
            } else {
                try {
                    result.append(tasklist.add(input)).append("\n");
                } catch (ArrayIndexOutOfBoundsException e) {
                    result.append("bad input: invalid format ").append(input).append("\n");
                } catch (IndexOutOfBoundsException e) {
                    result.append("bad input: invalid format ").append(input).append("\n");
                } catch (IllegalArgumentException e) {
                    result.append("Invalid input.\n");
                } catch (Exception e) {
                    result.append("error\n");
                }
            }
        }
        parse(input, tasklist);
        return result.toString();
    }
}
