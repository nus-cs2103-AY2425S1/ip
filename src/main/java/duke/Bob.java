package duke;

import java.util.ArrayList;
import java.util.Scanner;
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
    private static final boolean isGui = true;
    private TaskList tasks;

    private Ui ui;

    private Storage storage;

    /**
     * Constructor for a Bob application.
     * @param path The path of the file for persistent storage.
     */
    public Bob(String path, BlockingQueue<String> inputQueue, BlockingQueue<String> outputQueue) {
        this.ui = new Ui(inputQueue, outputQueue);
        this.storage = new Storage(path);
        try {
            ArrayList<Task> tasks = this.storage.load();
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
                command.execute(this.tasks, this.ui, this.storage);
                isExit = command.isExit();
            } catch (BobException e) {
                ui.write(e.toString());
            }
        }
    }

    /**
     * Main method.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        BlockingQueue<String> inputQueue = new ArrayBlockingQueue<>(10);
        BlockingQueue<String> outputQueue = new ArrayBlockingQueue<>(10);
        if (isGui) {
            Main.connect(inputQueue, outputQueue);
            new Thread(() -> {
                Application.launch(Main.class, args);
            }).start();
            new Bob("./data/bob.txt", inputQueue, outputQueue).run();
        } else {
            new Thread(() -> {
                Scanner in = new Scanner(System.in);
                while (true) {
                    String inputMessage = in.nextLine();
                    inputQueue.add(inputMessage);
                }
            }).start();
            new Thread(() -> {
                while (true) {
                    try {
                        String outputMessage = outputQueue.take();
                        System.out.println(outputMessage);
                    } catch (InterruptedException e) {
                        ;
                    }
                }
            }).start();
            new Bob("./data/bob.txt", inputQueue, outputQueue).run();
        }
    }
}