import java.util.Scanner;
public class Chobo {
    private static final Task[] tasks = new Task[100];
    private static String line = "----------------------------------------";
    private static int totalTask = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Chobo\nWhat can I do for you?");
        System.out.println(line);
        while (true) {
            String[] input = scanner.nextLine().split(" ");
            if (input[0].equals("bye")) {
                break;
            } else if (input[0].equals("list")) {
                listTask();
            } else if (input[0].equals("mark")){
                markTask(tasks[Integer.parseInt(input[1])-1]);
            } else if (input[0].equals("unmark")) {
                unmarkTask(tasks[Integer.parseInt(input[1])-1]);
            } else {
                addTask(String.join(" ", input));
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

    }

    private static void markTask(Task task) {
        System.out.println(line);
        task.mark();
        System.out.println("Nice! I have marked this task as done:\n" + task);
        System.out.println(line);
    }

    private static void unmarkTask(Task task) {
        System.out.println(line);
        task.unmark();
        System.out.println("OK, I have marked this task as not done yet\n" + task);
        System.out.println(line);
    }
    private static void listTask() {
        System.out.println(line);
        for (int i = 0; i < totalTask; i++) {
            System.out.println(i+1 + "." + tasks[i]);
        }
        System.out.println(line);
    }

    private static void addTask(String newTaskName) {
        Task newTask = new Task(newTaskName,false);
        tasks[totalTask] = newTask;
        System.out.println(line);
        System.out.println("added: " + newTaskName);
        totalTask++;
        System.out.println(line);
    }
}





