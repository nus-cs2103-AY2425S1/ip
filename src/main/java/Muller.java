import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Muller {
    private static final String FILE_PATH = "./data/muller.txt";
    public static void main(String[] args) throws MullerException {
        String logo = "____________________________________________________________";
        System.out.println(logo + "\nHello! I'm Muller\nWhat can I do for you?\n"
                + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = loadTasksFromFile();;
        while (true) {
            try {
                String in = sc.nextLine();
                String[] inputs = in.split(" ", 2);
                if (inputs[0].equals("bye")) {
                    break;
                } else if (inputs[0].equals("mark")) {

                    if (inputs.length < 2 || !isNumeric(inputs[1])) {
                        throw new MullerException("Pick a task number!");
                    }
                    int index = Integer.parseInt(inputs[1]) - 1;
                    if (index < 0 || index >= list.size()) {
                        throw new MullerException("Wrong task number!");
                    }
                    list.get(index).markAsDone();
                    System.out.println(logo + "\nNice! I've marked this task as done:\n");
                    System.out.println("  " + list.get(index) + "\n" + logo);
                    saveTasksToFile(list);
                } else if (inputs[0].equals("unmark")) {
                    if (inputs.length < 2 || !isNumeric(inputs[1])) {
                        throw new MullerException("Pick a task number!");
                    }
                    int index = Integer.parseInt(inputs[1]) - 1;
                    if (index < 0 || index >= list.size()) {
                        throw new MullerException("Wrong task number!");
                    }
                    list.get(index).markAsNotDone();
                    System.out.println(logo + "\nOK, I've marked this task as not done yet:\n");
                    System.out.println("  " + list.get(index) + "\n" + logo);
                    saveTasksToFile(list);
                } else if (inputs[0].equals("todo")) {
                    if (inputs.length < 2) {
                        throw new MullerException("Todo what?");
                    }
                    System.out.println(logo + "\nGot it. I've added this task:\n");
                    Task t = new Task(inputs[1]);
                    t.setType("T");
                    list.add(t);
                    System.out.println("  " + t);
                    System.out.println("\nNow you have " + list.size() + " tasks in the list.\n" + logo);
                    saveTasksToFile(list);
                } else if (inputs[0].equals("deadline")) {
                    if (inputs.length < 2) {
                        throw new MullerException("deadline for what?");
                    }
                    String[] detail = inputs[1].split("/by", 2);
                    if (detail.length < 2) {
                        throw new MullerException("Oops you didn't specify the deadline!");
                    }
                    System.out.println(logo + "\nGot it. I've added this task:\n");
                    Task t = new Task(detail[0]);
                    t.setType("D");
                    t.setDate("(by:" + detail[1] + ")");
                    list.add(t);
                    System.out.println("  " + t);
                    System.out.println("\nNow you have " + list.size() + " tasks in the list.\n" + logo);
                    saveTasksToFile(list);
                } else if (inputs[0].equals("event")) {
                    if (inputs.length < 2) {
                        throw new MullerException("event for what?");
                    }
                    System.out.println(logo + "\nGot it. I've added this task:\n");
                    String[] detail = inputs[1].split("/from");
                    if (detail.length < 2) {
                        throw new MullerException("Oops you didn't specify the dates!");
                    }
                    String[] ending = detail[1].split("/to");
                    if (ending.length < 2) {
                        throw new MullerException("You missed out either one of the dates!");
                    }
                    Task t = new Task(detail[0]);
                    t.setType("E");
                    t.setDate("(from:" + ending[0] + "to:" + ending[1] + ")");
                    list.add(t);
                    System.out.println("  " + t);
                    System.out.println("\nNow you have " + list.size() + " tasks in the list.\n" + logo);
                    saveTasksToFile(list);
                } else if (in.equals("list")) {
                    if (list.isEmpty()) {
                        throw new MullerException("No tasks found.");
                    }
                    System.out.println(logo);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= list.size(); i++) {
                        System.out.println(i + ": " + list.get(i - 1));
                    }
                    System.out.println(logo);

                } else if (inputs[0].equals("delete")) {

                    if (inputs.length < 2 || !isNumeric(inputs[1])) {
                        throw new MullerException("Pick a task number!");
                    }
                    int index = Integer.parseInt(inputs[1]) - 1;
                    if (index < 0 || index >= list.size()) {
                        throw new MullerException("Invalid task number!");
                    }
                    System.out.println(logo + "\nNoted. I've removed this task:\n");
                    System.out.println("  " + list.get(index) + "\n" + logo);
                    list.remove(index);
                    saveTasksToFile(list);
                } else {
                    System.out.println(logo + "\n" + "added: " + in + "\n" + logo);
                    Task t = new Task(in);
                    list.add(t);
                    saveTasksToFile(list);
                }
            } catch (MullerException e) {
                System.out.println(logo + "\n" + e.getMessage() + "\n" + logo);
            }
        }

        System.out.println(logo + "\nBye. Hope to see you again soon!\n" + logo);
        sc.close();
    }

    public static class MullerException extends Exception {
        public MullerException(String message) {
            super(message);
        }
    }

    // @@CraigTP -reused
    // reused from https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static class Task {
        private String name;
        private boolean isDone;
        private String type = "[ ]";
        private String date = "";

        public Task(String name) {
            this.name = name;
            this.isDone = false;
        }

        public void setType(String type) {
            this.type = "[" + type + "]";
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsNotDone() {
            this.isDone = false;
        }

        public String DoneIcon() {
            return (isDone ? "[X]" : "[ ]"); // Return X or space depending on isDone
        }

        @Override
        public String toString() {
            return this.type + DoneIcon() + " " + name + this.date;
        }

        public String toFileString() {
            return type.charAt(1) + " | " + (isDone ? "1" : "0") + " | " + name + " | " + date;
        }
    }

    private static void saveTasksToFile(ArrayList<Task> list) {
        try {
            File file = new File(FILE_PATH);
            File directory = new File(file.getParent());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : list) {
                writer.write(task.toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return list;  // No tasks to load, return empty list
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    throw new MullerException("File is corrupted.");
                }
                Task task = new Task(parts[2]);
                task.setType(parts[0]);
                task.setDate(parts.length > 3 ? parts[3] : "");
                if (parts[1].equals("1")) {
                    task.markAsDone();
                }
                list.add(task);
            }
            reader.close();
        } catch (IOException | MullerException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return list;
    }

}


