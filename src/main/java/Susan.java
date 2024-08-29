import config.Config;
import data.CSVTaskDAO;
import data.TaskDAO;
import features.command.CommandHandler;
import features.task.TaskManagement;
import utils.Utils;

import java.util.*;

public class Susan {
    private TaskManagement tm;
    private CommandHandler ch;
    private Scanner sc;

    private Susan(TaskDAO taskDAO) {
        tm = new TaskManagement(taskDAO);
        sc = new Scanner(System.in);
        ch = new CommandHandler(tm);
    }

    public static Susan init() throws Exception {
        TaskDAO taskDAO = new CSVTaskDAO();
        return new Susan(taskDAO);
    }

    public static void main(String[] args) {
        try {
            Susan s = Susan.init();
            s.run();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void run() {
        intro();

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            ch.handleCommand(command);
            command = sc.nextLine();
        }

        outro();
    }

    private void intro() {
        System.out.println(Config.logo);
        Utils.printItem(Config.intro);
    }

    private void outro() {
        Utils.printItem(Config.outro);
    }
}

