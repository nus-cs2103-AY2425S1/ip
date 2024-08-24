import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Duck {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "data/duck.txt";

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    enum Instruction {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        ON,
        BYE
    }
    public static void main(String[] args) {

        try {
            System.out.println("Initializing Duck...");
            File f = createFileIfDoesNotExist(FILE_PATH);
            readFromFile(f);
            System.out.println("Quack, Duck is up!");
            sayHi();
            getInputTillBye();
            System.out.println("Bye. Hope to see you again soon!");
        } catch (DuckException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sayHi() {
        String logo = """
                        ,---,                                  ,-.
                      .'  .' `\\                            ,--/ /|
                    ,---.'     \\          ,--,           ,--. :/ |
                    |   |  .`\\  |       ,'_ /|           :  : ' /
                    :   : |  '  |  .--. |  | :    ,---.  |  '  /
                    |   ' '  ;  :,'_ /| :  . |   /     \\ '  |  :
                    '   | ;  .  ||  ' | |  . .  /    / ' |  |   \\
                    |   | :  |  '|  | ' |  | | .    ' /  '  : |. \\
                    '   : | /  ; :  | : ;  ; | '   ; :__ |  | ' \\ \\
                    |   | '` ,/  '  :  `--'   \\'   | '.'|'  : |--'
                    ;   :  .'    :  ,      .-./|   :    :;  |,'
                    |   ,.'       `--`----'     \\   \\  / '--'
                    '---'                        `----'
                """;


        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duck");
        System.out.println("What can I do for you, QUACK?\n");
    }
    // obtain user input till he inputs bye, ignoring case
    public static void getInputTillBye() {
        Scanner in = new Scanner(System.in);
        String message;

        while (in.hasNextLine()) {
            message = in.nextLine().trim();
            if (message.equalsIgnoreCase(Instruction.BYE.toString())) {
                break;
            }
            reactTo(message);
        }
    }

    public static void reactTo(String message) {
        try {
            Instruction instruction = Instruction.valueOf(getInstruction(message));
            switch (instruction) {
            case LIST:
                listInstruction();
                break;
            case MARK, UNMARK:
                updateStatus(message);
                break;
            case DELETE:
                deleteTask(message);
                break;
            case TODO:
                toDoInstruction(message);
                break;
            case DEADLINE:
                deadlineInstruction(message);
                break;
            case EVENT:
                eventInstruction(message);
                break;
            case ON:
                onInstruction(message);
            default:
                break;
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Hey, that's not a valid instruction! I don't know how to respond to that.");
        } catch (DuckException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getInstruction(String message) {
        return message.split(" ")[0].toUpperCase();
    }

    public static ToDo parseToDo(String input) throws DuckException {
        // Normalize to lowercase and remove the todo keyword
        String description = input.replaceFirst("(?i)^todo\\s*", "").trim();
        if (description.isEmpty()) {
            throw new DuckException("What are you trying \"to do\", mate? " +
                    "Give me a valid description instead of an empty one.\n" +
                    "todo {description}");
        }
        return new ToDo(description);
    }

    public static Deadline parseDeadline(String input) throws DuckException {
        // Regular expression to match the pattern for deadline
        Pattern pattern = Pattern.compile("(?i)^deadline\\s+(.+)\\s+/by\\s+(.+)$");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            String deadlineStr = matcher.group(2);
            LocalDateTime deadline = convertToDateTime(deadlineStr);
            return new Deadline(description, deadline);
        }
        else {
            throw new DuckException("Hey, a deadline instruction should be of the following format:\n"
                    + "deadline {description} /by {deadline}");
        }
    }

    public static Event parseEvent(String input) throws DuckException {
        // Regular expression to match the pattern for event
        Pattern pattern = Pattern.compile("(?i)^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            String fromStr = matcher.group(2);
            String toStr = matcher.group(3);
            LocalDateTime from = convertToDateTime(fromStr);
            LocalDateTime to = convertToDateTime(toStr);
            if (!to.isAfter(from)){
                throw new DuckException("The end time of an event should be after the start time!");
            } else {
                return new Event(description, from, to);
            }

        } else {
            throw new DuckException("Give me a valid event format!\n"
                    + "event {description} /from {start} /to {end}");
        }

    }

    public static void listInstruction() {
        int idx = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasks) {
            System.out.println(idx++ + "." + t.toString());
        }
        System.out.println();
    }

    public static void toDoInstruction(String message) throws DuckException {
        ToDo todo = parseToDo(message);
        addTask(todo);
    }

    public static void deadlineInstruction(String message) throws DuckException {
        Deadline deadline = parseDeadline(message);
        addTask(deadline);
    }

    public static void eventInstruction(String message) throws DuckException {
        Event event = parseEvent(message);
        addTask(event);
    }

    // checks that the instruction is of the format "on yyyy-MM-dd" and list deadline with by on that date and list event with to after that date
    public static void onInstruction(String message) throws DuckException {
        String[] words = message.split(" ");
        if (words.length != 2) {
            throw new DuckException("The format for 'On' instruction is:\n"
                    + "on {yyyy-MM-dd}");
        }
        LocalDate date = LocalDate.parse(words[1]);
        AtomicInteger idx = new AtomicInteger(1);

        System.out.println("Here are the tasks due by " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        Stream<Deadline> deadlineStream = tasks.stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task)
                .filter(deadline -> deadline.isOn(date));
        deadlineStream.forEach(deadline -> System.out.println(idx.getAndIncrement() + "." + deadline.toString()));

        idx.set(1);
        System.out.println();

        System.out.println("Here are the events still happening on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        Stream<Event> eventStream = tasks.stream()
                .filter(task -> task instanceof Event)
                .map(task -> (Event) task)
                .filter(event -> event.isEndingAfter(date));
        eventStream.forEach(event -> System.out.println(idx.getAndIncrement() + "." + event.toString()));
        System.out.println();
    }

    public static boolean isCorrectUpdateFormat(String message) {
        String[] words = message.split(" ");
        return words.length == 2 && isInteger(words[1]);
    }

    private static boolean hasCorrectFileFormat(String[] words, TaskType type) {
        try {
            switch (type) {
            case TODO:
                return words.length == 3
                        && hasCorrectDoneFormat(words[1])
                        && !words[2].isEmpty();
            case DEADLINE:
                if (words.length == 4) {
                    convertToDateTime(words[3]);
                    return hasCorrectDoneFormat(words[1]) && !words[2].isEmpty();
                }

            case EVENT:
                if (words.length == 5) {
                    ;
                    return hasCorrectDoneFormat(words[1])
                            && !words[2].isEmpty()
                            && convertToDateTime(words[3])
                                    .isBefore(convertToDateTime(words[4]));
                }
            default:
                return false;
            }
        } catch (DuckException e) {
            return false;
        }

    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void updateStatus(String message) throws DuckException {
        if (!isCorrectUpdateFormat(message)) {
            throw new DuckException("Update tasks with correct format please >:(\n"
                    + "mark/unmark {index of task to update}");
        }

        String[] words = message.split(" ");
        try {
            if (words[0].equals("mark")) {
                tasks.get(Integer.parseInt(words[1]) - 1).markAsDone();
            } else {
                tasks.get(Integer.parseInt(words[1]) - 1).markAsIncomplete();
            }
            updateTaskInFile();

        } catch (NumberFormatException e) {
            throw new DuckException("Oops! you have to indicate a valid task index!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException("Oops! Index out of bound :( Input a valid task index.\n");
        }
    }

    public static void deleteTask(String message) throws DuckException {
        if (!isCorrectUpdateFormat(message)) {
            throw new DuckException("Delete tasks with correct format please >:(\n"
                    + "delete {index of task to delete}");
        }

        String[] words = message.split(" ");

        try {

            System.out.println("Noted. I've removed this task:\n"
                    + tasks.get(Integer.parseInt(words[1]) - 1));
            tasks.remove(Integer.parseInt(words[1]) - 1);
            updateTaskInFile();
            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");


        } catch (NumberFormatException e) {
            throw new DuckException("Oops! you have to indicate a valid task index!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException("Oops! Index out of bound :( Input a valid task index.\n");
        }
    }
    public static void addTask(Task task) throws DuckException {
        tasks.add(task);
        appendTaskToFile(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
    }

    public static boolean hasCorrectDoneFormat(String isDone) {
        return isDone.equals("0") || isDone.equals("1");
    }
    public static boolean getTaskDoneBoolean(String isDone) throws DuckException {
        if (!hasCorrectDoneFormat(isDone)) {
            throw new DuckException("Invalid task status in file: " + isDone);
        }
        return isDone.equals("1");
    }
    public static void readFromFile(File f) throws DuckException {
        try {
            Scanner sc = new Scanner(f);
            int lineNumber = 0;
            while (sc.hasNextLine()) {
                lineNumber++;
                String line = sc.nextLine();
                String[] words = line.split(" \\| ");
                Task task = null;
                switch (words[0]) {
                case "T":
                    if (hasCorrectFileFormat(words, TaskType.TODO)) {
                        task = new ToDo(
                                getTaskDoneBoolean(words[1]),
                                words[2]);
                    }
                    break;
                case "D":
                    if (hasCorrectFileFormat(words, TaskType.DEADLINE)) {
                        task = new Deadline(
                                getTaskDoneBoolean(words[1]),
                                words[2],
                                convertToDateTime(words[3]));
                    }
                    break;
                case "E":
                    if (hasCorrectFileFormat(words, TaskType.EVENT)) {
                        task = new Event(
                                getTaskDoneBoolean(words[1]),
                                words[2],
                                convertToDateTime(words[3]),
                                convertToDateTime(words[4]));
                    }
                    break;
                default:
                    continue;
                }
                if (task != null) {
                    tasks.add(task);
                } else {
                    System.out.println("Warning: Skipping invalid line " + lineNumber + ": " + line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DuckException("File not found: " + f.getPath());
        }
    }

    public static File createFileIfDoesNotExist(String filePath) throws DuckException{
        try {
            File directory = getFileDirectory(filePath);
            if (!directory.isDirectory()) {
                directory.mkdir();
            }

            File f = new File(filePath);

            if (f.createNewFile()) {
                System.out.println("New file created under root path:\n"
                        + "./" + filePath);
            }

            return f;
        } catch (SecurityException e) {
            System.out.println("Error creating directory due to security Exception:\n"
                    + e.getMessage());

        } catch (IOException e) {
            throw new DuckException("Error creating file:\n"
                    + e.getMessage());
        }
        return null;
    }

    public static File getFileDirectory(String filePath) {
        return new File(filePath.substring(0, filePath.lastIndexOf('/')));
    }

    // make adding new task efficient by appending to file
    public static void appendTaskToFile(Task task) throws DuckException {
        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {
            fw.write(task.toFileFormat() + System.lineSeparator());
        } catch (IOException e) {
            throw new DuckException("Error writing to file:\n" + e.getMessage());
        }
    }

    // update the file
    public static void updateTaskInFile() throws DuckException {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new DuckException("Error updating file:\n" + e.getMessage());
        }
    }


    public static LocalDateTime convertToDateTime(String dateTimeStr) throws DuckException {
        // Define the two accepted date-time formats
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

        try {
            // Try to parse the input string using the first format
            return LocalDateTime.parse(dateTimeStr, formatter1);
        } catch (DateTimeParseException e) {
            // If it fails, try to parse using the second format
            try {
                return LocalDateTime.parse(dateTimeStr, formatter2);
            } catch (DateTimeParseException ex) {
                // If both fail, throw a DuckException
                throw new DuckException("""
                        Invalid date format following the command /from /to /by!
                        Please use one of the following formats:
                        yyyy-MM-dd HHmm or yyyy/MM/dd HHmm""");
            }
        }
    }





}
