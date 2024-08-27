import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

public class Hamyo {

    private final UI ui;
    private boolean active;
    private final ArrayList<Task> tasks;
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Hamyo() {
        this.ui = new UI();
        this.active = true;
        this.tasks = new ArrayList<Task>();

        this.ui.greet();
    }

    public static void main(String[] args) {
        Hamyo hamyo = new Hamyo();
        Scanner scanner = new Scanner(System.in);
        try {
            loadData(hamyo);
            while (hamyo.active) {
                try {
                    String command = scanner.nextLine();
                    String commandType = command.split(" ")[0];
                    String commandFields = command.substring(commandType.length());
                    switch (commandType) {
                    case "todo":
                        add(hamyo, TaskType.TODO, commandFields);
                        break;
                    case "deadline":
                        add(hamyo, TaskType.DEADLINE, commandFields);
                        break;
                    case "event":
                        add(hamyo, TaskType.EVENT, commandFields);
                        break;
                    case "list":
                        listTasks(hamyo);
                        break;
                    case "listDate":
                        listTasksByDate(hamyo, commandFields);
                        break;
                    case "mark":
                        mark(hamyo, commandFields);
                        break;
                    case "unmark":
                        unmark(hamyo, commandFields);
                        break;
                    case "delete":
                        delete(hamyo, commandFields);
                        break;
                    case "bye":
                        hamyo.active = false;
                        hamyo.ui.terminate();
                        break;
                    default:
                        throw new HamyoException("Invalid Command!");
                    }
                    saveData(hamyo);
                } catch (HamyoException e) {
                    System.out.println(e.toString());
                    UI.printLine();
                }
            }
            scanner.close();
        } catch (HamyoException e) {
            System.out.println(e.toString());
            UI.printLine();
        }
    }

    public static void add (Hamyo hamyo, TaskType taskType, String task) throws HamyoException {
        if (taskType.equals(TaskType.TODO)) {
            if (task.length() <= 1) {
                throw new HamyoException("Usage: todo [task description]");
            }
            hamyo.tasks.add(new ToDo(new String[]{task.substring(1)}));
        } else if (taskType.equals(TaskType.DEADLINE)) {
            if (task.length() <= 1) {
                throw new HamyoException("Usage: deadline [task description] /by [deadline]");
            }
            String[] split = task.substring(1).split(" /by ");
            if (split.length != 2) {
                throw new HamyoException("Usage: deadline [task description] /by [deadline]");
            }
            hamyo.tasks.add(new Deadline(split));
        } else if (taskType.equals(TaskType.EVENT)) {
            if (task.length() <= 1) {
                throw new HamyoException("Usage: event [task description] /from [start timestamp] /to [end timestamp]");
            }
            String[] split = task.substring(1).split(" /from | /to ");
            if (split.length != 3) {
                throw new HamyoException("Usage: event [task description] /from [start timestamp] /to [end timestamp]");
            }
            hamyo.tasks.add(new Event(split));
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(hamyo.tasks.get(hamyo.tasks.size() - 1).toString());
        System.out.printf("There are %d tasks in the list now.\n", hamyo.tasks.size());
        UI.printLine();
    }

    public static void listTasks(Hamyo hamyo) throws HamyoException {
        System.out.println("These are your tasks:");
        for (int i = 0; i < hamyo.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + hamyo.tasks.get(i).toString());
        }
        UI.printLine();
    }
    public static void listTasksByDate(Hamyo hamyo, String str) throws HamyoException {
        try {
            LocalDate date = LocalDate.parse(str.substring(1));
            System.out.println("These are your tasks on " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ".");
            int counter = 1;
            for (int i = 0; i < hamyo.tasks.size(); i++) {
                if (hamyo.tasks.get(i) instanceof Deadline || hamyo.tasks.get(i) instanceof Event) {
                    if (hamyo.tasks.get(i).occursToday(date)) {
                        System.out.println(counter++ + ". " + hamyo.tasks.get(i).toString());
                    }
                }
            }
            UI.printLine();
        } catch (Exception e) {
            throw new HamyoException("Usage: listDate yyyy-MM-dd.");
        }
    }

    public static void mark(Hamyo hamyo, String str) throws HamyoException {
        try {
            if (str.length() <= 1) {
                throw new HamyoException("Usage: mark [index]");
            }
            int index = Integer.parseInt(str.substring(1)) - 1;
            if (index < 0 || index >= hamyo.tasks.size()) {
                throw new HamyoException("Usage: mark [index]");
            }
            hamyo.tasks.get(index).mark();
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: mark [index]");
        }
    }

    public static void unmark(Hamyo hamyo, String str) throws HamyoException {
        try {
            if (str.length() <= 1) {
                throw new HamyoException("Usage: unmark [index]");
            }
            int index = Integer.parseInt(str.substring(1)) - 1;
            if (index < 0 || index >= hamyo.tasks.size()) {
                throw new HamyoException("Usage: unmark [index]");
            }
            hamyo.tasks.get(index).unmark();
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: unmark [index]");
        }
    }

    public static void delete(Hamyo hamyo, String str) throws HamyoException {
        try {
            if (str.length() <= 1) {
                throw new HamyoException("Usage: delete [index]");
            }
            int index = Integer.parseInt(str.substring(1)) - 1;
            if (index < 0 || index >= hamyo.tasks.size()) {
                throw new HamyoException("Usage: delete [index]");
            }
            System.out.println("Noted. I've removed this task:");
            System.out.println(hamyo.tasks.get(index).toString());
            hamyo.tasks.remove(index);
            System.out.printf("There are %d tasks in the list now.\n", hamyo.tasks.size());
            UI.printLine();
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: delete [index]");
        }
    }

    public static void loadData(Hamyo hamyo) throws HamyoException {
        try {
            File savedTasks = new File("./savedTasks.txt");
            if (!savedTasks.exists()) {
                savedTasks.createNewFile();
            }
            Scanner scannedTasks = new Scanner(savedTasks);
            int currTask = 0;
            while (scannedTasks.hasNext()) {
                currTask++;
                String[] task = scannedTasks.nextLine().split(" \\| ");
                switch (task[0]) {
                case "T":
                    hamyo.tasks.add(new ToDo(new String[]{task[2]}));
                    break;
                case "D":
                    hamyo.tasks.add(new Deadline(new String[]{task[2], task[3]}));
                    break;
                case "E":
                    hamyo.tasks.add(new Event(new String[]{task[2], task[3], task[4]}));
                    break;
                default:
                    throw new HamyoException("Invalid case " + task[0] + ".");
                }
                switch (task[1]) {
                case "1":
                    mark(hamyo, " " + currTask);
                    break;
                case "0":
                    break;
                default:
                    throw new HamyoException("Invalid boolean " + task[1] + ".");
            }
            }
        } catch (HamyoException e) {
            throw new HamyoException("Possible File Corruption. " + e.getMessage());
        } catch (IOException e) {
            throw new HamyoException(e.getMessage());
        }
    }

    public static void saveData(Hamyo hamyo) throws HamyoException {
        try {
            FileWriter fw = new FileWriter("./savedTasks.txt");
            StringBuilder newData = new StringBuilder();
            for (Task task : hamyo.tasks) {
                newData.append(task.toFileFormat()).append(System.lineSeparator());
            }
            //System.out.println(newData.toString());
            fw.write(newData.toString());
            fw.close();
        } catch (IOException e) {
            throw new HamyoException(e.getMessage());
        }
    }
}
