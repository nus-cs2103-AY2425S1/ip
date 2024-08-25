import java.util.Scanner;

public class Ui {
    static Scanner reader = new Scanner(System.in);

    public Ui() {
    }

    public void greet() {
        System.out.println("Pika! I'm Pikappi!\nWhat can I do for you?\n");
    }

    public void goodbye() {
        System.out.println("Pi-kapi! See you again~\n");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return reader.nextLine();
    }
}
