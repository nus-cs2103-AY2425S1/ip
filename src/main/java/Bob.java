import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
    private static final List<String> userInputHistory = new ArrayList<>();
    private static int numUserInputs = 0;

    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob the chatbot!\nHow can I help you?");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            String endKeyword = "bye";
            String historyKeyword = "list";

            if (userInput.equalsIgnoreCase(endKeyword)) {
                System.out.println("Bye! Hope to see you again :)");
                break;
            }

            // Get input history
            if (userInput.equalsIgnoreCase(historyKeyword)) {
                String history = getHistory();
                System.out.println(history);
            } else {
                System.out.println(addUserInput(userInput));
            }
        }

        scanner.close();
    }

    public static String addUserInput(String userInput) {
        userInputHistory.add(userInput);
        numUserInputs++;
        return "added: " + userInput;
    }

    public static String getHistory() {
        StringBuilder history = new StringBuilder();
        for (int i = 1; i <= numUserInputs; i++) {
            String userInput = userInputHistory.get(i - 1);
            if (i == numUserInputs) {
                history.append(i).append(". ").append(userInput);
                continue;
            }
            history.append(i).append(". ").append(userInput).append("\n");
        }
        return history.toString();
    }
}
