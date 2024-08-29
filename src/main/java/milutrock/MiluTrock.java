package milutrock;

import java.util.Scanner;

import milutrock.exceptions.UnknownCommandException;

public class MiluTrock {
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    public MiluTrock(String path) {
        taskList = new TaskList();
        ui = new Ui("MiluTrock", taskList);
        parser = new Parser(taskList, ui);
        storage = new Storage(path, parser);
    }

    public void run() {
        ui.printBanner();

        storage.loadTasks();

        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;
        while (shouldContinue && scanner.hasNext()) {
            String input = scanner.nextLine();

            ui.printLineBreak();

            try {
                shouldContinue = parser.parseCommand(input);
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            }

            ui.printLineBreak();
        }
        scanner.close();

        storage.storeTasks();
    }

    public static void main(String[] args) {
        new MiluTrock("./data.txt").run();
    }
}
