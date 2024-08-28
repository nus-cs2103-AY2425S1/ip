import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The DGPT class represents a simple task management system.
 * It allows users to add tasks, mark them as done, unmark them, and view the list of tasks.
 */
public class DGPT {

    private final List<Task> listOfTasks;

    /**
     * Constructs a DGPT instance with an empty task list.
     */
    public DGPT() {
        listOfTasks = new ArrayList<>();
    }

    private void parseInput(String input) throws IncorrectInputException, TaskNotFoundException {
        String[] parts = input.split(" ", 2);

        switch (parts[0]) {
        case "list" -> {
            if (parts.length == 1) {
                showList();
            } else {
                throw new IncorrectInputException("OOPS!!! You should not have anything after your request. " +
                        "(e.g. \"list\")");
            }
        }
        case "mark" -> {
            if (parts.length == 2) {
                markTask(Integer.parseInt(parts[1]) - 1);
            } else {
                throw new IncorrectInputException("OOPS!!! You should have only 1 number after your request. " +
                        "(e.g. \"mark 1\")");
            }
        }
        case "unmark" -> {
            if (parts.length == 2) {
                unmarkTask(Integer.parseInt(parts[1]) - 1);
            } else {
                throw new IncorrectInputException("OOPS!!! You should have only 1 number after your request. " +
                        "(e.g. \"unmark 1\")");
            }
        }
        case "todo" -> {
            if (parts.length == 2) {
                addToDoToList(parts[1]);
            } else {
                throw new IncorrectInputException("OOPS!!! You should have a description after your request. " +
                        "(e.g. \"todo your_description\")");
            }
        }
        case "deadline" -> {
            if (parts.length == 2) {
                addDeadlineToList(parts[1]);
            } else {
                throw new IncorrectInputException("OOPS!!! You should have a description after your request. " +
                        "(e.g. \"todo your_description /by your_deadline\")");
            }
        }
        case "event" -> {
            if (parts.length == 2) {
                addEventToList(parts[1]);
            } else {
                throw new IncorrectInputException("OOPS!!! You should have a description after your request. " +
                        "(e.g. \"todo your_description /from your_start_time /to your_end_time\")");
            }
        }
        case "delete" -> {
            if (parts.length == 2) {
                deleteTask(Integer.parseInt(parts[1]) - 1);
            } else {
                throw new IncorrectInputException("OOPS!!! You should have only 1 number after your request. " +
                        "(e.g. \"delete 1\")");
            }
        }
        default -> throw new TaskNotFoundException("OOPS!!! I do not recognise that request. These are the " +
                "following requests that are supported:\n" +
                "-list\n" +
                "-mark\n" +
                "-unmark\n" +
                "-todo\n" +
                "-deadline\n" +
                "-event\n" +
                "-delete\n" +
                "-bye");
        }
    }

