import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    private String text;

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    public Parser() {

    }

    public void parseText(Scanner scanner) throws MaxException {
        boolean running = true;

        while (running) {
            String text = scanner.nextLine().trim();
            try {
                if (text.equals("bye")) {
                    running = false;
                } else if (text.equals("list")) {
                    ui.list(taskList.getTasks());
                } else if (text.startsWith("mark")) {
                    int index = Integer.parseInt(text.replace("mark ", "")) - 1;
                    Task task = taskList.getTask(index);
                    task.markDone();
                    ui.printMarkDone(task);
                } else if (text.startsWith("unmark")) {
                    int index = Integer.parseInt(text.replace("unmark ", "")) - 1;
                    Task task = taskList.getTask(index);
                    task.markNotDone();
                    ui.printMarkNotDone(task);
                } else if (text.startsWith("deadline")) {
                    handleDeadline(text);
                } else if (text.startsWith("todo")) {
                    handleTodo(text);
                } else if (text.startsWith("event")) {
                    handleEvent(text);
                } else if (text.startsWith("delete")){
                    int index = Integer.parseInt(text.replace("delete ", "")) - 1;
                    taskList.deleteTask(index);
                } else {
                    throw new MaxException("What does that mean?:( Begin with todo, event, or deadline.");
                }
                this.storage.saveTasks(taskList.getTasks());
            } catch (MaxException e) {
                ui.printLine();
                ui.printMessage(e.getMessage());
            }
        }


    }

    private void handleDeadline(String text) throws MaxException {
        String[] temp = text.replace("deadline ", "").split(" /by ");
        if (temp.length != 2) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
        checkTask(temp[0].trim());
        checkTask(temp[1].trim());

        LocalDateTime LDT = parseDate(temp[1]);
        Deadline deadline = (LDT != null) ? new Deadline(temp[0], LDT) : new Deadline(temp[0], temp[1]);
        taskList.addTask(deadline);
        ui.printTaskTypeAdded(deadline, taskList.getSize());
    }

    private void handleEvent(String text) throws MaxException {
        String[] temp = text.replace("event ", "").split(" /from ");
        if (temp.length != 2) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
        checkTask(temp[0].trim());
        checkTask(temp[1].trim());

        Event event = new Event(temp[0], temp[1]);
        taskList.addTask(event);
        ui.printTaskTypeAdded(event, taskList.getSize());
    }

    private void handleTodo(String text) throws MaxException {
        String temp = text.replace("todo", "").trim();
        checkTask(temp);

        Todo todo = new Todo(temp);
        taskList.addTask(todo);
        ui.printTaskTypeAdded(todo, taskList.getSize());
    }

    public LocalDateTime parseDate(String date) throws MaxException {
        DateTimeFormatter converter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime LDT = LocalDateTime.parse(date, converter);
            return LDT;
        } catch (DateTimeParseException e){
            throw new MaxException("Invalid date format! Please use d/M/yyyy HHmm. "
                    + "For example, '2/12/2024 1800'");
        }
    }

    public void checkTask(String todo) throws MaxException {
        if (todo.isEmpty()) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
    }
}
