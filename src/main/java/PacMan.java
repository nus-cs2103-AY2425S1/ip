import java.util.ArrayList;
import java.util.Scanner;

public class PacMan {
    private static final ArrayList<Task> list = new ArrayList<>();

    private static void echo(String text) {
        System.out.println("  " + text);
    }

    private static void greet() {
        echo("Hello! I'm PacMan");
        echo("How can I help you?");
    }

    private static void exit() {
        echo("Good bye. Hope to see you soon!");
    }


    private static void addList(String item) {
        Task newTask = new Task(item);
        list.add(newTask);
    }

    private static void printList() {
        for (int index = 1; index <= list.size(); index = index + 1) {
            echo(index + "." + list.get(index - 1));
        }
    }

    private static void markTask(int index) {
        echo("Nice! I've marked this task done");
        list.get(index - 1).setMarkDone(true);
        echo("  " + list.get(index - 1));
    }

    private static void unmarkTask(int index) {
        echo("Ok! I've marked this task as not done yet");
        list.get(index - 1).setMarkDone(false);
        echo("  " + list.get(index - 1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printList();
            } else if (input.split(" ")[0].equals("mark")) {
                try {
                    markTask(Integer.parseInt(input.split(" ")[1]));
                } catch (NumberFormatException e) {
                    echo("Invalid index input");
                }
            } else if (input.split(" ")[0].equals("unmark")) {
                try {
                    unmarkTask(Integer.parseInt(input.split(" ")[1]));
                } catch (NumberFormatException e) {
                    echo("Invalid index input");
                }
            } else {
                echo(input);
                addList(input);
            }
        }
        exit();
    }
}
