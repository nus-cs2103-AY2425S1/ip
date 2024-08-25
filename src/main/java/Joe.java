import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Joe {
    private static final String line =
            "____________________________________________________________";
    private static final List<Task> userTasks = new ArrayList<>();

    /**
     * Prints a goodbye message.
     */
    private static void bye() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    /**
     * Repeats the user's input until the user types "bye".
     */
    public static void echo() {
        Scanner reader = new Scanner(System.in);
        int count = 0;
        String userCmd = reader.nextLine();
        String joesThoughts = null;

        while (!userCmd.equalsIgnoreCase("bye")) {
            count++;
            switch (count) {
                case 1:
                    joesThoughts = "Joe: I'm just an echo chamber...";
                    break;
                case 5:
                    joesThoughts =  "Joe: I'm being overworked here ;~;";
                    break;
                case 10:
                    joesThoughts = "Joe: Please type bye";
                    break;
                case 15:
                    System.out.printf("%s\nJoe: I'm shutting this operation down!\n", line);
                    break;
                default:
                    joesThoughts = null;
            }

            if (count == 15) {
                break;
            }

            if (joesThoughts != null) {
                System.out.printf("%s\n%s\n%s\n%s\n", line, userCmd, joesThoughts, line);
            } else {
                System.out.printf("%s\n%s\n%s\n", line, userCmd, line);
            }

            userCmd = reader.nextLine();
        }
        bye();
    }

    /**
     * Adds a task to the user's list of tasks.
     * @param s a String representation of the user input
     * @throws InvalidCommandException if unknown user command was used
     */
    public static void add(String s) throws InvalidCommandException {
        System.out.println(line);
        int taskCount = userTasks.size();
        if (taskCount < 100) {
            Task newTask;
            try {
                if (s.startsWith("todo")) {
                    s = s.substring(4);
                    newTask = new ToDo(s);
                } else if (s.startsWith("deadline")) {
                    String[] params = s.substring(8).split("/");
                    if (params.length < 2) {
                        throw new IllegalArgumentException("Deadline: You did not provide a due date/time.");
                    }
                    newTask = new Deadline(params[0], params[1]);
                } else if (s.startsWith("event")) {
                    String[] params = s.substring(5).split("/");
                    if (params.length < 2) {
                        throw new IllegalArgumentException("Event: Did not provide start and end date/time");
                    } else if (params.length < 3) {
                        throw new IllegalArgumentException("Event: Did not provide end date/time");
                    }
                    newTask = new Event(params[0], params[1], params[2]);
                } else {
                    throw new InvalidCommandException(s);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n" + line);
                return;
            }
            userTasks.add(newTask);
            System.out.printf("Got it. I've added this task:\n  %s\n", newTask);
            System.out.printf("Now you have %d tasks in the list.\n", taskCount + 1);
        } else {
            System.out.println("Max number of tasks reached.\nYou currently have 100 tasks.");
        }
        System.out.println(line);
    }

    /**
     * Lists all the tasks in the user's list.
     */
    public static void list() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        int taskCount = userTasks.size();
        for (int i = 0; i < taskCount; i++) {
            Task currTask = userTasks.get(i);
            System.out.printf("%d.%s\n", i+1, currTask);
        }
        System.out.println(line);
    }

    /**
     * Marks a task as done.
     * @param idx the index of the Task the user wants to mark
     */
    public static void mark(int idx) {
        System.out.println(line);
        int taskCount = userTasks.size();
        if (idx > 0 && idx - 1 < taskCount) {
            Task toBeMarked = userTasks.get(idx-1);
            if (!toBeMarked.isDone()) {
                toBeMarked.setDone(true);
                System.out.printf("Nice! I've marked this task as done:\n  %s\n", toBeMarked);
            } else {
                System.out.println("This task is already marked.");
            }
        } else {
            System.out.printf("There is no task %d\n.", idx);
        }
        System.out.println(line);
    }

    /**
     * Unmarks a task.
     * @param idx the index of the Task the user wants to unmark
     */
    public static void unmark(int idx) {
        System.out.println(line);
        int taskCount = userTasks.size();
        if (idx > 0 && idx - 1 < taskCount) {
            Task toBeUnmarked = userTasks.get(idx-1);
            if (toBeUnmarked.isDone()) {
                toBeUnmarked.setDone(false);
                System.out.printf("OK, I've marked this task as not done yet:\n  %s\n", toBeUnmarked);
            } else {
                System.out.println("This task is already unmarked.");
            }
        } else {
            System.out.printf("There is no task %d.\n", idx);
        }
        System.out.println(line);
    }

    /**
     * Prints a list of commands.
     */
    public static void help() {
        System.out.println(line);
        System.out.println("bye: ends our interaction :-(");
        System.out.println("deadline <description> /by <due date/time>: Creates a Deadline task");
        System.out.println("delete <idx>: Deletes the task at your chosen index");
        System.out.println("event <description> /from <start date/time> /to <end date/time>: Creates an Event task");
        System.out.println("list: Displays your current tasks");
        System.out.println("mark <idx>: Marks the task at your chosen index");
        System.out.println("save : Saves all tasks in your current list to the database that will be automatically " +
                "loaded during your next session");
        System.out.println("todo <description>: Creates a ToDo task");
        System.out.println("unmark <idx>: Unmarks the task at your chosen index");
        System.out.println(line);
    }

    /**
     * Deletes a task at a specific index.
     * @param idx the index of the Task the user wants to delete
     */
    public static void delete(int idx) {
        System.out.println(line);
        int taskCount = userTasks.size();
        if (idx > 0 && idx - 1 < taskCount) {
            Task removedTask = userTasks.remove(idx - 1);
            System.out.printf("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list\n",
                    removedTask, taskCount-1);
        } else {
            System.out.printf("There is no task %d.\n", idx);
        }
        System.out.println(line);
    }

    private static void syncData() {
        File f = new File("src/main/data/joe.txt");
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String str = s.nextLine();
                String[] params = str.strip().split(" \\|");

                Task t;
                switch (params[0]) {
                case "T":
                    t = new ToDo(params[2]);
                    break;
                case "D":
                    t = new Deadline(params[2], params[3]);
                    break;
                case "E":
                    t = new Event(params[2], params[3], params[4]);
                    break;
                default:
                    throw new CorruptedFileException();
                }

                boolean done = Integer.parseInt(params[1].strip()) == 1;
                t.setDone(done);
                userTasks.add(t);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (CorruptedFileException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void save() {
        int numOfTasks = userTasks.size();
        System.out.println(line);
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numOfTasks; i++) {
                Task t = userTasks.get(i);
                System.out.println(t + " (saved)");
                if (t instanceof ToDo) {
                    sb.append(String.format("T | %d | %s",
                            t.isDone() ? 1 : 0,
                            t.getDescription()));
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    sb.append(String.format("D | %d | %s | %s",
                            d.isDone() ? 1 : 0,
                            d.getDescription(),
                            d.getDue().replace(":", "")));
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    sb.append(String.format("E | %d | %s | %s | %s",
                            e.isDone() ? 1 : 0,
                            e.getDescription(),
                            e.getStart().replace(":", ""),
                            e.getEnd().replace(":", "")));
                } else {
                    sb.append("<????????>");
                }
                sb.append(System.lineSeparator());
            }

            FileWriter fw = new FileWriter("src/main/data/joe.txt");
            fw.write(sb.toString());
            fw.close();
            System.out.println("Your tasks have been successfully saved.");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            System.out.println(line);
        }
        System.out.println(line);
    }

    public static void main(String[] args) {
        System.out.printf("%s\nHello! I'm Joe\nWhat can I do for you?\n%s\n",
                line, line);
//        echo();

        syncData();

        Scanner reader = new Scanner(System.in);
        String userIn = reader.nextLine().strip();
        while (!userIn.equals("bye")) {
            if (userIn.equals("/help")) {
                help();
            } else if (userIn.equals("list")) {
                list();
            } else if (userIn.toLowerCase().startsWith("mark")) {
                mark(getDigits(userIn));
            } else if (userIn.toLowerCase().startsWith("unmark")) {
                unmark(getDigits(userIn));
            } else if (userIn.toLowerCase().startsWith("delete")) {
                delete(getDigits(userIn));
            } else if (userIn.equals("save")) {
                save();
            } else {
                try {
                    add(userIn);
                } catch (InvalidCommandException e) {
                    System.out.println(e);
                    System.out.println(line);
                }
            }
            userIn = reader.nextLine();
        }

        bye();
    }

    /**
     * Extracts the first contiguous numerical substring from a string.
     * @param userIn the String representation of the user input
     * @return the numerical substring as an integer
     */
    private static int getDigits(String userIn) {
        int idx = -1;
        int n = userIn.length();
        for (int i = 0; i < n; i++) {
            while (i < n && Character.isDigit(userIn.charAt(i))) {
                if (idx < 0) {
                    idx = 0;
                }
                idx = idx * 10 + (userIn.charAt(i++) - '0');
            }
        }
        return idx;
    }
}
