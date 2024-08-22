import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
public class Yoda {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = "____________________________________________________________\n";
        String welcomeMessage = "Hello! For you, what can I do?\n";
        String exitMessage = "Bye. See you again soon, I hope to.\n";

        ArrayList<String> tasks = new ArrayList<>();


        System.out.println(line + welcomeMessage + line);

        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                System.out.println(exitMessage + line);
                break;
            } else if (Objects.equals(input, "list")) {
                System.out.println(line);
                String[] taskArray = tasks.toArray(new String[0]);
                for (int i = 0; i < taskArray.length; i++) {
                    System.out.printf("%d. %s%n", i + 1, taskArray[i]);
                }

                System.out.println(line);
            } else {
                tasks.add(input);
                System.out.println(line + input + "\n" + line);
            }
        }
    }
}
