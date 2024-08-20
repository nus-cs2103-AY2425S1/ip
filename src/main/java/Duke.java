import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int current = 0;

        Scanner reader = new Scanner(System.in);

        String greet = "Hello! I'm Bob \nWhat can I do for you? \n";
        String bye = "Bye. Hope to see you again soon!";
        String completed = "Nice! I've marked this task as done:";
        String notCompleted = "OK, I've marked this task as not done yet:";
        String input = "";

        System.out.println(greet);

        while (true) {
            input = reader.nextLine();
            if (input.contains("bye")) {
                System.out.println(bye);
                return;
            } else if (input.contains("list")) {
                for (int i = 0; i < current; i++) {
                    System.out.println(i+1 + ". " + tasks[i].toString());
                }
            } else if (input.contains("unmark")) {
                Task chosen = getTaskForMark(input, tasks);
                chosen.unMark();
                System.out.println(notCompleted);
                System.out.println(chosen);
            } else if (input.contains("mark")) {
                Task chosen = getTaskForMark(input, tasks);
                chosen.mark();
                System.out.println(completed);
                System.out.println(chosen);
            } else if (input.contains("todo")) {
                tasks[current] = new ToDo(getTask(input));
                printDetails(tasks[current], current + 1);
                current++;
            } else if (input.contains("deadline")) {
                String [] pieces = getTaskAndDeadLine(input);
                tasks[current] = new DeadLine(pieces[0], pieces[1]);
                printDetails(tasks[current], current + 1);
                current++;
            }else if (input.contains("event")) {
                String [] pieces = getTaskAndEvent(input);
                tasks[current] = new Event(pieces[0], pieces[1], pieces[2]);
                printDetails(tasks[current], current + 1);
                current++;
            }
        }
    }

    private static Task getTaskForMark(String input, Task[] tasks) {
        String [] pieces = input.split(" ");
        return tasks[Integer.parseInt(pieces[1]) - 1];
    }

    private static String getTask(String input) {
        String [] pieces = input.split(" ");
        Stream<String> a = Arrays.stream(pieces).skip(1);
        return a.reduce("", (s1, s2) -> s1  + s2 + " ");
    }

    private static String[] getTaskAndDeadLine(String input) {
        String noCommand = getTask(input);
        String [] pieces = noCommand.split("/by");
        return new String[] {pieces[0] , pieces[1].trim()};
    }

    private static String[] getTaskAndEvent(String input) {
        String noCommand = getTask(input); //removes event etc
        String [] pieces = noCommand.split("/from");

        String task = pieces[0];
        String noFrom = Arrays.stream(pieces).skip(1).reduce("", (s1, s2) -> s1  + s2 + " ");
        pieces = noFrom.split("/to");

        return new String[] {task , pieces[0].trim(), pieces[1].trim()};
    }

    private static void printDetails(Task task, int length) {
        System.out.println("Got it. I've added this task: \n" + task.toString());
        System.out.println("Now you have " + length + " tasks in the list.");
    }
}
