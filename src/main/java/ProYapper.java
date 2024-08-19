import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ProYapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String greeting = "Hello! I am Pro Yapper!\nWhat can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";

        List<String> list = new ArrayList<>();

        System.out.println(greeting);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("\n" + goodbye);
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("\n");
                for (int i = 0; i < list.size(); i++){
                    int lstNum = i + 1;
                    System.out.println(lstNum + ". " + list.get(i));
                }
                System.out.println("\n");
            } else {
                list.add(userInput);
                System.out.println("\nadded: " + userInput + "\n");
            }
        }
        scanner.close();
    }
}

