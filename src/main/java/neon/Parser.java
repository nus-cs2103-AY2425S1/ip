package neon;

import neon.Deadline;
import neon.Event;

import java.util.Objects;

public class Parser {
    private TaskList tasks;
    private Ui ui;

    private String lastInput;

    private Task deleteCache;

    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public Object[] processInput(String input) {
        String response;
        String[] inputArray = input.split(" ", 2);

        switch (inputArray[0]) {
        case "bye":
            return parseBye();

        case "list":
            response = tasks.printList();
            break;

        case "mark":
            assert  extractTaskIndex(input) <= tasks.getSize() : "input out of range";
            if (extractTaskIndex(input) > tasks.getSize()) {
                response = "input is out of range! do try again\n";
                break;
            }
            response = tasks.markItem(extractTaskIndex(input));
            break;

        case "unmark":
            assert  extractTaskIndex(input) <= tasks.getSize() : "input out of range";
            if (extractTaskIndex(input) > tasks.getSize()) {
                response = "input is out of range! do try again\n";
                break;
            }
            response = tasks.unmarkItem(extractTaskIndex(input));
            break;

        case "delete":
            assert  extractTaskIndex(input) <= tasks.getSize() : "input out of range";
            if (extractTaskIndex(input) > tasks.getSize()) {
                response = "input is out of range! do try again\n";
                break;
            }
            deleteCache = tasks.getTask(extractTaskIndex(input) - 1);
            response = tasks.removeTask(extractTaskIndex(input));
            break;

        case "undo":
            String[] undoInputArray = lastInput.split(" ", 2);
            String newInput;

            switch (undoInputArray[0]) {
            case "undo":
                response = "last input was an undo! unable to undo an undo\n";
                break;

            case "mark":
                newInput = lastInput.replace("mark", "unmark");
                return processInput(newInput);

            case "unmark":
                newInput = lastInput.replace("unmark", "mark");
                return processInput(newInput);

            case "delete":
                tasks.addTask(deleteCache);
                response = "task restored!\n";
                break;

            case "todo":
                tasks.removeTask(tasks.getSize());
                response = "todo removed!\n";
                break;

            case "deadline":
                tasks.removeTask(tasks.getSize());
                response = "deadline removed!\n";
                break;

            case "event":
                tasks.removeTask(tasks.getSize());
                response = "event removed!\n";
                break;

            default:
                response = "last input cannot be undone!\n";
                break;
            }
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
                message.append(foundTask.getTask(i) + "\n");
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

        lastInput = input;
        return new Object[] {false, response};
    }

    private Object[] parseBye() {
        return new Object[] {true, ui.printClosingLine()};
    }

    private int extractTaskIndex(String userInput) {
        String[] parts = userInput.split(" ");
        return Integer.parseInt(parts[1]);
    }

}
