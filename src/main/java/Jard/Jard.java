package Jard;

import java.util.List;
import java.util.ArrayList;

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

    /**
     * Finds tasks that contain the word after find.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A list of tasks that match the keyword.
     */

    private List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
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
                } else if (command.equals("find")) {
                    String keyword = inputParts[1];
                    List<Task> matchingTasks = findTasks(keyword);
                    ui.showFindResult(matchingTasks);
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
