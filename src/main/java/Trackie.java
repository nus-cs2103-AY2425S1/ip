import java.util.Scanner;
import java.util.ArrayList;

public class Trackie {
    private ArrayList<Task> tasks;

    public Trackie() {
        tasks = new ArrayList<>();
    }

    public void addTodoTask(String[] arguments) throws DukeException {
        if (arguments.length == 1) {
            throw new DukeException("Incorrect usage!");
        }
        StringBuilder sb = new StringBuilder();
        int ptr = 1;
        while (ptr < arguments.length) {
            sb.append(arguments[ptr]).append(" ");
            ptr++;
        }
        String desc = sb.substring(0, sb.length() - 1);
        Task instance = new Todo(desc);
        tasks.add(instance);
        System.out.println(String.format("Added: [%s][%s] %s",
                instance.getTaskType(), instance.getStatusIcon(), instance.getTaskInfo()));
        System.out.printf("You now have %d task(s) in total.\n", tasks.size());
    }

    public void addDeadlineTask(String[] arguments) throws DukeException {
        if (arguments.length == 1) {
            throw new DukeException("Incorrect usage!");
        }

        String desc = "";
        String deadline = "";

        //retrieve the description
        int ptr = 1;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        while (!arguments[ptr].equals("/by")) {
            if (ptr == arguments.length - 1) {
                throw new DukeException("Incorrect usage!");
            }
            sb.append(arguments[ptr]).append(' ');
            ptr++;
        }
        if (sb.isEmpty()) {
            throw new DukeException("Description cannot be empty!");
        } else {
            desc = sb.substring(0, sb.length() - 1);
        }

        //retrieve the deadline
        ptr++;
        while (ptr < arguments.length) {
            sb2.append(arguments[ptr]).append(' ');
            ptr++;
        }

        if (sb2.isEmpty()) {
            throw new DukeException("Deadline cannot be empty!");
        } else {
            deadline = sb2.substring(0, sb2.length() - 1);
        }

        Task instance = new Deadline(desc, deadline);
        tasks.add(instance);
        System.out.println(String.format("Added: [%s][%s] %s",
                instance.getTaskType(), instance.getStatusIcon(), instance.getTaskInfo()));
        System.out.printf("You now have %d task(s) in total.\n", tasks.size());
    }

    public void addEventTask(String[] arguments) throws DukeException {
        if (arguments.length == 1) {
            throw new DukeException("Incorrect usage!");
        }

        String desc = "";
        String start = "";
        String end = "";





        //retrieve the description
        int ptr = 1;
        StringBuilder sb = new StringBuilder();
        while (!arguments[ptr].equals("/from")) {
            if (ptr == arguments.length - 1) {
                throw new DukeException("Incorrect usage!");
            }
            sb.append(arguments[ptr]).append(' ');
            ptr++;
        }
        if (sb.isEmpty()) {
            throw new DukeException("Description cannot be empty!");
        } else {
            desc = sb.substring(0, sb.length() - 1);
        }

        ptr++;
        if (ptr >= arguments.length) {
            throw new DukeException("Incorrect usage!");
        }
        //retrieve the start time
        StringBuilder sb2 = new StringBuilder();
        while (!arguments[ptr].equals("/to")) {
            if (ptr == arguments.length - 1) {
                throw new DukeException("Incorrect usage!");
            }
            sb2.append(arguments[ptr]).append(" ");
            ptr++;
        }
        if (sb2.isEmpty()) {
            throw new DukeException("Start timing cannot be empty!");
        } else {
            start = sb2.substring(0, sb2.length() - 1);
        }

        ptr++;
        //retrieve the end time
        StringBuilder sb3 = new StringBuilder();
        while (ptr < arguments.length) {
            sb3.append(arguments[ptr]).append(" ");
            ptr++;
        }
        if (sb3.isEmpty()) {
            throw new DukeException("End timing cannot be empty!");
        } else {
            end = sb3.substring(0, sb3.length() - 1);
        }

        Task instance = new Event(desc, start, end);
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

    public void markTask(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Invalid index.");
        }
        Task t = tasks.get(index - 1);
        t.markDone();
        System.out.println("Amazing! The specified task is now marked as complete:");
        System.out.println(String.format("[%s] %s", t.getStatusIcon(), t.getTaskInfo()));
    }

    public void unmarkTask(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Invalid index.");
        }
        Task t = tasks.get(index - 1);
        t.markUndone();
        System.out.println("Alright, the specified task has been marked undone:");
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
                if (bot.tasks.isEmpty()) {
                    System.out.println("You currently have no tasks added.");
                } else {
                    bot.listTasks();
                }
            } else if (arguments[0].equals("mark")) {
                try {
                    int index = Integer.parseInt(arguments[1]);
                    bot.markTask(index);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e2) {
                    System.out.println("Invalid index.");
                }
            } else if (arguments[0].equals("unmark")) {
                try {
                    int index = Integer.parseInt(arguments[1]);
                    bot.unmarkTask(index);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e2) {
                    System.out.println("Invalid index.");
                }
            } else if (arguments[0].equals("todo")) {
                try {
                    bot.addTodoTask(arguments);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (arguments[0].equals("deadline")) {
                try {
                    bot.addDeadlineTask(arguments);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (arguments[0].equals("event")) {
                try {
                    bot.addEventTask(arguments);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Invalid command.");
            }
        }

    }
}
