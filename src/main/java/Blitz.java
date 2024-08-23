import java.util.ArrayList;
import java.util.Scanner;

public class Blitz{
    public static void main(String[] args) {
        System.out.println("---------------\n" +
                "Hello! I'm BLITZ \n" +
                "What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> inputHistory = new ArrayList<>();

        while (true) {
            String userInput = scanner.nextLine();
            String strippedInput = userInput.trim().toLowerCase();
            if (strippedInput.equals("bye")) {
                System.out.println("----------------\n" +
                        "Till we meet again, GOODBYE!");
                break;
            } else if (strippedInput.equals("list")) {
                System.out.println("---------------\n");
                inputHistory.forEach(str -> System.out.println((inputHistory.indexOf(str) + 1) +
                        ". " +
                        str));
                System.out.println("---------------\n");
            } else {
                inputHistory.add(userInput);
                System.out.println("----------------\n" +
                        "added: " + userInput + "\n" +
                        "----------------\n");
            }
        }
    }
}
