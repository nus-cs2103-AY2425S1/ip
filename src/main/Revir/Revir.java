package Revir;

import java.io.IOException;
import java.nio.file.Path;

import Revir.System.Storage;
import Revir.Tasks.*;
import Revir.User.*;
import Revir.User.Command.Command;

public class Revir {
    static TaskList taskList;
    static Storage storage; 
    static Ui ui;
    static Parser parser;
    public static void main(String[] args) {
        parser = new Parser();
        ui = new Ui("Revir");
        storage = new Storage(Path.of("data", "tasks.dat"));
        taskList = new TaskList(storage, ui);
        ui.showWelcome();
        while (ui.isOpen()) {
            String input = ui.readInput();
            try {
                Command c = parser.parse(input);
                c.execute(ui, taskList);
                
            } catch (NumberFormatException e) {
                ui.showError("Invalid task index. Expected a number.");
            } catch (IOException e) {
                ui.showError("Unable to save file: " + e.getMessage());
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showExit();
    }

   }
