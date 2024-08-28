import java.util.Scanner;

public class Ui {

    private final Scanner scanner = new Scanner(System.in);
    public void start() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm EchoBot");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public String nextInput() {
        return scanner.nextLine();
    }

    public void exit() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        scanner.close();
    }
}
