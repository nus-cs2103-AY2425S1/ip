package bmo;

import java.io.IOException;

import bmo.gui.Launcher;
import bmo.gui.Main;

import bmo.task.Task;

/**
 * Bmo is a chatbot that helps users manage their tasks.
 */
public class Bmo {

    private static String filePath = "data/BMO.txt";
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;

    /**
     * Constructor for Bmo class.
     *
     * @param filePath the path of the storage file
     * @throws Exception if unable to create the storage file
     */
    public Bmo(String filePath) throws Exception {
        try {
            assert filePath != null : "File path cannot be null";
            this.storage = new Storage(filePath);
            this.ui = new Ui();
            this.parser = new Parser();
            this.taskList = new TaskList();
            Bmo.filePath = filePath;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs the BMO chatbot program
     *
     * @throws IOException if unable to read or write to file
     * @throws BmoException if the user input is invalid
     */
    public String run(String userInput) throws Exception {
        storage.readStorageFile(taskList, filePath);
        assert userInput != null : "User input cannot be null";

        while (true) {
            String[] input = parser.parse(userInput);
            assert input.length > 0 : "User input cannot be empty";

            //Switch statement to handle different commands
            switch (input[0]) {
            //Lists all the tasks in the list
            case "list":
                return ui.printList(taskList.getTasks());

            //Marks a task as completed
            case "mark":
                //Parses the second word as the index of the task to mark
                int index = Integer.parseInt(input[1]) - 1;

                if (index >= taskList.getSize() || index < 0) {
                    throw new BmoException("\nOh no! The task number you specified does not exist!");
                }
                taskList.markTask(index);
                storage.updateStorageFile(taskList);
                return ui.printTaskMarked(taskList.getTask(index));

            //Unmarks a completed task as incomplete
            case "unmark":
                //Parses the second word as the index of the task to unmark
                int index2 = Integer.parseInt(input[1]) - 1;

                if (index2 >= taskList.getSize() || index2 < 0) {
                    throw new BmoException("\nOh no! The task number you specified does not exist!");
                }
                taskList.unmarkTask(index2);
                storage.updateStorageFile(taskList);
                return ui.printTaskUnmarked(taskList.getTask(index2));

            //Adds a todo task (no deadline) to the list
            case "todo":
                taskList.addTodo(input[1]);
                storage.saveTask(taskList.getTask(taskList.getSize() - 1));
                return ui.printTaskAdded(taskList.getTask(taskList.getSize() - 1), taskList.getSize());

            //Adds a deadline task (with deadline) to the list
            case "deadline":
                taskList.addDeadline(input[1], input[2]);
                storage.saveTask(taskList.getTask(taskList.getSize() - 1));
                return ui.printTaskAdded(taskList.getTask(taskList.getSize() - 1), taskList.getSize());

            //Adds an event task (with start and end timings) to the list
            case "event":
                //Splits the description into task description and event timings
                taskList.addEvent(input[1], input[2], input[3]);
                storage.saveTask(taskList.getTask(taskList.getSize() - 1));
                return ui.printTaskAdded(taskList.getTask(taskList.getSize() - 1), taskList.getSize());

            //Deletes a task from the list
            case "delete":
                //Parses the second word as the index of the task to delete
                int index3 = Integer.parseInt(input[1]) - 1;

                if (index3 >= taskList.getSize() || index3 < 0) {
                    throw new BmoException("\nOh no! The task number you specified does not exist!");
                }
                Task taskToDelete = taskList.getTask(index3);
                taskList.deleteTask(index3);
                storage.updateStorageFile(taskList);
                return ui.printTaskRemoved(taskToDelete, taskList.getSize());

            //Finds tasks with descriptions containing the keyword
            case "find":
                TaskList matchingTasks = taskList.findMatchingTasks(input[1]);
                return ui.printMatchingTasks(matchingTasks);

            case "bye":
                storage.updateStorageFile(taskList);
                storage.closeWriter();
                return ui.printGoodbye();
            //Catches unknown commands
            default:
                throw new BmoException("\nOh no! I do not recognise that command, please try again!");
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            assert input != null : "User input cannot be null";
            return run(input);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        try {
            Main.setBmo(new Bmo(filePath)); // Set the BMO instance
            Launcher.main(args); // Launch the Bmo Chatbot GUI
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
