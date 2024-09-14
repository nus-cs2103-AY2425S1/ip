package tars;

import java.io.IOException;

/**
 * This class is Chatbot application which handles Task Management, mainly ToDos, Deadline and Event tasks
 * Users can add the 3 different type of tasks and carry out other functions like mark, unmark and delete
 * Users can also view all the tasks added in a LIST
 * This application will run till "bye" command given
 *
 * @author csk
 * @version 1
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
     * Runs TARS application with using the different objects(eg: Ui, storage, taskList)
     * Handles scanning the input of user and execute relevant command based on input
     *
     * @throws IOException
     */
    /*public void run() throws IOException {
        ui.welcome();
        Scanner scanner = new Scanner(System.in); //initalising input scanner

        while (true) {
            String entry = scanner.nextLine();
            String[] entryParts = entry.split(" ");

            if (entryParts.length < 2) {
                if (entry.equals("bye")) {
                    break;
                } else {
                    parserHelp.checkEntry(entryParts, entry, taskList);
                }
            } else if (entryParts[0].equals("find")) {
                parserHelp.findTask(entryParts, taskList);
            } else {
                taskList.addTask(entryParts, entry);
            }
        }

        Storage.writeFile("./data/Tars.txt", taskList.getList());
        ui.bye();
    }*/

    public String getResponse(String entry) throws IOException {
        String[] entryParts = entry.split(" ");
        String result = "";

        if (entryParts.length < 2) {
            if (entry.equals("bye")) {
                result = ui.bye();
            } else {
                result = parser.checkEntry(entryParts, entry, taskList);
            }
        } else if (entryParts[0].equals("find")) {
            result = parser.findTask(entryParts, taskList);
        } else {
            result = taskList.addTask(entryParts, entry);
        }

        Storage.writeFile("./data/Tars.txt", taskList.getList());

        return result;
    }

    /**
    * Creates new Tars object with relative path to the list stored in text format of the User's laptop
    *
    * @param args
    */

    /*public static void main(String[] args) throws IOException {
        new Tars("./data/Tars.txt").run();
    }*/
}
