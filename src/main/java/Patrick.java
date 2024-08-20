import java.util.ArrayList;
import java.util.Scanner;
public class Patrick {
    public static void main(String[] args) {
        String horizontalLine = "-----------------------------------------------------------------------\n";
        String greetingMsg = horizontalLine + "Hello! I'm Patrick, Spongebob bestie\nHow can I help you?\n" + horizontalLine;
        String exitMsg = horizontalLine + "Bye. Hope to see you again soon!\n" + horizontalLine;
        String input;
        ArrayList list = new ArrayList();
        int count = 0;

        Scanner inputMsg = new Scanner(System.in);
        System.out.println(greetingMsg);
        do {
            input = inputMsg.nextLine();
            if (input.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            } else if (input.equals("bye")) {
                break;
            }
            else {
                list.add(input);
                System.out.println("added: " + input + "\n");
            }
        } while (true);
        System.out.println(exitMsg);
    }
}
