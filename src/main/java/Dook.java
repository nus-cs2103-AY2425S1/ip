import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Dook {

    private static Ui ui = new Ui();
    private static Storage storage = new Storage("data/Tasks.txt");
    private static TaskList taskList;
    private static Parser parser = new Parser();

    public static void main(String[] args) {

        try {
            storage.setup();
            taskList = new TaskList(storage.load());
            ui.greet();

            boolean isExit = false;
            while (!isExit) {
                String input = ui.readCommand();

                try {
                    Command command = parser.parse(input);
                    command.execute(taskList, ui, storage);
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
