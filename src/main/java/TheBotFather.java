import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class TheBotFather {

    public static void main(String[] args) {

        printLine();
        printGreeting();
        printLine();

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            printLine();
            if (Objects.equals(input, "bye")) break;
            if (Objects.equals(input, "list")) printList(taskList);
            else {

                if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(String.valueOf(input.split(" ")[1])) - 1;
                    print("Nice! I've marked this task as done:");
                    taskList.get(index).markAsDone();
                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(String.valueOf(input.split(" ")[1])) - 1;
                    print("OK, I've marked this task as not done yet:");
                    taskList.get(index).unmark();
                } else {
                    Task task = new Task(input);
                    print("added: " + task);
                    taskList.add(task);
                }

            }
            printLine();
        }
        printGoodBye();
        printLine();
    }


    public static void printList(ArrayList<Task> taskList) {
        print("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            print(i + 1 + ". " + taskList.get(i));
        }
    }

    public static void print(String str) {
        System.out.println("    " + str);
    }

    public static void printGreeting() {
        System.out.println("    Hello! I'm The BotFather");
        System.out.println("    What can I do for you?");
    }

    public static void printGoodBye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
}
