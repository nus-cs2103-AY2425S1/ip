package pixy;

import pixy.parser.Parser;
import pixy.storage.Storage;
import pixy.tasks.TaskList;
import pixy.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Pixy {

    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Ui ui;
    private Scanner sc;

    public Pixy(String filePath) {
        storage = new Storage(filePath);
        parser = new Parser();
        ui = new Ui();
        sc = new Scanner(System.in);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    private String inputTask() {
        return sc.nextLine();
    }

    private void saveTasks() {
        try {
            storage.save(tasks.getList());
        } catch (IOException e) {
            ui.showError("An error occurred while saving tasks.");
        }
    }

    public void run() {
        ui.showWelcomeMessage();

        while (true) {
            String command = inputTask();
            boolean exit = parser.parseCommand(command, tasks, ui);
            if (exit) {
                ui.showGoodbyeMessage();
                break;
            }
            saveTasks();
        }
    }

    public static void main(String[] args) {
        new Pixy("./data/tasks.txt").run();
    }
}
