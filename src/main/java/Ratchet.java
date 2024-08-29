import command.Command;
import exception.InvalidCommandArgumentException;
import exception.InvalidCommandException;
import exception.RatchetException;
import parser.Parser;
import storage.Storage;
import task.*;
import ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Ratchet {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Ratchet() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        parser = new Parser();
        storage.loadTasks(tasks);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.read();
            Command command = null;
            try {
                command = parser.parse(input);
                command.execute(storage, tasks, ui);
            } catch (RatchetException e) {
                ui.error(e.getMessage());
                isExit = false;
            }
            isExit = command.isExit();
        }
    }

    public static void main(String[] args) {
        new Ratchet().run();
    }
}