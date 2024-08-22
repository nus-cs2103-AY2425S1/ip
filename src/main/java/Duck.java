public class Duck {
    private static final String CHATBOT_NAME = "Duck";
    private static final String TEXT_SEPARATOR = "____________________________________________________________";
    private static final String TEXT_SEPARATOR_WITH_NEWLINE = TEXT_SEPARATOR + "\n";

    // Standard messages
    private static final String GREETING = String.format("Hello! I'm %s\n", CHATBOT_NAME)
            + "What can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";

    // List to store user inputs
    private static final TaskList STORED_TEXT = new TaskList();

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

    public static void main(String[] args) {
        // Start scanner
        Reader reader = new Reader(System.in);

        // Print greeting
        printAsResponse(GREETING);

        // User input loop
        while (true) {
            System.out.println("");

            String line = reader.getLine();

            if (line.equals("bye")) {
                printAsResponse(GOODBYE);
                break;
            } else if (line.equals("list")) {
                printAsResponse(STORED_TEXT.toString());
            } else {
                STORED_TEXT.addItem(line);

                String response = "added: " + line;
                printAsResponse(response);
            }
        }

        // Close Scanner
        reader.close();
    }
}
