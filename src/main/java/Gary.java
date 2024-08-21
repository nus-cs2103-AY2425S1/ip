import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Gary {
    public static void main(String[] args) {
        Scanner detector = new Scanner(System.in);
        List<String> taskList = new ArrayList<>();
        String greeting = """
                ────────────────────────────────────────────
                 Hello! I'm Gary
                 What can I do for you?     
                ──────────────────────────────────────────── 
                """;
        System.out.println(greeting);

        while (true) {
            String userInput = detector.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                int count = 1;
                for (String task : taskList) {
                    System.out.println("\t" + count + ". " + task);
                    count++;
                }
                continue;
            }
            System.out.println("\tadded: " + userInput);
            taskList.add(userInput);
        }
        detector.close();
    }
}
