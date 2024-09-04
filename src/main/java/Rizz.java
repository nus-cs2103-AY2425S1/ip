import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Rizz {
    private final Scanner input = new Scanner(System.in);
    private final ArrayList<Task> arrList = new ArrayList<>();
    Path dataFilePath = Path.of("./data/rizz.txt");
    boolean isNotDone = false;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");


    public void saveTasks(TaskList taskList) {
        String[] data = taskList.exportTasks();

        try {
            if (!Files.exists(dataFilePath)) {
                Files.createDirectories(dataFilePath.getParent());
                Files.createFile(dataFilePath);
            }

            StringBuilder builder = new StringBuilder();
            if (data.length > 0) {
                builder.append(data[0]);
                for (int i = 1; i < data.length; i++) {
                    builder.append("\n").append(data[i]);
                }
            }
            Files.writeString(dataFilePath, builder.toString());

        } catch (IOException e) {
            System.out.println("There was an error saving the tasks.");
        }
    }

    private void loadTasks() {
        if (Files.exists(dataFilePath)) {
            try {
                List<String> lines = Files.readAllLines(dataFilePath);
                for (int i = 0; i < lines.size(); i++) {
                    String line = lines.get(i);
                    Task task = decodeTasks(line);
                    if (task != null) {
                        arrList.add(task);
                    }
                }
                if (arrList.isEmpty()) {
                    System.out.println("No saved tasks found. Starting with an empty list.");
                } else {
                    System.out.println("Tasks loaded successfully!");
                }
            } catch (IOException e) {
                System.out.println("There was an error loading the tasks.");
            }
        } else {
            System.out.println("No saved tasks found. Starting with an empty list.");
        }
    }

    private Task decodeTasks(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String text = parts[2];

        switch (taskType) {
            case "T":
                return new ToDo(text, isDone);
            case "D":
                try {
                    LocalDateTime time = LocalDateTime.parse(parts[3], formatter);
                    return new Deadline(text, time, isDone);
                } catch (DateTimeParseException e) {
                    System.out.println("\t The date/time format is incorrect. Please use yyyy-MM-dd HHmm.");
                    return null;
                }
            case "E":
                String from = parts[3];
                String to = parts[4];
                return new Event(text, from, to, isDone);
            default:
                System.out.println("Unknown task type: " + taskType);
                return null;
        }
    }




    private void greet() {
        this.loadTasks();
        String str = "\t____________________________________________________________\n" +
                "\tHello! I'm Rizz\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n";
        System.out.println(str);
    }

    private void exit() {
        String str =  "\t____________________________________________________________\n" +
                "\tBye! Hope to see you again soon!\n" +
                "\t____________________________________________________________\n";
        System.out.println(str);
    }

    private void outputList() {
        if (arrList.isEmpty()) {
            System.out.println("\tNo items in the list.\n");
        } else {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < arrList.size(); i++) {
                strBuilder.append("\t").append(i + 1).append(". ").append(arrList.get(i)).append("\n");
            }
            String str = "\t____________________________________________________________\n" +
                    "\tHere are the tasks in your list:\n" + strBuilder +
                    "\t____________________________________________________________\n";
            System.out.println(str);
        }
    }

    private void markTask(int index) {
            Task task = arrList.get(index - 1);
            task.markAsDone();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t" + task + "\n");
    }

    private void unmarkTask(int index) {
            Task task = arrList.get(index - 1);
            task.unmarkAsDone();
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t\t" + task + "\n");
    }

    private void caseCheck() {
        while (true) {
            if (!input.hasNextLine()) {
                System.out.println("\tNo more commands in input.\n");
                break;
            }
            String textInput = this.input.nextLine().trim();

            if (textInput.equals("bye")) {
                this.exit();
                break;

            } else if (textInput.equals("list")) {
                this.outputList();

            } else if (textInput.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(textInput.split(" ")[1].trim());
                    this.markTask(index);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("\t The mark command is missing an index or has an invalid index.");
                }

            } else if (textInput.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(textInput.split(" ")[1].trim());
                    this.unmarkTask(index);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("\t The unmark command is missing an index or has an invalid index.");
                }

            } else if (textInput.startsWith("todo ")) {
                String description = textInput.substring(5).trim();
                if (description.isEmpty()) {
                    System.out.println("\t The todo details cannot be empty / incorrectly formatted.");
                } else {
                    this.addToDo(description);
                }

            } else if (textInput.startsWith("deadline ")) {

                try {
                    String[] parts = textInput.substring(9).split("/by", 2);
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                        System.out.println("\t The deadline's details cannot be empty / incorrectly formatted.");
                    } else {
                        String description = parts[0].trim();
                        String by = parts[1].trim();

                        LocalDateTime byDateTime = LocalDateTime.parse(by, formatter);
                        this.addDeadline(description, byDateTime);
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("\t The date/time format is incorrect. Please use yyyy-MM-dd HHmm.");
                }

            } else if (textInput.startsWith("event ")) {
                String[] parts = textInput.substring(6).split("/from|/to", 3);
                if (parts.length < 3 || parts[0].trim().isEmpty()) {
                    System.out.println("\t The event's details cannot be empty / incorrectly formatted.");
                } else {
                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    this.addEvent(description, from, to);
                }
            } else if (textInput.startsWith("delete ")) {
                    try {
                        int index = Integer.parseInt(textInput.split(" ")[1].trim());
                        this.deleteTask(index);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("\t The deletion command is empty / incorrectly formatted.");
                    }
            } else {
                System.out.println("\t I'm sorry, but I don't know what that means :-(");
            }

            this.saveTasks(new TaskList(arrList));
        }
    }

    private void addEvent(String text, String from, String to) {
        this.arrList.add(new Event(text, from, to, isNotDone));
        System.out.println("\tadded event: " + text);
        System.out.printf("\tYou have %d tasks in the list.\n", this.arrList.size());
    }

    private void addToDo(String text) {
        this.arrList.add(new ToDo(text, isNotDone));
        System.out.println("\tadded todo: " + text);
        System.out.printf("\tYou have %d tasks in the list.\n", this.arrList.size());
    }

    private void addDeadline(String text, LocalDateTime by) {
        this.arrList.add(new Deadline(text, by, isNotDone));
        System.out.println("\tadded deadline: " + text);
        System.out.printf("\tYou have %d tasks in the list.\n", this.arrList.size());
    }

    private void deleteTask(int index) {
            Task task = arrList.remove(index - 1);
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t " + task);
            System.out.printf("\tNow you have %d tasks in the list.\n\n", arrList.size());
    }

    public static void main(String[] args) {
        Rizz rizz = new Rizz();
        rizz.greet();
        rizz.caseCheck();
    }
}
