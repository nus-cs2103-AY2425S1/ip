import java.util.Scanner;
public class Bao {
    private static Task[] messages = new Task[100];
    private static int messageCount = 0;

    private static String baoHappy =   "     ___   \n"
            + "   /     \\  \n"
            + "  /       \\ \n"
            + " |  ^   ^  |\n"
            + " |    3    |\n"
            + " \\________/ \n";

    private static String baoSad =   "     ___   \n"
            + "   /     \\  \n"
            + "  /       \\ \n"
            + " |  T   T  |\n"
            + " |    ^    |\n"
            + " \\________/ \n";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(baoHappy);
        System.out.println("Hello! I'm Bao but you can call me Bao");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(baoSad);
                System.out.println("Bye :( Come back soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equalsIgnoreCase("list")){
                System.out.println("____________________________________________________________");
                showMessages();
                System.out.println("____________________________________________________________");
            } else {
                addMessage(input);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }

    private static void showMessages() {
        if (messageCount == 0) {
            System.out.println("You haven't told Bao anything yet!");
        } else {
            for (int i = 0; i < messageCount; i++) {
                System.out.println((i + 1) + ". " + messages[i]);
            }
        }
    }

    private static void addMessage(String message) {
        if (messageCount < 100) {
            messages[messageCount] = new Task(message);
            messageCount++;
        } else {
            System.out.println(baoSad);
            System.out.println("Bao cannot remember so many things :(");
        }
    }
}

class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private void mark() {
        this.isDone = true;
    }

    private void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
