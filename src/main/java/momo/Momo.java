package momo;

import javafx.scene.image.Image;
import momo.command.*;
import momo.exception.MomoException;
import momo.task.TaskList;

/**
 * Handles the main control flow of the chatbot program, responsible
 * for initialising the programs necessary components like {@link Storage}, {@link TaskList},
 * and {@link Ui} to get the program running
 */
public class Momo {

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/userIcon.png"));
    private final Image momoImage = new Image(this.getClass().getResourceAsStream("/images/momoIcon.png"));


    /**
     * The file path where the task data is stored.
     */
    public static final String FILE_PATH = "data/momo.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Momo(String filePath, Ui ui) {
        this.ui = ui;
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
    }

    public void processCommand(String input, CommandType command) {

        try {
            if (command == CommandType.BYE) {
                ui.showFarewell();
            } else if (command == CommandType.LIST) {
                ui.printList(tasks);
            } else if (command == CommandType.FIND) {
                FindCommand.run(input, tasks, ui);
            } else if (command == CommandType.MARK) {
                MarkCommand.run(input, tasks, storage, ui);
            } else if (command == CommandType.UNMARK) {
                UnmarkCommand.run(input, tasks, storage, ui);
            } else if (command == CommandType.DELETE) {
                DeleteCommand.run(input, tasks, storage, ui);
            } else {
                if (command == CommandType.TODO) {
                    TodoCommand.run(input, storage, tasks, ui);
                } else if (command == CommandType.DEADLINE) {
                    DeadlineCommand.run(input, storage, tasks, ui);
                } else if (command == CommandType.EVENT) {
                    EventCommand.run(input, storage, tasks, ui);
                }
            }
        } catch (MomoException e) {
            ui.printDialogue(e.getMessage());
        }
    }

    /**
     * Main method which is the entry point to the chatbot program
     *
     * @param args CLI arguments (not used)
     * @throws MomoException if unexpected chatbot related errors occur
     */
    public static void main(String[] args)  {
        // new Momo(FILE_PATH).run();
    }




}










