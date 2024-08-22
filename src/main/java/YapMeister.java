import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class YapMeister {
    final static int MAX_TASKS = 5;
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                               Hello I'm YapMeister
                               YAPYAPYAPYAP
                               """);
        String input = "";
        boolean running = true;
        Task[] tasks = new Task[MAX_TASKS];
        int taskIndex = 0;
        while (running) {
            System.out.print("\n");
            input = scanner.nextLine();
            String[] command = input.split(" ");
            switch (command[0]) {
                case "bye":
                    running = false;
                    break;
                case "list":
                    for (int i = 0; i < taskIndex; i++) {
                        System.out.println(String.format("%d. %s", i + 1, tasks[i].toString()));
                    }
                    break;
                case "mark":
                    //might need error handling
                    int index = parseInt(command[1]) - 1;
                    tasks[index].setCompleted(true);
                    System.out.println("You did this:");
                    System.out.println(tasks[index].toString());
                    break;
                case "unmark":
                    int umindex = parseInt(command[1]) - 1;
                    tasks[umindex].setCompleted(false);
                    System.out.println("You undid this:");
                    System.out.println(tasks[umindex].toString());
                    break;
                default:
                    if (taskIndex >= MAX_TASKS) {
                        System.out.println("task list full");
                        break;
                    }
                    tasks[taskIndex] = new Task(input);
                    taskIndex++;
                    System.out.println("added: " + input);
            }
        }
        System.out.println("Fine. Bye. Leave and never return");
    }
}
