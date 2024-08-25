package duck;

import duck.commands.Command;
import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Deadline;
import duck.data.task.Event;
import duck.data.task.Task;
import duck.data.task.ToDo;
import duck.parser.Parser;
import duck.storage.Storage;
import duck.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Duck {
    private final TaskList tasks;
    private Storage storage;
    private final Ui ui;
    private static final String FILE_PATH = "data/duck.txt";



    public Duck(String filePath) {
        this.ui = new Ui();
        tasks = new TaskList();
        try {
            ui.showStartUpMessage();
            storage = new Storage(filePath);
            storage.loadTasks(tasks);
            ui.showStartUpCompleteMessage();
        } catch (DuckException e) {
            ui.displayDukeExceptionMessage(e);
        }
    }

    public void run() {
        ui.sayHi();
        boolean isExit = false;
        while (!isExit) {
            try {
                String message = ui.readCommand();
                Command command = Parser.parse(message);
                command.execute(tasks, storage, ui);
                isExit = command.isExit();
            } catch (DuckException e) {
                ui.displayDukeExceptionMessage(e);
            }
        }
    }
    public static void main(String[] args) {

        new Duck(FILE_PATH).run();
    }

}
