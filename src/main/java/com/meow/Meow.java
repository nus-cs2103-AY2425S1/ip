package com.meow;
import java.io.IOException;
import java.util.Scanner;

public class Meow {
    public enum Command {
        TODO, DEADLINE, EVENT, LIST, DELETE, MARK, UNMARK, BYE
    }
    private Save save;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;
    
    // Public constructor for Meow
    public Meow() throws IOException, Meowception {
        tasks = new TaskList();
        ui = new Ui();
        try {
            
            save = new Save(tasks);

        } catch (Meowception e) {
            ui.showMeowceptionError(e.toString());
        } catch (IOException e) {
            ui.showMeowceptionError("Meowception 007: meow meow error creating file meow");
        }
    }
    /*
     * runs the main program loop
     * inits parser 
     * @param void
     * @return void
     */
    public void run() {
        parser = new Parser(tasks);
        ui.printStartMessage();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                String msg = parser.commandValidation(input);
                ui.showTaskOutcomeMessage(msg);
            } catch (Meowception e) {
                ui.showMeowceptionError(e.toString());
            }
            input = sc.nextLine();
        }
        save.saveTasks(tasks);
        ui.printExitMessage();
        sc.close();
        
    }
    /*
     * main method to run the program
     * @param String[] args
     * @return void
     */
    public static final void main(String[] args) throws Meowception, IOException {
        new Meow().run();
    }
}
