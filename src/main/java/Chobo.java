import java.util.Scanner;
public class Chobo {
    private static final String[] tasks = new String[100];
    private static String line = "----------------------------------------";
    private static int totalTask = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Chobo\nWhat can I do for you?");
        System.out.println(line);
        while (true) {
            String input = scanner.nextLine();
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
            if (input.equals("bye")) {
                break;
            }
        }
//        while (true) {
//            String input = scanner.nextLine();
//            if (input.equals("bye")) {
//                break;
//            } else if (input.equals("list")) {
//                listTask();
//            } else {
//                addTask(input);
//            }
//        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

    }

    private static void listTask() {
        System.out.println(line);
        for (int i = 0; i < totalTask; i++) {
            System.out.println(i+1 + "." + tasks[i]);
        }
        System.out.println(line);
    }

    private static void addTask(String newTask) {
        tasks[totalTask] = newTask;
        System.out.println(line);
        System.out.println("added: " + newTask);
        totalTask++;
        System.out.println(line);
    }
}





