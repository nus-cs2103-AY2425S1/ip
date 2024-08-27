import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

public class Talky {
    private static ArrayList<Task> userTasks = new ArrayList<>();
    private final static String SAVE_PATH = "./data/talky.txt";
    private boolean running;
    private Talky() {
        initializeSave();
        this.running = true;
    }
    public static void main(String[] args) throws TalkyException {
        //ArrayList<Task> userTasks = new ArrayList<>();

        Talky chatData = new Talky();

        printSeperator("Hello I'm Talky\n" + "How may I help you?");

        Scanner sc = new Scanner(System.in);
        while (chatData.running) {
            try {
                String input = sc.nextLine();
                String[] commandLine = input.split(" ", 2);
                switch (commandLine[0]) {
                case "bye":
                    printSeperator("Bye!!! Do let me know if there's anything else!");
                    chatData.running = false;
                    break;
                case "list":
                    list(commandLine);
                    break;
                case "mark":
                    mark(commandLine);
                    break;
                case "unmark":
                    unmark(commandLine);
                    break;
                case "todo":
                    addTodo(commandLine);
                    break;
                case "deadline":
                    addDeadline(commandLine);
                    break;
                case "event":
                    addEvent(commandLine);
                    break;
                case "delete":
                    delete(commandLine);
                    break;
                default:
                    throw new TalkyException("Invalid Command");
                }
            } catch (TalkyException err) {
                printSeperator(err.getMessage());
            }
        }
    }

    public static void printSeperator(String content) {
        String SEPERATOR = "---------------------------------------";
        System.out.println(SEPERATOR);
        System.out.println(content);
        System.out.println(SEPERATOR);
    }

    private static void list(String[] commandLine) throws TalkyException {
        String textsList = "";
        int rank = 1;
        for (Task task : userTasks) {
            textsList += rank + "." + task.toString() + "\n";
            rank++;
        }
        printSeperator(textsList);
    }

    private static void mark(String[] commandLine) throws TalkyException {
        if (commandLine.length != 2) {
            throw new TalkyException("Specify mark in the format: mark [index]");
        }
        int indexToChange = Integer.parseInt(commandLine[1]) - 1;
        if (indexToChange < 0 | indexToChange > userTasks.size()) {
            throw new TalkyException("mark index out of bounds");
        }
        Task changedTask = userTasks.get(indexToChange);
        changedTask.setMark(true);
        saveMark(indexToChange, "true");
        printSeperator("I've marked this task as done: " + changedTask.getName());
    }

    private static void unmark(String[] commandLine) throws TalkyException {
        if (commandLine.length != 2) {
            throw new TalkyException("Specify unmark in the format: unmark [index]");
        }
        int indexToChange = Integer.parseInt(commandLine[1]) - 1;
        if (indexToChange < 0 | indexToChange > userTasks.size()) {
            throw new TalkyException("unmark index out of bounds");
        }
        Task changedTask = userTasks.get(indexToChange);
        changedTask.setMark(false);
        saveMark(indexToChange, "false");
        printSeperator("I've marked this task as not done: " + changedTask.getName());
    }

    private static void addTodo(String[] commandLine) throws TalkyException {
        if (commandLine.length != 2) {
            throw new TalkyException("Specify what task to do in the format: todo [name]");
        }
        String name = commandLine[1];
        userTasks.add(new ToDo(name));
        String dataLine = String.format("ToDo %s false", name);
        save(dataLine);
        printSeperator("Added ToDo: " + name);
    }

    private static void addDeadline(String[] commandLine) throws TalkyException {
        if (commandLine.length != 2) {
            throw new TalkyException("Specify deadline in the format: deadline [name] /by [date] [24hr Time]");
        }
        String[] params = commandLine[1].split(" /by ");
        if (params.length != 2) {
            throw new TalkyException("Specify deadline in the format: deadline [name]  /by [date] [24hr Time]");
        }
        String name = params[0];
        String by = params[1];
        String dataLine = String.format("Deadline %s %s false", name, by);
        save(dataLine);
        ArrayList<LocalDateTime> formattedDate = parseDate(by);
        userTasks.add(new Deadline(name, formattedDate.get(0)));
        printSeperator("Added Deadline: " + name);
    }

    private static void addEvent(String[] commandLine) throws TalkyException {
        if (commandLine.length != 2) {
            throw new TalkyException("Specify event in the format: event [name] /from [date] [24hr Time] /to [date] [24hr Time]");
        }
        String[] params = commandLine[1].split(" /from | /to ");
        if (params.length != 3) {
            throw new TalkyException("Specify deadline in the format: event [name] /from [date] [24hr Time] /to [date] [24hr Time]");
        }
        String name = params[0];
        String from = params[1];
        String to = params[2];
        String dataLine = String.format("Event %s %s %s false", name, from, to);
        save(dataLine);
        ArrayList<LocalDateTime> formattedDates = parseDate(from, to);
        userTasks.add(new Event(name, formattedDates.get(0), formattedDates.get(1)));
        printSeperator("Added Event: " + name);
    }

