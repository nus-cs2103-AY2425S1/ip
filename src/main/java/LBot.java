import command.Command;
import exception.*;
import helper.Storage;
import helper.TaskList;
import helper.Ui;
import task.*;
import helper.Parser;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
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
                ui.print(userInput);
                Command c = Parser.parse(userInput);
                ui.print(c.toString());
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

