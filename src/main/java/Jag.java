import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Jag {
    static String dashed = "----------";

    public static ArrayList<Task> recoverHistory() throws FileNotFoundException{
        File f = new File("./data/jag.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        char taskType;
        boolean isDone;
        String description;
        String by;
        String from;
        String to;

        if (f.exists()) {
            Scanner readFile = new Scanner(f);
            while (readFile.hasNextLine()) {
                String item = readFile.nextLine();
                taskType = item.charAt(1);
                isDone = item.charAt(4) == 'X';

                if (taskType == 'T') {
                    // If todos
                    int startIndex = item.indexOf("] ") + 1;
                    description = item.substring(startIndex).trim();

                    Todo todo = new Todo(description);
                    todo.setStatus(isDone);
                    tasks.add(todo);
                } else if (taskType == 'D') {
                    // If deadline
                    int startIndex = item.indexOf("] ") + 1;
                    int endIndex = item.indexOf("(");
                    description = item.substring(startIndex, endIndex).trim();
                    startIndex = item.indexOf(":") + 1;
                    endIndex = item.indexOf(')');
                    by = item.substring(startIndex, endIndex).trim();

                    Deadline deadline = new Deadline(description, by);
                    deadline.setStatus(isDone);
                    tasks.add(deadline);
                } else {
                    // If event
                    int startIndex = item.indexOf("] ") + 1;
                    int endIndex = item.indexOf('(');
                    description = item.substring(startIndex, endIndex).trim();
                    startIndex = item.indexOf(":") + 1;
                    endIndex = item.indexOf("to:");
                    from = item.substring(startIndex, endIndex).trim();
                    startIndex = item.indexOf("to: ") + 4;
                    endIndex = item.indexOf(')');
                    to = item.substring(startIndex, endIndex).trim();

                    Event event = new Event(description, from, to);
                    event.setStatus(isDone);
                    tasks.add(event);
                }
            }
        }

        return tasks;
    }

    public static void recordHistory(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter("./data/jag.txt");

        for (Task task : tasks) {
            fw.write(task.toString() + "\n");
        }

        fw.close();
    }

    public static void list(ArrayList<Task> tasks) {
        System.out.println(dashed);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
        System.out.println(dashed);
    }

    public static ArrayList<Task> mark(String answer, ArrayList<Task> tasks) {
        char marker = answer.charAt(answer.length() - 1);
        int index = 0;

        // Convert index character to a string
        if (Character.isDigit(marker)) {
            index = Integer.parseInt(Character.toString(marker));
        }

        // Feature for mark
        Task task = tasks.get(index-1);

        System.out.println(dashed);
        System.out.println("Nice! I've marked this task as done:");
        task.setStatus(true);
        System.out.println(task.toString());
        System.out.println(dashed);

        try {
            recordHistory(tasks);
        } catch (IOException io) {
            System.out.println("Error in writing history" + io.getMessage());
        }

        return tasks;
    }

    public static ArrayList<Task> unmark(String answer, ArrayList<Task> tasks) {
        char marker = answer.charAt(answer.length() - 1);
        int index = 0;

        // Convert index character to a string
        if (Character.isDigit(marker)) {
            index = Integer.parseInt(Character.toString(marker));
        }
        // Feature for unmark
        Task task = tasks.get(index-1);

        System.out.println(dashed);
        System.out.println("OK, I've marked this task as not done yet:");
        task.setStatus(false);
        System.out.println(task.toString());
        System.out.println(dashed);

        try {
            recordHistory(tasks);
        } catch (IOException io) {
            System.out.println("Error in writing history" + io.getMessage());
        }

        return tasks;

    }

    public static ArrayList<Task> todo(String answer, ArrayList<Task> tasks) {
        String[] split = answer.split("todo");
        String description = split[1].trim();
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);

        try {
            recordHistory(tasks);
        } catch (IOException io) {
            System.out.println("Error in writing history" + io.getMessage());
        }

        System.out.println(dashed);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTodo.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(dashed);

        return tasks;
    }

    public static ArrayList<Task> deadline(String answer, ArrayList<Task> tasks) {
        String[] split = answer.split("/by");
        String description = split[0].replaceFirst("deadline", "").trim();
        String by = split[1].trim();
        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);

        try {
            recordHistory(tasks);
        } catch (IOException io) {
            System.out.println("Error in writing history" + io.getMessage());
        }

        System.out.println(dashed);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(dashed);

        return tasks;
    }

    public static ArrayList<Task> event(String answer, ArrayList<Task> tasks) {
        String[] split = answer.split("/from | /to");
        String description = split[0].replaceFirst("event", "").trim();
        String from = split[1].trim();
        String to = split[2].trim();
        Event newEvent = new Event(description, from, to);
        tasks.add(newEvent);

        try {
            recordHistory(tasks);
        } catch (IOException io) {
            System.out.println("Error in writing history" + io.getMessage());
        }

        System.out.println(dashed);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(dashed);

        return tasks;

    }

    public static ArrayList<Task> delete(String answer, ArrayList<Task> tasks) {
        char marker = answer.charAt(answer.length() - 1);
        int index = 0;

        if (Character.isDigit(marker)) {
            index = Integer.parseInt(Character.toString(marker));
        }

        Task task = tasks.get(index - 1);

        System.out.println(dashed);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        tasks.remove(index - 1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(dashed);

        try {
            recordHistory(tasks);
        } catch (IOException io) {
            System.out.println("Error in writing history" + io.getMessage());
        }
        return tasks;
    }

    public static void main(String[] args) throws AExceptions {
        // Variables
        Scanner scanner = new Scanner(System.in);
        String greetings = """
                ------------------
                Hello! I'm Jag
                What can I do for you?
                ------------------""";
        String bye = """
                ------------------
                Bye. Hope to see you again soon!
                ------------------""";

        ArrayList<Task> tasks = new ArrayList<Task>();


        // Read and Load file
        try {
            tasks = recoverHistory();
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        }


        // Greeting and receiving first input
        System.out.println(greetings);
        String answer = scanner.nextLine();

        // Chatbot
        while (!answer.equals("bye")) {

            // Breaking down command input
            String[] splitWords = answer.split(" ");
            String cmd = splitWords[0].toUpperCase();
            Commands command;

            // Error handling for unexpected command input
            try {
                command = Commands.valueOf(cmd);
            } catch (IllegalArgumentException e) {
                System.out.println(dashed);
                AExceptions ex = new AExceptions("I'm sorry, but I don't know what that means :-(");
                System.out.println(ex.getErrorMessage());
                System.out.println(dashed);
                answer = scanner.nextLine();
                continue;
            }

            // Command input processing
            switch (command) {
                case LIST:
                    list(tasks);
                    answer = scanner.nextLine();
                    continue;

                case MARK:
                    tasks = mark(answer, tasks);
                    answer = scanner.nextLine();
                    continue;

                case UNMARK:
                    tasks = unmark(answer, tasks);
                    answer = scanner.nextLine();
                    continue;

                case TODO:
                    // Exception handling
                    if (answer.length() == 4) {
                        System.out.println(dashed);
                        AExceptions ex = new AExceptions("The description of a todo cannot be empty.");
                        System.out.println(ex.getErrorMessage());
                        System.out.println(dashed);

                        answer = scanner.nextLine();
                        continue;
                    }
                    tasks = todo(answer, tasks);
                    answer = scanner.nextLine();
                    continue;

                case DEADLINE:
                    tasks = deadline(answer, tasks);
                    answer = scanner.nextLine();
                    continue;

                case EVENT:
                    tasks = event(answer, tasks);
                    answer = scanner.nextLine();
                    continue;

                case DELETE:
                    tasks = delete(answer, tasks);
                    answer = scanner.nextLine();
                    continue;

            }

        }

        // Goodbye message
        System.out.printf(bye);
        scanner.close();

    }
}