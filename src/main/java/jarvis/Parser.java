package jarvis;

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
        if (input.equals("list")) {
            tasklist.list();
        } else if (!input.equals("bye")) {
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
            } else {
                try {
                    tasklist.add(input);
                    String result = tasklist.acknowledge();
                    System.out.println("__________________________________\n" + result + "\n" +
                            "__________________________________");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("bad input: invalid format " + input);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("bad input: invalid format " + input);
                } catch (IllegalArgumentException e) {
                } catch (Exception e) {
                    System.out.println("error");
                }
            }
        }
    }
}
