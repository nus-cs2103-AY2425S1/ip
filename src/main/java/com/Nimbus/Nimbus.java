package com.nimbus;

import java.util.Scanner;

import com.commands.ByeCommand;
import com.commands.Command;

public class Nimbus {
    final private static String name = "Nimbus";

    private final TaskList tasks;
    private boolean isRunning;
    private final Ui ui;
    private final Storage storage;

    public Nimbus(String filePath) {
        this.ui = new Ui(name);
        this.storage = new Storage(filePath);
        this.isRunning = true;
        this.tasks = new TaskList();
    }

    public static void main(String[] args) {
        new Nimbus("./data/data.txt").run();
    }

    public void run() {
        tasks.add(storage.load());
        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String line;

        while(isRunning) {
            line = scanner.nextLine();
            try {
                Command c = Parser.parse(line);
                c.execute(ui, storage, tasks);
                if (c instanceof ByeCommand)
                    isRunning = false;
            } catch (InvalidCommandException | InvalidArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
