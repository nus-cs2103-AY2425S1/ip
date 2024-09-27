import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Axel {
    private static final String FILE_PATH = "data/tasks.txt";
    public static void displayList(List<Task> anyList) {
        for (int i = 0; i < anyList.size(); i++) {
            System.out.println((i + 1) + "." + anyList.get(i).toString());
        }
    }

    public static void saveTasks(List<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : taskList) {
                writer.write(task.saveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static List<Task> loadTasks() {
        List<Task> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partition = line.split(" \\| ");
                String taskType = partition[0];
                boolean isDone = partition[1].equals("1");
                String taskName = partition[2];
                switch (taskType) {
                    case "T":
                        ToDo todo = new ToDo(taskName);
                        if (isDone) todo.mark();
                        taskList.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(taskName, partition[3]);
                        if (isDone) deadline.mark();
                        taskList.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(taskName, partition[3], partition[4]);
                        if (isDone) event.mark();
                        taskList.add(event);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous task hehe");
        } catch (IOException e) {
            System.out.println("Ooops! there is an error in loading tasks: " + e.getMessage());
        }
        return taskList;
    }

    public static void main(String[] args) {
        String welcomeMsg = " /\\_/\\\n" +
                "( ^.^ ) Hii! I am Axel, nice to meet you\n" +
                "(  >**< )";
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = loadTasks();
        String exitMsg = "Bye, see u again!";

        System.out.println(welcomeMsg);
        while (sc.hasNext()) {
            String prompt = sc.nextLine();
            if (prompt.equals("bye")) {
                System.out.println(exitMsg);
                break;
            } else if (prompt.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                displayList(taskList);
            } else if (prompt.startsWith("mark")) {
                try {
                    String idxString = prompt.substring(5);
                    Integer idxInt = Integer.parseInt(idxString);
                    taskList.get(idxInt - 1).mark();
                    System.out.println("Very nice! I have marked this task as done:\n"
                            + taskList.get(idxInt - 1).toString());
                    saveTasks(taskList);
                } catch (NumberFormatException e) {
                    System.out.println("Please input a valid number hehe");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Ooops! There is no task to be marked hehe");
                }
            } else if (prompt.startsWith("unmark")) {
                try {
                    String idxString = prompt.substring(7);
                    Integer idxInt = Integer.parseInt(idxString);
                    taskList.get(idxInt - 1).unmark();
                    System.out.println("OK! I have marked this task as not done yet:\n"
                            + taskList.get(idxInt - 1).toString());
                    saveTasks(taskList);
                } catch (NumberFormatException e) {
                    System.out.println("Please input a valid number hehe");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Ooops! There is no task to be unmarked hehe");
                }
            } else if (prompt.startsWith("delete")) {
                try {
                    String idxString = prompt.substring(7);
                    Integer idxInt = Integer.parseInt(idxString);
                    Task task = taskList.remove(idxInt - 1);
                    System.out.println("Alright! I have deleted this task:\n"
                            + task.toString() + "\nNow you have " + taskList.size() + " tasks in the list");
                    saveTasks(taskList);
                } catch (NumberFormatException e) {
                    System.out.println("Please input a valid number hehe");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Ooops! There is no task to be deleted hehe");
                }
            } else if (prompt.startsWith("todo")) {
                try {
                    String taskName = prompt.substring(4).trim();
                    if (taskName.isEmpty()) {
                        throw new IllegalArgumentException("Ooops! Wrong format for todo hehe");
                    }
                    ToDo task = new ToDo(taskName);
                    taskList.add(task);
                    System.out.println("Got it. I've added this task:\n"
                            + task.toString() + "\nNow you have " + taskList.size() + " tasks in the list");
                    saveTasks(taskList);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (prompt.startsWith("deadline")) {
                try {
                    String imm1 = prompt.substring(8).trim();
                    String[] partition = imm1.split("/by");
                    if (partition.length < 2 || partition[0].trim().isEmpty() || partition[1].trim().isEmpty()) {
                        throw new IllegalArgumentException("Ooops! Wrong format for deadline hehe");
                    }
                    String taskName = partition[0].trim();
                    String dueBy = partition[1].trim();
                    Deadline task = new Deadline(taskName, dueBy);
                    taskList.add(task);
                    System.out.println("Got it. I've added this task:\n"
                            + task.toString() + "\nNow you have " + taskList.size() + " tasks in the list");
                    saveTasks(taskList);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (prompt.startsWith("event")) {
                try {
                    String imm1 = prompt.substring(5).trim();
                    String[] partition1 = imm1.split("/from");
                    if (partition1.length < 2 || partition1[0].trim().isEmpty() || partition1[1].trim().isEmpty()) {
                        throw new IllegalArgumentException("Ooops! Wrong format for event hehe");
                    }
                    String taskName = partition1[0].trim();
                    String imm2 = partition1[1];
                    String[] partition2 = imm2.split("/to");
                    if (partition2.length < 2 || partition2[0].trim().isEmpty() || partition2[1].trim().isEmpty()) {
                        throw new IllegalArgumentException("Ooops! Wrong format for event hehe");
                    }
                    String from = partition2[0].trim();
                    String to = partition2[1].trim();
                    Event task = new Event(taskName, from, to);
                    taskList.add(task);
                    System.out.println("Got it. I've added this task:\n"
                            + task.toString() + "\nNow you have " + taskList.size() + " tasks in the list");
                    saveTasks(taskList);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Ooops! I don't understand your command hehe");
            }
        }
    }
}
