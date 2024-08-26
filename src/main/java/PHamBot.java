import javax.lang.model.type.ErrorType;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class PHamBot {
    private static final String line = "____________________________________________________________\n";
    private static TaskList tasks = new TaskList();

    private static final String[] UserGreetings = {"Hello", "Hi", "What's up"};
    private static final String[] Days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public static void main(String[] args) {
        Greet();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                SayGoodbye();
                break;
            }

            if (input.equals("list")) {
                ListTasks();
            }

            if (input.contains("delete")) {
                try {
                    int index = checkIndexInput(input) - 1;
                    printRemoveTask(index);
                    tasks.deleteTask(index);
                } catch (MissingIndexException e) {
                    OutlineMessage("Bro, which task you want me to delete sia...");
                }
            }

            if (input.contains("todo")) {
                try {
                    ToDo task = checkToDoInput(input, new ToDo());
                    tasks.addTask(task);
                    OutlineMessage("Added: " + task.toString());
                } catch (MissingTaskException e) {
                    OutlineMessage("You gotta give me a task man!");
                }
            }

            if (input.contains("deadline")) {
                try {
                    DatedTask task = checkDatedTaskInput(input, new Deadline());
                    tasks.addTask(task);
                    OutlineMessage("Added: " + task.toString());
                } catch (MissingTaskException e) {
                    OutlineMessage("You gotta give me a task man!");
                } catch (MissingDateException e) {
                    OutlineMessage("You haven't added a date for the task!");
                } catch (MissingDividerException e) {
                    OutlineMessage("You missed out a slash to separate the task and date!");
                }
            }

            if (input.contains("event")) {
                try {
                    DatedTask task = checkDatedTaskInput(input, new Event());
                    tasks.addTask(task);
                    OutlineMessage("Added: " + task.toString());
                } catch (MissingTaskException e) {
                    OutlineMessage("You gotta give me a task man!");
                } catch (MissingDateException e) {
                    OutlineMessage("You haven't added a date for the task!");
                } catch (MissingDividerException e) {
                    OutlineMessage("You missed out a slash to separate the task and date!");
                }
            }

            if (input.contains("unmark")) {
                unmark(Integer.parseInt(input.substring(7)));
            } else if (input.contains("mark")) {
                mark(Integer.parseInt(input.substring(5)));
            }
            else {
                for (String userGreeting : UserGreetings) {
                    if (input.contains(userGreeting)) {
                        OutlineMessage("Hi! How can I help you?");
                        break;
                    }
                }
            }


        }
    }

    private static void OutlineMessage(String msg) {
        System.out.println(line + msg + "\n" + line);
    }

    public static void Greet() {
        String greeting = "Hi! I'm PHamBot\nHappy to be of service to you today!";
        OutlineMessage(greeting);
    }

    public static void SayGoodbye() {
        String goodbye = "Hope I was able to help\nGoodbye!";
        OutlineMessage(goodbye);
    }

    public static String addToDo(String task) {
        tasks.addTask(new ToDo(task));
        return "Added:\n" + task;
    }

    public static String addDeadline(String task, String deadline) {
        tasks.addTask(new Deadline(task, deadline));
        return "Added:\n" + task;
    }

    public static String addEvent(String task, String date) {
        tasks.addTask(new Event(task, date));
        return "Added:\n" + task;
    }

    public static void ListTasks() {
        if (tasks.taskCount() == 0) {
            OutlineMessage("You're free! There aren't any tasks currently.");
            return;
        }
        OutlineMessage(tasks.toString());
    }

    public static void mark(int index) {
        int i = index - 1;
        tasks.markTask(i);
        OutlineMessage("I've marked the following task as done!\n" + tasks.getTask(i));
    }

    public static void unmark(int index) {
        int i = index - 1;
        tasks.unmarkTask(i);
        OutlineMessage("I've marked the following task as not done.\n" + tasks.getTask(i));
    }


    public static void printRemoveTask(int index) {
        OutlineMessage("On it boss. I've removed this task:\n" + tasks.getTask(index).toString());
    }

    public static ToDo checkToDoInput(String input, ToDo toDo) throws MissingTaskException {
        String[] temp = input.split(" ", 2);
        if (temp.length < 2) {
            throw new MissingTaskException("Missing a task to add!");
        }
        else {
            toDo.setTaskName(temp[1]);
            return toDo;
        }
    }

    public static DatedTask checkDatedTaskInput(String input, DatedTask task)
            throws MissingTaskException, MissingDateException, MissingDividerException {
        String[] temp = input.split("/", 2);
        System.out.println(Arrays.toString(temp) + "1");
        if (temp.length < 2) {
            if (input.trim().isEmpty() || input.trim().split(" ").length < 2) {
                throw new MissingTaskException("Missing a task to add!");
            }
            for (int i = 0; i < Days.length; i++) {
                if (input.contains(Days[i])) {
                    throw new MissingDividerException("You're missing a slash between your task and date!");
                }
            }
            throw new MissingDateException("Missing a date for your task!");
        }
        if (temp[1].trim().isEmpty()) {
            throw new MissingDateException("Missing a date for your task!");
        }
        String[] temp2 = temp[0].trim().split(" ", 2);
        System.out.println(Arrays.toString(temp2) + "2");
        if (temp2.length < 2 || temp2[1].trim().isEmpty()) {
            throw new MissingTaskException("Missing a task to add!");
        }
        task.setTaskName(temp2[1].trim());
        task.setDate(temp[1].trim());
        return task;
    }

    public static int checkIndexInput(String input) throws MissingIndexException {
        String[] temp = input.split(" ", 2);
        if (temp.length < 2 || Objects.equals(temp[1], "")) {
            throw new MissingIndexException("There is no list index!");
        }
        else {
            return Integer.parseInt(temp[1]);
        }
    }

}
