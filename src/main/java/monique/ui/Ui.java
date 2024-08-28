package monique.ui;

import monique.tasklist.TaskList;
import monique.task.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {

    public static final String HORIZONTAL_LINE = "_____________________________________________\n";
    private final BufferedReader br;

    public Ui() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void addMessage(Task task){
        System.out.println("added: " + task);
    }

    public void deleteMessage(Task task, TaskList tasks) {
        System.out.println("deleted: " + task);
        System.out.println("You now have " + tasks.getNumItems() + " tasks in the list");
    }

    public void markMessage(Task task) {
        System.out.println("Nice lah.. Great job on doing work! I've marked it: " + task);
    }

    public void unmarkMessage(Task task) {
        System.out.println("ok... I've unmarked:" + task);
    }

        public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void showWelcome() {
        System.out.println("Hello, I am Monique,\nI am your personal assistant :)\n ");
    }

    public void showGoodbye(){
        System.out.println("Monique: Goodbye! Have a great day!");
    }

    public String readCommand(){
        String line = ""; 
        try {
            line = this.br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line; 
    }

    public void emptyListMessage(){
        System.out.println("There are no tasks in your List :)");
    }
}
