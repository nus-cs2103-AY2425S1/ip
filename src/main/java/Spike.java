// General imports
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.util.Comparator;
import java.util.List;

// IO imports
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

enum InputType {
    BYE,
    LIST,
    LISTBYDATE,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    TASK,
    DELETE,
    ERROR
}

public class Spike {

    private static ArrayList<Task> toDoList = new ArrayList<>();
    public static final String FILE_PATH = "data/spike.txt";

    public static void helloMessage() {
        System.out.println("     _________________________________________________________");
        System.out.println("     Hello! I'm Spike\n     What can I do for you?");
        System.out.println("     _________________________________________________________");
        return;
    }

    public static void byeMessage() {
        System.out.println("     _________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("     _________________________________________________________");
        return;
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                String[] inputSplit = input.split(" ", 2);
                InputType inputType = parseInput(inputSplit[0]);

                switch (inputType) {
                    case BYE:
                        byeMessage();
                        break;
                    case LIST:
                        listTasks();
                        break;
                    case LISTBYDATE:
                        listTasksByDate();
                        break;
                    case MARK:
                        markTask(inputSplit[1]);
                        break;
                    case UNMARK:
                        unmarkTask(inputSplit[1]);
                        break;
                    case DELETE:
                        deleteTask(inputSplit[1]);
                        break;
                    case TODO:
                        checkDescription(inputSplit, "todo");
                        addToDo(inputSplit[1]);
                        break;
                    case DEADLINE:
                        checkDescription(inputSplit, "deadline");
                        addDeadline(inputSplit[1]);
                        break;
                    case EVENT:
                        checkDescription(inputSplit, "event");
                        addEvent(inputSplit[1]);
                        break;
                    case ERROR:
                        throw new SpikeException("Please enter a valid command");
                }

                if (inputType == InputType.BYE) {
                    break;
                }
            } catch (SpikeException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static InputType parseInput(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return InputType.BYE;
        } else if (input.equalsIgnoreCase("list")) {
            return InputType.LIST;
        } else if (input.equalsIgnoreCase("listbydate")) {
            return InputType.LISTBYDATE;
        } else if (input.equalsIgnoreCase("mark")) {
            return InputType.MARK;
        } else if (input.equalsIgnoreCase("unmark")) {
            return InputType.UNMARK;
        } else if (input.equalsIgnoreCase("delete")) {
            return InputType.DELETE;
        } else if (input.equalsIgnoreCase("todo")) {
            return InputType.TODO;
        } else if (input.equalsIgnoreCase("deadline")) {
            return InputType.DEADLINE;
        } else if (input.equalsIgnoreCase("event")) {
            return InputType.EVENT;
        } else {
            return InputType.ERROR;
        }
    }

    public static void checkDescription (String[] inputArray, String inputType) throws SpikeException {
        if ((inputArray.length == 1) || (inputArray[1].isEmpty())) {
            throw new SpikeException("The description of a " + inputType + " cannot be empty.");
        }
    }

    private static void listTasks() {
        System.out.println("     _________________________________________________________");
        System.out.println("      Here are the tasks in your list:");
        int counter = 0;
        for (Task item : toDoList) {
            counter++;
            System.out.println("      " + counter + ". " + item.toString());
        }
        System.out.println("     _________________________________________________________");
    }

    private static Comparator<Task> dateComparator = new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {
            LocalDateTime date1 = t1 instanceof Deadline ? ((Deadline) t1).getDate() : ((Event) t1).getFrom();
            LocalDateTime date2 = t2 instanceof Deadline ? ((Deadline) t2).getDate() : ((Event) t2).getFrom();
            return date1.compareTo(date2);
        }
    };

    private static void listTasksByDate() {
        System.out.println("     _________________________________________________________");
        System.out.println("      Here are the tasks in your list by date:");
        List<Task> filteredAndSortedTasks = toDoList.stream()
                .filter(t -> t instanceof Deadline || t instanceof Event)
                .sorted(dateComparator)
                .toList();

        int counter = 0;
        for (Task item : filteredAndSortedTasks) {
            counter++;
            System.out.println("      " + counter + ". " + item.toString());
        }
        System.out.println("     _________________________________________________________");
    }

    public static void markTask(String input) throws SpikeException {
        try {
            int index = Integer.parseInt(input) - 1;
            toDoList.get(index).markAsDone();
        } catch (NumberFormatException e) {
            throw new SpikeException("Please enter a valid number");
        } catch (IndexOutOfBoundsException e) {
            throw new SpikeException("Please enter a valid task number");
        }
    }

    public static void unmarkTask(String input) throws SpikeException {
        try {
            int index = Integer.parseInt(input) - 1;
            toDoList.get(index).unmark();
        } catch (NumberFormatException e) {
            throw new SpikeException("Please enter a valid number");
        } catch (IndexOutOfBoundsException e) {
            throw new SpikeException("Please enter a valid task number");
        }
    }

