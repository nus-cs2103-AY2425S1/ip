public class Duck {
    private static final String CHATBOT_NAME = "Duck";
    private static final String TEXT_SEPARATOR = "____________________________________________________________";
    private static final String TEXT_SEPARATOR_WITH_NEWLINE = TEXT_SEPARATOR + "\n";

    // Standard messages
    private static final String GREETING = String.format("Hello! I'm %s\n", CHATBOT_NAME)
            + "What can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";

    // List to store user inputs
    private static final TaskList TASKS = new TaskList();

    private static String addSeparators(String text) {
        return TEXT_SEPARATOR_WITH_NEWLINE
                + text + "\n"
                + TEXT_SEPARATOR_WITH_NEWLINE;
    }

    private static String indentText(String text, int indentLevel) {
        StringBuilder indentedText = new StringBuilder();
        String[] lines = text.split("\n");

        for (String line : lines) {
            indentedText.append(" ".repeat(indentLevel)).append(line).append("\n");
        }

        // Convert to String and trim the last newline character
        return indentedText.toString().replaceAll("[\n\r]$", "");
    }

    private static String formatAsResponse(String text) {
        return indentText(addSeparators(indentText(text, 1)), 4);
    }

    private static void printAsResponse(String text) {
        System.out.println(formatAsResponse(text));
    }

    private static void handleNewTask(Task task) {
        TASKS.addTask(task);
        String response = "Got it. I've added this task:\n"
                + indentText(task.toString(), 2) + "\n"
                + "Now you have " + TASKS.getTaskCount() + " tasks in the list.";
        printAsResponse(response);
    }

    public static void main(String[] args) {
        // Start scanner
        Reader reader = new Reader(System.in);

        // Print greeting
        printAsResponse(GREETING);

        // User input loop
        while (true) {
            System.out.println("");
            String line = reader.getWholeLine();
            CommandBuffer lineBuffer = new CommandBuffer(line);

            // "bye" and "list" will only work if they are the only word in the line
            // e.g. "bye bye" would not work
            if (Command.BYE.equalsName(line)) {
                printAsResponse(GOODBYE);
                break;
            } else if (Command.LIST.equalsName(line)) {
                String response = "Here are the tasks in your list:\n"
                        + TASKS.toString();
                printAsResponse(response);
            } else {
                String command = lineBuffer.getWord();

                // "mark" / "unmark" commands
                if (Command.MARK.equalsName(command)) {
                    int taskLabel = lineBuffer.getInt();
                    Task task = TASKS.getItem(taskLabel);
                    task.markAsDone();

                    String response = "Nice! I've marked this task as done:\n"
                            + indentText(task.toString(), 2);
                    printAsResponse(response);
                } else if (Command.UNMARK.equalsName(command)) {
                    int taskLabel = lineBuffer.getInt();
                    Task task = TASKS.getItem(taskLabel);
                    task.markAsNotDone();

                    String response = "OK, I've marked this task as not done yet:\n"
                            + indentText(task.toString(), 2);
                    printAsResponse(response);
                }

                // Tasks
                else if (Command.TODO.equalsName(command)) {
                    String taskPart = lineBuffer.getRemainingLine();
                    Task task = new TaskTodo(taskPart);
                    handleNewTask(task);
                } else if (Command.DEADLINE.equalsName(command)) {
                    String taskPart = lineBuffer.getUntilAndRemovePattern("/by");
                    String deadlinePart = lineBuffer.getRemainingLine();
                    Task task = new TaskDeadline(taskPart, deadlinePart);
                    handleNewTask(task);
                } else if (Command.EVENT.equalsName(command)) {
                    String taskPart = lineBuffer.getUntilAndRemovePattern("/from");
                    String fromPart = lineBuffer.getUntilAndRemovePattern("/to");
                    String toPart = lineBuffer.getRemainingLine();
                    Task task = new TaskEvent(taskPart, fromPart, toPart);
                    handleNewTask(task);
                } else {
                    printAsResponse("Oops, I do not understand you.");
                }
            }
        }

        // Close Scanner
        reader.close();
    }
}
