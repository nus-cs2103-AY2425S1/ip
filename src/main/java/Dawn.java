import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Dawn {
    private static Scanner scanner = new Scanner(System.in);
    enum Command {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        BYE,
        LIST,
        TODAY
    }
    public static void main(String[] args) {
        String divider = "--".repeat(30);

        // Handles folder-does-not-exist-yet case to save tasks
        String currentDir = System.getProperty("user.dir");
        String dirPath = currentDir + File.separator + "data";
        File file = new File(dirPath);
        file.mkdir();

        System.out.println(divider);
        System.out.println("Dawn 🌙 speaking, what can I do for you?");

        try {
            loadPrevTasks("./data/dawn.txt"); //
            respond();
        } catch (DawnException ex) {
            System.err.print(ex);
        } finally {
            scanner.close();
        }
        System.out.println(divider);
    }
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void respond() throws DawnException { //provide responses to the user input
        while (scanner.hasNextLine()) {
            String command = scanner.next();
            Command cmd;
            try {
                cmd = Command.valueOf(command.toUpperCase()); // convert the command input to a corresponding enum constant
            } catch (IllegalArgumentException e) {
                throw new DawnException("Am I supposed to know what that means? Try something else\n");
            }

            String input = scanner.nextLine().trim();
            switch (cmd) {
            case BYE:
                System.out.println("Byeeee~ nice chatting with you! See you next time, Dawn 🌙 out");
                saveTasks("./data/dawn.txt");
                return;
            case LIST:
                System.out.println("listing all tasks...");
                if (tasks.isEmpty()) {
                    System.out.println("There are no tasks in the list... \nPlease feed me some tasks!");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d. %s  \n", i + 1, tasks.get(i));
                    }
                }
                break;
            case MARK: case UNMARK:
                mark(command, input);
                break;
            case DELETE:
                delete(input);
                break;
            case TODO: case DEADLINE: case EVENT:
                addTask(cmd, input);
                break;
            case TODAY:
                doByToday();
                break;
            }
        }
    }

    private static void mark(String cmd, String index) throws DawnException{ // mark the tasks accordingly
        int ind;
        try {
            ind = Integer.parseInt(index);
        } catch (NumberFormatException e){
            throw new DawnException("Please specify the index of the task to be marked!\n");
        }

        if (cmd.equals("unmark")) {
            tasks.get(ind - 1).markAsNotDone(); // account for index starting at 0
            System.out.println("  ok, I've unmarked this task :( ");
        } else {
            tasks.get(ind - 1).markAsDone();
            System.out.println("  i've marked this task as done!: ");
        }
        System.out.println("  " + ind + ". " + tasks.get(ind - 1));
    }

    private static void addTask(Command cmd, String input) throws DawnException{
        if (input.isBlank()) {
            throw new DawnException("You might be missing the task description, please check again\n");
        }

        Task t = null;
        String[] s = input.split("/");

        switch (cmd) {
        case TODO:
            t = new ToDo(s[0]);
            break;
        case DEADLINE:
            if (s.length < 2) {
                throw new DawnException("Make sure you include both the task description and the deadline!\n");
            }
            t = new Deadline(s[0], s[1]);
            break;
        case EVENT:
            if (s.length < 3) {
                throw new DawnException("Make sure you include the task description, start, and end times for " +
                        "your event!\n");
            }
            t = new Event(s[0], s[1], s[2]);
            break;
        }

        tasks.add(t);
        System.out.println("  Gotcha! I've added this task: \n" + tasks.size() + "." + t);
        System.out.printf("  Now you have %d task(s) in the list \n", tasks.size());
    }

    private static void delete(String index) throws DawnException {
        int ind;
        try {
            ind = Integer.parseInt(index);
        } catch (NumberFormatException e){
            throw new DawnException("Please specify the index of the task to be deleted!\n");
        }
        Task t = tasks.get(ind - 1); // to account for index starting at 0
        System.out.println("  OK! I have removed this task for you: \n" + t);
        tasks.remove(ind - 1);
        System.out.printf("  Now you have %d task(s) in the list \n", tasks.size());
    }

    enum TaskType {
        T,
        D,
        E
    }
    private static void loadPrevTasks(String filePath) throws DawnException {
        File f;
        try {
            f = new File(filePath);
            // handles case file-already-exists, and
            // case file-does-not-exist-yet by creating a new text file
            if (!f.createNewFile()) {
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    String[] taskContent = s.nextLine().split(" \\| ");
                    TaskType type = TaskType.valueOf(taskContent[0]);
                    Task task = generateSavedTask(taskContent, type);
                    tasks.add(task);
                }
                clearSavedTask(filePath); // clear the previously saved tasks
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Task generateSavedTask(String[] taskContent, TaskType type) throws DawnException {
        boolean isDone = taskContent[1].equals("1"); // checks if the status of the task is 0 or 1
        Task task;
        switch (type) {
            case T:
                task = new ToDo(taskContent[2]);
                break;
            case D:
                task = new Deadline(taskContent[2], taskContent[3]);
                break;
            case E:
                task = new Event(taskContent[2], taskContent[3], taskContent[4]);
                break;
            default:
                // corrupted data file case, i.e. content not in the expected format
                // todo handle case where the content is not in the expected format
                throw new DawnException("Invalid task type!");
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    private static void clearSavedTask(String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath, false);
        writer.write(""); // to clear the content of the files
        writer.close();
    }

    private static void saveTasks(String filePath) throws DawnException {
        try {
            for (int i = 0; i < tasks.size(); i++) {
                writeToFile(filePath, tasks.get(i));
            }
        } catch (IOException e) {
            throw new DawnException("Something went wrong: " + e.getMessage());
        }
    }

    private static void writeToFile(String filePath, Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String separator = " | ";
        String isDone = task.isDone() ? "1" : "0";
        String desc = task.getDesc();
        String deadline;
        String from;
        String to;

        String textToAdd = "";

        if (task instanceof ToDo) {
            textToAdd = "T" + separator + isDone + separator + desc;
        } else if (task instanceof Deadline) {
            // We can safely typecast here since we already checked that task is an instance of Deadline
            Deadline t = (Deadline) task;
            deadline = (t).getDeadline();
            textToAdd = "D" + separator + isDone + separator + desc + separator + deadline;
        } else { // task instanceof Event
            Event t = (Event) task;
            from = t.getFromTime();
            to = t.getToTime();
            textToAdd = "E" + separator + isDone + separator + desc + separator + from + separator + to;
        }
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }

    private static void doByToday() {
        System.out.println("Deadlines and events happening today: ");
        Boolean haveTasks = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                Deadline d = (Deadline) tasks.get(i);
                if (d.getDate().equals(LocalDate.now())) {
                    System.out.println(d);
                    haveTasks = true;
                }
            } else if (tasks.get(i) instanceof Event) {
                Event e = (Event) tasks.get(i);
                if (e.getDate().equals(LocalDate.now())) {
                    System.out.println(e);
                    haveTasks = true;
                }
            }
        }
        if (!haveTasks) {
            System.out.println("There are no deadlines and events happening today!");
        }
    }
}
