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
        while (true) {
            input = sc.nextLine();
            if (Objects.equals(input, "bye")) break;
            System.out.println(horizontalLine + "\n\t" + input + "\n" + horizontalLine);
        }
        System.out.println(horizontalLine);
        System.out.println("\tGood Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
