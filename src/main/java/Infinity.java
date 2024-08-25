import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Infinity <T extends Task> {
    
    enum KnownCommands {
        BYE, 
        DEADLINE, 
        DELETE,
        EVENT, 
        LIST, 
        MARK, 
        SAVE,
        TODO
    }

    private static final String BOTNAME = "Infinity";
    private static final String BREAKLINE = 
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final int MAXSIZE = 100;
    private static final String FILEDIRPATH = "../../../data";
    private static final String FILENAME = "save-file.txt";
    private static final String FILEPATH = FILEDIRPATH + "/" + FILENAME;
    private static final String DELIMITER = "\0";

    private ArrayList<Task> tasks = new ArrayList<>(MAXSIZE);
    private int nextTaskIndex = 0;

    private static String botReply(String input) {
        return String.format("%s: %s", BOTNAME, input);
    }

    private <T extends Task> void addTask(T task) throws InfinityException {
        if (nextTaskIndex >= MAXSIZE) {
            throw new InfinityException(botReply(
                    "I'm sorry, but I can't remember more tasks."));
        }

        tasks.add(task);
        nextTaskIndex++;

        System.out.println(botReply(String.format("I've added '%s'", task)));
    }

    private void deleteTask(String currentInput) throws InfinityException {
        int taskIndex = 0;

        try {
            taskIndex = Integer.parseInt(currentInput);
        } catch (NumberFormatException e) {
            System.out.println(botReply("Hey! That's not a number"));
            System.out.println(BREAKLINE);
        }

        taskIndex--;

        if (taskIndex >= nextTaskIndex || taskIndex < 0) {
            throw new InfinityException(botReply(
                    "Hmmm, you seem to have chose a task that doesn't exist. Nice try :)"));
        } else {
            try {
                Task removedTask = tasks.remove(taskIndex);

                System.out.println(botReply(String.format(
                        "I've removed task %d:", taskIndex + 1)));
                System.out.println(removedTask.toString());
            } catch (IndexOutOfBoundsException e) {
                throw new InfinityException(botReply(
                        "Hmmm, you seem to have chose a task that doesn't exist. Nice try :)"));
            }
        }
    }

    private void markTask(String currentInput) throws InfinityException {
        String[] words = currentInput.split(" ");

        int taskIndex = Integer.parseInt(words[1]) - 1;
        if (taskIndex >= nextTaskIndex || taskIndex < 0) {
            throw new InfinityException(botReply(
                    "Hmmm, I can't find that task. Please try again."));
        }

        tasks.get(taskIndex).markAsDone();

        System.out.println(botReply(String.format(
                "I've marked task %d as done:", taskIndex + 1)));
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println(BREAKLINE);
    }

    private void listTasks() {
        if (nextTaskIndex == 0) {
            System.out.println(botReply("Task list is empty :("));
        } else {
            System.out.println(botReply(""));

            int i = 1;
            for (Task task : tasks) {
                System.err.println(String.format("    %d. %s", i, task.toString()));
                i++;
            }
        }
    }

    private void echo(String input) throws InfinityException {
        throw new InfinityException("Wait a minute, that's not something I recognise...");
    }

    private String combineDescription(String[] description, int startIndex) {
        StringBuilder combinedDescription = new StringBuilder();

        for (int i = startIndex; i < description.length; i++) {
            combinedDescription.append(description[i]);
            if (i != description.length - 1) {
                combinedDescription.append(DELIMITER);
            }
        }

        return combinedDescription.toString();
    }

    private void readFile() {
        if (doesFileExist()) {
            try {
                Scanner file = new Scanner(new File(FILEPATH));
                while (file.hasNextLine()) {

                    String currentLine = file.nextLine();

                    String[] words = currentLine.split(DELIMITER);
                    String taskType = words[0];
                    boolean isDone = words[1].equals("1");
                    Task task = null;

                    switch (taskType) {
                    case "T":
                        task = new ToDos(isDone, 
                                combineDescription(words, 2));
                        break;
                    case "D":
                        task = new Deadline(isDone, 
                                combineDescription(words, 3), words[2]);
                        break;
                    case "E":
                        task = new Event(isDone, 
                                combineDescription(words, 4), words[2], words[3]);
                        break;
                    default:
                        break;
                    }

                    if (task != null) {
                        tasks.add(task);
                        nextTaskIndex++;
                    }

                }
                file.close();
            } catch (IOException e) {
                System.out.println(botReply(
                        "I'm sorry, I'm a noob at this, I can't read the file, can you help me debug? " 
                        + e.getMessage()));
            } catch (InfinityException e) {
                System.out.println(botReply(
                        "I'm sorry, I'm a noob at this, I can't read the file, can you help me debug? " 
                        + e.getMessage()));
            } catch (NoSuchElementException e) {
                // Do nothing, likely means end of file
            }
        }
    }

    private void saveFile(ArrayList<Task> tasks) throws IOException {
        createFileIfNotExists();

        FileWriter file = new FileWriter(FILEPATH);

        for (Task task : tasks) {
            file.write(task.saveFileFormat(DELIMITER));
            file.write("\n");
        }

        file.close();

        System.out.println(botReply("Save Successful, Woohoo!"));
    }

    private boolean doesFileExist() {
        File file = new File(FILEPATH);
        return file.exists() && !file.isDirectory();
    }

    private void createFileIfNotExists() {
        try {
            if (!doesFileExist()) {
                Files.createDirectories(Paths.get(FILEDIRPATH));
                Files.createFile(Paths.get(FILEPATH));
            }
        } catch (IOException e) {
            System.out.println(botReply(
                    "I'm sorry, I'm a noob at this, I can't create the file, can you help me debug? " 
                    + e.getMessage()));
        }
    }

    public Infinity() {
        Scanner userInputs = new Scanner(System.in);

        System.out.println(BREAKLINE);
        System.out.println(botReply(String.format(
                "Hello, I'm a dummy bot called %s", BOTNAME)));
        System.out.println(botReply("What can I not do for you?"));
        System.out.println(BREAKLINE);

        readFile();

        while (true) {
            String currentInput = userInputs.nextLine();
            System.out.println(BREAKLINE);
            try {

                if (currentInput.equals(KnownCommands.BYE.toString().toLowerCase())) {

                    System.out.println(botReply(
                            "Well, if you are leaving, then I must be infinitely too dumb :("));
                    System.out.println(BREAKLINE);
                    userInputs.close();
                    System.exit(0);

                } else if (currentInput.equals(KnownCommands.LIST.toString().toLowerCase())) {

                    this.listTasks();
                    System.out.println(BREAKLINE);

                } else if (currentInput.startsWith(KnownCommands.MARK.toString().toLowerCase()) 
                        && currentInput.length() > 5) {

                    this.markTask(currentInput);

                } else if (currentInput.startsWith(KnownCommands.TODO.toString().toLowerCase()) 
                        && currentInput.length() > 5) {

                    this.addTask(new ToDos(currentInput.substring(5)));

                } else if (currentInput.startsWith(KnownCommands.DEADLINE.toString().toLowerCase()) 
                        && currentInput.length() > 9) {

                    this.addTask(new Deadline(currentInput.substring(9)));

                } else if (currentInput.startsWith(KnownCommands.EVENT.toString().toLowerCase()) 
                        && currentInput.length() > 6) {

                    this.addTask(new Event(currentInput.substring(6)));

                } else if (currentInput.startsWith(KnownCommands.DELETE.toString().toLowerCase()) 
                        && currentInput.length() > 7) {

                    this.deleteTask(currentInput.substring(7));

                } else if (currentInput.startsWith(KnownCommands.SAVE.toString().toLowerCase())) {
                    
                    try {
                        this.saveFile(tasks);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        System.out.println(botReply(
                                "I'm sorry, I'm a noob at this, I can't save the file, can you help me debug? " 
                                + e.getMessage()));
                    }

                } else {

                    this.echo(currentInput);

                }

            } catch (InfinityException e) {

                System.out.println(botReply(e.getMessage()));
                System.out.println(BREAKLINE);

            }
        }
    }

    public static void main(String[] args) {
        Infinity infinityBot = new Infinity();
    }

}
