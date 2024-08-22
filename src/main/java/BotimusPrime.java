
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BotimusPrime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String name = "BotimusPrime";
        String greetingMessage =
                "____________________________________________________________\n" +
                " Hello! I'm " + name + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        String byeMessage =
                "____________________________________________________________\n" +
                " Bye! Hope to see you again soon!\n" +
                "____________________________________________________________\n";

        List<String> textList = new ArrayList<String>();

        System.out.println(greetingMessage);

        while (true) {
            String input = sc.nextLine();
            System.out.println("\n");
            if (input.equals("bye")) {
                System.out.println(byeMessage);
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < textList.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, textList.get(i));
                }
                System.out.println("____________________________________________________________\n");
            } else {
                textList.add(input);
                System.out.println(
                        "____________________________________________________________\n" +
                        String.format("added: %s\n", input) +
                        "____________________________________________________________\n");
            }
        }

        sc.close();
    }
}
