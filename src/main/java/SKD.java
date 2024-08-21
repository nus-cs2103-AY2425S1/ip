import java.util.ArrayList;
import java.util.Scanner;
public class SKD {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public static void main(String[] args) {
        String line = "    ____________________________________________________________";
        System.out.println(line);
        System.out.println("     Hello! I'm SKD");
        System.out.println("     What can I do for you?");
        System.out.println(line);
        System.out.println();

        ArrayList<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(input);
                System.out.println(line);
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                System.out.println(input);
                System.out.println(line);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks.get(i));
                }
                System.out.println(line);
            } else {
                tasks.add(input);
                System.out.println(input);
                System.out.println(line);
                System.out.println("     added: " + input);
                System.out.println(line);
                System.out.println();
            }
        }
        scanner.close();
    }
}
