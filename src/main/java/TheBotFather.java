import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class TheBotFather {

    public static void main(String[] args) {

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        printLine();
        printGreeting();
        printLine();

        while (true) {
            String input = sc.nextLine();
            printLine();
            if (Objects.equals(input, "bye")) break;
            String[] taskDec = input.split(" ", 2);
            Task task;
            int index;

            switch (taskDec[0]) {
                case "list":
                    print("Here are the tasks in your list:");
                    printList(taskList);
                    printLine();
                    break;

                case "todo":
                    task = new Todo(taskDec[1]);
                    taskList.add(task);
                    print("Got it. I've added this task:");
                    print("    " + task.toString());
                    print(Task.getCOUNT());
                    printLine();
                    break;

                case "deadline":
                    String[] deadlineInfo = taskDec[1].split(" /by ");
                    task = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                    taskList.add(task);
                    print("Got it. I've added this task:");
                    print("    " + task.toString());
                    print(Task.getCOUNT());
                    printLine();
                    break;

                case "event":
                    String[] eventInfo = taskDec[1].split(" /from | /to ");
                    if (eventInfo.length != 3) break;
                    task = new Event(eventInfo[0], eventInfo[1], eventInfo[2]);
                    taskList.add(task);
                    print("Got it. I've added this task:");
                    print("    " + task.toString());
                    print(Task.getCOUNT());
                    printLine();
                    break;

                case "mark":
                    index = Integer.parseInt(String.valueOf(taskDec[1])) - 1;
                    task = taskList.get(index);
                    task.markAsDone();
                    print("Nice! I've marked this task as done:");
                    print("    " + task.toString());
                    printLine();
                    break;

                case "unmark":
                    index = Integer.parseInt(String.valueOf(taskDec[1])) - 1;
                    task = taskList.get(index);
                    task.unmark();
                    print("OK, I've marked this task as not done yet:");
                    print("    " + task.toString());
                    printLine();
                    break;

                default:
                    print("I don't know this command: " + taskDec[0]);
                    break;
            }
        }

        printGoodBye();
        printLine();
    }


    public static void printList(ArrayList<Task> taskList) {
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