    private void addToDoToList(String text) {
        Task newTask = new ToDo(text);
        this.listOfTasks.add(newTask);
        System.out.println("-----------------------");
        System.out.println("DGPT> Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.printf("Now you have %d tasks in the list.%n", this.listOfTasks.size());
        System.out.println("-----------------------");
    }

    private void addDeadlineToList(String text) {
        String[] parts = text.split(" /by ");
        Task newTask = new Deadline(parts[0], parts[1]);
        this.listOfTasks.add(newTask);
        System.out.println("-----------------------");
        System.out.println("DGPT> Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.printf("Now you have %d tasks in the list.%n", this.listOfTasks.size());
        System.out.println("-----------------------");
    }

    private void addEventToList(String text) {
        String[] parts = text.split(" /");
        Task newTask = new Event(parts[0],parts[1].substring(5), parts[2].substring(3));
        this.listOfTasks.add(newTask);
        System.out.println("-----------------------");
        System.out.println("DGPT> Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.printf("Now you have %d tasks in the list.%n", this.listOfTasks.size());
        System.out.println("-----------------------");
    }

    private void showList() {
        int numOfItems = this.listOfTasks.size();
        System.out.println("-----------------------");
        System.out.println("DGPT> Here are the tasks in your list:");
        for (int i = 1; i <= numOfItems; i++) {
            Task currTask = this.listOfTasks.get(i - 1);
            System.out.println(i + "." + currTask.toString());
        }
        System.out.println("-----------------------");
    }

    private void markTask(int index) {
        this.listOfTasks.get(index).mark();
        System.out.println("-----------------------");
        System.out.println("DGPT> Nice! I've marked this task as done: ");
        System.out.println(this.listOfTasks.get(index).toString());
        System.out.println("-----------------------");
    }

    private void unmarkTask(int index) {
        this.listOfTasks.get(index).unmark();
        System.out.println("-----------------------");
        System.out.println("DGPT> OK, I've marked this task as not done yet:");
        System.out.println(this.listOfTasks.get(index).toString());
        System.out.println("-----------------------");
    }

    private void deleteTask(int index) {
        System.out.println("-----------------------");
        System.out.println("DGPT> OK, I've removed this task from the list:");
        System.out.println(this.listOfTasks.get(index).toString());
        this.listOfTasks.remove(index);
        System.out.printf("Now you have %d tasks in the list.%n", this.listOfTasks.size());
        System.out.println("-----------------------");
    }

    private void loadExistingFile(String filePath) throws IOException {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String curr = s.nextLine();
                String[] parts = curr.split(" \\| ");
                switch (parts[0]) {
                case "T" -> {
                    ToDo i = new ToDo(parts[2]);
                    if (parts[1].equals("1")) {
                        i.mark();
                    }
                    this.listOfTasks.add(i);

                }
                case "D" -> {
                    Deadline i = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        i.mark();
                    }
                    this.listOfTasks.add(i);
                }
                case "E" -> {
                    Event i = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1")) {
                        i.mark();
                    }
                    this.listOfTasks.add(i);
                }
                default -> throw new IOException("File format is invalid");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not load existing data");
        }
    }

    private void saveToFile(String filePath) throws IOException {
        File file = new File(filePath);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + parentDir.getAbsolutePath());
            }
        }

        try {
            FileWriter fw = new FileWriter(filePath);
            StringBuilder s = new StringBuilder();
            for (Task t : listOfTasks) {
                if (t instanceof ToDo) {
                    s.append("T | ")
                            .append(t.getIsDone() ? "1 | " : "0 | ")
                            .append(t.getDescription())
                            .append("\n");
                } else if (t instanceof Deadline) {
                    s.append("D | ")
                            .append(t.getIsDone() ? "1 | " : "0 | ")
                            .append(t.getDescription())
                            .append(" | ")
                            .append(((Deadline) t).getDueDate())
                            .append("\n");
                } else if (t instanceof Event) {
                    s.append("E | ")
                            .append(t.getIsDone() ? "1 | " : "0 | ")
                            .append(t.getDescription())
                            .append(" | ")
                            .append(((Event) t).getFromDate())
                            .append(" | ")
                            .append(((Event) t).getToDate())
                            .append("\n");
                }
            }
            fw.write(s.toString());
            fw.close();
        } catch (IOException e) {
            throw new IOException("An error occurred while writing to the file: " + e.getMessage(), e);
        }
    }

    /**
     * Main method to start the program and interact with the user.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {

        DGPT dgpt = new DGPT();

        String filePath = "./data/dgpt.txt";


        // loading in existing hard drive data
        System.out.println("-----------------------");
        System.out.println("DGPT> Hello! I'm DGPT");
        try {
            dgpt.loadExistingFile(filePath);
            dgpt.showList();
        } catch (IOException e) {
            System.out.println("-----------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------");
        }




        Scanner scanner = new Scanner(System.in);
        boolean isActive = true;
        String input;

        // bot is ready for user
        while (isActive) {
            System.out.print("User> ");
            input = scanner.nextLine();
            if (input.equals("bye")) {
                isActive = false;
            } else {
                try {
                    dgpt.parseInput(input);
                } catch (TaskNotFoundException | IncorrectInputException e) {
                    System.out.println("-----------------------");
                    System.out.println(e.getMessage());
                    System.out.println("-----------------------");
                }
            }
        }

        scanner.close();

        try {
            dgpt.saveToFile(filePath);
        } catch (IOException e) {
            System.out.println("-----------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------");
        }

        // Closing Message
        System.out.println("-----------------------");
        System.out.println("DGPT> Bye. Hope to see you again soon!");
        System.out.println("-----------------------");
    }
}
