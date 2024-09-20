package tars;

import java.io.IOException;

/**
 * Acts as main class for Tars application which handles Task Management, mainly ToDos, Deadline and Event tasks
 * Users can add the 3 different type of tasks and carry out other functions like mark, un mark and delete
 *
 * @author SKarthikeyan28
 */
public class Tars {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs the Tars object, taking in the filePath for storage and initialising Ui and Parsers objects
     *
     * @param filePath
     * @throws IOException
     */
    public Tars(String filePath) throws IOException {
        this.ui = new Ui();
        storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            taskList = new TaskList(storage.readFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets response from TARS with using the different objects(eg: Ui, storage, taskList)
     * Handles the input of user and execute relevant command based on input
     *
     * @throws IOException
     */
    public String getResponse(String entry) throws IOException {
        String[] entryParts = entry.split(" ");
        String result = "";

        if (entryParts.length < 2) {
            if (entry.equals("bye")) {
                result = ui.bye();
            } else if (entry.equals("help")) {
                result = ui.help();
            } else {
                result = parser.checkEntry(entryParts, entry, taskList);
            }
        } else if (entryParts[0].equals("find")) {
            result = parser.findTask(entryParts, taskList);
        } else {
            result = parser.addTask(entryParts, entry, taskList);
        }

        Storage.writeFile("./data/Tars.txt", taskList.getList());

        return result;
    }
}
