package Papagu.Ui; 

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalTime;


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
        new Papagu("data/tasks.txt").run();
    }
}


