import java.util.Objects;
import java.util.Scanner;
public class Patrick {
    public static void main(String[] args) {
        String horizontalLine = "-----------------------------------------------------------------------\n";
        String greetingMsg = horizontalLine + "Hello! I'm Patrick, Spongebob bestie\nHow can I help you?\n" + horizontalLine;
        String exitMsg = "Bye. Hope to see you again soon!\n";
        String input;

        Scanner inputMsg = new Scanner(System.in);
        System.out.println(greetingMsg);
        do {
            input = inputMsg.nextLine();
            System.out.println(horizontalLine + input + "\n" + horizontalLine);
        } while (!input.equals("bye"));
        System.out.println(exitMsg);
    }
}
