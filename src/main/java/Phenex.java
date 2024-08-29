import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;


public class Phenex {
    private final String line = "\t____________________________________________________________\n";
    private final ArrayList<Task> tasks;
    private final String logo = "  _____    _    _   ______   _   _   ______  __   __\n"
            + " |  __ \\  | |  | | |  ____| | \\ | | |  ____| \\ \\ / /\n"
            + " | |__) | | |__| | | |__    |  \\| | | |__     \\ V /\n"
            + " | |      | |  | | | |____  | |\\  | | |____   / . \\\n"
            + " |_|      |_|  |_| |______| |_| \\_| |______| /_/ \\_\\\n";
    private final String greetMsg = "Hello! I'm\n"
            + logo
            + "Your favourite solid gold mobile suit!\n"
            + line
            + "\tWhat can I do for you?\n"
            + line;
    private final String farewellMsg = "\t Goodbye. Extinguish the Zeon forces on your way out!\n" + line;
    private final Path filePath;

    public enum TaskType {
        TASK_TODO, TASK_DEADLINE, TASK_EVENT;
    }

    public Phenex() {
        this.tasks = new ArrayList<>();
        String home = System.getProperty("user.home");
        this.filePath = Paths.get(home, "Downloads", "CS2103T_AY2425", "iP", "data", "phenex.txt");
        try {
            this.readMemory();
        } catch (FileNotFoundException e) {
            System.out.println("404 Error, Memory Not Found: " + e.getMessage());
        }
    }

    public void greet() {
        System.out.print(greetMsg);
    }

    public void sayFarewell() {
        System.out.println(farewellMsg);
    }

    public void printLine() {
        System.out.print(line);
    }

    public void printTasks() {
        int size = this.tasks.size();
        if (size == 0) {
            System.out.println("\t No scheduled missions. Rest up for the next battle, soldier!");
            return;
        }

        System.out.println("\t Outstanding missions:");
        for (int i = 0; i < size; i++) {
            String row = "\t "
                    + (i + 1)
                    + "."
                    + tasks.get(i);
            System.out.println(row);
        }
    }

    public void markTaskCompleted(int idx) throws PhenexException {
        if (idx >= this.tasks.size()) {
            throw new PhenexException("\t Invalid input, no such mission!");
        } else {
            System.out.println("\t Mission marked as complete. Good job, soldier!");
            Task taskToMark = this.tasks.get(idx);
            taskToMark.setCompleted();
            System.out.println("\t\t" + taskToMark);
        }
    }

    public void markTaskIncomplete(int idx) throws PhenexException {
        if (idx >= this.tasks.size()) {
            throw new PhenexException("\t Invalid input, no such mission!");
        } else {
            System.out.println("\t Mission marked as incomplete.");
            Task taskToUnmark = this.tasks.get(idx);
            taskToUnmark.setUncompleted();
            System.out.println("\t\t" + taskToUnmark);
        }
    }

    public void addTaskFromMemory(String data) throws PhenexException {
        String[] taskDetails = data.split(", ");

        if (taskDetails.length <= 1) {
            throw new PhenexException("Error, corrupted memory.");
        }

        String symbol = taskDetails[0];
        String status = taskDetails[1];
        String name;
        Task taskToAdd;

        switch (symbol) {
        case "T":
            if (taskDetails.length != 3) {
                throw new PhenexException("Error, corrupted memory.");
            }
            name = taskDetails[2];
            taskToAdd = new ToDo(name);
            break;
        case "D":
            if (taskDetails.length != 4) {
                throw new PhenexException("Error, corrupted memory.");
            }
            name = taskDetails[2];
            String byDate = taskDetails[3];
            taskToAdd = new Deadline(name, byDate);
            break;
        case "E":
            if (taskDetails.length != 5) {
                throw new PhenexException("Error, corrupted memory.");
            }
            name = taskDetails[2];
            String from = taskDetails[3];
            String to = taskDetails[4];
            taskToAdd = new Event(name, from, to);
            break;
        default:
            throw new PhenexException("Error, corrupted memory");
        }

        if (status.equals("1")) {
            taskToAdd.setCompleted();
        }
        this.tasks.add(taskToAdd);
    }

