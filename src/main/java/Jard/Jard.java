package Jard;

import java.util.List;

public class Jard {
    private Storage storage;
    private List<Task> tasks;
    private Ui ui;

    public Jard(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.loadTasks();
    }

    public static class JardException extends RuntimeException {
        public JardException(String message) {
            super(message);
        }
    }

    public void run() {
        ui.showWelcome();
        while (true) {
            String input = ui.readCommand();
            String[] inputParts = Parser.parseCommand(input);
            String command = inputParts[0];

            try {
                if (command.equals("bye")) {
                    ui.showBye();
                    break;
                } else if (command.equals("list")) {
                    ui.showTaskList(tasks);
                } else if (command.equals("mark")) {
                    int taskIndex = Parser.parseTaskIndex(inputParts[1], tasks.size());
                    Task task = tasks.get(taskIndex);
                    task.markAsDone();
                    ui.showMarkTask(task);
                } else if (command.equals("unmark")) {
                    int taskIndex = Parser.parseTaskIndex(inputParts[1], tasks.size());
                    Task task = tasks.get(taskIndex);
                    task.markAsNotDone();
                    ui.showUnmarkTask(task);
                } else if (command.equals("delete")) {
                    int taskIndex = Parser.parseTaskIndex(inputParts[1], tasks.size());
                    Task task = tasks.remove(taskIndex);
                    ui.showDeleteTask(task, tasks.size());
                } else {
                    Task task = Parser.parseTask(input);
                    tasks.add(task);
                    ui.showAddTask(task, tasks.size());
                }
                storage.saveTasks(tasks);
            } catch (Jard.JardException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        new Jard("./data/jard.txt").run();
    }
}
