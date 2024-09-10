package com.nimbus;

import java.util.Scanner;

import com.commands.ByeCommand;
import com.commands.Command;
import com.exceptions.InvalidArgumentException;
import com.exceptions.InvalidCommandException;

/**
 * Nimbus is a chatbot
 */
public class Nimbus {
    private static String name = "Nimbus";

    private final TaskList tasks;
    private boolean isRunning;
    private final Ui ui;
    private final Storage storage;

    /**
     * Create a new chatbot
     * @param filePath file path of the data stored by the chatbot
     * @param ui ui used for the chatbot to show message to user
     */
    public Nimbus(String filePath, Ui ui) {
        this.ui = ui;
        this.storage = new Storage(filePath);
        this.isRunning = true;
        this.tasks = new TaskList();
        tasks.add(storage.load());
        ui.showWelcomeMessage();
    }

    /**
     * Execute command with the chatbot
     * @param command command to be executed
     */
    public void executeCommand(String command) {
        try {
            ui.showUserMessage(command);
            Command c = Parser.parse(command);
            c.execute(ui, storage, tasks);
            if (c instanceof ByeCommand) {
                isRunning = false;
                ui.stopGraphicalUI();
            }
        } catch (InvalidCommandException | InvalidArgumentException e) {
            ui.showError(e.getMessage());
        } catch (Exception e) {
            System.out.println("Failed to exit the GUI");
        }
    }

    /**
     * Run the Nimbus Chatbot
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String line;

        while (isRunning) {
            line = scanner.nextLine();
            executeCommand(line);
        }
    }
}
