import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> toDoList = new ArrayList<Task>();
    private static int counter = 1;
    private static final String FILE_PATH = "duke.txt";
    private static final String DATA_DIR = "data/";

    private static void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < toDoList.size(); i++) {
            int serial = i + 1;
            Task task = toDoList.get(i);
            System.out.println(serial + "." + task.toString());
        }
    }

    private static void mark(int index) throws InvalidIndexException {
        if (index - 1 < 0 || index - 1 >= toDoList.size()) {
            throw new InvalidIndexException("Invalid index provided, please provide proper index.");
        }
        Task task = toDoList.get(index - 1);
        task.markAsDone();
        replaceLineInFile(index - 1);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    private static void unmark(int index) throws InvalidIndexException {
        if (index - 1 < 0 || index - 1 >= toDoList.size()) {
            throw new InvalidIndexException("Invalid index provided, please provide proper index.");
        }
        Task task = toDoList.get(index - 1);
        task.unmarkAsUndone();
        replaceLineInFile(index - 1);
        System.out.println("Ok! I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    private static void delete(int index) throws InvalidIndexException {
        if (index - 1 < 0 || index - 1 >= toDoList.size()) {
            throw new InvalidIndexException("Invalid index provided, please provide proper index.");
        }
        Task task = toDoList.get(index - 1);
        toDoList.remove(index - 1);
        deleteLineFromFile(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        counter -= 1;
        System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
    }

    private static void writeToFile(Task task) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_DIR + FILE_PATH, true))) {
            String taskString = convertTaskToString(task);
            writer.write(taskString);
            writer.newLine();  // Add a newline after the task string
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String convertTaskToString(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            return "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + ((Event) task).getStart() + " | " + ((Event) task).getEnd();
        }
        return "";
    }

    public static void replaceLineInFile(int index) {
        File inputFile = new File(DATA_DIR + FILE_PATH);
        File tempFile = new File(DATA_DIR + "temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            int currentIdx = 0;

            while ((currentLine = reader.readLine()) != null) {
                if (currentIdx != index) {
                    writer.write(currentLine);
                    writer.newLine();
                } else {
                    String replacedLine = convertTaskToString(toDoList.get(index));
                    writer.write(replacedLine);
                    writer.newLine();
                }
                currentIdx++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        boolean isDeleted = inputFile.delete();
        boolean isRenamed = tempFile.renameTo(inputFile);
    }

    public static void deleteLineFromFile(int index) {
        File inputFile = new File(DATA_DIR + FILE_PATH);
        File tempFile = new File(DATA_DIR + "temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            int currentIdx = 0;

            while ((currentLine = reader.readLine()) != null) {
                if (currentIdx != index) {
                    writer.write(currentLine);
                    writer.newLine();
                }
                currentIdx++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        boolean isDeleted = inputFile.delete();
        boolean isRenamed = tempFile.renameTo(inputFile);
    }

    private static void loadFile(ArrayList<Task> toDoList) throws IOException, EmptyDescriptionException, EmptyDeadlineException, EmptyTimeException {
        try {
            File fileDir = new File(DATA_DIR);
            if (!fileDir.exists()) {
                boolean isDirCreated = fileDir.mkdir();
            }
            File file = new File(DATA_DIR + FILE_PATH);
            if (!file.exists()) {
                boolean isFileCreated = file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(DATA_DIR + FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                toDoList.add(convertStringToTask(line));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Task convertStringToTask(String line) throws EmptyDescriptionException, EmptyDeadlineException, EmptyTimeException {
        Task task = null;
        try {
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            if (taskType.equals("T")) {
                Todo todo = new Todo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                task = todo;
            } else if (taskType.equals("D")) {
                Deadline deadline = new Deadline(description, parts[3]);
                if (isDone) {
                    deadline.markAsDone();
                }
                task = deadline;
            } else {
                Event event = new Event(description, parts[3], parts[4]);
                if (isDone) {
                    event.markAsDone();
                }
                task = event;
            }
        } catch (EmptyDescriptionException e) {
            System.out.println(e.toString());
        } catch (EmptyDeadlineException e) {
            System.out.println(e.toString());
        } catch (EmptyTimeException e) {
            System.out.println(e.toString());
        }
        return task;
    }

    public static void main(String[] args) throws DukeException, EmptyDescriptionException, IOException, EmptyDeadlineException {
        loadFile(toDoList);
        greet();
        Scanner sc = new Scanner(System.in);
        String command;
        label:
        while (sc.hasNext()) {
            command = sc.nextLine();
            String[] getInstr = command.split(" ", 2);
            String instr = getInstr[0];
            switch (instr) {
                case "mark":
                    try {
                        int index;
                        if (getInstr.length <= 1) {
                            index = -1;
                        } else {
                            index = Integer.parseInt(getInstr[1]);
                        }
                        mark(index);
                    } catch (InvalidIndexException e) {
                        System.out.println(e.toString());
                    }
                    break;
                case "unmark":
                    try {
                        int index;
                        if (getInstr.length <= 1) {
                            index = -1;
                        } else {
                            index = Integer.parseInt(getInstr[1]);
                        }
                        unmark(index);
                    } catch (InvalidIndexException e) {
                        System.out.println(e.toString());
                    }
                    break;
                case "delete":
                    try {
                        int index;
                        if (getInstr.length <= 1) {
                            index = -1;
                        } else {
                            index = Integer.parseInt(getInstr[1]);
                        }
                        delete(index);
                    } catch (InvalidIndexException e) {
                        System.out.println(e.toString());
                    }
                    break;
                case "list":
                    printList();
                    break;
                case "bye":
                    break label;
                case "todo":
                    try {
                        Task task;
                        if (getInstr.length <= 1) {
                            task = new Todo(null);
                        } else {
                            task = new Todo(getInstr[1]);
                        }
                        toDoList.add(task);
                        writeToFile(task);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + counter + " tasks in the list.");
                        counter += 1;
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.toString());
                    }
                    break;
                case "deadline":
                    try {
                        Task task;
                        if (getInstr.length <= 1) {
                            task = new Deadline(null, null);
                        } else {
                            String[] getBy = getInstr[1].split(" /by ", 2);
                            if (getBy.length <= 1) {
                                task = new Deadline(getBy[0], null);
                            } else {
                                task = new Deadline(getBy[0], getBy[1]);
                            }
                        }
                        toDoList.add(task);
                        writeToFile(task);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + counter + " tasks in the list.");
                        counter += 1;
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.toString());
                    } catch (EmptyDeadlineException e) {
                        System.out.println(e.toString());
                    }
                    break;
                case "event":
                    try {
                        Task task;
                        if (getInstr.length <= 1) {
                            task = new Event(null, null, null);
                        } else {
                            String[] getTime = getInstr[1].split(" /", 3);
                            if (getTime.length == 3) {
                                String[] from = getTime[1].split(" ", 2);
                                String[] to = getTime[2].split(" ", 2);
                                task = new Event(getTime[0], from[1], to[1]);
                            } else {
                                task = new Event(getTime[0], null, null);
                            }
                        }
                        toDoList.add(task);
                        writeToFile(task);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + counter + " tasks in the list.");
                        counter += 1;
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.toString());
                    } catch (EmptyTimeException e) {
                        System.out.println(e.toString());
                    }
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        }
        sc.close();
        bye();
    }
}