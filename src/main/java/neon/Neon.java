package neon;

import java.io.IOException;
import java.util.*;


public class Neon {
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;
    private final Ui ui;

    private static final String STORAGE_FILE_PATH = "./data/data.txt";

    public Neon(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser(tasks, ui);
    }

    public void run() {
        boolean isExited = false;

        Scanner scanner = new Scanner(System.in);

        while (!isExited) {
            Object[] processedInput = new Object[2];
            processedInput = parser.processInput(scanner.nextLine());
            isExited = (boolean)processedInput[0];
        }

        storage.save(tasks.getTasks());
    }

    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        try {
            Object[] processedInput = new Object[2];
            processedInput = parser.processInput(input);
            return (String) processedInput[1];
        } catch (Exception e) {
            return ("error: " + e);
        }
    }


    public static void main(String[] args) throws IOException {
        new Neon(STORAGE_FILE_PATH).run();
    }

}
