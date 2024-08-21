import java.util.Scanner;
public class Gary {
    public static void main(String[] args) {
        Scanner detector = new Scanner(System.in);
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
            System.out.println("\t" + userInput);
        }
        detector.close();
    }
}
