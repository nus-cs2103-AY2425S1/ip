import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DemureBot {
    private static TaskList list;
    private static Storage storage;
    private static Ui ui;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // check if user ended session
        boolean isFinished = false;

        DemureBot.storage = new Storage();
        DemureBot.ui = new Ui();

        // introduction to chatbot
        DemureBot.ui.displayStart();

        // check if data folder exists if not create it
        String folderPath = "./data";
        DemureBot.storage.checkFolder(folderPath);

        // check if saved data exists if not create it
        String filePath = "./data/tasks.txt";
        boolean savedDataExists = DemureBot.storage.checkFile(filePath);

        // load saved data if exists
        if (savedDataExists) {
            try {
                // fetch list of items to do
                DemureBot.list = new TaskList(DemureBot.storage.load(filePath));
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            } catch (DemureBotException e) {
                System.out.println("Error loading task: " + e.getMessage());
            }
        }

        if (list == null) {
            DemureBot.list = new TaskList(new ArrayList<>());
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
                    Parser.parse(command, list, DemureBot.ui);
                } catch (DemureBotException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // close scanner and end session
        scanner.close();
        DemureBot.ui.displayEnd();

        // save task list
        DemureBot.storage.save(filePath, DemureBot.list);
    }
}
