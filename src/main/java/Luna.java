import java.util.Scanner;

public class Luna {

    public static void main(String[] args) {
        String greetings = "Hello! I'm Luna\n" +
                "What can I do for you?";
        System.out.println(greetings);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskNum = 0;

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                String exit = "Bye! Hope to see you again soon!";
                System.out.println(exit);
                break;
            }

            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskNum; i++) {
                    String taskStr = String.format("%d.%s",
                            i + 1, tasks[i].toString());
                    System.out.println(taskStr);
                }
            } else {
                String[] str = input.split(" ", 2);
                String command = str[0];

                if (command.equals("mark")) {
                    int i = Integer.parseInt(str[1]) - 1;
                    tasks[i].markAsDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[i].toString());

                } else if (command.equals("unmark")) {
                    int i = Integer.parseInt(str[1]) - 1;
                    tasks[i].unmark();

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[i].toString());

                } else {
                    String[] taskDescription = str[1].split(" /");
                    Task task = null;

                    switch (command) {
                        case "todo":
                            task = new ToDo(taskDescription[0]);
                            tasks[taskNum] = task;
                            break;

                        case "deadline":
                            task = new Deadline(taskDescription[0],
                                    taskDescription[1].substring(3));
                            tasks[taskNum] = task;
                            break;

                        case "event":
                            task = new Event(taskDescription[0],
                                    taskDescription[1].substring(5),
                                    taskDescription[2].substring(3));
                            tasks[taskNum] = task;
                            break;
                    }

                    taskNum++;
                    assert task != null;

                    String s = String.format("Now you have %d tasks in the list.", taskNum);
                    System.out.println("Got it. I've added this task:\n" +
                            "  " + task.toString() + "\n" + s);
                }
            }
        }

        scanner.close();

    }
}
