import java.util.Scanner;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;

public class Pixel {
    private static String name = "Pixel";

    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    private static void PixelSays(String... args) {
        printLine();
        for (String arg : args) {
            System.out.println("    " + arg);
        }
        printLine();
        System.out.println("");
    }

    private static void printList(ArrayList<Task> list) {
        String[] outputs = new String[list.size() + 1];
        outputs[0] = "Here are the tasks in your list:";
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            outputs[i + 1] = index + ". " + list.get(i).getType() + list.get(i).getStatusIcon() + " " + list.get(i);
        }
        PixelSays(outputs);
    }

    private static void addTask(Task t, ArrayList<Task> list) {
        list.add(t);
        PixelSays("Got it. I've added this task:", "  " + t.getType() + t.getStatusIcon() + t,
                "Now you have " + list.size() + " tasks in the list.");
    }

    private static void removeTask(Task t, ArrayList<Task> list) {
        list.remove(t);
        PixelSays("Noted. I've removed this task:", "  " + t.getType() + t.getStatusIcon() + t,
                "Now you have " + list.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        PixelSays("Hello! I'm " + name, "What can I do for you?");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String fullInput = sc.nextLine();
            String cmdString = fullInput.split(" ")[0];
            PixelCommand cmd;

            try {
                cmd = PixelCommand.valueOf(cmdString.toUpperCase());
            } catch (IllegalArgumentException e) {
                PixelSays("OH NO!!! I don't understand this! Try Again!");
                continue;
            } finally {

            }

            String input = fullInput.substring(cmdString.length());

            switch (cmd) {
                case BYE:
                    PixelSays("Bye. Hope to see you again soon!");
                    sc.close();
                    return;
                case LIST:
                    printList(list);
                    break;
                case MARK:
                    Task taskToMark = list.get(Integer.parseInt(input.strip()));
                    taskToMark.toggleIsDone();
                    PixelSays(
                            "Nice! I've marked this task as done:",
                            taskToMark.getStatusIcon() + " " + taskToMark);
                    break;
                case UNMARK:
                    Task taskToUnmark = list.get(Integer.parseInt(input.strip()));
                    taskToUnmark.toggleIsDone();
                    PixelSays("OK, I've marked this task as not done yet:",
                            taskToUnmark.getStatusIcon() + " " + taskToUnmark);
                    break;
                case TODO:
                    try {
                        Task todo = new Todo(input);
                        addTask(todo, list);
                    } catch (TaskException e) {
                        PixelSays(e.getMessage());
                    } finally {

                    }
                    break;
                case DEADLINE:
                    try {
                        Task todo = new Deadline(input);
                        addTask(todo, list);
                    } catch (TaskException e) {
                        PixelSays(e.getMessage());
                    } finally {

                    }
                    break;
                case EVENT:
                    try {
                        Task todo = new Event(input);
                        addTask(todo, list);
                    } catch (TaskException e) {
                        PixelSays(e.getMessage());
                    } finally {

                    }
                    break;
                case DELETE:
                    Task taskToDelete = list.get(Integer.parseInt(input.strip()) - 1);
                    removeTask(taskToDelete, list);
                    break;
                default:
                    PixelSays("OH NO!!! I don't understand this! Try Again!");
            }
        }
    }
}