    private static void delete(String[] commandLine) throws TalkyException {
        if (commandLine.length != 2) {
            throw new TalkyException("Specify mark in the format: delete [index]");
        }
        int indexToChange = Integer.parseInt(commandLine[1]) - 1;
        if (indexToChange < 0 | indexToChange > userTasks.size()) {
            throw new TalkyException("delete index out of bounds");
        }
        Task removedTask = userTasks.get(indexToChange);
        userTasks.remove(indexToChange);
        saveDelete(indexToChange);
        printSeperator("I've deleted this task: " + removedTask.getName());
    }

    public static void initializeSave() {
        File saveData = new File(SAVE_PATH);
        System.out.println("Searching for Save Data: " + saveData.getAbsolutePath());
        try {
            saveData.getParentFile().mkdir();
            if (saveData.createNewFile()) {
                System.out.println("Save Data Does Not Exist");
                System.out.println("Save Data Created");
            } else {
                System.out.println("Located Save Data");
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
        try {
            Scanner sc = new Scanner(saveData);
            while(sc.hasNext()) {
                String[] taskDetails = sc.nextLine().split(" ");
                switch (taskDetails[0]) {
                case "ToDo":
                    ToDo newTodo = new ToDo(taskDetails[1]);
                    newTodo.setMark(Boolean.parseBoolean(taskDetails[2]));
                    userTasks.add(newTodo);
                    break;
                case "Deadline":
                    String saveBy = String.join(" ", taskDetails[2], taskDetails[3]);
                    ArrayList<LocalDateTime> deadlineDate = parseDate(saveBy);
                    LocalDateTime by = deadlineDate.get(0);
                    Deadline newDeadline = new Deadline(taskDetails[1], by);
                    newDeadline.setMark(Boolean.parseBoolean(taskDetails[3]));
                    userTasks.add(newDeadline);
                    break;
                case "Event":
                    String saveFrom = String.join(" ", taskDetails[2], taskDetails[3]);
                    String saveTo = String.join(" ", taskDetails[4], taskDetails[5]);
                    ArrayList<LocalDateTime> eventDates = parseDate(saveFrom, saveTo);
                    LocalDateTime from = eventDates.get(0);
                    LocalDateTime to = eventDates.get(1);
                    Event newEvent = new Event(taskDetails[1], from, to);
                    newEvent.setMark(Boolean.parseBoolean(taskDetails[6]));
                    userTasks.add(newEvent);
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException err) {
            err.printStackTrace();
        } catch (TalkyException err) {
            printSeperator(err.getMessage());
        }
    }

    private static void save(String dataLine) {
        try {
            FileWriter fw = new FileWriter(SAVE_PATH, true);
            fw.write(dataLine + System.lineSeparator());
            fw.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private static void saveMark(int index, String mark) {
        try {
            Path savePath = Paths.get(SAVE_PATH);
            List<String> dataLines = Files.readAllLines(savePath, StandardCharsets.UTF_8);
            String[] newData = dataLines.get(index).split(" ");
            newData[newData.length - 1] = mark;
            dataLines.set(index, String.join(" ", newData));
            Files.write(savePath, dataLines, StandardCharsets.UTF_8);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private static void saveDelete(int index) {
        try {
            Path savePath = Paths.get(SAVE_PATH);
            List<String> dataLines = Files.readAllLines(savePath, StandardCharsets.UTF_8);
            dataLines.remove(index);
            Files.write(savePath, dataLines, StandardCharsets.UTF_8);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private static ArrayList<LocalDateTime> parseDate(String... dates) throws TalkyException {
        ArrayList<LocalDateTime> formattedDates = new ArrayList<>();
        for (int i = 0; i < dates.length; i++) {
            LocalDate date;
            LocalTime time;
            String[] format = dates[0].split(" ");
            try {
                date = LocalDate.parse(format[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                time = LocalTime.parse(format[1], DateTimeFormatter.ofPattern("HHmm"));
                LocalDateTime formatted = LocalDateTime.of(date, time);
                formattedDates.add(formatted);
            } catch (DateTimeParseException err) {
                throw new TalkyException("DateTime Format invalid, input in the format dd/MM/yyy HHmm");
            }
        }
        return formattedDates;
    }
}
