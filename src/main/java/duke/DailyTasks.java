package duke;

import duke.commands.Command;
import duke.tasks.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class DailyTasks {
    private final TaskList taskList;
    private Ui ui;
    private Storage storage;

    public DailyTasks() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            List<Task> tasks = storage.loadStateFileToTasksList();
            this.taskList.setTasks(tasks);
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    public static void main(String[] args) {
        DailyTasks dailyTasks = new DailyTasks();

        try {
            dailyTasks.start();
        } finally {
            dailyTasks.end();
        }
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
        this.ui.showWelcome();

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equals("bye")) {
                this.ui.showGoodbye();
                break;
            }

//            handleCommand(userInput);
            Command command = Parser.parseUserInput(userInput);
            command.execute(this.taskList, this.ui, this.storage);
        }
    }

    private void end() {
        try {
            Storage.saveTasksListToStateFile(this.taskList.getTasks());
            System.out.println("saved");
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }
}
