package lbot;

import java.util.Scanner;

import lbot.command.Command;
import lbot.exception.ExecuteCommandException;
import lbot.exception.FileException;
import lbot.exception.InvalidCommandException;
import lbot.exception.LBotException;
import lbot.exception.ParseCommandException;
import lbot.helper.Parser;
import lbot.helper.Storage;
import lbot.helper.TaskList;
import lbot.helper.Ui;


/**
 * <h1>LBot</h1>
 * The LBot program is a greenfield project that
 * is being built to learn more about Software Engineering principles.
 *
 * @author Lewis Lye
 * @version 0.1
 * @since 2024-08-20
 */
public class LBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Public constructor for LBot class.
     * Loads {@link Storage}, {@link Ui} and {@link TaskList}.
     *
     * @param filePath The location where user wishes to save their tasks.
     * @throws FileException Thrown when error is encountered while reading file.
     */
    public LBot(String filePath) throws FileException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readTasksFromFile());
        } catch (FileException e) {
            // dk man, do smt
            tasks = new TaskList();
            ui.printDataLoadFailed();
        }
    }

    /**
     * Starts LBot.
     *
     * @throws InvalidCommandException Thrown when command keyword is not supported.
     * @throws ParseCommandException   Thrown when arguments in command is not parseable
     *                                 (e.g. wrong {@link java.time.LocalDateTime} format).
     * @throws ExecuteCommandException Thrown when command provided is not in a readable format.
     */
    public void run() throws InvalidCommandException, ParseCommandException, ExecuteCommandException {
        // while loop here
        // try catch here also to catch all the exceptions
        Scanner scanner = new Scanner(System.in);
        ui.printGreeting();
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
                ui.printException(e.getMessage());
            }
        }
    }

    /**
     * Main class of LBot.
     */
    public static void main(String[] args) throws LBotException {
        new LBot("data/tasks.txt").run();
    }


}

