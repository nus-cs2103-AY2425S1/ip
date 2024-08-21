import java.util.Scanner;

public class Joe {
    private static final String line =
            "____________________________________________________________";
    private static Task[] userTasks = new Task[100];
    private static int taskCount = 0;
    private static void bye() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    public static void echo() {
        Scanner reader = new Scanner(System.in);
        int count = 0;
        String userCmd = reader.nextLine();
        String joesThoughts = null;

        while (!userCmd.equalsIgnoreCase("bye")) {
            count++;
            switch (count) {
                case 1:
                    joesThoughts = "Joe: I'm just an echo chamber...";
                    break;
                case 5:
                    joesThoughts =  "Joe: I'm being overworked here ;~;";
                    break;
                case 10:
                    joesThoughts = "Joe: Please type bye";
                    break;
                case 15:
                    System.out.printf("%s\nJoe: I'm shutting this operation down!\n", line);
                    break;
                default:
                    joesThoughts = null;
            }

            if (count == 15) {
                break;
            }

            if (joesThoughts != null) {
                System.out.printf("%s\n%s\n%s\n%s\n", line, userCmd, joesThoughts, line);
            } else {
                System.out.printf("%s\n%s\n%s\n", line, userCmd, line);
            }

            userCmd = reader.nextLine();
        }
        bye();
    }

    public static void add(String s) {
        System.out.println(line);
        if (taskCount < 100) {
            Task newTask = new Task(s);
            userTasks[taskCount++] = newTask;
            System.out.printf("added: %s\n", newTask);
        } else {
            System.out.println("Input has reached max capacity!");
        }
        System.out.println(line);
    }

    public static void list() {
        System.out.println(line);
        for (int i = 0; i < taskCount; i++) {
            Task currTask = userTasks[i];
            System.out.printf("%d. %s\n", i+1, currTask);
        }
        System.out.println(line);
    }

    public static void mark(int idx) {
        System.out.println(line);
        if (idx-1 < taskCount) {
            Task toBeMarked = userTasks[idx-1];
            if (!toBeMarked.isDone()) {
                toBeMarked.setDone(true);
                System.out.printf("Nice! I've marked this task as done:\n  %s\n", toBeMarked);
            } else {
                System.out.println("This task has already been marked.");
            }
        } else {
            System.out.printf("There is no task %d\n", idx);
        }
        System.out.println(line);
    }

    public static void main(String[] args) {
        System.out.printf("%s\nHello! I'm Joe\nWhat can I do for you?\n%s\n",
                line, line);
//        echo();

        Scanner reader = new Scanner(System.in);
        String userIn = reader.nextLine().strip();
        while (!userIn.equalsIgnoreCase("bye")) {
            if (userIn.equalsIgnoreCase("list")) {
                list();
            } else if (userIn.startsWith("mark")) {
                int idx = -1;
                int n = userIn.length();
                for (int i = 5; i < n; i++) {
                    while (i < n && Character.isDigit(userIn.charAt(i))) {
                        if (idx < 0) {
                            idx = 0;
                        }
                        idx = idx * 10 + (userIn.charAt(i++) - '0');
                    }
                }
                mark(idx);
            } else {
                add(userIn);
            }
            userIn = reader.nextLine();
        }

        bye();
    }
}
