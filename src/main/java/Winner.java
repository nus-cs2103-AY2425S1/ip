import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Winner {
//    private static final String HOME = System.getProperty("user.home"); //Storage
//    private static final Path FOLDER_PATH = Paths.get(HOME, "Winner"); //Storage
//    private static final Path TASKLIST_PATH = Paths.get(HOME, "Winner", "tasklist.txt"); //Storage

    public static void main(String[] args) {
//        ArrayList<Task> tasks = new ArrayList<>();
        checkAndCreateFile();
        loadTasks(tasks);
        WinnerTaskBot(tasks);
        saveTasks(tasks);
    }

    private static void WinnerTaskBot(ArrayList<Task> tasks) {
        Scanner scanner = new Scanner(System.in);

        //winnerSaysHi


        while (true) {
            try {
                String input = scanner.nextLine();

/*                if (input.matches("(?i)hi|hello")) { //Parser
                    System.out.println(("-".repeat(100) + "\n" //UI
                            + "hi" + "\n"
                            + "-".repeat(100)).indent(10));

                } else if (input.matches("(?i).*\\b+todo\\b+.*")) { //Parser
                    String description = input.split("todo", 2)[1].trim().toLowerCase(); //TaskList
                    if (description.isEmpty()) { //Ui
                        throw new WinnerException("""
                                Oh no! Your task cannot be empty.
                                Please tell me your task in this form :
                                todo (task)""");
                    }
                    ToDo newToDo = new ToDo(description); //TaskList
                    tasks.add(newToDo);
                    System.out.println(("-".repeat(100) + "\n" //Ui
                            + newToDo.addTaskToString() + "\n"
                            + "-".repeat(100)).indent(10));

                } else if (input.matches("(?i).*\\b+deadline\\b+.*")) { //Parser
                    String[] parts = input.split("(?i)\\b+deadline\\b+ | \\bby\\b"); //TaskList
                    if (parts.length != 3) { //Ui
                        throw new WinnerException("""
                                Oh no! You are missing a task and a deadline.
                                Please tell me your task in this form :
                                deadline (task) by (dd/mm/yyyy) at (time - 24 hour format)""");
                    }
                    if (parts[1].trim().isEmpty()) {
                        throw new WinnerException("""
                                Oh no! Your task cannot be empty.
                                Please tell me your task in this form :
                                deadline (task) by (dd/mm/yyyy) at (time - 24 hour format)""");
                    }
                    if (parts[2].trim().isEmpty()) {
                        throw new WinnerException("""
                                Oh no! We are going to need a deadline for this task.
                                Please tell me your task in this form :
                                deadline (task) by (dd/mm/yyyy) at (time - 24 hour format)""");
                    }
                    String description = parts[1].trim().toLowerCase(); //TaskList
                    Deadline newDeadline; //TaskList
                    try {
                        if (parts[2].matches(".*\\bat\\b.*")) { //Parser
                            String dateTime = parts[2].trim(); //TaskList
                            DateTimeFormatter formattedDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HHmm");
                            LocalDateTime byDateTime = LocalDateTime.parse(dateTime, formattedDateTime);
                            newDeadline = new Deadline(description, byDateTime);
                        } else {
                            String date = parts[2].trim(); //TaskList
                            DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate byDate = LocalDate.parse(date, formattedDate);
                            newDeadline = new Deadline(description, byDate);
                        }
                        tasks.add(newDeadline); //TaskList
                        System.out.println(("-".repeat(100) + "\n" //Ui
                                + newDeadline.addTaskToString() + "\n"
                                + "-".repeat(100)).indent(10));
                    } catch (DateTimeParseException e) { //Ui
                        System.out.println(("-".repeat(100) + "\n"
                                + "Please format your date and time in this form: " + "\n"
                                + "date - dd/mm/yyyy" + "\n"
                                + "time - 24 hour format" + "\n"
                                + "-".repeat(100)).indent(10));
                    }

                } else if (input.matches("(?i).*\\b+event\\b+.*")) { //Parser
                    String[] parts = input.split("(?i)\\b+event\\b+ | \\bfrom\\b | \\bto\\b"); //TaskList
                    if (parts.length != 4) { //Ui
                        throw new WinnerException("""
                                Oh no! You are missing a task, start and end day/date/time.
                                Please tell me your task in this form :
                                event (task) from (start) to (end)""");
                    }

                    if (parts[1].trim().isEmpty()) {
                        throw new WinnerException("""
                                Oh no! Your task cannot be empty.
                                Please tell me your task in this form :
                                event (task) from (start) to (end)""");
                    }
                    if (parts[2].trim().isEmpty()) {
                        throw new WinnerException("""
                                Oh no! We are going to need a start day/date/time for this task.
                                Please tell me your task in this form :
                                event (task) from (start) to (end)""");
                    }
                    if (parts[3].trim().isEmpty()) {
                        throw new WinnerException("""
                                Oh no! We are going to need an end day/date/time for this task.
                                Please tell me your task in this form :
                                event (task) from (start) to (end)""");
                    }
                    String description = parts[1].trim().toLowerCase(); //TaskList
                    String start = parts[2].trim().toLowerCase();
                    String end = parts[3].trim().toLowerCase();
                    Event newEvent = new Event(description, start, end);
                    tasks.add(newEvent);
                    System.out.println(("-".repeat(100) + "\n" //Ui
                            + newEvent.addTaskToString() + "\n"
                            + "-".repeat(100)).indent(10));

                } else if (input.matches("(?i).*\\b+list\\b+.*")) { //Parser
                    int counter = 1; //TaskList
                    System.out.println(" ".repeat(10) + "-".repeat(100)); //Ui
                    System.out.println(" ".repeat(10) + "Here are the tasks you have in your list:"); //Ui
                    for (Task i : tasks) { //TaskList
                        System.out.println(" ".repeat(10) + counter + ". " + i.taskToString()); //TaskList but dont use sout, return a String
                        counter++;
                    }
                    System.out.println(" ".repeat(10) + "-".repeat(100) + "\n"); //Ui

                } else if (input.matches("(?i).*\\b+mark\\b+.*")) { //Parser
                    int taskNumber = Integer.parseInt(input.replaceAll("[^0-9]", "")); //TaskList
                    if (taskNumber < 1 || taskNumber > tasks.size()) { //Ui
                        throw new WinnerException("""
                                Oh no! I cannot mark this task as done because the number is invalid.
                                Please tell me which task to mark as done in this form :
                                mark (task number)""");
                    }
                    Task task = tasks.get(taskNumber - 1); //TaskList
                    if (task.isDone) {
                        throw new WinnerException("You have already marked this task as DONE :D");
                    }
                    task.markDone(); //TaskList
                    System.out.println(("-".repeat(100) + "\n" //Ui
                            + task.markDoneToString() + "\n"
                            + "-".repeat(100)).indent(10));

                } else if (input.matches("(?i).*\\b+unmark\\b+.*")) { //Parser
                    int taskNumber = Integer.parseInt(input.replaceAll("[^0-9]", "")); //TaskList
                    if (taskNumber < 1 || taskNumber > tasks.size()) { //Ui
                        throw new WinnerException("""
                                Oh no! I cannot mark this task as undone because the number is invalid.
                                Please tell me which task to mark as undone in this form :
                                unmark (task number)""");
                    }
                    Task task = tasks.get(taskNumber - 1); //TaskList
                    if (!task.isDone) { //Ui
                        throw new WinnerException("You cannot unmark this task as it has not been marked as done :(");
                    }
                    task.unmarkDone(); //TaskList
                    System.out.println(("-".repeat(100) + "\n" //Ui
                            + task.unmarkDoneToString() + "\n"
                            + "-".repeat(100)).indent(10));

                } else if (input.matches("(?i).*\\b+delete\\b+.*")) { //Parser
                    int taskNumber = Integer.parseInt(input.replaceAll("[^0-9]", "")); //TaskList
                    if (taskNumber < 1 || taskNumber > tasks.size()) { //Ui
                        throw new WinnerException("""
                                Oh no! I cannot remove this task from the list because the number is invalid.
                                Please tell me which task to remove in this form :
                                delete (task number)""");
                    }
                    Task task = tasks.get(taskNumber - 1); //TaskList
                    tasks.remove(taskNumber - 1);
                    System.out.println(("-".repeat(100) + "\n" //Ui
                            + task.deleteTask() + "\n"
                            + "-".repeat(100)).indent(10));

                }

                //winnerSaysBye

                else if (input.matches("(?i).*bye.*")) { //Parser
                    System.out.println(("-".repeat(100) + "\n"
                            + "Hope I have been of service!" + "\n"
                            + "Thank you and see you again soon :D" + "\n"
                            + "Remember!!! A WINNER NEVER LOSES!!!" + "\n"
                            + "-".repeat(100)).indent(10));
                    break;


                } else { //Ui
                    throw new WinnerException("""
                            Sorry, I do not know what that means
                            ⡴⠒⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠉⠳⡆⠀
                            ⣇⠰⠉⢙⡄⠀⠀⣴⠖⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣆⠁⠙⡆
                            ⠘⡇⢠⠞⠉⠙⣾⠃⢀⡼⠀⠀⠀⠀⠀⠀⠀⢀⣼⡀⠄⢷⣄⣀⠀⠀⠀⠀⠀⠀⠀⠰⠒⠲⡄⠀⣏⣆⣀⡍
                            ⠀⢠⡏⠀⡤⠒⠃⠀⡜⠀⠀⠀⠀⠀⢀⣴⠾⠛⡁⠀⠀⢀⣈⡉⠙⠳⣤⡀⠀⠀⠀⠘⣆⠀⣇⡼⢋⠀⠀⢱
                            ⠀⠘⣇⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⡴⢋⡣⠊⡩⠋⠀⠀⠀⠣⡉⠲⣄⠀⠙⢆⠀⠀⠀⣸⠀⢉⠀⢀⠿⠀⢸
                            ⠀⠀⠸⡄⠀⠈⢳⣄⡇⠀⠀⢀⡞⠀⠈⠀⢀⣴⣾⣿⣿⣿⣿⣦⡀⠀⠀⠀⠈⢧⠀⠀⢳⣰⠁⠀⠀⠀⣠⠃
                            ⠀⠀⠀⠘⢄⣀⣸⠃⠀⠀⠀⡸⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠈⣇⠀⠀⠙⢄⣀⠤⠚⠁⠀
                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⢹⠀⠀⠀⠀⠀⠀⠀⠀⠀
                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⢘⠀⠀⠀⠀⠀⠀⠀⠀⠀
                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⢰⣿⣿⣿⡿⠛⠁⠀⠉⠛⢿⣿⣿⣿⣧⠀⠀⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀
                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡀⣸⣿⣿⠟⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⡀⢀⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀
                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡇⠹⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⡿⠁⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣤⣞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢢⣀⣠⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                            ⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⠙⠲⢤⣀⣀⠀⢀⣀⣀⠤⠒⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""");
                }
*/
            } catch (WinnerException e) {
                System.out.println(("-".repeat(100) + "\n"
                        + e.getMessage() + "\n"
                        + "-".repeat(100)).indent(10));
            }
        }
        scanner.close();
    }

/*    private static void checkAndCreateFile() { //Storage
        try {
          if (Files.exists(FOLDER_PATH)) {
                Files.createFile(TASKLIST_PATH);
          } else {
                Files.createDirectories(FOLDER_PATH);
                Files.createFile(TASKLIST_PATH);
          }
        } catch (IOException e) {
            System.out.println("Error creating file or directory: " + e.getMessage());
        }
    }

    private static void loadTasks(ArrayList<Task> tasks) { //Storage
        try (BufferedReader br = Files.newBufferedReader(TASKLIST_PATH)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                String whichTask = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                Task task = null;
                switch (whichTask) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    LocalDateTime byDateTime = LocalDateTime.parse(parts[3].trim());
                    task = new Deadline(description, byDateTime);
                    break;
                case "E":
                    String start = parts[3].trim();
                    String end = parts[4].trim();
                    task = new Event(description, start, end);
                    break;
                default:
                    break;
                }
                if (task != null) {
                    if (isDone) {
                        task.markDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private static void saveTasks(ArrayList<Task> tasks) { //Storage
        try (BufferedWriter bw = Files.newBufferedWriter(TASKLIST_PATH)) {
            for (Task i : tasks) {
                String description = i.description;
                String isDone = i.isDone ? "1" : "0";
                if (i instanceof ToDo) {
                    bw.write(String.format("T | %s | %s%n", isDone, description));
                } else if (i instanceof Deadline) {
                    LocalDateTime byDateTime = ((Deadline) i).byDateTime;
                    bw.write(String.format("D | %s | %s | %s%n", isDone, description, byDateTime));
                } else if (i instanceof Event) {
                    String start = ((Event) i).start;
                    String end = ((Event) i).end;
                    bw.write(String.format("E | %s | %s | %s | %s%n", isDone, description, start, end));
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing tasks to file: " + e.getMessage());
        }
    }
*/
}