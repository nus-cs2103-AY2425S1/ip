package grok;

import commands.Command;
import exceptions.GrokInvalidUserInputException;
import parser.Parser;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

import java.util.Scanner;

public class Grok {
    private static final String TEXT_FILE_DIRECTORY = "./data/duke.txt";
    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;
    private final Parser parser;
    private final Scanner scanner;

    public Grok() {
        storage = new Storage(TEXT_FILE_DIRECTORY);
        taskList = new TaskList(storage.parseTextStorage());
        ui = new Ui();
        parser = new Parser();
        scanner = new Scanner(System.in);

        ui.printWelcomeMessage();
        while (true) {
            try {
                Command c = parser.parseUserInput(scanner.nextLine(), taskList);
                c.execute(taskList, ui, storage);
                if (c.isExit()) {
                    break;
                }
            } catch (GrokInvalidUserInputException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Grok();
    }
}