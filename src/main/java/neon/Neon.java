package neon;

import java.io.IOException;
import java.util.List;

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

    public String getGreetingLine() {
        return ui.printGreetingLine();
    }

    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        response.append(ui.printLine());

        try {
            Object[] processedInput = new Object[2];
            processedInput = parser.processInput(input);

            if ((boolean)processedInput[0]) {
                return "exit";
            }

            response.append(processedInput[1]);
        } catch (Exception e) {
            return ("error: " + e);
        }

        response.append(ui.printLine());
        return response.toString();
    }

    public void updateData() {
        storage.save(tasks.getTasks());
    }
}
