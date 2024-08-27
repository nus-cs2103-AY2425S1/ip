import java.util.Scanner;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
            outputs[i + 1] = index + ". " + list.get(i);
        }
        PixelSays(outputs);
    }

    private static void addTask(Task t, ArrayList<Task> list) {
        list.add(t);
        PixelSays("Got it. I've added this task:", "  " + t,
                "Now you have " + list.size() + " tasks in the list.");
    }

    private static void removeTask(Task t, ArrayList<Task> list) {
        list.remove(t);
        PixelSays("Noted. I've removed this task:", "  " + t,
                "Now you have " + list.size() + " tasks in the list.");
    }

    private static void writeFile(ArrayList<Task> list) {
        try {
            File file = new File("data.txt");
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i).getData() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            PixelSays("Unable to write to file!");
        }
    }

    private static ArrayList<Task> readFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            String line = br.readLine();
            while (line != null) {
                String[] taskRepresentation = line.split(",");
                TaskType taskType = TaskType.valueOf(taskRepresentation[0]);
                switch (taskType) {
                    case E:
                        Task eventTask = new Event(taskRepresentation[2], taskRepresentation[1]);
                        taskList.add(eventTask);
                        break;
                    case D:
                        Task deadlineTask = new Deadline(taskRepresentation[2], taskRepresentation[1]);
                        taskList.add(deadlineTask);
                        break;
                    case T:
                        Task todoTask = new Todo(taskRepresentation[2], taskRepresentation[1]);
                        taskList.add(todoTask);
                        break;

                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            PixelSays(e.getMessage());
        } finally {

        }
        return taskList;
    }

    public static void main(String[] args) {
        ArrayList<Task> list = readFile();
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

            String input = fullInput.substring(cmdString.length()).strip();

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
                            " " + taskToMark);
                    break;
                case UNMARK:
                    Task taskToUnmark = list.get(Integer.parseInt(input.strip()));
                    taskToUnmark.toggleIsDone();
                    PixelSays("OK, I've marked this task as not done yet:",
                            " " + taskToUnmark);
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

            writeFile(list);
        }
    }
}
