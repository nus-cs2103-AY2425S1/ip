package sora;

import sora.Tasks.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sora is the main class.
 * Sora is a ChatBot.
 */
public class Sora {
    /** Storage to save and write tasks to a file */
    private Storage storage;
    /** List of Tasks */
    private TaskList taskList;
    /** User Interface */
    private Ui ui;

    /**
     * Constructs a new Sora.
     *
     * @param filePath Path of file to save and write.
     */
    public Sora(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
        this.ui = new Ui();
        try {
            this.storage.loadTaskList(this.taskList);
        } catch (SoraException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Starts Sora. Begins accepting user input.
     */
    public void run() {
        Scanner commandScanner = new Scanner(System.in);
        boolean isLive = true;

        this.ui.printGreeting();

        while (isLive) {
            ArrayList<String> parsedCommand = Parser.parse(commandScanner.nextLine().trim());
            String mainCommand = parsedCommand.get(0).toLowerCase();
            try {
                switch (mainCommand) {
                    case "bye":
                        this.ui.printFarewell();
                        isLive = false;
                        break;
                    case "list":
                        this.taskList.displayList();
                        break;
                    case "mark":
                        this.taskList.markTask(parsedCommand.get(1));
                        break;
                    case "unmark":
                        this.taskList.unmarkTask(parsedCommand.get(1));
                        break;
                    case "todo":
                        this.taskList.addTask("todo", parsedCommand);
                        break;
                    case "deadline":
                        this.taskList.addTask("deadline", parsedCommand);
                        break;
                    case "event":
                        this.taskList.addTask("event", parsedCommand);
                        break;
                    case "delete":
                        this.taskList.deleteTask(parsedCommand.get(1));
                        break;
                    case "":
                        throw new SoraException("\tPlease Enter a Command\n" + Ui.HORIZONTAL_LINE);
                    default:
                        this.ui.printInvalidCommand();
                }
                this.storage.saveTaskList(taskList);
                System.out.println(Ui.HORIZONTAL_LINE);
            } catch (SoraException e) {
                System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                this.ui.printInvalidCommand();
            }
        }
    }

    public static void main(String[] args) {
        new Sora("savedtasks.txt").run();
    }
}
