package yoda;

import yoda.commands.Command;
import yoda.exceptions.YodaException;

public class Yoda {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Yoda(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (YodaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        String welcomeMessage = "Hello! For you, what can I do?";
        Parser parser = new Parser();

        ui.printLine();
        System.out.println(welcomeMessage);
        ui.printLine();

        while (true) {
            String input = ui.getNextLine();
            try {
                Command command = parser.handle(input, tasks);
                ui.printLine();
                command.run();
                ui.printLine();
                storage.saveTasks(tasks.getTasks());
            } catch (YodaException e) {
                ui.printLine();
                System.out.println(e.getMessage());
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Yoda("data/tasks.txt").run();
    }

}
