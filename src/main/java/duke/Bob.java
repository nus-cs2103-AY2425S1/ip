package duke;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import duke.command.Command;
import duke.gui.Main;
import duke.tasks.Task;
import javafx.application.Application;


/**
 * Represents the Bob Chatbot application.
 */
public class Bob {
    /**
     * Whether the app use the gui or cli.
     */
    private static final boolean isGui = true;

    private static final String storagePath = "./data/storage.txt";
    private static final String archivePath = "./data/archive.txt";

    /**
     * The maximum capacity of the input and output message queues.
     */
    private static final int queueCapacity = 10;

    private TaskList tasks;

    private Ui ui;

    private Storage storage;

    private Archive archive;

    /**
     * Constructor for a Bob application.
     */
    public Bob(BlockingQueue<String> inputQueue, BlockingQueue<String> outputQueue) {
        this.ui = new Ui(inputQueue, outputQueue);
        this.storage = new Storage(storagePath);
        this.archive = new Archive(archivePath);
        try {
            this.archive.init();

            ArrayList<Task> tasks = this.storage.load();
            assert tasks != null;
            this.tasks = new TaskList(tasks);
        } catch (BobException e) {
            ui.write(e.toString());
        }
    }

    /**
     * Runs the Bob application.
     */
    public void run() {
        ui.greet();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.read();
                Command command = Parser.parseInput(input);
                assert command != null;
                command.execute(this.tasks, this.ui, this.storage, this.archive);
                isExit = command.isExit();
            } catch (BobException e) {
                ui.write(e.toString());
            }
        }
        System.exit(0);
    }

    /**
     * Main method.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        BlockingQueue<String> inputQueue = new ArrayBlockingQueue<>(queueCapacity);
        BlockingQueue<String> outputQueue = new ArrayBlockingQueue<>(queueCapacity);
        Bob bob = new Bob(inputQueue, outputQueue);

        if (isGui) {
            Main.connect(inputQueue, outputQueue);
            new Thread(() -> Application.launch(Main.class, args)).start();
        } else {
            Ui.useStdio(inputQueue, outputQueue);
            bob.ui.useFormatter((msg) -> "____________________________________________________________\n"
                    + msg + "\n____________________________________________________________\n");
        }

        bob.run();
    }
}
