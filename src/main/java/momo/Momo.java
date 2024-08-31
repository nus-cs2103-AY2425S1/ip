package momo;

import momo.command.*;
import momo.task.TaskList;

/**
 * Handles the main control flow of the chatbot program, responsible
 * for initialising the programs necessary components like {@link Storage}, {@link TaskList},
 * and {@link Ui} to get the program running
 */
public class Momo {

    /**
     * The file path where the task data is stored.
     */
    public static final String FILE_PATH = "data/momo.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Momo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Starts the main control loop of the chatbot program,
     * greeting the user then facilitating a continuous reading
     * of their input, triggering ui, task related commands or
     * reminders on properly formatting the input depending on user
     * input until the 'bye' command is triggered
     */
    public void run() {
        ui.showGreeting();
        String input = ui.getUserInput().trim();

        while (true) {
            try {
                CommandType command = Parser.parseInput(input);
                if (command == CommandType.BYE) {
                    ui.showFarewell();
                } else if (command == CommandType.LIST) {
                    ui.printList(tasks);
                } else if (command == CommandType.FIND) {
                    FindCommand.run(input, tasks);
                } else if (command == CommandType.MARK) {
                    MarkCommand.run(input, tasks, storage);
                } else if (command == CommandType.UNMARK) {
                    UnmarkCommand.run(input, tasks, storage);
                } else if (command == CommandType.DELETE) {
                    DeleteCommand.run(input, tasks, storage);
                } else {
                    if (command == CommandType.TODO) {
                        TodoCommand.run(input, storage, tasks);
                    } else if (command == CommandType.DEADLINE) {
                        DeadlineCommand.run(input, storage, tasks);
                    } else if (command == CommandType.EVENT) {
                        EventCommand.run(input, storage, tasks);
                    }
                }
            } catch (MomoException e) {
                System.out.println(e.getMessage());
            }
            input = ui.getUserInput();
        }
    }

    /**
     * Main method which is the entry point to the chatbot program
     *
     * @param args CLI arguments (not used)
     * @throws MomoException if unexpected chatbot related errors occur
     */
    public static void main(String[] args) throws MomoException {
        new Momo(FILE_PATH).run();
    }


}










