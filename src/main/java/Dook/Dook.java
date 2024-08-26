package Dook;

import Dook.Commands.Command;
import Dook.Parser.Parser;
import Dook.Tasks.TaskList;
import Dook.Storage.Storage;
import Dook.Ui.Ui;

import java.io.FileNotFoundException;

import java.io.IOException;

public class Dook {

    private static Ui ui = new Ui();
    private static Storage storage = new Storage("data/Tasks.txt");
    private static TaskList tasks;
    private static Parser parser = new Parser();

    public static void main(String[] args) {

        try {
            storage.setup();
            tasks = new TaskList(storage.load());
            ui.greet();

            boolean isExit = false;
            while (!isExit) {
                String input = ui.readCommand();

                try {
                    Command command = parser.parse(input);
                    command.execute(tasks, ui, storage);
                    isExit = command.isExit();
                } catch (DookException e) {
                    ui.errorMessage(e.getMessage());
                } catch (IOException e) {
                    ui.errorMessage(e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            ui.errorMessage("File not found");
        } catch (IOException e) {
            ui.errorMessage("IO Error");
        }
    }
}
