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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        greet();
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            System.out.println(horizontalLine);
            if (input.equals("bye")) {
                break;
            }
            System.out.println(input);
            System.out.println(horizontalLine);
        }
        farewell();
        scanner.close();
    }
}
