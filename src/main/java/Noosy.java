import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Noosy {

    public static void main(String[] args) {

        // Constant statements
        String greeting = "Heyo! This is Noosy! \n" +
                "Noosy is da best, tell me what you need! :>";
        String goodbye = "Alright, see ya!";
        String done = "Hooray you've done this: \n";
        String undo = "Ok don't worry, you can continue working on this: \n";
        String delete = "Oo, removing the irrelevant task: \n";

        // instead of creating new task list every time, we now try to load it
        String filePath = "./data/noosy.txt";
        Storage storage = new Storage(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println(greeting);
        int totalTasks = -1;

        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            String[] separated = userInput.split(" ", 2);
            String command = separated[0];
            String input = separated.length > 1 ? separated[1] : "";

            switch (command) {
                case "list":
                    System.out.println("Heyo here are the task(s) we have: ");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    break;

                case "on":
                    printTasksOnDate(tasks, input);
                    break;

                case "mark":
                    int taskNum = Integer.parseInt(input);
                    Task completed = tasks.get(taskNum - 1);
                    completed.isDone();
                    System.out.println(done + completed);
                    break;

                case "unmark":
                    taskNum = Integer.parseInt(input);
                    Task uncomplete = tasks.get(taskNum - 1);
                    uncomplete.unDone();
                    System.out.println(undo + uncomplete);
                    break;

                case "delete":
                    if (input.equals("")) {
                        System.out.println("So what are we deleting???");
                        break;
                    }

                    taskNum = Integer.parseInt(input);
                    Task toDelete = tasks.get(taskNum - 1);
                    tasks.remove(toDelete);
                    System.out.println(delete + toDelete);
                    totalTasks = tasks.size();
                    System.out.println("We're left with " + totalTasks + " tasks in the bucket!");
                    break;

                case "todo":
                    if (input.equals("")) {
                        System.out.println("I think you forgot the description?");
                        break;
                    }

                    Todo todo = new Todo(input);
                    tasks.add(todo);
                    System.out.println("I added it to the list! \n" + todo);
                    totalTasks = tasks.size();
                    System.out.println("We've now got " + totalTasks + " tasks in the bucket!");
                    break;

                case "deadline":
                    String[] withDue = input.split(" /by ");
                    if (withDue.length < 2) {
                        // error msg
                        System.out.println("I think you forgot the description / deadline?");
                        break;
                    }

                    LocalDate due = null;
                    while (due == null) {
                        try {
                            due = LocalDate.parse(withDue[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        } catch (DateTimeParseException e) {
                            System.out.println("Please enter the date in the format yyyy-MM-dd:");
                            withDue[1] = scanner.nextLine();
                        }
                    }
                    Deadline deadline = new Deadline(withDue[0], due);
                    tasks.add(deadline);
                    System.out.println("I added it to the list! \n" + deadline);
                    totalTasks = tasks.size();
                    System.out.println("We've now got " + totalTasks + " tasks in the bucket!");
                    break;

                case "event":
                    String[] withDuration = input.split(" /from | /to ");
                    if (withDuration.length < 3) {
                        // error msg
                        System.out.println("I think you forgot the description / duration?");
                        break;
                    }

                    LocalDateTime start = null, end = null;
                    while (start == null) {
                        try {
                            start = LocalDateTime.parse(withDuration[1],
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                        } catch (DateTimeParseException e) {
                            System.out.println("Please enter the start time in the format yyyy-MM-dd HH:mm:");
                            withDuration[1] = scanner.nextLine();
                        }
                    }

                    while (end == null) {
                        try {
                            end = LocalDateTime.parse(withDuration[2],
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                        } catch (DateTimeParseException e) {
                            System.out.println("Please enter the end time in the format yyyy-MM-dd HH:mm:");
                            withDuration[2] = scanner.nextLine();
                        }
                    }

                    Event event = new Event(withDuration[0], start, end);
                    tasks.add(event);
                    System.out.println("I added it to the list! \n" + event);
                    totalTasks = tasks.size();
                    System.out.println("We've now got " + totalTasks + " tasks in the bucket!");
                    break;

                default:
                    System.out.println("Beep Boop, Noosy no understand");
                    break;
            }
            userInput = scanner.nextLine();
        }
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("I couldn't save the tasks: " + e.getMessage());
        }

        System.out.println(goodbye);
        scanner.close();
    }

    private static void printTasksOnDate(ArrayList<Task> tasks, String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println("You needa do this on " + dateStr + ":");
            boolean found = false;
            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    if (deadline.getDate().equals(date)) {
                        System.out.println(deadline);
                        found = true;
                    }
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    if (event.getStart().toLocalDate().equals(date)) {
                        System.out.println(event);
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("Hooray! Nothing to do on " + dateStr);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Please retype the command with a date in the format yyyy-MM-dd.");
        }
    }
}