package tira;

import java.io.IOException;
import java.io.PrintWriter;

import java.time.format.DateTimeFormatter;

import tira.task.TaskList;


/**
 * Main class for Tira chatbot.
 * Manages initialization and execution of the Tira app.
 *
 */

public class Tira {
    private static final String Directory = "./data";
    private static final String FileName = "./data/Tira.txt";
    private static DateTimeFormatter IN_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    /**
     * Initialises the Tira class with the input filepath.
     * Loads task list from the file with the matching filename.
     *
     * @param filePath Filepath of the file to be updated.
     */

    public Tira(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (TiraException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(tasks);

    }

    /**
     * Runs Tira chatbot by accepting user input through Ui class.
     * @throws TiraException
     */

    public void run() throws TiraException{
        Ui ui = new Ui();
        ui.welcome();
        boolean isDone = false;
        PrintWriter printer = new PrintWriter(System.out);
        // check the user input
        /*
        ArrayList<Task> taskList = new ArrayList<Task>();
         */
        //Solution for Save below (Level-7)  inspired by https://github.com/hansneddyanto/ip/blob/master/src/main/java/Hana.java
        while (true) {
            String command = ui.read();
            String[] splitCommand = command.split(" ");
            String firstWord = splitCommand[0];
            if (command.equals("bye")) { //BYE
                break;
            } else {
                parser.parseCommand(command);
            }
        }
        try {
            storage.save(tasks.getTasks());
            System.out.println("managed to save");
        } catch (IOException e) {
            System.out.println("Error while saving tasks. Please try again");
        }
        ui.bye();
        printer.close();
    }

    /**
     * Main method of Tira chatbot.
     * @param args Terminal input.
     * @throws TiraException
     * @throws IOException
     */

    public static void main(String[] args) throws TiraException, IOException {
        // variable declarations
        new Tira("data/tasks/txt").run();
    }

    public String getResponse(String input) {
        return "Tira heard: " + input;
    }
}


