package terminator;

import terminator.command.CommandParser;
import terminator.command.Command;
import terminator.command.DeadlineCommand;
import terminator.command.DeleteCommand;
import terminator.command.DukeException;
import terminator.command.EventCommand;
import terminator.command.TodoCommand;
import terminator.task.Storage;
import terminator.task.TaskList;

import java.util.Scanner;

public class Terminator {

    private final TaskList taskList;

    private final CommandParser parser;

    private final Ui ui;

    private final Storage storage;

    public Terminator() {
        this.taskList = new TaskList();
        this.parser = new CommandParser();
        this.ui = new Ui();
        this.storage = new Storage();
    }

    private void run() {
        // Load data from storage
        try {
            this.storage.loadDataFromFile(this.taskList);
        } catch (DukeException e) {
            System.out.println("Invalid data format.");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            this.ui.showErrorMsg();
            return;
        }

        // Start main event loop
        this.ui.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            this.ui.printHorizontalLine();
            try {
                Command command = this.parser.parse(input);
                command.execute(this.taskList.getTaskList());
                if (command instanceof TodoCommand
                    || command instanceof EventCommand
                    || command instanceof DeadlineCommand
                    || command instanceof DeleteCommand) {
                    taskList.printTasksRemaining();
                }
            } catch (DukeException de) {
                System.out.println("Error detected: " + de.getMessage());
            }
            this.ui.printHorizontalLine();
            input = sc.nextLine();
        }
        sc.close();

        // Save data before exit
        try {
            taskList.writeToDisk(this.storage);
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        }

        this.ui.showExitMsg();
    }

    public static void main(String[] args) {
        Terminator tChatbot = new Terminator();
        tChatbot.run();
    }
}
