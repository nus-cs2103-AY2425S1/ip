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

    public static void handleDone(Task[] list, int index) {
        if (list[index] != null) {
            list[index].toggleDone();
            System.out.printf("Nice! I've marked this task as done: \n%s\n%s", list[index], horizontalLine);
        }
    }

    public static void handleUndone(Task[] list, int index) {
        if (list[index] != null) {
            list[index].toggleDone();
            System.out.printf("Nice! I've marked this task as not done yet: \n%s\n%s", list[index], horizontalLine);
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
            else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                handleDone(store, index);
            }
            else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                handleUndone(store, index);
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
