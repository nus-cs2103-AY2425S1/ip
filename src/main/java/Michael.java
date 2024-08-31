import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Michael {
    private static final String BORDER = "--------------------------------------";
    private static void printer(String text) { // Function to help print each interaction
        System.out.println(BORDER);
        System.out.println(text);
        System.out.println(BORDER);
    }

    private static final String PATH = "src/main/data/save";
    private static ArrayList<Task> tasks = new ArrayList<>(); // store user inputs

    public static void main(String[] args) {
        Scanner user = new Scanner(System.in); // scanner for user input

        try {
            File f = new File(PATH);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                Task t = load(s.nextLine());
                tasks.add(t);
            }
            // Greet user first
            printer("Hello! I'm Michael.\n" + "What can I do for you?");

            // Read user's input
            while (true) {
                String input = user.nextLine().strip();
                if (input.equals("bye")) { // special bye command to exit
                    break;
                }

                if (input.equals("list")) { // list user inputs thus far
                    String list = "Here are the tasks in your list:\n";
                    for (int i = 0; i < tasks.size(); i++) {
                        String elem = String.valueOf(i + 1) + ". " + tasks.get(i) + "\n";
                        list = list.concat(elem);
                    }
                    printer(list.substring(0, list.length() - 1)); // substring to remove last line break
                    continue;
                }

                try {
                    processor(input);
                } catch (MichaelException e) {
                    System.out.println(e.getMessage());
                }
            }

            FileWriter writer = new FileWriter(PATH);
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                String save = convert(t);
                writer.write(save);
            }
            writer.close();

            // Exit
            printer("Bye. Hope to see you again soon!");

        } catch (FileNotFoundException e) {
            System.out.println("Save file not found!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Function to match command to required actions
    private static void processor(String input) throws MichaelException {
        if (input.length() >= 4 && input.substring(0, 4).equals("mark")) { // mark a task as done
            if (input.length() < 6) { // no number given to mark
                throw new MichaelException("Enter integer position of task on list to mark. " +
                        "Use command list to check the position of the required task.");
            }
            int index = Integer.valueOf(input.substring(5));
            Task target = tasks.get(index - 1);
            target.doTask();
            printer("Nice! I've marked this task as done:\n" + "  " + target);
        } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) { // unmark a task
            if (input.length() < 8) { // no number given to unmark
                throw new MichaelException("Enter integer position of task on list to unmark. " +
                        "Use command list to check the position of the required task.");
            }
            int index = Integer.valueOf(input.substring(7));
            Task target = tasks.get(index - 1);
            target.undoTask();
            printer("OK, I've marked this task as not done yet:\n" + "  " + target);
        } else if (input.length() >= 4 && input.substring(0, 4).equals("todo")) { // task of type todo to be added
            if (input.length() < 6) { // no task given
                throw new MichaelException("Enter a task to be done.");
            }
            ToDo curr = new ToDo(input.substring(5));
            tasks.add(curr);
            String message = "Got it. I've added this task:\n" + "  " + curr.toString() + "\n"
                    + "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
            printer(message);
        } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) { // task of type deadline to be added
            if (input.length() < 10) { // no deadline task given
                throw new MichaelException("Enter a valid task with a deadline.");
            }
            String task = input.substring(9);
            String[] parts = task.split("/");
            for (int i = 0; i < parts.length - 1; i++) {
                String curr = parts[i];
                parts[i] = curr.substring(0, curr.length() - 1);
            }
            Deadline curr = new Deadline(parts[0], parts[1].substring(3));
            tasks.add(curr);
            String message = "Got it. I've added this task:\n" + "  " + curr.toString() + "\n"
                    + "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
            printer(message);
        } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
            if (input.length() < 7) { // no event given
                throw new MichaelException("Enter a valid event.");
            }
            String task = input.substring(6);
            String[] parts = task.split("/");
            for (int i = 0; i < parts.length - 1; i++) {
                String curr = parts[i];
                parts[i] = curr.substring(0, curr.length() - 1);
            }
            Event curr = new Event(parts[0], parts[1].substring(5), parts[2].substring(3));
            tasks.add(curr);
            String message = "Got it. I've added this task:\n" + "  " + curr.toString() + "\n"
                    + "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
            printer(message);
        } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
            if (input.length() < 8) { // no number given to delete
                throw new MichaelException("Enter integer position of task on list to delete. " +
                        "Use command list to check the position of the required task.");
            }
            int index = Integer.valueOf(input.substring(7));
            Task target = tasks.get(index - 1);
            tasks.remove(index - 1);
            printer("Noted. I've removed this task:\n" + "  " + target + "\n" +
                    "Now you have " + tasks.size() + " tasks in the list.");
        } else { // invalid command
            throw new MichaelException("Invalid command entered. Please enter one of the following valid commands: " +
                    "todo, deadline, event, mark, unmark, list, bye");
        }
    }

    // Method loads task from save file
    private static Task load(String line) {
        String[] parts = line.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            String s = parts[i];
            parts[i] = s.strip();
        }

        if (parts[0].equals("T")) {
            Task t = new ToDo(parts[2]);
            if (Integer.valueOf(parts[1]) == 1) {
                t.doTask();
            }
            return t;
        } else if (parts[0].equals("D")) {
            Task t = new Deadline(parts[2], parts[3]);
            if (Integer.valueOf(parts[1]) == 1) {
                t.doTask();
            }
            return t;
        } else {
            Task t = new Event(parts[2], parts[3], parts[4]);
            if (Integer.valueOf(parts[1]) == 1) {
                t.doTask();
            }
            return t;
        }
    }

    // Method converts task inputted to text format required for save file
    private static String convert(Task task) {
        StringBuilder s = new StringBuilder();

        // Add status of task
        if (task.isDone()) {
            s.append("1 | ");
        } else {
            s.append("0 | ");
        }
        // Add name of task
        s.append(task.getTaskName());

        if (task instanceof ToDo) {
            s.insert(0, "T | ").append("\n");
            return s.toString();
        } else if (task instanceof Deadline) {
            s.insert(0, "D | ");
            Deadline d = (Deadline) task;
            s.append(" | ").append(d.getDeadline()).append("\n");
            return s.toString();
        } else {
            s.insert(0, "E | ");
            Event e = (Event) task;
            s.append(" | ").append(e.getStart()).append(" | ").append(e.getEnd()).append("\n");
            return s.toString();
        }
    }
}
