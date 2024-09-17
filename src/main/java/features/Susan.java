package features;

import data.CSVTaskDAO;
import data.TaskDAO;
import features.command.Command;
import features.command.CommandHandler;
import features.task.TaskManagement;
import features.ui.Ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Susan {
    private TaskManagement tm;
    private CommandHandler ch;
    private Scanner sc;
    private Ui ui;

    private Susan(TaskDAO taskDAO) {
        tm = new TaskManagement(taskDAO);
        ch = new CommandHandler(tm);
        ui = new Ui(new Scanner(System.in));
    }

    public static Susan init() throws Exception {
        try {
            TaskDAO taskDAO = new CSVTaskDAO();
            return new Susan(taskDAO);
        } catch (FileNotFoundException e) {
            System.out.println("Usage: Place data.csv file in the same directory as this file.");
            System.exit(1);
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            Susan s = Susan.init();
            s.run();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command command = new Command(input);
        return ch.handleCommand(command);
    }

    /**
     * Runs Susan bot using the UI.
     */
    public void run() {
        ui.intro();

        Command command = ui.readCommand();
        while (!command.isExitCommand()) {
            ch.handleCommand(command);
            ui.updateCommand(command);
        }

        ui.outro();
    }
}

