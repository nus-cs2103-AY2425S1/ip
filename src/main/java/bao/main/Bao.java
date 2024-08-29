package bao.main;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * The Bao class represents the main application that manages the tasks.
 * Initialises the UI, handles storage, and manages the list of tasks.
 */
public class Bao {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private static final String file_Path = "./data/bao.json.txt";
    public static DateTimeFormatter inputDateFormat;
    public static DateTimeFormatter fileDateFormat;
    public static DateTimeFormatter dateOnlyFormat;
    public static DateTimeFormatter outputDateFormat;

    /**
     * Constructs a new Bao instance with the specified file path for storage.
     * Initialises the UI, storage, and loads the list of tasks.
     *
     * @param filePath Path of the file used for storage of tasks.
     */
    public Bao(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        initialiseDates();
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (Exception e) {
            ui.showMessage("Bao was fed a corrupted file, starting new one!");
            ui.showMessage("Error details: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    private void initialiseDates() {
        inputDateFormat = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("d-M-yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("d-M-yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("d-MM-yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd-M-yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-d HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-dd HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/d HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/M/dd HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/M/d HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-d"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-dd"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/d"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/M/dc"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/M/d"))
                .toFormatter();
        fileDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        dateOnlyFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
        outputDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
    }

    /**
     * Runs the main application, processes user input until application is terminated.
     */
    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            try {
                String command = ui.command().trim();
                Parser.parse(command, tasks, ui, storage);
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public TaskList getTaskList() {
        return this.tasks;
    }

    /**
     * The main entry point of the application.
     * Creates an instance of Bao and runs it.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Bao(file_Path).run();
    }
}
