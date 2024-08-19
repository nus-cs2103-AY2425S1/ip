import java.util.Scanner;

public class SlothingWaffler {
    public static void main(String[] args) {
        System.out.println("""
                Hello! I'm the Slothing Waffler!
                Let's stop slothing and get cracking!""");

        String[] tasks = new String[100];
        int tasksCount = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.strip().equals("bye")) {
                System.out.println("See you next time! Remember to get a waffle!");
                break;
            }
            if (input.strip().equals("list")) {
                for (int i = 1; i <= tasksCount; i++) {
                    System.out.println((i) + ". " + tasks[i - 1]);
                }
            } else {
                tasks[tasksCount] = input;
                tasksCount++;
                System.out.println("added: " + input);
            }
        }
        scanner.close();
    }
}
