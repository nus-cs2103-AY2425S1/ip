package demurebot;

import demurebot.command.Command;
import demurebot.storage.Storage;
import demurebot.task.Task;
import demurebot.task.TaskList;
import demurebot.ui.Parser;
import demurebot.ui.Ui;

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

        if (this.list == null) {
            this.list = new TaskList(new ArrayList<>());
        }

        // while user hasn't ended session
        while (!isFinished) {
            String command = scanner.nextLine();
            try {
                Command c = Parser.parse(command);
                c.execute(this.list, this.ui);
                isFinished = c.isExit();
            } catch (DemureBotException e) {
                System.out.println(e.getMessage());
            }
        }

        // close scanner and end session
        scanner.close();

        // save task list
        this.storage.save(filePath, this.list);
    }

    public static void main(String[] args) {
        new DemureBot().run();
    }
}
