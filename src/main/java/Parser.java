import java.util.Scanner;

public class Parser {
    private TaskList tasks;
    private Ui ui;

    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public boolean processInput(String input) {
        String[] inputArray = input.split(" ", 2);

        ui.printLine();

        switch (inputArray[0]) {
            case "bye":
                return true;

            case "list":
                tasks.printList();
                break;

            case "mark":
                tasks.markItem(extractTaskIndex(input));
                break;

            case "unmark":
                tasks.unmarkItem(extractTaskIndex(input));
                break;

            case "delete":
                tasks.removeTask(extractTaskIndex(input));
                break;

            case "todo":
                String taskTodo = input.replace("todo", "").trim();
                if (taskTodo.isEmpty()) {
                    System.out.println("found no description of task! do try again");
                    break;
                }
                Todo newTodo = new Todo(taskTodo, false);
                tasks.addTask(newTodo);
                System.out.println("adding todo to list : " + taskTodo);
                break;

            case "deadline":
                String taskDeadline = input.replace("deadline", "").trim();
                String[] partsDeadline = taskDeadline.split("\\s*/by\\s*");
                if (partsDeadline.length == 1) {
                    System.out.println("found no description of task! do try again");
                    break;
                }
                Deadline newDeadline = new Deadline(partsDeadline[0],
                        false,
                        removeSpace(partsDeadline[1]));
                tasks.addTask(newDeadline);
                System.out.println("adding deadline to list : " + newDeadline.getName());
                break;

            case "event":
                String taskEvent = input.replace("event", "").trim();
                String[] partsEvent = taskEvent.split("\\s*/from\\s*|\\s*/to\\s*");
                if (partsEvent.length == 1) {
                    System.out.println("found no description of task! do try again");
                    break;
                } else if (partsEvent.length < 3 || partsEvent[1].isEmpty()) {
                    System.out.println("dates are incomplete! do try again");
                    break;
                }

                Event newEvent = new Event(partsEvent[0], false,
                        partsEvent[1], partsEvent[2]);
                tasks.addTask(newEvent);
                System.out.println("adding event to list : " + newEvent.getName());
                break;

            default:
                System.out.println("cannot read : " + inputArray[0]);
                break;
        }
        ui.printLine();
        return false;
    }

    private static String removeSpace(String line) {
        if (line.isEmpty()) {
            return line;
        }
        return line.substring(0, line.length() - 1);
    }

    private int extractTaskIndex(String userInput) {
        String[] parts = userInput.split(" ");
        return Integer.parseInt(parts[1]);
    }

}
