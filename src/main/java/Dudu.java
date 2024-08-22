import java.util.Scanner;

public class Dudu {
    public static void main(String[] args) {
        class LineWrapper {
            static final String lineRule = "--------------------------------------------\n";
            public static String wrap(String content) {
                return lineRule + content + "\n" + lineRule;
            }
        }
        class Task {
            boolean marked = false;
            String name;
            public Task(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                String status = this.marked ? "[X]" : "[ ]";
                return status + " " + this.name;
            }
        }
        String lineRule = "--------------------------------------------\n";
        String welcomeMessage = LineWrapper.wrap("Hello! I'm dudu\n"
                                                + "What can I do for you?");
        String goodbyeMessage = LineWrapper.wrap("Bye. Hope to see you again soon!");

        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int count = 0;
        while (sc.hasNextLine()) {
            String message = sc.nextLine();
            if (message.equals("list")) {
                System.out.println(lineRule);
                if (count == 0) {
                    System.out.println("No tasks");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
                System.out.println(lineRule);
            } else if (message.equals("bye")) {
                break;
            } else if (message.matches("mark \\d")) {
                int pos = Integer.parseInt(message.replaceAll("\\D+","")) - 1;
                tasks[pos].marked = true;
                String output = LineWrapper.wrap("Nice! I've marked this task as done:\n"
                                                + "    " + tasks[pos]);
                System.out.println(output);
            } else if (message.matches("unmark \\d")){
                int pos = Integer.parseInt(message.replaceAll("\\D+","")) - 1;
                tasks[pos].marked = false;
                String output = LineWrapper.wrap("OK, I've marked this task as not done yet:\n"
                                                + "    " + tasks[pos]);
                System.out.println(output);
            } else {
                Task task = new Task(message);
                tasks[count] = task;
                count++;
                System.out.println(LineWrapper.wrap("added: " + message));
            }
        }
        System.out.println(goodbyeMessage);
    }
}
