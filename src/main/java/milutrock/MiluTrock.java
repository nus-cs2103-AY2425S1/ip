package milutrock;

import java.util.Scanner;

import milutrock.exceptions.UnknownCommandException;

/**
 * A task management program that interacts with users through a
 * command-line interface.
 */
public class MiluTrock {
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    public MiluTrock(String path) {
        this.taskList = new TaskList();
        this.ui = new Ui("MiluTrock", this.taskList);
        this.parser = new Parser(this.taskList, this.ui);
        this.storage = new Storage(path, this.parser);
    }

    private void run() {
        this.ui.printBanner();

        this.storage.loadTasks();

        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;
        while (shouldContinue && scanner.hasNext()) {
            String input = scanner.nextLine();

            this.ui.printLineBreak();

            try {
                shouldContinue = this.parser.parseCommand(input);
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            }

            this.ui.printLineBreak();
        }
        scanner.close();

        this.storage.storeTasks();
    }

    public static void main(String[] args) {
        new MiluTrock("./data.txt").run();
    }
}
