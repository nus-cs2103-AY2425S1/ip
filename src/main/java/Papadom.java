import java.util.Objects;
import java.util.Scanner;
public class Papadom {
    public static String printList(String[] list) {
        String finalList = "";
        int current = 1;
        for (String task : list) {
            if (task == null) break;
            finalList += current++ + ". " + task + "\n";
        }
        return finalList;
    }
    public static void main(String[] args) {

        String logo = "____________________________________________________________\n"
                + "Hello! I'm Papadom\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        String[] list = new String[100];
        int current = 0;

        String exitMessage = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";

        System.out.println(logo);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            if (Objects.equals(text, "list")) {
                String currentList = printList(list);
                System.out.println(currentList);
                continue;
            } else if (Objects.equals(text, "bye")) {
                break;
            } else {
                list[current++] = text;
                String response = "____________________________________________________________\n"
                        + " added: " + text + "\n"
                        + "____________________________________________________________";
                System.out.println(response);
            }
        }
        System.out.println(exitMessage);
    }
}
