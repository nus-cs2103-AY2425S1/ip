import java.util.Scanner;

public class Peter {
    // Helper functions
    static Task compressTaskArray(int startingIndex, int lastIndex, Task[] arr) {
        Task t = arr[startingIndex];
        arr[startingIndex] = null;
        for (int i = startingIndex; i < lastIndex; i++) {
            arr[i] = arr[i + 1];
        }

        return t;
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Peter!\nWhat can I do for you?\n");

        // Init Globals
        Task[] tasks = new Task[100];
        int lastIndex = 0;
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().strip();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < lastIndex; i++) {
                    Task t = tasks[i];
                    System.out.println((i + 1) + String.format(".%s ", t.toString()));
                }
            } else if (command.contains("unmark")) {
                // extract integer value
                String intValue = command.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(intValue);

                Task t = tasks[index - 1];
                t.markAsNotDone();

                System.out.println("OK! I've marked the task as not done yet: ");
                System.out.println(t.toString());
            } else if (command.contains("mark")) {
                // extract integer value
                String intValue = command.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(intValue);

                Task t = tasks[index - 1];
                t.markAsDone();

                System.out.println("Good job! I've marked this task as done: ");
                System.out.println(t.toString());
            } else if (command.contains("delete")) {
                // extract integer value
                String intValue = command.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(intValue);

                Task t = compressTaskArray(index - 1, lastIndex, tasks);
                lastIndex--;
                System.out.println("Noted! I've removed this task:");
                System.out.println(t.toString());
            } else {
                String name = "";
                if (command.contains("todo")) {
                    name = command.replace("todo ", "");
                    tasks[lastIndex++] = new ToDos(name);
                } else if (command.contains("deadline")) {
                    String[] splits = command.split("/");
                    name = splits[0].replace("deadline ", "").strip();
                    String details = splits[1].replace("by", "by:");
                    tasks[lastIndex++] = new Deadlines(name, details);
                } else if (command.contains("event")) {
                    String[] splits = command.split("/");
                    name = splits[0].replace("event ", "").strip();
                    String details = splits[1].replace("from", "from:") + splits[2].replace("to", "to:");
                    tasks[lastIndex++] = new Event(name, details);
                }
                System.out.println("I've added: " + name);
                System.out.println(String.format("Now you have %d in the list!", lastIndex));
            }

            command = scanner.nextLine();
        }

        // EXIT
        System.out.println("\nBye, hope to see you again soon!");
        scanner.close();
    }
}
