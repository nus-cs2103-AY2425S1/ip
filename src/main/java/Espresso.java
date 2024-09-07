import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
public class Espresso {
    private ArrayList<Task> tasks ;
    private int count;
    private final String FILE_PATH = "./data/Espresso.txt";

    public Espresso() {
        tasks = new ArrayList<>();
        count = 0;
        loadTasks();
    }

    void loadTasks() {
        try {
            File file = new File("./data/Espresso.txt");
            if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            } else {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String taskLine = sc.nextLine();
                    String[] parts = taskLine.split(" \\| ");
                    String taskType = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String status = parts[2];
                    Task task = null;
                    if (taskType.equals("T")) {
                        task = new todoTask(status);
                    } else if (taskType.equals("D")) {
                        String dl = parts[3];
                        task = new deadlineTask(status, dl);
                    } else if (taskType.equals("E")) {
                        String starts = parts[3];
                        String ends = parts[4];
                        task = new eventTask(status, starts, ends);
                    } else {
                        System.out.println("Incorrect task entry..moving ahead");
                        continue;
                    }
                    if (task != null) {
                        if (isDone) {
                            task.mark();
                        }
                        tasks.add(task);
                        count++;
                    }
                }
                sc.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }


    void addToList(String str) {
        Task task = null;
        try {
                if (str.startsWith("todo")) {
                    String status = str.substring(4).trim();
                    if (status.isEmpty())
                        throw new IllegalArgumentException("Description of todo task is required");
                    task = new todoTask(status);
                } else if (str.startsWith("deadline")) {
                    String split[] = str.substring(8).split(" /by");
                    String status = split[0].trim();
                    if (status.isEmpty())
                        throw new IllegalArgumentException("Description of deadline task is required");
                    task = new deadlineTask(status, split[1].trim());
                } else if (str.startsWith("event")) {
                    String split1[] = str.substring(5).split(" /from ");
                    String status = split1[0].trim();
                    if (status.isEmpty())
                        throw new IllegalArgumentException("Description of event task is required");
                    String split2[] = split1[1].split(" /to ");
                    task = new eventTask(status, split2[0].trim(), split2[1].trim());
                } else {
                    throw new IllegalArgumentException("Random Input! I don't know what that means :(");
                }
                tasks.add(task);
                saveTaskList();
                System.out.println("____________________________________________________________");System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                if (count == 0)
                    System.out.println("Now you have " + (count + 1) + " task in the list.");
                else
                    System.out.println("Now you have " + (count + 1) + " tasks in the list.");
                System.out.println("____________________________________________________________");
                count++;
        }
        catch (IllegalArgumentException e) {
            System.out.println("____________________________________________________________");
            System.out.println("Oops! " + e.getMessage());
            System.out.println("____________________________________________________________");
        }
    }

    void saveTaskList() {
        try {
            FileWriter filewriter = new FileWriter("./data/Espresso.txt");
            for (Task task : tasks) {
                filewriter.write(taskToFileFormat(task) + "\n");
            }
            filewriter.close();
        } catch (IOException e) {
            System.out.println("Error encountered in saving tasks to file: " + e.getMessage());
        }
    }

    String taskToFileFormat(Task task) {
        if (task instanceof todoTask) {
            return "T | " + (task.getIsDone() ? "1" : "0") + " | " + task.getStatus();
        } else if (task instanceof deadlineTask) {
            deadlineTask deadline = (deadlineTask) task;
            return "D | " + (task.getIsDone() ? "1" : "0") + " | " + task.getStatus() + " | " + deadline.getDeadline();
        } else if (task instanceof eventTask) {
            eventTask event = (eventTask) task;
            return "E | " + (task.getIsDone() ? "1" : "0") + " | " + task.getStatus() + " | " + event.getStarts() + " | " + event.getEnds();
        }
        return "";
    }


    void deleteTask(int position) {
        System.out.println("____________________________________________________________");
        Task rem = tasks.remove(position);
        saveTaskList();
        count--;
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + rem);
        if (count == 1)
            System.out.println("Now you have " + count + " task in the list.");
        else
            System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println("_________________________________________");
    }

    void printList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < count; i++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    void process() {
        System.out.println("_________________________________________");
        System.out.println("Hello! I'm Espresso");
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________");
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            str = sc.nextLine();

            if (str.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (str.startsWith("mark ")) {
                String extractNum = str.substring(str.indexOf(' ') + 1).trim();
                int pos = Integer.valueOf(extractNum);
                markTask(pos - 1);
            } else if (str.startsWith("unmark ")) {
                String extractNum = str.substring(str.indexOf(' ') + 1).trim();
                int pos = Integer.valueOf(extractNum);
                unmarkTask(pos - 1);
            }else if (str.equals("list")) {
                printList();
            } else if (str.startsWith("delete ")) {
                String extractNum = str.substring(str.indexOf(' ') + 1).trim();
                int pos = Integer.valueOf(extractNum);
                deleteTask(pos - 1);
            }
            else {
                addToList(str);
            }
        }
        sc.close();
    }
    void markTask(int position) {
        System.out.println("_________________________________________");
        if (position >= 0 && position < count) {
            tasks.get(position).mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(position));
            System.out.println("_________________________________________");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void unmarkTask(int position) {
        System.out.println("_________________________________________");
        if (position >= 0 && position < count) {
            tasks.get(position).unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(position));
            System.out.println("_________________________________________");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public static void main(String[] args) {
        Espresso esp = new Espresso();
        esp.process();
    }
}
