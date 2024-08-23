import java.util.Scanner;

public class Devon {

    private Scanner userInput = new Scanner(System.in);

    private void printLongLine() {
        String LINE_SEPARATOR = "____________________";
        System.out.println("\t" + LINE_SEPARATOR);
    }

    private void introduction() {
        printLongLine();
        System.out.println("\t" + "Hello! I'm Devon.");
        System.out.println("\t" + "What can I do for you?");
        printLongLine();
    }

    private void goodbye() {
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        printLongLine();
    }

    private void echo(String msg) {
        System.out.println("\t" + msg);
    }

    public static void main(String[] args) {
        Devon bot = new Devon();
        bot.introduction();
        bot.goodbye();
    }
}
