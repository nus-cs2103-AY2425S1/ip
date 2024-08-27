package dude;

import dude.exception.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Dude {
    private static final String BOT_NAME = "Dude";
    private static final String LINE = "____________________________________________________________";
    private static final String FILE_PATH = "./data/dude.txt";
    private Scanner scanner;
    private String input;
    private ArrayList<Task> tasks;
    private int tasksSize;
    private boolean isRunning;

    public Dude() {
        scanner = new Scanner(System.in);
        input = "";
        tasks = loadData();
        tasksSize = tasks.size();
        isRunning = true;
    }

    public void start() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        while (isRunning) {
            try {
                readAndReact();
            } catch (DudeException e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            }
        }
    }

    public void readAndReact() throws DudeException {
        input = scanner.nextLine().strip();
        String[] splitInput = input.split(" ", 2);
        String taskDes = splitInput.length < 2 ? "" : splitInput[1];

        if (splitInput[0].isEmpty()) {
            throw new DudeNullCommandException();
        } else if (splitInput[0].equals("bye")) {
            exit();
        } else if (splitInput[0].equals("list")) {
            list();
        } else if (splitInput[0].equals("mark")) {
            mark(taskDes);
        } else if (splitInput[0].equals("unmark")) {
            unmark(taskDes);
        } else if (splitInput[0].equals("todo")) {
            addToDo(taskDes);
        } else if (splitInput[0].equals("deadline")) {
            addDeadline(taskDes);
        } else if (splitInput[0].equals("event")) {
            addEvent(taskDes);
        } else if (splitInput[0].equals("delete")) {
            delete(taskDes);
        } else {
            throw new DudeInvalidCommandException(splitInput[0]);
        }
    }

    public void addToDo(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("todo");
        } else {
            Task newTask = new ToDo(taskDes);
            this.tasks.add(newTask);
            tasksSize++;

            System.out.println(LINE);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.println("Now you have " + tasksSize + " tasks in the list.");
            System.out.println(LINE);
        }
    }

    public void addDeadline(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("deadline");
        } else {
            String[] splitDes = taskDes.split("/", 2);
            if (splitDes.length < 2) {
                throw new DudeNullDateTimeException("deadline");
            }

            String[] splitBy = splitDes[1].split(" ", 2);
            if (!splitBy[0].equals("by")) {
                throw new DudeInvalidArgumentException("deadline", splitBy[0], "by");
            } else if (splitBy.length == 1) {
                throw new DudeNullDateTimeException("deadline");
            }

            Task newTask = new Deadline(splitDes[0].strip(), splitBy[1].strip());
            this.tasks.add(newTask);
            tasksSize++;

            System.out.println(LINE);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.println("Now you have " + tasksSize + " tasks in the list.");
            System.out.println(LINE);
        }
    }

    public void addEvent(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("event");
        } else {
            String[] splitDes = taskDes.split("/", 3);
            if (splitDes.length < 3) {
                throw new DudeNullDateTimeException("event");
            }

            String[] splitFrom = splitDes[1].split(" ", 2);
            if (!splitFrom[0].equals("from")) {
                throw new DudeInvalidArgumentException("event", splitFrom[0], "from");
            } else if (splitFrom.length == 1) {
                throw new DudeNullDateTimeException("event");
            }

            String[] splitTo = splitDes[2].split(" ", 2);
            if (!splitTo[0].equals("to")) {
                throw new DudeInvalidArgumentException("event", splitTo[0], "to");
            } else if (splitTo.length == 1) {
                throw new DudeNullDateTimeException("event");
            }

            Task newTask = new Event(splitDes[0].strip(), splitFrom[1].strip(), splitTo[1].strip());
            this.tasks.add(newTask);
            tasksSize++;

            System.out.println(LINE);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.println("Now you have " + tasksSize + " tasks in the list.");
            System.out.println(LINE);
        }
    }

    public void list() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");

        for (int i = 1; i <= tasksSize; i ++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }

        System.out.println(LINE);
    }

    public void mark(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("mark");
        }

        int index = checkAndConvertNumber(taskDes);

        Task task = tasks.get(index - 1);
        task.markAsDone();

        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(LINE);
    }

    public void unmark(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("unmark");
        }

        int index = checkAndConvertNumber(taskDes);

        Task task = tasks.get(index - 1);
        task.markAsNotDone();

        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(LINE);
    }

    public void delete(String taskDes) throws DudeException {
        if(taskDes.isEmpty()){
            throw new DudeNullDescriptionException("delete");
        }

        int index = checkAndConvertNumber(taskDes);
        Task task = tasks.remove(index - 1);
        tasksSize--;

        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasksSize + " tasks in the list.");
        System.out.println(LINE);
    }

    public int checkAndConvertNumber(String s) throws DudeException {
        int index;
        try {
            index = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new DudeNumberException(s);
        }

        if (index < 1 || index > tasksSize) {
            throw new DudeNumberException(s);
        }

        return index;
    }

    public void exit() {
        this.isRunning = false;
        scanner.close();
        saveData();

        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public ArrayList<Task> loadData() {
        File dataFile = new File(FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(dataFile);

            while (fileScanner.hasNextLine()) {
                String dataLine = fileScanner.nextLine();
                try {
                    tasks.add(stringDataToTask(dataLine));
                } catch (DudeCorruptedDataException e) {
                    System.out.println(e.getMessage());
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            createNewDataFile();
        }

        return tasks;
    }

    public void createNewDataFile(){
        File dataFile = new File(FILE_PATH);
        File parent = new File(dataFile.getParent());
        parent.mkdirs();

        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("There is something wrong while creating data file:");
            System.out.println(e.getMessage());
        }
    }

    public Task stringDataToTask(String stringData) throws DudeCorruptedDataException {
        String[] taskComponent = stringData.split("\\|");
        Task task;

        switch (taskComponent[0]) {
        case "T":
            task = new ToDo(taskComponent[2]);
            break;
        case "D":
            task = new Deadline(taskComponent[2], taskComponent[3]);
            break;
        case "E":
            task = new Event(taskComponent[2], taskComponent[3], taskComponent[4]);
            break;
        default:
            throw new DudeCorruptedDataException();
        }

        if (taskComponent[1].equals("X")){
            task.markAsDone();
        }

        return task;
    }

    public void saveData() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Task task : tasks){
                bufferedWriter.write(task.taskToStringData());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e){
            System.out.println("There is something wrong while saving to data file:");
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Dude dude = new Dude();
        dude.start();
    }
}
