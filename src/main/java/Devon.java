import java.util.Scanner;

public class Devon {

    private Scanner scanner = new Scanner(System.in);
    private String[] tasks = new String[100];

    private void printLongLine() {
        String LINE_SEPARATOR = "____________________";
        System.out.println("\t" + LINE_SEPARATOR);
    }

    private void introduction() {
        this.printLongLine();
        System.out.println("\t" + "Hello! I'm Devon.");
        System.out.println("\t" + "What can I do for you?");
        this.printLongLine();
    }

    private void goodbye() {
        this.printLongLine();
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        this.printLongLine();
    }

    private void receiveUserInput() {
        String input = this.scanner.nextLine();
        if (input.equals("bye")) {
            this.goodbye();
        } else {
            this.echo(input);
            this.receiveUserInput();
        }
    }

    private void echo(String msg) {
        this.printLongLine();
        System.out.println("\t" + msg);
        this.printLongLine();
    }

    public static void main(String[] args) {
        Devon bot = new Devon();
        bot.introduction();
        bot.receiveUserInput();
    }
}
