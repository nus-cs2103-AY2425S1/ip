import java.util.NoSuchElementException;
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

    private static final TaskList taskList = new TaskList();

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

        if (!inputScanner.hasNext()) {
            display.output("Oops, please type something!");
            isAwaitingInput = false;
            awaitInput();
        }
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
                if (taskList.isEmpty()) {
                    display.output("Oops, nothing to see here!");
                } else {
                    display.output(new String[]{
                            "Here is your current list!",
                            taskList.toString()
                    });
                }
                break;
            case "mark":
                if (!scanner.hasNextInt()) {
                    display.output("Please input a valid task number!");
                    break;
                }

                try {
                    int markIndex = scanner.nextInt() - 1;
                    taskList.mark(markIndex);
                    display.output(new String[]{
                            "Well done on completing the task!",
                            "\t" + taskList.getTask(markIndex).toString()
                    });
                } catch (RuntimeException e) {
                    display.output("Task number does not exist!");
                }
                break;
            case "unmark":
                if (!scanner.hasNextInt()) {
                    display.output("Please input a valid task number!");
                    break;
                }

                try {
                    int unmarkIndex = scanner.nextInt() - 1;
                    taskList.unmark(unmarkIndex);
                    display.output(new String[]{
                            "I have unmarked the task!",
                            "\t" + taskList.getTask(unmarkIndex).toString()
                    });
                } catch (RuntimeException e) {
                    display.output("Task number does not exist!");
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                Task.TASK_TYPES type = Task.TASK_TYPES.TODO;
                if (command.equals("deadline")) {
                    type = Task.TASK_TYPES.DEADLINE;
                } else if (command.equals("event")) {
                    type = Task.TASK_TYPES.EVENT;
                }

                try {
                    if (!scanner.hasNext()) {
                        throw new TaskFieldException("Description");
                    }

                    display.output(new String[]{
                            "Alright, the task has been added!",
                            "\t" + taskList.insert(type, scanner.nextLine()).toString(),
                            String.format("You now have %s %s!", taskList.length(), taskList.length() == 1 ? "task" : "tasks")
                    });
                } catch (TaskFieldException e) {
                    display.output("Please provide the " + e.field + " field!");
                }
                break;
            case "delete":
                if (!scanner.hasNextInt()) {
                    display.output("Please input a valid task number!");
                    break;
                }

                try {
                    int deleteIndex = scanner.nextInt() - 1;
                    String deletedTask = taskList.pop(deleteIndex).toString();
                    display.output(new String[]{
                            "I have deleted the task!",
                            "\t" + deletedTask,
                            String.format("You now have %s %s!", taskList.length(), taskList.length() == 1 ? "task" : "tasks")
                    });
                    break;
                } catch (RuntimeException e) {
                    display.output("Task number does not exist!");
                }
                break;
            default:
                display.output("Oh no! I'm afraid I don't understand...");
        }

        scanner.close();
        awaitInput();
    }
}
