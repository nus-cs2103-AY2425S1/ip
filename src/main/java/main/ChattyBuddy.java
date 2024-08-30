package main;

import exceptions.EmptyTaskException;
import exceptions.InvalidInputException;
import exceptions.TaskIndexOutOfBound;
import task.TaskList;
import ui.Ui;
import storage.Storage;
import parser.Parser;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ChattyBuddy {

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/chattybuddy.txt");
        TaskList taskList;

        ui.showWelcomeMessage();

        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showErrorMessage("Data file not found. Starting with an empty task list.");
            taskList = new TaskList(new ArrayList<>());
        }

        String response = ui.readCommand();

        while (!response.equals("bye")) {
            try {
                Parser.parseCommand(response, taskList, ui, storage);
            } catch (InvalidInputException | EmptyTaskException | TaskIndexOutOfBound e) {
                ui.showErrorMessage(e.getMessage());
            }

            response = ui.readCommand();
        }

        ui.showGoodbyeMessage();
    }
}

