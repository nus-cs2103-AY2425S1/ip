package tars;

import java.util.Scanner;
import java.io.IOException;
/**
 * This class is Chatbot application which handles Task Management, mainly ToDos, Deadline and Event tasks
 * Users can add the 3 different type of tasks and carry out other functions like mark, unmark and delete
 * Users can also view all the tasks added in a LIST
 * This application will exist till "bye" command entered by User
 *
 * @author csk
 * @version 1
 */
public class Tars {
    static String line = "    _____________________________________________";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parserHelp;

    public Tars(String filePath) throws IOException {
        this.ui = new Ui();
        storage = new Storage(filePath);
        this.parserHelp = new Parser();
        try {
            taskList = new TaskList(storage.readFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
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
            } else {
                taskList.addTask(entryParts, entry);
            }
        }

        Storage.writeFile("./data/Tars.txt", taskList.getList());
        ui.bye();
    }

    /**
    * Runs the Tars application, where it takes in users inputs until "bye" is entered by user
    *
    * @param args (command line arguments given by User)
    */
    public static void main(String[] args) throws IOException {
        new Tars("./data/Tars.txt").run();
    }
}
