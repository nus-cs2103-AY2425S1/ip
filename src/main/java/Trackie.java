import java.util.Scanner;
import java.util.ArrayList;

public class Trackie {
    private ArrayList<Task> tasks;

    public Trackie() {
        tasks = new ArrayList<>();
    }

    public void addTodoTask(String description) {
        Task instance = new Todo(description);
        tasks.add(instance);
        System.out.println(String.format("Added: [%s][%s] %s",
                instance.getTaskType(), instance.getStatusIcon(), instance.getTaskInfo()));
        System.out.printf("You now have %d task(s) in total.\n", tasks.size());
    }

    public void addDeadlineTask(String description, String deadline) {
        Task instance = new Deadline(description, deadline);
        tasks.add(instance);
        System.out.println(String.format("Added: [%s][%s] %s",
                instance.getTaskType(), instance.getStatusIcon(), instance.getTaskInfo()));
        System.out.printf("You now have %d task(s) in total.\n", tasks.size());
    }

    public void addEventTask(String description, String start, String end) {
        Task instance = new Event(description, start, end);
        tasks.add(instance);
        System.out.println(String.format("Added: [%s][%s] %s",
                instance.getTaskType(), instance.getStatusIcon(), instance.getTaskInfo()));
        System.out.printf("You now have %d task(s) in total.\n", tasks.size());
    }

    public void listTasks() {
        int counter = 1;
        for (Task t : tasks) {
            System.out.println(String.format("%d. [%s][%s] %s",
                    counter, t.getTaskType(), t.getStatusIcon(), t.getTaskInfo()));
            counter++;
        }
    }

    public void markTask(int index) {
        Task t = tasks.get(index - 1);
        t.markDone();
        System.out.println("Amazing! The specified task is now marked as complete:");
        System.out.println(String.format("[%s] %s", t.getStatusIcon(), t.getTaskInfo()));
    }

    public void unmarkTask(int index) {
        Task t = tasks.get(index - 1);
        t.markUndone();
        System.out.println("Alright, the specified task has been marked undone.");
        System.out.println(String.format("[%s] %s", t.getStatusIcon(), t.getTaskInfo()));
    }

    public void greet() {
        System.out.println(
                "Hello, I'm Trackie. Nice to meet you =)\n"
                        + "Instructions:\n"
                        + "To add a todo task: todo [description]\n"
                        + "To add a deadline task: deadline [description] /by [deadline]\n"
                        + "To add an event task: event [description /from [start timing] /to [end timing]\n"

        );
        System.out.println("Type \"list\" to see the list of tasks");
        System.out.println("Type \"mark\" or \"unmark\" followed by the task number to mark said task as done or undone.");
        System.out.println("Type \"bye\" to exit.");
    }

    public void exit() {
        System.out.println("Seeya!");
    }

    public static void main(String[] args) {

        Trackie bot = new Trackie();
        bot.greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Type something: ");
            String userInput = sc.nextLine();
            String[] arguments = userInput.split(" ");

            if (userInput.equals("bye")) {
                bot.exit();
                break;
            }

            if (arguments[0].equals("list")) {
                bot.listTasks();
            } else if (arguments[0].equals("mark")) {
                int index = Integer.parseInt(arguments[1]);
                bot.markTask(index);
            } else if (arguments[0].equals("unmark")) {
                int index = Integer.parseInt(arguments[1]);
                bot.unmarkTask(index);
            } else if (arguments[0].equals("todo")) {
                bot.addTodoTask(userInput.substring(5));
            } else if (arguments[0].equals("deadline")) {
                int ptr = 1;
                StringBuilder sb = new StringBuilder();

                //retrieve description of deadline task
                while (!arguments[ptr].equals("/by")) {
                    sb.append(arguments[ptr]).append(" ");
                    ptr++;
                }
                String desc = sb.substring(0, sb.length() - 1);
                ptr++;
                StringBuilder sb2 = new StringBuilder();

                //retrieve deadline
                while (ptr < arguments.length) {
                    sb2.append(arguments[ptr]).append(" ");
                    ptr++;
                }
                if (sb2.isEmpty()) {
                    System.out.println("Please enter a deadline!");
                } else {
                    String deadline = sb2.substring(0, sb2.length() - 1);
                    bot.addDeadlineTask(desc, deadline);
                }
            } else if (arguments[0].equals("event")) {
                int ptr1 = 1;
                String desc = "";
                String start = "";
                String end = "";
                StringBuilder sb = new StringBuilder();

                //retrieve description of event task
                while (!arguments[ptr1].equals("/from")) {
                    sb.append(arguments[ptr1]).append(" ");
                    ptr1++;
                }
                desc = sb.substring(0, sb.length() - 1);

                //retrieve start time of event task
                ptr1++;
                StringBuilder sb2 = new StringBuilder();
                while (!arguments[ptr1].equals("/to")) {
                    sb2.append(arguments[ptr1]).append(" ");
                    ptr1++;
                }
                if (sb2.isEmpty()) {
                    System.out.println("Please enter a start timing!");
                } else {
                    start = sb2.substring(0, sb2.length() - 1);
                }

                //retrieve end time of events task
                ptr1++;
                StringBuilder sb3 = new StringBuilder();
                while (ptr1 < arguments.length) {
                    sb3.append(arguments[ptr1]).append(" ");
                    ptr1++;
                }
                if (sb3.isEmpty()) {
                    System.out.println("Please enter an end timing!");
                } else {
                    end = sb3.substring(0, sb3.length() - 1);
                }
                if (!desc.isEmpty() && !start.isEmpty() && !end.isEmpty()) {
                    bot.addEventTask(desc, start, end);
                }
            }
        }

    }
}
