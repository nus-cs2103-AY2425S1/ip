import java.util.ArrayList;
import java.util.Scanner;

public class Charlotte {
    public static void main(String[] args) {
        String line = "_________________________________________________";
        System.out.println(line + "\n Hello! I'm Charlotte!\n What can I do for you?\n" + line);

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        String input;


        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                System.out.println(line);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                }
                System.out.println(line);
            } else {
                tasks.add(input);
                System.out.println(line + "\n added: " + input + "\n" + line);
            }

        }

        System.out.println(line + "\n Bye. Hope to see you again soon!\n" + line);

        scanner.close();
    }
}
