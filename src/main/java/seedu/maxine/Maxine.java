package seedu.maxine;

import java.util.Scanner;

public class Maxine {
    
    static String[] words;
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
            System.out.println(e.getMessage());
        }
        
    }
    

    /**
     * 
     * @return
     */
    public String ask() {
        System.out.print("What can I do for you today? : ");
        String answer = scanner.nextLine().toLowerCase();
        words = answer.split(" ");
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
