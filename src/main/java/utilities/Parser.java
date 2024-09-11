package utilities;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    private Storage s;
    private Ui ui;
    private TaskList tasks;
    private Scanner scanner;




    public Parser(Ui ui, TaskList tasks, Storage s, Scanner scanner) {
        this.ui = ui;
        this.tasks = tasks;
        this.s = s;
        this.scanner = scanner;
    }

    static LocalDateTime parseDateTime(String input) throws BigmouthException {
        try {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new BigmouthException("OOPS! Please enter the date/time in the format 'M/d/yyyy HHmm'.");
        }
    }

    protected void parseInput() {
        while (true) {
            String userInput = scanner.nextLine();

            try {
                if (userInput.equals("bye")) {
                    ui.showGoodbyeMessage();
                    break;
                }

                if (userInput.equals("list")) {
                    if (tasks.isEmpty()) {
                        throw new BigmouthException("Your task list is empty!");
                    }
                    ui.showTaskList(tasks);
                } else if (userInput.startsWith("mark ")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new BigmouthException("Invalid task number. Please enter a valid task number.");
                    }
                    tasks.get(taskNumber).markAsDone();
                    s.saveToFile();
                    ui.showTaskMarked(tasks.get(taskNumber));
                } else if (userInput.startsWith("unmark ")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new BigmouthException("Invalid task number. Please enter a valid task number.");
                    }
                    tasks.get(taskNumber).markAsNotDone();
                    s.saveToFile();
                    ui.showTaskUnmarked(tasks.get(taskNumber));
                } else if (userInput.startsWith("todo ")) {
                    String description = userInput.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new BigmouthException("OOPS! The description of a todo cannot be empty.");
                    }
                    Todo curr = new Todo(description);
                    tasks.add(curr);
                    s.saveToFile();
                    ui.showTaskAdded(curr, tasks.size());
                } else if (userInput.startsWith("deadline ")) {
                    String[] parts = userInput.split(" /by ");
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new BigmouthException("OOPS! The deadline command is missing a date/time.");
                    }
                    String description = parts[0].substring(9).trim();
                    LocalDateTime by = Parser.parseDateTime(parts[1].trim());
                    if (description.isEmpty()) {
                        throw new BigmouthException("OOPS! The description of a deadline cannot be empty.");
                    }
                    Deadline curr = new Deadline(description, by);
                    tasks.add(curr);
                    s.saveToFile();
                    ui.showTaskAdded(curr, tasks.size());
                } else if (userInput.startsWith("event ")) {
                    String[] parts = userInput.split(" /from | /to ");
                    if (parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                        throw new BigmouthException("OOPS! The event command is missing a start or end time.");
                    }
                    String description = parts[0].substring(6).trim();
                    LocalDateTime from = Parser.parseDateTime(parts[1].trim());
                    LocalDateTime to = Parser.parseDateTime(parts[2].trim());
                    if (description.isEmpty()) {
                        throw new BigmouthException("OOPS! The description of an event cannot be empty.");
                    }
                    Event curr = new Event(description, from, to);
                    tasks.add(curr);
                    s.saveToFile();
                    ui.showTaskAdded(curr, tasks.size());
                } else if (userInput.startsWith("delete ")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new BigmouthException("Invalid task number. Please enter a valid task number.");
                    }
                    Task removedTask = tasks.remove(taskNumber);
                    s.saveToFile();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removedTask);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new BigmouthException("OOPS! I don't know what that means. Please try again.");
                }
            } catch (BigmouthException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS! Please enter a valid number.");
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }

}

