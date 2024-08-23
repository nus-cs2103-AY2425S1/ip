import java.util.Scanner;

public class Dawn {
    public static void main(String[] args) {
        String divider = "--".repeat(30);

        System.out.println(divider);
        System.out.println("Dawn 🌙 speaking, what can I do for you?");
        respond();
        System.out.println(divider);
    }
    private static Scanner scanner = new Scanner(System.in);
    private static Task[] tasks = new Task[100];
    private static int counter = 0;

    private static void respond() { //provide responses to the user input
        String input = scanner.nextLine();

        if (input.equals("bye")) {
            System.out.println("Byeeee~ nice chatting with you! See you next time, Dawn 🌙 out");
        } else if (input.equals("list")) {
            System.out.println("listing all tasks...");
            for (int i = 0; i < counter; i++) {
                System.out.printf("%d. %s  \n", i + 1, tasks[i].getDesc());
            }
            respond();
        } else if (input.contains("mark")) {
            int ind = scanner.nextInt(); // checkkkkkkkk!!!
            mark(input, ind);
            respond();
        } else {
            Task t;
            String[] s = input.split("/");
            int indexOfSpace = s[0].indexOf(" ");
            s[0] = s[0].substring(indexOfSpace + 1); // remove the command word e.g. todo
            if (input.contains("todo")) {
                t = new ToDo(s[0]);
            } else if (input.contains("deadline")) {
                t = new Deadline(s[0], s[1]);
            } else {
                t = new Event(s[0], s[1], s[2]);
            }
            tasks[counter] = t;
            counter++;
            System.out.println("Gotcha! I've added this task: \n" + t.getDesc());
            System.out.printf("Now you have %d task(s) in the list \n", counter);
            respond();
        }
    }

    private static void mark(String input, int ind) { // mark the tasks accordingly
        if (input.contains("unmark")) {
            tasks[ind].markAsNotDone();
        } else {
            tasks[ind].markAsDone();
        }
        System.out.println(tasks[ind].getDesc());
    }
}