    public void storeTasksToMemory() {

        // write into file from tasks
        try {
            FileWriter fileWriter = new FileWriter(this.filePath.toString());
            for (Task task : this.tasks) {
                String line = task.parseTaskInfo();
                fileWriter.write(line);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTask(Matcher matcher, TaskType tt) throws PhenexException {
        String taskName = matcher.group(1);
        String emptyNameRegex = "\\s*";
        Pattern emptyNamePattern = Pattern.compile(emptyNameRegex);
        Matcher emptyNameMatcher = emptyNamePattern.matcher(taskName);

        Task taskToAdd;

        switch (tt) {
        case TASK_TODO:
            if (emptyNameMatcher.matches()) {
                throw new PhenexException("Error, invalid todo name");
            }
            taskToAdd = new ToDo(taskName);
            break;
        case TASK_DEADLINE:
            if (emptyNameMatcher.matches()) {
                throw new PhenexException("Error, invalid deadline name");
            }
            String deadlineBy = matcher.group(2);
            taskToAdd = new Deadline(taskName, deadlineBy);
            break;
        case TASK_EVENT:
            if (emptyNameMatcher.matches()) {
                throw new PhenexException("Error, invalid event name");
            }
            String eventFrom = matcher.group(2);
            String eventTo = matcher.group(3);
            taskToAdd = new Event(taskName, eventFrom, eventTo);
            break;
        default:
            System.out.println("Unknown input");
            return;
        }

        this.tasks.add(taskToAdd);
        this.printTaskAdded(taskToAdd);
        this.storeInMemory(taskToAdd.parseTaskInfo());
    }

    public void deleteTask(int idx) throws PhenexException {
        if (idx >= this.tasks.size()) {
            throw new PhenexException("Error, no such mission exists");
        }
        Task taskToDelete = this.tasks.get(idx);
        this.tasks.remove(idx);
        System.out.println("\t OK. Mission aborted, retreat!");
        System.out.println("\t  " + taskToDelete);
        System.out.println("\t " + this.tasks.size() + " missions remaining. Destroy the enemy!");
    }

    public void printTaskAdded(Task task) {
        System.out.println("\t Mission " + task.name + " added:");
        System.out.println("\t   " + task);
        System.out.println("\t Total upcoming missions: " + this.tasks.size());
    }

    public void validateFilePath() throws PhenexException {
        boolean filePathExists = Files.exists(this.filePath);
        if (!filePathExists) {
            throw new PhenexException("404 Error: Memory not found! Abort.");
        }
    }

    protected void readMemory() throws FileNotFoundException {
        File memFile = new File(this.filePath.toString());
        Scanner scanner = new Scanner(memFile);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            try {
                addTaskFromMemory(data);
            } catch (PhenexException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    protected void storeInMemory(String memoryToAdd) {
        String pathString = this.filePath.toString();
        try {
            FileWriter fw = new FileWriter(pathString, true);
            fw.write(memoryToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Phenex p = new Phenex();
        p.greet();

        if (!Files.exists(p.filePath)) {
            // create file if it doesn't exist
            try {
                Files.createFile(p.filePath);
                System.out.println("Memory initialised.");
            } catch (IOException e) {
                System.out.println("Error, unable to initialise memory: " + e.getMessage());
            }
        }

        // check if file path is valid
        try {
            p.validateFilePath();
        } catch (PhenexException e) {
            System.out.println(e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String userInput;

        // regex's for commands which tell Phenex to do actions
        String terminatingRegex = "(?i)bye\\s*$";
        String listRegex = "(?i)list\\s*$";
        String markRegex = "^mark \\d+\\s*$";
        String unmarkRegex = "^unmark \\d+\\s*$";
        String deleteRegex = "^delete \\d+\\s*$";

        // regex's for commands which tell Phenex to add Task
        String todoRegex = "^(?i)todo (.+)";
        String deadlineRegex = "^(?i)deadline (.+) /by (.+)";
        String eventRegex = "^(?i)event (.+) /from (.+) /to (.+)$";

        // Patterns and Matchers for each regex
        Pattern terminatingPattern = Pattern.compile(terminatingRegex);
        Pattern listPattern = Pattern.compile(listRegex);
        Pattern markPattern = Pattern.compile(markRegex);
        Pattern unmarkPattern = Pattern.compile(unmarkRegex);
        Pattern todoPattern = Pattern.compile(todoRegex);
        Pattern deadlinePattern = Pattern.compile(deadlineRegex);
        Pattern eventPattern = Pattern.compile(eventRegex);
        Pattern deletePattern = Pattern.compile(deleteRegex);

        Matcher terminatingMatcher;
        Matcher listMatcher;
        Matcher markMatcher;
        Matcher unmarkMatcher;
        Matcher todoMatcher;
        Matcher deadlineMatcher;
        Matcher eventMatcher;
        Matcher deleteMatcher;

        while (true) {
            // scan inputs
            userInput = scanner.nextLine();
            terminatingMatcher = terminatingPattern.matcher(userInput);

            // detect terminating string
            if (terminatingMatcher.find()) {
                break;
            }

            // Matchers to detect which input
            listMatcher = listPattern.matcher(userInput);
            markMatcher = markPattern.matcher(userInput);
            unmarkMatcher = unmarkPattern.matcher(userInput);
            todoMatcher = todoPattern.matcher(userInput);
            deadlineMatcher = deadlinePattern.matcher(userInput);
            eventMatcher = eventPattern.matcher(userInput);
            deleteMatcher = deletePattern.matcher(userInput);

            p.printLine();

            if (listMatcher.find()) {
                p.printTasks();
            } else if (markMatcher.find()) {
                // mark task as done
                int taskNumber = Integer.parseInt(markMatcher.group().substring(5));
                int idx = taskNumber - 1;
                try {
                    p.markTaskCompleted(idx);
                } catch (PhenexException e) {
                    System.out.println("WARNING! SYSTEM OVERLOAD " + e.getMessage());
                }
            } else if (unmarkMatcher.find()) {
                // unmark task as done
                int taskNumber = Integer.parseInt(unmarkMatcher.group().substring(7));
                int idx = taskNumber - 1;
                try {
                    p.markTaskIncomplete(idx);
                } catch (PhenexException e) {
                    System.out.println("WARNING! SYSTEM OVERLOAD " + e.getMessage());
                }
            } else if (todoMatcher.matches()) {
                try {
                    p.addTask(todoMatcher, TaskType.TASK_TODO);
                } catch (PhenexException e) {
                    System.out.println("WARNING! SYSTEM OVERLOAD " + e.getMessage());
                }
            } else if (deadlineMatcher.matches()) {
                try {
                    p.addTask(deadlineMatcher, TaskType.TASK_DEADLINE);
                } catch (PhenexException e) {
                    System.out.println("WARNING! SYSTEM OVERLOAD " + e.getMessage());
                }
            } else if (eventMatcher.matches()) {
                try {
                    p.addTask(eventMatcher, TaskType.TASK_EVENT);
                } catch (PhenexException e) {
                    System.out.println("WARNING! SYSTEM OVERLOAD " + e.getMessage());
                }
            } else if (deleteMatcher.matches()) {
                int idx = Integer.parseInt(deleteMatcher.group().substring(7)) - 1;
                try {
                    p.deleteTask(idx);
                } catch (PhenexException e) {
                    System.out.println("WARNING! SYSTEM OVERLOAD " + e.getMessage());
                }
            } else {
                System.out.println("\tError, invalid input.");
            }

            p.printLine();
        }

        p.storeTasksToMemory();
        scanner.close();
        p.sayFarewell();
    }
}