    public static void deleteTask(String input) throws SpikeException {
        try {
            int index = Integer.parseInt(input) - 1;
            Task deletedTask = toDoList.remove(index);
            System.out.println("     _________________________________________________________");
            System.out.println("     " + "Noted. I've removed this task:");
            System.out.println("       " + deletedTask.toString());
            System.out.println("     " + "Now you have " + toDoList.size() + " tasks in the list.");
            System.out.println("     _________________________________________________________");
        } catch (NumberFormatException e) {
            throw new SpikeException("Please enter a valid number");
        } catch (IndexOutOfBoundsException e) {
            throw new SpikeException("Please enter a valid task number");
        }
    }

    public static void addToDo(String input) throws SpikeException {
        ToDo toDo = new ToDo(input);
        toDoList.add(toDo);
        addTaskString(toDo.toString());
    }

    public static void addEvent(String input) throws SpikeException {
        String[] parts = input.split(" /from | /to ");

        if (parts.length != 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            if (parts[0].trim().isEmpty()) {
                throw new SpikeException("Please enter a valid event description followed by " +
                        "/from yyyy-MM-dd'T'HH:mm:ss <start date, time> " +
                        "/to yyyy-MM-dd'T'HH:mm:ss <end date, time>");
            }
            throw new SpikeException("Please enter a valid event in the right format: event description " +
                    "/from yyyy-MM-dd'T'HH:mm:ss <start date, time> " +
                    "/to yyyy-MM-dd'T'HH:mm:ss <end date, time>");
        }

        try {
            LocalDateTime start = LocalDateTime.parse(parts[1].trim());
            LocalDateTime end = LocalDateTime.parse(parts[2].trim());
            if (start.isAfter(end)) {
                throw new SpikeException("Please enter a valid event with a start date and time that is before the end date and time");
            }
            Event formattedEvent = new Event(parts[0].trim(), start, end);
            toDoList.add(formattedEvent);
            addTaskString(formattedEvent.toString());
        } catch (DateTimeException e) {
            throw new SpikeException("Please enter a valid event with a start date and time followed by /to end date and time");
        }
    }


    public static void addDeadline(String input) throws SpikeException {
        String[] split = input.split(" /by ", 2);

        if (split.length != 2 || split[0].trim().isEmpty() || split[1].trim().isEmpty()) {
            if (split.length != 2 || split[1].trim().isEmpty()) {
                throw new SpikeException("Please enter a valid deadline in the right format: " +
                        "deadline description /by yyyy-MM-dd'T'HH:mm:ss <due date, time>");
            }
            throw new SpikeException("Please enter a valid deadline description followed by " +
                    "/by yyyy-MM-dd'T'HH:mm:ss <due date, time>");
        }

        try {
            LocalDateTime deadline = LocalDateTime.parse(split[1].trim());
            Deadline formattedDeadline = new Deadline(split[0].trim(), deadline);
            toDoList.add(formattedDeadline);
            addTaskString(formattedDeadline.toString());
        } catch (DateTimeException e) {
            throw new SpikeException("Please enter a deadline with a valid due date and time");
        }
    }

    public static void addTaskString(String taskToString) {
        System.out.println("     _________________________________________________________");
        System.out.println("     " + "Got it. I've added this task:");
        System.out.println("       " + taskToString);
        System.out.println("     " + "Now you have " + toDoList.size() + " tasks in the list.");
        System.out.println("     _________________________________________________________");
    }

    public static void writeToFile() throws SpikeException {
        try {
            File file = new File(FILE_PATH);
            FileWriter writer = new FileWriter(file);
            for (Task task : toDoList) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new SpikeException("An error occurred while writing to file");
        }
    }

    public static ArrayList<Task> loadFromFile() throws SpikeException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return loadedTasks;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                    case "T":
                        task = new ToDo(parts[2]);
                        break;
                    case "D":
                        LocalDateTime deadline = LocalDateTime.parse(parts[3]);
                        task = new Deadline(parts[2], deadline);
                        break;
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(parts[3]);
                        LocalDateTime to = LocalDateTime.parse(parts[4]);
                        task = new Event(parts[2], from, to);
                        break;
                    default:
                        throw new SpikeException("An error occurred while reading from file");
                }
                if (parts[1].equals("1")) {
                    task.markAsDone();
                }
                loadedTasks.add(task);
            }
            scanner.close();
        } catch (IOException e) {
            throw new SpikeException("An error occurred while reading from file");
        }
        return loadedTasks;
    }

    public static void run() {
        try {
            toDoList = loadFromFile();
        } catch (SpikeException e) {
            System.out.println(e.getMessage());
        }
        helloMessage();
        echo();
        try {
            writeToFile();
        } catch (SpikeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        run();
    }
}
