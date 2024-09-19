package snowy;

import snowy.common.Command;
import snowy.common.CommandResult;
import snowy.data.SnowyException;
import snowy.storage.Storage;
import snowy.tasklist.TaskList;
import snowy.parser.Parser;

public class Snowy {
    private static final String FILE_PATH = "../ip/src/main/data";
    private static final String FILENAME = "snowy.txt";
    private final Parser parser;
    private final Storage storage;
    private final TaskList tasklist;

    public Snowy() {
        this.parser = new Parser();
        this.storage = new Storage(FILE_PATH, FILENAME);
        this.tasklist = new TaskList(storage);
        try {
            this.tasklist.loadStorage();
        } catch (SnowyException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }


    public String getResponse(String input) {
        try {
           Command command = parser.parseCommand(input);
           command.setData(this.tasklist);
           CommandResult result = command.execute();
           return result.getFeedback();
        } catch (SnowyException e) {
                return e.getMessage();
        }
    }
}