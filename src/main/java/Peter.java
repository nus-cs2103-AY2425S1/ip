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

    static void updateUser(String name, int lastIndex) {
        System.out.println("I've added: " + name);
        System.out.println(String.format("Now you have %d in the list!", lastIndex));
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Peter!\nWhat can I do for you?\n");

        // Init Globals
        Task[] tasks = new Task[100];
        int lastIndex = 0;
        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (true) {
            command = scanner.nextLine().strip();
            if (command.equals("bye")) {
                break;
            }
            try {

                if (command.startsWith("list")) {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < lastIndex; i++) {
                        Task t = tasks[i];
                        System.out.println((i + 1) + String.format(".%s ", t.toString()));
                    }
                } else if (command.startsWith("unmark")) {
                    // extract integer value
                    String intValue = command.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(intValue);

                    Task t = tasks[index - 1];
                    t.markAsNotDone();

                    System.out.println("OK! I've marked the task as not done yet: ");
                    System.out.println(t.toString());
                } else if (command.startsWith("mark")) {
                    // extract integer value
                    String intValue = command.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(intValue);

                    Task t = tasks[index - 1];
                    t.markAsDone();

                    System.out.println("Good job! I've marked this task as done: ");
                    System.out.println(t.toString());
                } else if (command.startsWith("delete")) {
                    // extract integer value
                    String intValue = command.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(intValue);

                    Task t = compressTaskArray(index - 1, lastIndex, tasks);
                    lastIndex--;
                    System.out.println("Noted! I've removed this task:");
                    System.out.println(t.toString());
                } else {
                    String name = "";
                    if (command.startsWith("todo")) {
                        name = command.substring(4).strip();
                        if (name.isEmpty()) {
                            throw new BadDescriptionException(TaskTypes.TODO);
                        }
                        tasks[lastIndex++] = new ToDos(name);
                        updateUser(name, lastIndex);
                    } else if (command.startsWith("deadline")) {
                        String[] splits = command.split("/");
                        name = splits[0].substring(8).strip();
                        if (splits.length != 2 || name.isEmpty()) {
                            throw new BadDescriptionException(TaskTypes.DEADLINE);
                        }
                        // specific to deadlines
                        String details = splits[1].replace("by", "by:");
                        tasks[lastIndex++] = new Deadlines(name, details);
                        updateUser(name, lastIndex);
                    } else if (command.startsWith("event")) {
                        String[] splits = command.split("/");
                        name = splits[0].substring(5).strip();
                        if (splits.length != 3 || name.isEmpty()) {
                            throw new BadDescriptionException(TaskTypes.EVENT);
                        }
                        // specific to events
                        String details = splits[1].replace("from", "from:") + splits[2].replace("to", "to:");
                        tasks[lastIndex++] = new Event(name, details);
                        updateUser(name, lastIndex);
                    } else {
                        throw new UnknownCommandException();
                    }
                }
            } catch (UnknownCommandException | BadDescriptionException e) {
                System.out.println(e.getMessage());
            }
        }

        // EXIT
        System.out.println("\nBye, hope to see you again soon!");
        scanner.close();
    }
}
