package colby;

import java.io.IOException;
import java.util.Scanner;

public class Colby {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Colby(String file) {
        ui = new Ui();
        storage = new Storage(file);
        taskList = new TaskList();
    }

    public void run() throws IOException {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String input = scanner.nextLine();
                String command = Parser.parseCommand(input);

                if (command.equalsIgnoreCase("bye")) {
                    ui.showGoodbyeMessage();
                    break;
                } else if (command.equalsIgnoreCase("list")) {
                    ui.showTaskList(taskList);
                } else if (command.startsWith("mark")) {
                    int index = Parser.parseIndex(input);
                    taskList.markTaskAsDone(index);
                    storage.writeToFile(taskList.getTasks());
                    ui.showTaskMarked(taskList.getTask(index));
                } else if (command.startsWith("unmark")) {
                    int index = Parser.parseIndex(input);
                    taskList.markTaskAsUndone(index);
                    storage.writeToFile(taskList.getTasks());
                    ui.showTaskUnmarked(taskList.getTask(index));
                } else if (command.startsWith("todo")) {
                    Task task = Parser.parseToDoTask(input);
                    taskList.addTask(task);
                    storage.appendToFile(task.toString() + "\n");
                    ui.showTaskAdded(task, taskList.size());
                } else if (command.startsWith("deadline")) {
                    Task task = Parser.parseDeadlineTask(input);
                    taskList.addTask(task);
                    storage.appendToFile(task.toString() + "\n");
                    ui.showTaskAdded(task, taskList.size());
                } else if (command.startsWith("event")) {
                    Task task = Parser.parseEventTask(input);
                    taskList.addTask(task);
                    storage.appendToFile(task.toString() + "\n");
                    ui.showTaskAdded(task, taskList.size());
                } else if (command.startsWith("delete")) {
                    int index = Parser.parseIndex(input);
                    Task task = taskList.deleteTask(index);
                    storage.writeToFile(taskList.getTasks());
                    ui.showTaskDeleted(task);
                } else {
                    throw new ColbyException("Sorry!! I'm not sure how to add that to the list for " +
                            "you, try specifying the type of task!");
                }
            }
        } catch (Exception e) {
            String result = e.getMessage();
            System.out.println(result);
        }

        scanner.close();
    }
    public static void main(String[] args) throws IOException {
        new Colby("Data.txt").run();
    }
}