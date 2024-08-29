import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class Lexi {
    private final static ArrayList<Task> tasks = new ArrayList<>();
    private final static String LINE_BREAK = "____________________________________________________________";
    private final static File directory = new File("./data");
    private static File data = null;
    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    public static void main(String[] args) throws IOException {
        Scanner userInput = new Scanner(System.in);
        greet();
        loadTasks();
        String response = userInput.nextLine();
        while (!response.toUpperCase().equals(Commands.BYE.name())) {
            try {
                String[] parts = response.split(" ");
                Commands command = Commands.valueOf(parts[0].toUpperCase());
                switch (command) {
                    case MARK:
                    case UNMARK:
                        handleMark(parts);
                        break;
                    case TODO:
                        handleTodo(response);
                        break;
                    case DEADLINE:
                        handleDeadline(response);
                        break;
                    case EVENT:
                        handleEvent(response);
                        break;
                    case DELETE:
                        handleDelete(parts);
                        break;
                    case LIST:
                        listTasks();
                        break;
                }
            } catch(LexiException e) {
                System.out.println(e.getMessage());
            } catch(IllegalArgumentException e) {
                System.out.println("You have entered an invalid command!\nKey in one of them from the list below:\n");
                Commands.printCommands();
            } finally {
                response = userInput.nextLine();
            }
        }
            userInput.close();
            bye();
        }

    private static void loadTasks() throws IOException {
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }
        File file = new File(directory, "data.txt");
        file.createNewFile();
        data = file;
        Scanner contents = new Scanner(data);
        while (contents.hasNextLine()) {
            String[] parts = contents.nextLine().split(" !- ");
            String taskName = parts[2];
            if (parts[0].equals("T")) {
                Todo task = new Todo(taskName);
                if (parts[1].equals("1")) {
                    task.doTask();
                }
                tasks.add(task);
            } else if (parts[0].equals("D")) {
                String deadline = parts[3];
                LocalDateTime by = LocalDateTime.parse(deadline, Lexi.inputFormatter);
                Deadline task = new Deadline(taskName, by);
                if (parts[1].equals("1")) {
                    task.doTask();
                }
                tasks.add(task);
            } else if (parts[0].equals("E")) {
                String start = parts[3];
                String end = parts[4];
                LocalDateTime from = LocalDateTime.parse(start, Lexi.inputFormatter);
                LocalDateTime to = LocalDateTime.parse(end, Lexi.inputFormatter);
                Event task = new Event(taskName, from, to);
                if (parts[1].equals("1")) {
                    task.doTask();
                }
                tasks.add(task);
            }
        }
    }

    private static void handleDelete(String[] parts) throws LexiException {
        if(parts.length != 2) {
            throw new LexiException("Please key in the command in this format " +
                    "\"delete <task number>\"\n");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            if(taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new LexiException("You can't delete a task that doesn't exist");
            }
            Task removedTask = tasks.remove(taskNumber-1);
            handleChangeInData(tasks);
            System.out.println(LINE_BREAK);
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.printf("Now you have %d tasks in the list.%n", tasks.size());
            System.out.println(LINE_BREAK);
        } catch(NumberFormatException e) {
            throw new LexiException("Please enter a valid task number as follows:\n" +
                    "\"delete <task number>\"\n");
        }
    }

    private static void handleEvent(String response) throws LexiException {
        String[] parts = response.split(" /from ");
        String errorMessage = "Please key in the command in this format\n"
                + "\"event <task> /from <date and time> /to <date and time>\"\n"
                + "\"<date> in format DD/MM/YYYY HHmm like 13/01/2002 1700\"\n";
        // If only command "event" is present
        if(parts[0].equals(response)) {
            throw new LexiException(errorMessage);
        }
        String taskName = parts[0].substring(6);
        if(parts.length < 2) {
            throw new LexiException(errorMessage);
        }
        String[] range = parts[1].split(" /to ");
        // No "to" command entered
        if(parts[1].equals(range[0])) {
            throw new LexiException(errorMessage);
        }
        String start  = range[0];
        String end = range[1];
        try {
            LocalDateTime from = LocalDateTime.parse(start, Lexi.inputFormatter);
            LocalDateTime to = LocalDateTime.parse(end, Lexi.inputFormatter);
            addTask(new Event(taskName, from, to));
        } catch (DateTimeParseException e) {
            throw new LexiException("You have entered the date/time in the incorrect format\n"
                    + "Please follow this format: DD/MM/YYYY HHmm like 13/01/2002 1700");
        }
    }

    private static void handleDeadline(String response) throws LexiException {
        String[] parts = response.split(" /by ");
        String errorMessage = "Please key in the command in this format\n"
                + "\"deadline <task> /by <date>\"\n"
                + "\"date and time in format DD/MM/YYYY HHmm like 13/01/2002 1700\"\n";
        if (parts[0].equals(response) || parts.length != 2) {
            throw new LexiException(errorMessage);
        }
        String taskName = parts[0].substring(9);
        String dateTime = parts[1];
        try {
            LocalDateTime by = LocalDateTime.parse(dateTime, Lexi.inputFormatter);
            addTask(new Deadline(taskName, by));
        } catch (DateTimeParseException e) {
            throw new LexiException("You have entered the date/time in the incorrect format\n"
            + "Please follow this format: DD/MM/YYYY HHmm like 13/01/2002 1700");
        }
    }

    private static void handleTodo(String response) throws LexiException{
        String errorMessage = "Sorry! The description of a todo cannot be empty\n" +
                "Please write in this format \"todo <description>\"\n";
        if(response.length() < 6) {
            throw new LexiException(errorMessage);
        }

        String taskName = response.substring(5);

        if(taskName.isBlank()) {
            throw new LexiException(errorMessage);
        }
        addTask(new Todo(taskName));
    }

    private static void handleMark(String[] parts) throws LexiException {

        if(parts.length != 2 || parts[1].isEmpty() || parts[1].isBlank()) {
            throw new LexiException("Please enter your command in this format\n" +
            "\"mark <number>\"");
        }
        int taskNumber =  Integer.parseInt(parts[1]) - 1;
        if(taskNumber > tasks.size() - 1) {
            throw new LexiException("Sorry! That task does not exist.\nPlease key in the correct task number");
        }
        Task taskToBeMarked = tasks.get(taskNumber);
        if(parts[0].equals("unmark")) {
            unmarkTask(taskToBeMarked, taskNumber);
        } else {
            markTask(taskToBeMarked, taskNumber);
        }
    }
    private static void unmarkTask(Task taskToBeMarked, int taskNumber) throws LexiException {
            if(!taskToBeMarked.getIsDone()) {
                throw new LexiException("This task has already been unmarked!\n");
            }
            taskToBeMarked.undoTask();
            tasks.remove(taskNumber);
            tasks.add(taskNumber, taskToBeMarked);
            handleChangeInData(tasks);
            System.out.println(LINE_BREAK);
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + taskToBeMarked);
            System.out.println(LINE_BREAK + "\n");

    }

    public static void markTask(Task taskToBeMarked, int taskNumber) throws LexiException {
        if(taskToBeMarked.getIsDone()) {
            throw new LexiException("This task has already been marked!\n");
        }
        taskToBeMarked.doTask();
        tasks.remove(taskNumber);
        tasks.add(taskNumber, taskToBeMarked);
        handleChangeInData(tasks);
        System.out.println(LINE_BREAK);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskToBeMarked);
        System.out.println(LINE_BREAK + "\n");
    }


    public static void listTasks() {
        System.out.println(LINE_BREAK);
        System.out.println(" Here are the tasks in your list:");
        for(int i = 0;i<tasks.size();i++) {
            Task currTask = tasks.get(i);
            System.out.printf("  %d. %s%n", i+1, currTask);
        }
        System.out.println(LINE_BREAK + "\n");
    }

    public static void greet() {
        System.out.println(LINE_BREAK);
        System.out.println(" Hello! I'm Lexi\n What can I do for you?");
        System.out.println(LINE_BREAK + "\n");
    }
    public static void bye() {
        System.out.println(LINE_BREAK);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK + "\n");
    }
    public static void handleChangeInData(ArrayList<Task> tasks) throws LexiException {
        try {
            FileWriter fw = new FileWriter(data);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            for (Task task: tasks) {
                char taskType = task.toString().split(" ")[0].charAt(1);
                if (taskType == 'T') {
                    fw.write(String.format("%c !- %d !- %s", taskType,
                            task.getIsDone() ? 1 : 0, task.getTaskName()) + System.lineSeparator());
                } else if (taskType == 'D') {
                    Deadline obj = (Deadline) task;
                    fw.write(String.format("%c !- %d !- %s !- %s", taskType,
                            obj.getIsDone() ? 1 : 0, obj.getTaskName(), obj.getBy().format(formatter))
                            + System.lineSeparator());
                } else if (taskType == 'E') {
                    Event obj = (Event) task;
                    fw.write(String.format("%c !- %d !- %s !- %s !- %s", taskType,
                            obj.getIsDone() ? 1 : 0, obj.getTaskName(),
                            obj.getFrom().format(formatter),
                            obj.getTo().format(formatter)) + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new LexiException("Something went wrong with writing to data\n"
                    + "Please contact your system administrator!\n"
                    + "Error Message:\n"
                    + e.getMessage());
        }
    }
    public static void addTask(Task task) throws LexiException {
        tasks.add(task);
        handleChangeInData(tasks);
        int taskSize = tasks.size();
        System.out.println(LINE_BREAK);
        System.out.println(" Got it. I've added this task:");
        System.out.printf("   %s%n", task);
        System.out.printf(" Now you have %d task%s in the list.%n", taskSize, taskSize == 1 ? "" : "s");
        System.out.println(LINE_BREAK + "\n");
    }

}
