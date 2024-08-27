package demurebot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DemureBot {
    private TaskList list;
    private final Storage storage;
    private final Ui ui;

    public DemureBot() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.list = null;
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);

        // check if user ended session
        boolean isFinished = false;

        // introduction to chatbot
        this.ui.displayStart();

        // check if data folder exists if not create it
        String folderPath = "./data";
        this.storage.checkFolder(folderPath);

        // check if saved data exists if not create it
        String filePath = "./data/tasks.txt";
        boolean savedDataExists = this.storage.checkFile(filePath);

        // load saved data if exists
        if (savedDataExists) {
            try {
                // fetch list of items to do
                this.list = new TaskList(this.storage.load(filePath));
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            } catch (DemureBotException e) {
                System.out.println("Error loading task: " + e.getMessage());
            }
        }

        if (list == null) {
            this.list = new TaskList(new ArrayList<>());
        }

        // while user hasn't ended session
        while (!isFinished) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                // end session
                isFinished = true;
            } else if (command.equals("list")) {
                // list all tasks
                for (int i = 0; i < list.getSize(); i++) {
                    Task task = list.getTask(i);
                    System.out.println((i + 1) + "." + task);
                }
            } else {
                try {
                    Parser.parse(command, list, this.ui);
                } catch (DemureBotException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // close scanner and end session
        scanner.close();
        this.ui.displayEnd();

        // save task list
        this.storage.save(filePath, this.list);
    }

    public static void main(String[] args) {
        new DemureBot().run();
    }
}
