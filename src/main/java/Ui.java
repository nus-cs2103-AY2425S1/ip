import java.util.Scanner;

public class Ui {

    public final String unknownCommandMsg = "ERROR! Unknown command!";
    public final String breakLine = "__________________________________________________";
    private Scanner human;
    private Parser parser;
    private TaskList tasklist;
    private boolean active = false;

    public Ui(TaskList tasklist) {
        this.tasklist = tasklist;
        this.human = new Scanner(System.in);
        this.parser = new Parser();
    }

    public void breakLine() {
        System.out.println(this.breakLine);
    }

    public void initialise() {
        this.active = true;
        System.out.println();
        this.breakLine();
        System.out.println("Hello! I'm Blob");
        System.out.println("What can I do for you?");
        this.breakLine();
    }

    public void exit() {
        this.active = false;
    }

    public void converse() {
        while (this.active) {
            String action = human.nextLine();
            this.breakLine();
            parser.evaluateAction(this, tasklist, action);
        }
    }
}
