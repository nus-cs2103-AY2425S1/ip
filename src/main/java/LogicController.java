import java.util.Scanner;

public class LogicController {
    enum Actions {
        NOTHING
    }

    private static final DisplayHandler display = new DisplayHandler();
    private Actions currentAction = Actions.NOTHING;
    private boolean isRunning = false;
    private boolean isAwaitingInput = false;

    private static final TodoList todoList = new TodoList();

    public void begin() {
        display.output(new String[]{
                "Hello! I'm " + Oyster.CHATBOT_NAME,
                "What can I do for you?"
        });

        isRunning = true;

        awaitInput();
    }

    private void awaitInput() {
        if (!isRunning) return;

        isAwaitingInput = true;

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        String input = scanner.nextLine();

        isAwaitingInput = false;

        parseInput(input);
    }

    private void parseInput(String input) {
        String trimmed = input.toLowerCase().trim();

        switch (trimmed) {
            case "bye":
                display.output("See you again! " + Oyster.CHATBOT_EMOJI);
                isRunning = false;
                break;
            case "list":
                if (todoList.isEmpty()) {
                    display.output("Oops, nothing to see here!");
                } else {
                    display.output(new String[]{
                            "Here is your current list!",
                            todoList.toString()
                    });
                }
                break;
            case "mark":
                if (todoList.isEmpty()) {
                    display.output("Oops, nothing to see here!");
                } else {
                    display.output(new String[]{
                            "Here is your current list!",
                            todoList.toString()
                    });
                }
                break;
            default:
                todoList.insert(input);
                display.output("Item added: " + input);
                // display.output("Oh no! I'm afraid I don't understand...");
        }

        awaitInput();
    }
}
