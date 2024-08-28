package hoshi;

import hoshi.exception.HoshiException;
import hoshi.task.*;
import hoshi.ui.Ui;
import hoshi.utils.Parser;
import hoshi.utils.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Hoshi {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Hoshi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        parser= new Parser();

        try {
            storage.load(taskList);
        } catch (FileNotFoundException e) {
            ui.displayLoadingError(e.getMessage());
        }
    }

    public void run() {

        ui.displayWelcome();
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            ui.displayPrompt();
            input = scanner.nextLine();
            parser.parseCommand(input, scanner, taskList, ui);

        } while (!input.equalsIgnoreCase("bye"));

        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.displaySavingError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Hoshi("data/Hoshi.txt").run();

    }


}
