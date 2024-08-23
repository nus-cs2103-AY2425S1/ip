import java.util.Scanner

public class Devon {

    private Scanner userInput = new Scanner(System.in);

    private void printLongLine() {
        String LINE_SEPARATOR = "____________________";
        System.out.println(LINE_SEPARATOR);
    }

    private void introduction() {
        printLongLine();
        System.out.println("Hello! I'm Devon.");
        System.out.println("What can I do for you?");
        printLongLine();
    }

    private void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLongLine();
    }

    public static void main(String[] args) {
        Devon bot = new Devon();
        bot.introduction();
        bot.goodbye();
    }
}
