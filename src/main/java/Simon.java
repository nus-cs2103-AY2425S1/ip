import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.*;
import java.util.NoSuchElementException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Simon {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Parser parser;

    public Simon(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        //...
        ui.showWelcome();;
        Scanner sc = new Scanner(System.in);
        String input;
        storage.load();
        while (!(input = sc.nextLine().trim()).equals("bye")) {
            try {
                if (input.isEmpty()) {
                    continue;
                }
                else {
                    Command command = parser.parse(input);
                    assert command != null;
                    command.execute(tasks, ui, storage);
                }
            } catch (Error | Exception e) {
                System.out.print(e.toString());
            }
        }
        ui.showExit();
    }

    public static void main(String[] args) {
        new Simon("tasks.txt").run();
    }




}
