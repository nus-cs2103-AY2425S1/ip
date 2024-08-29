package LBot;

import LBot.command.Command;
import LBot.exception.*;
import LBot.helper.Storage;
import LBot.helper.TaskList;
import LBot.helper.Ui;
import LBot.task.*;
import LBot.helper.Parser;

import java.util.Scanner;

public class LBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public LBot(String filePath) throws FileException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readTasksFromFile());
        } catch (FileException e) {
            // dk man, do smt
            tasks = new TaskList();
        }
    }

    public void run() throws InvalidCommandException, ParseCommandException, ExecuteCommandException {
        // while loop here
        // try catch here also to catch all the exceptions
        Scanner scanner = new Scanner(System.in);
        ui.print("Hello, welcome!");
        ui.print("How can I help you?");
        String userInput = "";
        while (true) {
            try {
                userInput = scanner.nextLine();
                Command c = Parser.parse(userInput);
                if (c == null) {
                    throw new ExecuteCommandException("Command could not be executed!");
                } else {
                    c.execute(ui, storage, tasks);
                }
            } catch (LBotException e) {
                // print error
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws LBotException {
        new LBot("data/tasks.txt").run();
    }


}

