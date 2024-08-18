import java.util.Objects;
import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {

        String horizontalLine = "\t____________________________________________________________"; // For better readability

        System.out.println(horizontalLine);
        System.out.println("\tWelcome Back! I'm Friday");
        System.out.println("\tWhat can I do for you today?");
        System.out.println(horizontalLine);

        Scanner sc = new Scanner(System.in);
        String input;
        String[] items = new String[100];
        int index = 0;
        while (true) {
            input = sc.nextLine();
            if (Objects.equals(input, "bye")) break;
            if (Objects.equals(input, "list")) {
                System.out.println(horizontalLine);
                for (int i = 0; i < index; i++) {
                    System.out.println("\t" + (i + 1) + ". " + items[i]);
                }
                System.out.println(horizontalLine);
                continue;
            }
            items[index++] = input;
            System.out.println(horizontalLine + "\n\tadded: " + input + "\n" + horizontalLine);
        }
        System.out.println(horizontalLine);
        System.out.println("\tGood Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
