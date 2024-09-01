package Papagu.Ui; 

import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;


public class Papagu {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private File file;

    /**
     * Constructor for Papagu
     * Reads off any tasks from tasks.txt file
     * 
     */
    public Papagu(String filePath) {
        ui = new Ui();
        this.file = new File(filePath);
        this.tasks = new TaskList();
        this.storage = new Storage(filePath, tasks, file);
        try {
            Parser.parseFile(file, tasks);
        } catch (Exception e) {
            Ui.printLoadingError();
        }
    }

    public void run() {
        Ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            Parser.parseInput(tasks, input, this.storage);
            input = sc.nextLine();
        }
        Ui.printBye();
        sc.close();
    }

    public static void main(String[] args) {
        // Print current working directory
        //System.out.println("Working Directory = " + Paths.get("").toAbsolutePath().toString());

        // Print classpath
        //System.out.println("Classpath = " + System.getProperty("java.class.path"));
        new Papagu("data/tasks.txt").run();
    }
}


