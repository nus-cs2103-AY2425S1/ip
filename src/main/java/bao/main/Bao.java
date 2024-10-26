package bao.main;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * The Bao class represents the main application that manages the tasks.
 * Initialises the UI, handles storage, and manages the list of tasks.
 */
@SuppressWarnings("checkstyle:Regexp")
public class Bao {
    private static DateTimeFormatter inputDateFormat;
    private static DateTimeFormatter fileDateFormat;
    private static DateTimeFormatter dateOnlyFormat;
    private static DateTimeFormatter outputDateFormat;
    private static final String FILE_PATH = "./data/bao.json.txt";
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new Bao instance with the specified file path for storage.
     * Initialises the UI, storage, and loads the list of tasks.
     *
     * @param filePath Path of the file used for storage of tasks.
     */
    public Bao(String filePath) {
        assert filePath != null : "File path should not be null";
        assert !filePath.isEmpty() : "File path should not be empty";

        storage = new Storage(filePath);
        initialiseDates();
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (Exception e) {
            System.out.println("Bao was fed a corrupted file, starting new one!");
            System.out.println("Error details: " + e.getMessage());
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
     * Returns a response based on the input command.
     *
     * @param input User input command.
     * @return The response from Bao.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            response = Parser.parse(input, tasks, storage);
        } catch (Exception e) {
            response = "Bao encountered an error: " + e.getMessage();
        }
        return response;
    }

    public TaskList getTaskList() {
        return this.tasks;
    }

    public Storage getStorage() {
        return storage;
    }

    public static DateTimeFormatter getInputDateFormat() {
        return inputDateFormat;
    }

    public static DateTimeFormatter getFileDateFormat() {
        return fileDateFormat;
    }

    public static DateTimeFormatter getDateOnlyFormat() {
        return dateOnlyFormat;
    }

    public static DateTimeFormatter getOutputDateFormat() {
        return outputDateFormat;
    }

    /**
     * The main entry point of the application.
     * Creates an instance of Bao and runs it.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Bao(FILE_PATH);
    }
}
