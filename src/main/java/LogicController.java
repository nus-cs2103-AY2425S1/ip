import java.util.Scanner;

public class LogicController {
    enum Actions {
        NOTHING
    }

    private static final DisplayHandler display = new DisplayHandler();
    private Actions currentAction = Actions.NOTHING;
    private boolean isRunning = false;
    private boolean isAwaitingInput = false;

    private Scanner inputScanner = new Scanner(System.in);

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

        String input = inputScanner.nextLine();

        isAwaitingInput = false;

        parseInput(input);
    }

    private void parseInput(String input) {
        Scanner scanner = new Scanner(input);
        String command = scanner.next().toLowerCase();

        switch (command) {
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
                int markIndex = scanner.nextInt() - 1;
                todoList.mark(markIndex);
                display.output(new String[]{
                        "Well done on completing the task!",
                        todoList.getTask(markIndex).toString()
                });
                break;
            case "unmark":
                int unmarkIndex = scanner.nextInt() - 1;
                todoList.unmark(unmarkIndex);
                display.output(new String[]{
                        "I have unmarked the task!",
                        todoList.getTask(unmarkIndex).toString()
                });
                break;
            default:
                todoList.insert(input);
                display.output("Item added: " + input);
                // display.output("Oh no! I'm afraid I don't understand...");
        }

        scanner.close();
        awaitInput();
    }
}
