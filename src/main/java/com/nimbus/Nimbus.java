package com.nimbus;

import java.util.Scanner;

import com.commands.ByeCommand;
import com.commands.Command;
import javafx.scene.image.Image;

public class Nimbus {
    final private static String name = "Nimbus";

    private final TaskList tasks;
    private boolean isRunning;
    private final Ui ui;
    private final Storage storage;

    public Nimbus(String filePath, Ui ui) {
        this.ui = ui;
        this.storage = new Storage(filePath);
        this.isRunning = true;
        this.tasks = new TaskList();
        tasks.add(storage.load());
        ui.showWelcomeMessage();
    }

    public void executeCommand(String command) {
        try {
            ui.showUserMessage(command);
            Command c = Parser.parse(command);
            c.execute(ui, storage, tasks);
            if (c instanceof ByeCommand) {
                isRunning = false;
                ui.stopGUI();
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

        while(isRunning) {
            line = scanner.nextLine();
            executeCommand(line);
        }
    }
}
