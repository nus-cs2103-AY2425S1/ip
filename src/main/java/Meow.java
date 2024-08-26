import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Meow {
    private final String name = "Meow";
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;

    private void line() {
        System.out.println("____________________________________________________________");
    }

    private void saveTasks() { // whenever we update the list: add delete, mark unmark
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tasks.ser"))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadTasks() { // whenever we start up the app load the tasks
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tasks.ser"))) {
            tasks.addAll((ArrayList<Task>) ois.readObject());
            taskCount = tasks.size();
        } catch (IOException | ClassNotFoundException e) { // catch errors like missing file for first run
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    private static LocalDate parseDate(String dateString) throws MeowException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (Exception e) {
            throw new MeowException("Invalid date format. Use yyyy-MM-dd");
        }
    }

    public static class MeowException extends Exception {
        public MeowException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Meow MEOW = new Meow();

        MEOW.loadTasks();

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm " + MEOW.name + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        while (true) {
            try {
                String input = scanner.nextLine();
                MEOW.line();

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    MEOW.line();
                    break;
                } else if (input.equals("list")) {
                    if (MEOW.taskCount == 0) {
                        System.out.println("No outstanding tasks. MEOW!");
                    } else {
                        for (int i = 0; i < MEOW.taskCount; i++) {
                            System.out.println((i + 1) + "." + MEOW.tasks.get(i));
                        }
                    }
                    MEOW.line();
                } else if (input.startsWith("mark")) {
                    if (input.startsWith("mark ")) {
                        int index = Integer.parseInt(input.substring(5).trim()) - 1; // chop off the first few letters to get index
                        if (index >= 0 && index < MEOW.taskCount) {
                            MEOW.tasks.get(index).mark();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(MEOW.tasks.get(index));
                            MEOW.saveTasks();
                        } else {
                            throw new MeowException("GRRR! Invalid task number, you only have " + MEOW.taskCount + (MEOW.tasks.size() == 1 ? " task." : " tasks."));
                        }
                        MEOW.line();
                    } else { // "mark"
                        throw new MeowException("Please specify which task to mark. Example: mark 1");
                    }
                } else if (input.startsWith("unmark")) {
                    if (input.startsWith("unmark ")) {
                        int index = Integer.parseInt(input.substring(7).trim()) - 1; // chop off the first few letters to get index
                        if (index >= 0 && index < MEOW.taskCount) {
                            MEOW.tasks.get(index).unMark();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(MEOW.tasks.get(index));
                            MEOW.saveTasks();
                        } else {
                            throw new MeowException("GRRR! Invalid task number, you only have " + MEOW.taskCount + (MEOW.tasks.size() == 1 ? " task." : " tasks."));
                        }
                        MEOW.line();
                    } else {
                        throw new MeowException("Please specify which task to unmark. Example: unmark 1");
                    }
                } else if (input.startsWith("deadline")) {
                    if (input.startsWith("deadline ")) {
                        String[] parts = input.substring(9).split(" /by "); // chop off the first few letters to get deadline
                        if (parts.length == 2) {
                            try {
                                LocalDate by = parseDate(parts[1].trim());
                                MEOW.tasks.add(new Deadline(parts[0], by));
                                System.out.println("Got it. I've added this task:");
                                System.out.println(MEOW.tasks.get(MEOW.tasks.size() - 1));
                                MEOW.taskCount++;
                                System.out.println(MEOW.taskCount <= 1 ? "Now you have " + MEOW.taskCount + " task in the list."
                                        : "Now you have " + MEOW.taskCount + " tasks in the list.");
                                MEOW.saveTasks();
                            } catch (MeowException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            throw new MeowException("Invalid deadline format. Example: deadline return book /by Christmas");
                        }
                    } else {
                        throw new MeowException("Invalid deadline format. Example: deadline return book /by Christmas");
                    }
                    MEOW.line();
                } else if (input.startsWith("event")) {
                    if (input.startsWith("event ")) {
                        String[] parts = input.substring(6).split(" /from | /to "); // split input into 3 args for event
                        if (parts.length == 3) {
                            try {
                                LocalDate from = parseDate(parts[1].trim());
                                LocalDate to = parseDate(parts[2].trim());
                                if (from.isBefore(to)) {
                                    MEOW.tasks.add(new Event(parts[0], from, to));
                                    System.out.println("Got it. I've added this task:");
                                    System.out.println(MEOW.tasks.get(MEOW.tasks.size() - 1));
                                    MEOW.taskCount++;
                                    System.out.println(MEOW.taskCount <= 1 ? "Now you have " + MEOW.taskCount + " task in the list."
                                            : "Now you have " + MEOW.taskCount + " tasks in the list.");
                                    MEOW.saveTasks();
                                } else {
                                    throw new MeowException("GRRR! Start date cannot be after End date");
                                }
                            } catch (MeowException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            throw new MeowException("Invalid event format. Example: event gym workout /from Mon 1pm /to 2.30pm");
                        }
                    } else {
                        throw new MeowException("Invalid event format. Example: event gym workout /from Mon 1pm /to 2.30pm");
                    }
                    MEOW.line();
                } else if (input.startsWith("todo")) {
                    if (input.startsWith("todo ")) {
                        String description = input.substring(5).trim(); // chop off the first few letters to get task
                        MEOW.tasks.add(new ToDo(description));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(MEOW.tasks.get(MEOW.tasks.size() - 1));
                        MEOW.taskCount++;
                        System.out.println(MEOW.taskCount <= 1 ? "Now you have " + MEOW.taskCount + " task in the list."
                                : "Now you have " + MEOW.taskCount + " tasks in the list.");
                        MEOW.saveTasks();
                        MEOW.line();
                    } else {
                        throw new MeowException("Invalid todo format. Example: todo eat lunch");
                    }

                } else if (input.startsWith("delete")) {
                   if (input.startsWith("delete ")) {
                       int index = Integer.parseInt(input.substring(7).trim()) - 1; // Get index from the input
                       if (index >= 0 && index < MEOW.tasks.size()) {
                           Task removedTask = MEOW.tasks.remove(index); // Remove task from the list
                           System.out.println("Noted. I've removed this task:");
                           System.out.println(removedTask);
                           MEOW.taskCount--;
                           System.out.println("Now you have " + MEOW.tasks.size() + (MEOW.tasks.size() == 1 ? " task" : " tasks") + " in the list.");
                           MEOW.saveTasks();
                           MEOW.line();
                       } else {
                           throw new MeowException("GRRR! Invalid task number, you only have " + MEOW.tasks.size() + (MEOW.tasks.size() == 1 ? " task" : " tasks"));
                       }
                   } else {
                        throw new MeowException("Invalid todo format. Example: delete 1");
                   }

                } else {
                    throw new MeowException("Whatchu sayin bruh?");
                }
            } catch (MeowException e) {
                System.out.println(e.getMessage());
                MEOW.line();
            }
        }
    }
}