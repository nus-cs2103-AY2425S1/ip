package seedu.maxine;

import seedu.maxine.task.Task;

import java.util.Scanner;

public class Maxine {
    
    static String[] arr;
    static Scanner scanner = new Scanner(System.in);
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    
    public Maxine() {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage("data/maxine.txt");
    }

    /**
     * This constructor exists solely for testing purposes and
     * is to be used in the JUnit
     * @param ui
     * @param storage
     * @param tasks
     */
    public Maxine(Ui ui, Storage storage, TaskList tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
        parser = new Parser();
    }
    
    public void run() {
        try {
            ui.greet();
            storage.load();

            while (parser.getStatus()) {
                String answer = ask();
                parser.parse(answer);
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    /**
     * 
     * @return
     */
    public String ask() {
        System.out.print("What can I do for you today? : ");
        String answer = scanner.nextLine().toLowerCase();
        arr = answer.split(" ");
        return answer;
    }

    /**
     * This is the main method
     * @param args Command-line arguments passed to the program
     */
    public static void main(String[] args) {
        new Maxine().run();
    }
    
}
