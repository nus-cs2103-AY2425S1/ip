import java.util.Scanner;
public class Joe {
    public static final String horizontalLine = "____________________________________________________________";
    public static final String chatbotName = "Joe";

    public static void greet() {
        System.out.printf("%s\nHello! I'm %s \nWhat can I do for you? \n%s\n", horizontalLine, chatbotName, horizontalLine);
    }

    public static void farewell() {
        System.out.printf("Bye. Hope to see you again soon!\n%s", horizontalLine);
    }

    public static void handleList(Task[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.println(i + 1 + ". " + list[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Task[] store = new Task[100];
        int currIndex = 0;

        greet();
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            System.out.println(horizontalLine);
            if (input.equals("bye")) {
                break;
            }
            else if (input.equals("list")) {
                handleList(store);
            }
            else {
                System.out.printf("added: %s\n", input);
                store[currIndex] = new Task(input);
                currIndex++;
            }
            System.out.println(horizontalLine);
        }
        farewell();
        scanner.close();
    }
}
