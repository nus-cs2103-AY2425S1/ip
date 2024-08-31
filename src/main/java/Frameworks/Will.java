package Frameworks;

import Task.Task;
import Task.TaskList;
import Utilities.Parser;
import Utilities.Storage;
import Utilities.Ui;

import java.util.ArrayList;
import java.util.Scanner;


public class Will {

    private final ArrayList<Task> tasks;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Set up the task list, storage, and parser and loads any previously saved tasks from storage into the task list.
     */
    public Will(){
        this.tasks = new ArrayList<>();
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.parser = new Parser();

        storage.load(tasks);
    }

    /**
     * Processes the user's input command and returns the appropriate response.
     *
     * @param input The command input by the user.
     * @return A string representing the result of the command execution.
     */
    public String getResponse(String input) {
        return this.parser.parseUICommand(tasks, input, taskList, parser, storage);
    }


    /**
     * Task management chatbot.
     */
    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage();
        storage.load(tasks);

        String logo = "WILL";
        Ui ui = new Ui();
        ui.greetMsg(logo);

        Scanner scanner = new Scanner(System.in);

        TaskList taskList = new TaskList();
        Parser dateTimeParser = new Parser();
        Parser parser = new Parser();

        while (true) {
            String userInput = scanner.nextLine();
            if (parser.parseCommand(tasks, userInput, taskList, ui, dateTimeParser)){
                break;
            }
        }
    }
}
