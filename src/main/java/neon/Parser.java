package neon;

import neon.Deadline;
import neon.Event;

public class Parser {
    private TaskList tasks;
    private Ui ui;

    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public Object[] processInput(String input) {
        String response = "nothing here for now";
        String[] inputArray = input.split(" ", 2);

        ui.printLine();

        switch (inputArray[0]) {
        case "bye":
            return new Object[] {true, ui.printClosingLine()};

        case "list":
            response = tasks.printList();
            break;

        case "mark":
            assert  extractTaskIndex(input) <= tasks.getSize() : "input out of range";
            response = tasks.markItem(extractTaskIndex(input));
            break;

        case "unmark":
            assert  extractTaskIndex(input) <= tasks.getSize() : "input out of range";
            response = tasks.unmarkItem(extractTaskIndex(input));
            break;

        case "delete":
            assert  extractTaskIndex(input) <= tasks.getSize() : "input out of range";
            response = tasks.removeTask(extractTaskIndex(input));
            break;

        case "find":
            String taskToFind = input.replace("find", "").trim();
            assert !taskToFind.isEmpty() : "no task found";
            if (taskToFind.isEmpty()) {
                response = "found no description of task! do try again\n";
                break;
            }
            StringBuilder message = new StringBuilder();
            message.append("list of tasks:\n");

            TaskList foundTask = tasks.findTask(taskToFind);

            for (int i = 0; i < foundTask.getSize(); i++) {
                message.append(foundTask.getTask(i));
            }

            response = message.toString();
            break;

        case "todo":
            String taskTodo = input.replace("todo", "").trim();
            assert  !taskTodo.isEmpty() : "no task found";
            if (taskTodo.isEmpty()) {
                response = "found no description of task! do try again\n";
                break;
            }
            Todo newTodo = new Todo(taskTodo, false);

            tasks.addTask(newTodo);

            response = "adding todo to list : " + taskTodo + "\n";
            break;

        case "deadline":
            String taskDeadline = input.replace("deadline", "").trim();
            String[] partsDeadline = taskDeadline.split("\\s*/by\\s*");
            assert partsDeadline.length != 1 : "no task found";
            if (partsDeadline.length == 1) {
                response = "found no description of task! do try again\n";
                break;
            }
            Deadline newDeadline = new Deadline(partsDeadline[0], false,
                    (partsDeadline[1]));

            tasks.addTask(newDeadline);

            response = "adding deadline to list : " + newDeadline.getName() + "\n";
            break;

        case "event":
            String taskEvent = input.replace("event", "").trim();
            String[] partsEvent = taskEvent.split("\\s*/from\\s*|\\s*/to\\s*");
            assert partsEvent.length != 3 : "incomplete input";
            if (partsEvent.length == 1) {
                response = "found no description of task! do try again";
                break;
            } else if (partsEvent.length < 3 || partsEvent[1].isEmpty()) {
                response = "dates are incomplete! do try again\n";
                break;
            }

            Event newEvent = new Event(partsEvent[0], false,
                    partsEvent[1], partsEvent[2]);

            tasks.addTask(newEvent);

            response = "adding event to list : " + newEvent.getName() + "\n";
            break;

        default:
            response = "cannot read : " + inputArray[0] + "\n";
            break;
        }

        return new Object[] {false, response};
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
