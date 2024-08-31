package grok;

import commands.Command;

import exceptions.GrokInvalidUserInputException;

import parser.Parser;

import storage.Storage;

import taskList.TaskList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import ui.Ui;

import java.util.Scanner;

public class Grok {
    private static final String TEXT_FILE_DIRECTORY = "./data/duke.txt";
    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;
    private final Parser parser;

    public Grok() {
        storage = new Storage(TEXT_FILE_DIRECTORY);
        taskList = new TaskList(storage.parseTextStorage());
        ui = new Ui();
        parser = new Parser();

        ui.printWelcomeMessage();

        // If data changes, prompt a save to text file
        boolean hasUnsavedChanges = false;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                Command c = parser.parseUserInput(scanner.nextLine());
                c.execute(taskList, ui, storage);
                if (c.isExit()) {
                    break;
                }
            } catch (GrokInvalidUserInputException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }


        System.out.println(padMessage("Bye. Hope to see you again soon!"));
    }

    public static void main(String[] args) {
        new Grok();
    }
}