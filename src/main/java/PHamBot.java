import javax.lang.model.type.ErrorType;
import java.util.Scanner;

public class PHamBot {
    private static final String line = "____________________________________________________________\n";
    private static TaskList tasks = new TaskList();

    private static final String[] UserGreetings = {"Hello", "Hi", "What's up"};
    public enum Errors {
        MissingTask,
        MissingDivider,
        MissingDate,
        UnknownCommand
    }

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
            if (input.contains("todo")) {
                if (input.length() < 6) {
                    handleErrors(Errors.MissingTask);
                }
                else {
                    String task = input.substring(5);
                    OutlineMessage(addToDo(task));
                }
            }
            if (input.contains("deadline")) {
                int dateIndex = input.indexOf("/");
                //handle error for missing /

                if (input.length() < 10) {
                    handleErrors(Errors.MissingTask);
                } else if (dateIndex == -1) {
                    handleErrors(Errors.MissingDivider);
                } else if (input.substring(dateIndex).length() == 1) {
                    handleErrors(Errors.MissingDate);
                }
                else {
                    String task = input.substring(9, dateIndex);
                    String deadline = input.substring(dateIndex + 1);
                    OutlineMessage(addDeadline(task, deadline));
                }
            }
            if (input.contains("event")) {
                int dateIndex = input.indexOf("/");

                if (input.length() < 7) {
                    handleErrors(Errors.MissingTask);
                }
                else if (dateIndex == -1) {
                    handleErrors(Errors.MissingDivider);
                } else if (input.substring(dateIndex).length() == 1) {
                    handleErrors(Errors.MissingDate);
                }
                else {
                    String task = input.substring(6, dateIndex);
                    String deadline = input.substring(dateIndex + 1);
                    OutlineMessage(addEvent(task, deadline));
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

    public static void handleErrors(Errors error) {
        switch (error) {
            case MissingTask:
                OutlineMessage("I'm sorry, I can't add a task without knowing what to add!\n" +
                        "Please try again, and enter the intended task after the task type");
                break;
            case MissingDivider:
                OutlineMessage("I'm sorry, I can't identify the date.\n" +
                        "Please try again and add a slash (/) after the task.");
                break;
            case MissingDate:
                OutlineMessage("Sorry, I couldn't find a date.\n" +
                        "Try again with the date after the slash (/)");
                break;
            case UnknownCommand:
                OutlineMessage("...\n" +
                        "Huh?");
                break;
        }
    }


}
