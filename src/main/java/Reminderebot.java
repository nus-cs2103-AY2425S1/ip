import java.util.Scanner;
public class Reminderebot {
    private Task[] tasks = new Task[100];
    private static final String topBuffer = "____________________________________________________________\n";
    private static final String bottomBuffer = "____________________________________________________________";
    static int index = 0;
    private static final String greetingText = topBuffer +
            " Hello! I'm [Reminderebot]\n" +
            " What can I do for you?\n" +
            topBuffer;

    private static final String goodbyeText = topBuffer +
            " Bye. Hope to see you again soon!\n" +
            bottomBuffer;

    public static void main(String[] args) {
        Reminderebot reminderebot = new Reminderebot();
        Scanner sc = new Scanner(System.in);
        reminderebot.greeting();
        while (true) {
            System.out.println("");
            String command = sc.nextLine();
            // Check if the command is mark or unmark
            String[] arr = command.split(" ");
            int idx; // used to mark task as done or undone
            if (arr.length>1 && (arr[0].equals("mark") || arr[0].equals("unmark"))) {
                try {
                    idx = Integer.parseInt(arr[1]); // possible error: next input is not an int
                    command = arr[0];   // possible error: mark is the only word in the string
                } catch (NumberFormatException e) {
                    // don't set command as mark or unmark
                }
            }
            // executes command
            switch (command) {
                case "bye":
                    reminderebot.goodbye();
                    break;
                case "list":
                    reminderebot.printTasks();
                    break;
                case "mark":
                    if (arr.length>1) {
                        idx = Integer.parseInt(arr[1]);
                        if (idx < arr.length) {
                            reminderebot.markTask(idx);
                            System.out.println("Nice! I've marked this task as done:\n" +
                                    reminderebot.tasks[idx - 1]);
                        } else {
                            System.out.println("index is out of bounds");
                        }
                        break;
                    } else {
                        reminderebot.addTasks(command);
                        System.out.println("____________________________________________________________\n" +
                                "added: " + command +
                                "\n____________________________________________________________"
                        );
                    }
                    break;
                case "unmark":
                    if (arr.length>1) {
                        idx = Integer.parseInt(arr[1]);
                        if (idx < arr.length) {
                            reminderebot.unmarkTask(idx);
                            System.out.println("OK, I've marked this task as not done yet:\n" +
                                    reminderebot.tasks[idx - 1]);
                        } else {
                            System.out.println("index is out of bounds");
                        }
                        break;
                    } else {
                        reminderebot.addTasks(command);
                        System.out.println("____________________________________________________________\n" +
                                "added: " + command +
                                "\n____________________________________________________________"
                        );
                    }
                    break;
                default:
                    reminderebot.addTasks(command);
                    System.out.println("____________________________________________________________\n" +
                            "added: " + command +
                            "\n____________________________________________________________"
                    );
            }
            if (command.equals("bye")) break;
        }
    }

    private void greeting() {
        System.out.println(greetingText);
    }

    private void goodbye() {
        System.out.println(goodbyeText);
    }

    private void printTasks() {
        StringBuilder output = new StringBuilder();
        output.append(topBuffer);
        output.append("Here are the tasks in your list:\n");
        for (int i=0; i<index; i++) {
            output.append(i+1).append(".").append(tasks[i]).append("\n");
        }
        output.append(bottomBuffer);
        String taskList = output.toString();
        System.out.println(taskList);
    }

    private void addTasks(String task) {
        Task newTask = new Task(task);
        tasks[index] = newTask;
        index++;
    }

    private void markTask(int idx) {
        if (idx > 0 && idx < index+1) {
            Task task = tasks[idx-1];
            task.markAsDone();
        }
    }

    private void unmarkTask(int idx) {
        if (idx > 0 && idx <= index) {
            Task task = tasks[idx - 1];
            task.markAsUndone();
        }
    }
}
