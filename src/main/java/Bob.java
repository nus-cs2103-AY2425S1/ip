import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Bob {
    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String GREETINGS = "Hello! I'm Bob\n"
            + "What can I do for you?\n";
    private static final String EXIT = "Bye. Hope to see you again soon!\n";
    public static void main(String[] args) {

        List<Task> myTasks = readData();

        System.out.print(DIVIDER + GREETINGS + DIVIDER);

        startChatBot(myTasks);

        System.out.print(EXIT + DIVIDER);
    }

    public static List<Task> readData() {
        List<Task> myTasks = new ArrayList<>();
        System.out.print(DIVIDER);
        try (Scanner scanner =  new Scanner(new File("./userdata.txt"), "UTF-8")){
            while (scanner.hasNextLine()){
                String in = scanner.nextLine();
                try {
                    Task newTask;
                    switch (in.split(" ")[0]) {
                    case "deadline":
                        newTask = newDeadline(in.split(" ", 3)[2]);
                        break;
                    case "event":
                        newTask = newEvent(in.split(" ", 3)[2]);
                        break;
                    case "todo":
                        newTask = newToDo(in.split(" ", 3)[2]);
                        break;
                    default:
                        System.out.println("Corrupted data found");
                        continue;
                    }
                    if (Objects.equals(in.split(" ")[1], "true")) {
                        newTask.mark();
                    }
                    myTasks.add(newTask);
                } catch (MissingArgumentException | EmptyArgumentException | DateTimeParseException e) {
                    System.out.println("Corrupted data found");
                }
            }
            if (myTasks.isEmpty()) {
                System.out.println("No data was loaded");
            } else {
                System.out.printf("Successfully loaded %d entries from last save\n", myTasks.size());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found");
        }
        return myTasks;
    }

    public static void writeData(List<Task> myTasks) {
        try {
            PrintWriter writer = new PrintWriter("./userdata.txt", "UTF-8");
            myTasks.forEach(task -> writer.println(task.export()));
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("Failed to save data");
        }

    }

    public static void startChatBot(List<Task> myTasks) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.next();
            System.out.print(DIVIDER);
            try {
                switch (input) {
                case "bye":
                    return;
                case "list":
                    listTasks(myTasks);
                    break;
                case "mark":
                    int index = scanner.nextInt() - 1;
                    if (!(index < myTasks.size()) || index < 0) {
                        throw new InvalidTaskNumberException();
                    }
                    markTask(myTasks.get(index));
                    break;
                case "unmark":
                    int ind = scanner.nextInt() - 1;
                    if (!(ind < myTasks.size()) || ind < 0) {
                        throw new InvalidTaskNumberException();
                    }
                    unmarkTask(myTasks.get(ind));
                    break;
                case "todo":
                    myTasks.add(printAddTask(newToDo(scanner.nextLine().trim().replace("  ", " "))));
                    System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                    break;
                case "deadline":
                    myTasks.add(printAddTask(newDeadline(scanner.nextLine().trim().replace("  ", " "))));
                    System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                    break;
                case "event":
                    myTasks.add(printAddTask(newEvent(scanner.nextLine().trim().replace("  ", " "))));
                    System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                    break;
                case "delete":
                    int inde = scanner.nextInt() - 1;
                    if (!(inde < myTasks.size()) || inde < 0) {
                        throw new InvalidTaskNumberException();
                    }
                    Task delTask = myTasks.remove(inde);
                    System.out.println(" Noted. I've removed this task:\n " + delTask);
                    System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                    break;
                default:
                    scanner.nextLine();
                    throw new InvalidInputException();
                }
                writeData(myTasks);
            } catch (EmptyArgumentException | MissingArgumentException |
                     InvalidTaskNumberException | InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date/time has been entered. Please key in with a DD/MM/YYYY format or DD/MM/YYYY HHMM format");
            }

            System.out.print(DIVIDER);
        }
    }

    public static Task printAddTask(Task task) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("\t" + task);
        return task;
    }
    public static ToDos newToDo(String input) throws EmptyArgumentException {
        if (input.isEmpty()) {
            throw new EmptyArgumentException("description", "todo");
        }
        return new ToDos(input);
    }

    public static Deadlines newDeadline(String input) throws EmptyArgumentException, MissingArgumentException, DateTimeParseException {

        if (!input.matches("^\\S{1}.+")) {
            throw new EmptyArgumentException("description", "deadline");
        } else if (!input.matches("^.*/by.*$")) {
            throw new MissingArgumentException("by", "deadline");
        } else if (!input.matches("^\\S{1}.+/by.*$")) {
            throw new EmptyArgumentException("description", "deadline");
        } else if (!input.matches("^\\S{1}.+ /by \\S{1}.+$")) {
            throw new EmptyArgumentException("by", "deadline");
        }

        String[] inputs = input.split("/by", 2);
        String[] dateTime = inputs[1].trim().split(" ");
        if (dateTime.length == 2) {
            return new Deadlines(inputs[0].trim(),
                    LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm")));
        }
        return new Deadlines(inputs[0].trim(), LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")));
    }

    public static EventTask newEvent(String input) throws EmptyArgumentException, MissingArgumentException, DateTimeParseException {

        if (!input.matches("^\\S{1}.+")) {
            throw new EmptyArgumentException("description", "event");
        } else if (!input.matches("^.*/from.*$")) {
            throw new MissingArgumentException("from", "event");
        } else if (!input.matches("^\\S{1}.+/from.*$")) {
            throw new EmptyArgumentException("description", "event");
        } else if (!input.matches("^.*/from.*/to.*$")) {
            throw new MissingArgumentException("to", "event");
        } else if (!input.matches("^\\S{1}.+ /from \\S{1}.+/to.*$")) {
            throw new EmptyArgumentException("from", "event");
        } else if (!input.matches("^\\S{1}.+ /from \\S{1}.+/to \\S{1}.+$")) {
            throw new EmptyArgumentException("to", "event");
        }

        String[] inputs = input.split("/from", 2);
        String[] dates = inputs[1].split("/to", 2);
        String[] startDateTime = dates[0].trim().split(" ");
        String[] endDateTime = dates[1].trim().split(" ");
        if (startDateTime.length == 1 && endDateTime.length == 1) {
            return new EventTask(inputs[0].trim(),
                    LocalDate.parse(startDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalDate.parse(endDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        } else if (startDateTime.length == 1 && endDateTime.length == 2) {
            return new EventTask(inputs[0].trim(),
                    LocalDate.parse(startDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalDate.parse(endDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalTime.parse(endDateTime[1], DateTimeFormatter.ofPattern("HHmm")));
        } else if (startDateTime.length == 2 && endDateTime.length == 1) {
            return new EventTask(inputs[0].trim(),
                    LocalDate.parse(startDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalTime.parse(startDateTime[1], DateTimeFormatter.ofPattern("HHmm")),
                    LocalDate.parse(endDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        }
        return new EventTask(inputs[0].trim(),
                LocalDate.parse(startDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse(startDateTime[1], DateTimeFormatter.ofPattern("HHmm")),
                LocalDate.parse(endDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse(endDateTime[1], DateTimeFormatter.ofPattern("HHmm")));
    }

    public static void markTask(Task task) {
        task.mark();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + task);
    }

    public static void unmarkTask(Task task) {
        task.unmark();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println(" " + task);
    }

    public static void listTasks(List<Task> myTasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 1; i < myTasks.size() + 1; i++) {
            System.out.print(" " + i + "." + myTasks.get(i-1) + "\n");
        }
    }
}

