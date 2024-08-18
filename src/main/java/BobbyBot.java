import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BobbyBot {
    public static void main(String[] args) {
        String chatBotName = "BobbyBot";

        Task[] tasks = new Task[100];
        Scanner myScanner = new Scanner(System.in);
        printInput("Hello! I'm " + chatBotName, "What can I do for you?");
        while (true) {
            String input = myScanner.nextLine();
            switch (input) {
                case "list":
                    listTasks(tasks);
                    break;
                case "bye":
                    printInput("Bye. Hope to see you again soon!");
                    return;
                default:
                    if (input.startsWith("mark")) {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        tasks[index].setIsDone(true);
                        printInput("Nice! I've marked this task as done:",  "\t" + tasks[index]);
                    } else if (input.startsWith("unmark")) {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        tasks[index].setIsDone(false);
                        printInput("OK, I've marked this task as not done yet:",  "\t" + tasks[index]);
                    } else {
                        printInput("added: " + input);
                        for (int i = 0; i < tasks.length; i++) {
                            if (tasks[i] == null) {
                                tasks[i] = new Task(input);
                                break;
                            }
                        }
                    }
                    break;
            }
        }
    }

    private static void listTasks(Task[] tasks) {
        String[] formattedMessages = IntStream.
                range(0, tasks.length).
                filter(i -> tasks[i] != null).
                mapToObj(i -> i + 1 + ". " + tasks[i]).
                toArray(String[]::new);
        String[] combined = new String[formattedMessages.length + 1];
        combined[0] = "Here are the tasks in your list:";
        System.arraycopy(formattedMessages, 0, combined, 1, formattedMessages.length);
        printInput(combined);
    }

    private static void printInput(String ...input) {
        System.out.print("\t");
        printLine();
        for (String i : input) {
            System.out.println("\t" + i);
        }
        System.out.print("\t");
        printLine();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
