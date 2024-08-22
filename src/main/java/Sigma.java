import java.io.*;
import java.util.*;
public class Sigma {
    public static ArrayList<String> items;

    public static String toPrettyList(List<String> itemsArray) {
        StringBuilder result = new StringBuilder(); // this is a terrible time complexity
        for (int i = 0; i < itemsArray.size(); i++) {
            result.append(i + 1).append(". ").append(itemsArray.get(i)).append("\n");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        items = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        String welcomeMessage = "Hello! I'm Sigma \nWhat can I do for you? \n";
        System.out.println(welcomeMessage);

        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.contains("list")) {
                System.out.println(toPrettyList(items));
                continue;
            }
            if (userInput.contains("bye")) {
                break;
            }
            items.add(userInput);
            System.out.println("added: " + userInput);
        }
        System.out.println("Bye! See you soon");
    }
}
