import java.util.Scanner;

public class Ui {

    protected Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hello! I'm Bigdog!");
        System.out.println("What can I do for you?\n");
    }

    public void bye() {
        this.scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void print(String s) {
        System.out.println(s);
    }

    public String readInput() {
        return this.scanner.nextLine();
    }

}
