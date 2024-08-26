package hoshi;

import hoshi.exception.HoshiException;
import hoshi.task.*;
import hoshi.ui.Ui;
import hoshi.utils.Parser;
import hoshi.utils.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Hoshi {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Hoshi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        parser= new Parser();

        try {
            storage.load(taskList);
        } catch (FileNotFoundException e) {
            ui.displayLoadingError(e.getMessage());
        }
    }

    public void run() {

        ui.displayWelcome();
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            ui.displayPrompt();
            input = scanner.nextLine();
            parser.parseCommand(input, scanner, taskList, ui, storage);
        } while (!input.equalsIgnoreCase("bye"));

        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.displaySavingError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Hoshi("data/Hoshi.txt").run();

    }


    /**
     * Gets tasks from hoshi txt file if user is not new else greets the user.
     *
     * @param filePath  String filepath that contains the path of the hoshi txt file.
     * @param arrayList ArrayList of 3 types of tasks to be retrieved from hoshi txt file.
     */
    private static void getFileContents(String filePath, ArrayList<Task> arrayList) throws FileNotFoundException {

        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);

            //Deadline, D, deadline1, 1200

            while (scanner.hasNext()) {

                String line = scanner.nextLine();
                String[] parts = line.split(", ");

                String taskType = parts[0];
                Boolean isDone = Boolean.FALSE;
                if (Objects.equals(parts[1], "D")) {
                    isDone = Boolean.TRUE;
                }
                String description = parts[2];

                switch (taskType) {
                case "Todo":

                    Todo todo = new Todo(description, isDone);
                    arrayList.add(todo);
                    break;

                case "Deadline":

                    String deadlineEndTime = parts[3];

                    LocalDate deadlineDateTimeEnd = LocalDate.parse(deadlineEndTime);

                    Deadline deadline = new Deadline(description, isDone, deadlineDateTimeEnd);
                    arrayList.add(deadline);
                    break;

                case "Event":

                    String endTime = parts[3];
                    String startTime = parts[4];

                    LocalDate dateTimeEnd = LocalDate.parse(endTime);
                    LocalDate dateTimeStart = LocalDate.parse(startTime);

                    Event event = new Event(description, isDone, dateTimeEnd, dateTimeStart);
                    arrayList.add(event);
                    break;

                default:

                    System.out.println("Hoshi is not aware of this task type: " + taskType + "!");
                    break;
                }

            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Hoshi has detected a new user! Welcome!");
        }

    }

    /**
     * Writes tasks added and retrieved during the program to hoshi txt file.
     *
     * @param filePath  String filepath that contains the path of the hoshi txt file.
     * @param arrayList ArrayList of 3 types of tasks to be written to hoshi txt file.
     */
    private static void writeToFile(String filePath, ArrayList<Task> arrayList) throws IOException {

        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (Task task : arrayList) {

                // Deadline(TaskType), T(D = Done/ ND = Not Done), Description, endTime, startTime
                String taskType = task.getClass().getName();
                String isDone = task.getStatusIcon();
                if (Objects.equals(isDone, " ")) {
                    isDone = "ND";
                } else {
                    isDone = "D";
                }
                String description = task.getDesc();

                String additionalFields = "";

                if (taskType.equals("Deadline")) {

                    Deadline deadline = ((Deadline) task);
                    additionalFields = ", " + deadline.getEndTime();

                } else if (taskType.equals("Event")) {

                    Event event = ((Event) task);
                    additionalFields = ", " + event.getEndTime() + ", " + event.getStartTime();

                }

                String textToAdd = taskType + ", " + isDone + ", " + description + additionalFields + System.lineSeparator();
                fileWriter.write(textToAdd);
            }
            fileWriter.close();


        } catch (IOException e) {
            System.out.println("Hoshi has an error! " + e.getMessage());
        }

    }

    /**
     * Adds either to do/deadline/event tasks that are described by the user to ArrayList which is to be written to a
     * txt file later
     *
     * @param input  String that represents general user input before add task details are required.
     * @param scanner Scanner that allows user input to be read.
     * @param arrayList ArrayList of 3 types of tasks that will be added to in this method.
     */
    private static void addTask(String input, Scanner scanner, ArrayList<Task> arrayList) {

        if (input.trim().length() < 4) {

            System.out.println("Please specify the task to add! E.g. Add {task to be added} \n");

        } else {
            {

            }
            String[] splitInput = input.split(" ");


            String taskInput = splitInput[1];

            switch (taskInput) {
            case "todo" -> {

                System.out.println("Understood! What is your ToDo? ");
                String desc = scanner.nextLine();

                try {

                    if (desc.isEmpty()) {
                        throw new HoshiException("Hoshi doesn't understand! Is input empty? \n");
                    }

                    Todo newToDo = new Todo(desc);
                    arrayList.add(newToDo);
                    System.out.println("added: " + input);

                } catch (HoshiException e) {
                    System.out.println(e.getMessage());
                }

            }
            case "deadline" -> {

                System.out.println("Understood! What is your Deadline? ");
                String desc = scanner.nextLine();

                try {

                    if (desc.isEmpty()) {
                        throw new HoshiException("Hoshi doesn't understand! Is input empty? \n");
                    }

                    System.out.println("When would you like your Deadline to be due by? ");

                    // take in input
                    String endTime = scanner.nextLine();

                    LocalDate dateTime = LocalDate.parse(endTime);

                    Deadline newDeadline = new Deadline(desc, dateTime);
                    arrayList.add(newDeadline);
                    System.out.println("added: " + input);

                } catch (HoshiException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("Hoshi doesn't understand! Try YYYY-MY-DD format");
                }


            }
            case "event" -> {

                System.out.println("Understood! What is your Event? ");

                String desc = scanner.nextLine();

                try {
                    if (desc.isEmpty()) {
                        throw new HoshiException("Hoshi doesn't understand! Is input empty? \n");
                    }

                    System.out.println("When would you like your Event to start? ");

                    // take in input
                    String startTime = scanner.nextLine();

                    LocalDate dateTimeStart = LocalDate.parse(startTime);


                    System.out.println("When would you like your Event to end? ");

                    // take in input
                    String endTime = scanner.nextLine();

                    LocalDate dateTimeEnd = LocalDate.parse(endTime);

                    Event newEvent = new Event(desc, dateTimeStart, dateTimeEnd);
                    arrayList.add(newEvent);
                    System.out.println("added: " + input);


                } catch (HoshiException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("Hoshi doesn't understand! Try YYYY-MY-DD format");
                }


            }
            default ->

                // in event of invalid input
                    System.out.println("Hoshi doesn't understand! Please try again with the above keywords");
            }

        }

    }

    /**
     * Prints bye message when user terminates the program
     *
     */
    static void bye () {

        System.out.println("""
                    Bye. Hope to see you again soon!\s
                    ____________________________________________________________
                    """);
    }





}
