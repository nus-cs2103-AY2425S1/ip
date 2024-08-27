import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Majima {
    private static final int MAX_TASKS = 100;
    private static List<Task> tasks = new ArrayList<>();
    //Deprecated due to swap to ArrayList
    //private static int task_count = 0;
    private static final String LINEGAP = "____________________________________________________________";
    private static final String FILE_PATH = "./data/Majima.txt";

    public static void main(String[] args) {
        try {
            loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println(LINEGAP);
            System.out.println("Majima.txt was not found.");
            System.out.println(LINEGAP);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println(LINEGAP);
        System.out.println("KIIIIIRYU-CHAN! It's ya old pal, Majima!");
        System.out.println("What can I do fer ya?");
        System.out.println(LINEGAP);

        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(LINEGAP);
                    System.out.println("Bye bye! Don't keep me waiting fer too long now, ya hear?");
                    System.out.println(LINEGAP);
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (input.startsWith("mark")) {
                    markTask(input);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(input);
                //THE SPACE AFTER deadline, todo and event are EXTREMELY IMPORTANT AND WILL CAUSE RUNTIME ERROR IF NOT PRESENT
                //DO NOT CHANGE OR WE WILL DIE
                } else if (input.startsWith("deadline ")) {
                    addDeadline(input);
                } else if (input.startsWith("todo ")) {
                    addTodo(input);
                } else if (input.startsWith("event ")) {
                    addEvent(input);
                } else if (input.startsWith("delete ")) {
                    deleteTask(input);
                } else if (input.equalsIgnoreCase("vim")) {
                    System.out.println(LINEGAP);
                    System.out.println("Kiryu, whadaya think this is? CS2030S? Wake up!");
                    System.out.println(LINEGAP);
                } else {
                    throw new MajimaException("Uhh, Kiryu-chan? There ain't no sense in whatever ya just said! Regular tasks start with 'todo', tasks with deadlines start with 'deadline' and gotta have a /by date and time, while 'events' need a /from and /to argument.");
                }
            } catch (MajimaException e) {
                System.out.println(LINEGAP);
                System.out.println(e.getMessage());
                System.out.println(LINEGAP);
            } catch (NumberFormatException e) {
                System.out.println(LINEGAP);
                System.out.println("Kiryu-chan? That ain't no number ya gave me!");
                System.out.println(LINEGAP);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(LINEGAP);
                System.out.println("Kiryu-chan? That task number seems to be out of bounds.");
                System.out.println(LINEGAP);
            }
        }
        scanner.close();
    }

    private static void deleteTask(String input) {
        int numberArgument = Integer.parseInt(input.substring(7).trim()) - 1;
        if (numberArgument >= 0 && numberArgument < tasks.size()) {
            Task removedTask = tasks.remove(numberArgument);
            System.out.println(LINEGAP);
            System.out.println("Noted, Kiryu-chan. I'll axe this task fer ya:");
            System.out.println(" " + removedTask.toString());
            System.out.println("Now, you've " + tasks.size() + " tasks need doin'.");
            System.out.println(LINEGAP);
            deleteAndRebuild();
        } else {
            System.out.println(LINEGAP);
            System.out.println("Kiryu! That number ain't even there!");
            System.out.println(LINEGAP);
        }
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(LINEGAP);
            System.out.println("おめでとう, Kiryu-chan! There ain't nothing to do left!");
            System.out.println(LINEGAP);
            return;
        }
        System.out.println(LINEGAP);
        System.out.println("Here's whatcha gotta do, Kiryu-chan!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println(LINEGAP);
    }

    private static void markTask(String input) throws MajimaException {
        int numberArgument = Integer.parseInt(input.substring(5).trim()) - 1;
        if (numberArgument >= 0 && numberArgument < tasks.size()) {
            tasks.get(numberArgument).markAsDone();
            System.out.println(LINEGAP);
            System.out.println("Okay, I've gone ahead and marked that one fer ya.");
            System.out.println("  " + tasks.get(numberArgument).toString());
            System.out.println(LINEGAP);
        } else {
            throw new MajimaException("Eh? Kiryu-chan, yain't making any sense! Tell me the number of the task ya want changed!");
        }
    }

    private static void unmarkTask(String input) throws MajimaException {
        int numberArgument = Integer.parseInt(input.substring(7).trim()) - 1;
        if (numberArgument >= 0 && numberArgument < tasks.size()) {
            tasks.get(numberArgument).markAsUndone();
            System.out.println(LINEGAP);
            System.out.println("Okay, I've gone ahead and unmarked that one fer ya.");
            System.out.println("  " + tasks.get(numberArgument).toString());
            System.out.println(LINEGAP);
        } else {
            throw new MajimaException("Eh? Kiryu-chan, yain't making any sense! Tell me the number of the task ya want changed!");
        }
    }

    private static void addDeadline(String input) throws MajimaException {
        String[] parts = input.split("/by", 2);
        if (parts.length < 2) {
            throw new MajimaException("Eh? Kiryu-chan, yain't got no '/by' argument!");
        }
        String description = parts[0].substring(9).trim();
        if (description.isEmpty()) {
            throw new MajimaException("Kiryu-chan, you gotta describe what the task is!");
        }
        String by = parts[1].trim();
        if (canAddTask()) {
            Deadline newDeadline = new Deadline(description, by);
            tasks.add(newDeadline);
            try {
                appendToFile(FILE_PATH, newDeadline.toFileString()
                        + System.lineSeparator());
            } catch (IOException e) {
                System.out.println(LINEGAP);
                System.out.println("Eh? Something went wrong while savin' the task!");
                System.out.println(LINEGAP);
            }
            System.out.println(LINEGAP);
            System.out.println("Understood, Kiryu-chan! Adding that task to the list.");
            System.out.println(" " + tasks.get(tasks.size() - 1).toString());
            System.out.println("Now you've got " + tasks.size() + " tasks need doin'.");
            System.out.println(LINEGAP);
        }
    }

    private static void addTodo(String input) throws MajimaException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new MajimaException("Kiryu-chan, you gotta describe what the task is!");
        }
        if (canAddTask()) {
            Todo newTodo = new Todo(description);
            tasks.add(newTodo);
            try {
                appendToFile(FILE_PATH, newTodo.toFileString()
                        + System.lineSeparator());
            } catch (IOException e) {
                System.out.println(LINEGAP);
                System.out.println("Eh? Something went wrong while savin' the task!");
                System.out.println(LINEGAP);
            }
            System.out.println(LINEGAP);
            System.out.println("Understood, Kiryu-chan! Adding that task to the list.");
            System.out.println(" " + tasks.get(tasks.size() - 1).toString());
            System.out.println("Now you've got " + tasks.size() + " tasks need doin'.");
            System.out.println(LINEGAP);
        }
    }

    private static void addEvent(String input) throws MajimaException {
        String[] parts = input.split("/from", 2);
        if (parts.length < 2) {
            throw new MajimaException("Eh? Kiryu-chan, y'aint got no '/from' argument!");
        }
        String description = parts[0].substring(6).trim();
        if (description.isEmpty()) {
            throw new MajimaException("Kiryu-chan, you gotta describe what the task is!");
        }
        String[] dateParts = parts[1].split("/to", 2);
        if (dateParts.length < 2) {
            throw new MajimaException("Eh? Kiryu-chan, y'aint got no '/to' argument!");
        }
        String from = dateParts[0].trim();
        String to = dateParts[1].trim();
        if (canAddTask()) {
            Event newEvent = new Event(description, from, to);
            tasks.add(newEvent);
            try {
                appendToFile(FILE_PATH, newEvent.toFileString()
                        + System.lineSeparator());
            } catch (IOException e) {
                System.out.println(LINEGAP);
                System.out.println("Eh? Something went wrong while savin' the task!");
                System.out.println(LINEGAP);
            }
            System.out.println(LINEGAP);
            System.out.println("Understood, Kiryu-chan! Adding that task to the list.");
            System.out.println(" " + tasks.get(tasks.size() - 1).toString());
            System.out.println("Now you've got " + tasks.size() + " tasks need doin'.");
            System.out.println(LINEGAP);
        }
    }

    private static boolean canAddTask() {
        if (tasks.size() >= MAX_TASKS) {
            System.out.println(LINEGAP);
            System.out.println("O-oi, Kiryu-chan! Ya can't expect me to 'member all this crap!");
            System.out.println("This ol' noggin of mine can only fit a hundred tasks at best!");
            System.out.println(LINEGAP);
            return false;
        }
        return true;
    }

    private static void firstBootCheck() {
        File data = new File("./data/");
        if (!data.exists()) {
            data.mkdir();
        }
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println(LINEGAP);
                System.out.println("Majima.txt has successfully been created. Nice ta meet 'cha!");
                System.out.println(LINEGAP);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void deleteAndRebuild() {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(LINEGAP);
            System.out.println("Eh? Something went wrong while updating the file!");
            System.out.println(LINEGAP);
        }
    }

    private static void loadTasks() throws FileNotFoundException {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("Majima.txt not found. Let's start anew, yeah?");
                firstBootCheck();
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                loadLine(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid format/possible corrupted data in Majima.txt. Consider deleting the file");
            System.out.println("to generate a new one from scratch");
        }
    }

    private static void loadLine(String line) {
        String[] parts = line.split( " \\| ");
        if (parts.length < 3) {
            System.out.println("Invalid format/possible corrupted data in Majima.txt. Consider deleting the file");
            System.out.println("to generate a new one from scratch");
        }

        Task.TaskStatus status = parts[1].equals("[X]") ? Task.TaskStatus.DONE : Task.TaskStatus.UNDONE;
        String description = parts[2];
        Task task = null;

        switch (parts[0]) {
            case "[T]":
                task = new Todo(description);
                break;
            case "[D]":
                if (parts.length < 4) {
                    System.out.println("Invalid format/possible corrupted data in Majima.txt. Consider deleting the file");
                    System.out.println("to generate a new one from scratch");
                }
                try {
                    task = new Deadline(description, parts[3]);
                } catch (MajimaException e) {
                    System.out.println("Invalid format/possible corrupted data in Majima.txt. Consider deleting the file");
                    System.out.println("to generate a new one from scratch");
                }
                break;
            case "[E]":
                if (parts.length < 5) {
                    System.out.println("Invalid format/possible corrupted data in Majima.txt. Consider deleting the file");
                    System.out.println("to generate a new one from scratch");
                }
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                System.out.println("Invalid format/possible corrupted data in Majima.txt. Consider deleting the file");
                System.out.println("to generate a new one from scratch");
        }

        if (task != null) {
            task.status = status;
            tasks.add(task);
        }
    }

    /*
    We may not be using writeToFile(), but we'll just leave this here in case we need it.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

}
