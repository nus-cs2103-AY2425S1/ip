import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Nah {
    private static String greetLine = "________________________________________________________________________________\n"
            + " Hello! I'm NAH\n"
            + " What can I do for you?\n"
            + "________________________________________________________________________________\n";
    private static String byeLine = " Bye. Hope to see you again soon!\n";

    private enum Command {BYE, TODO, DEADLINE, EVENT, LIST, DUEON, DELETE, MARK, UNMARK, UNKNOWN};
    private LinkedList<Task> task = new LinkedList<Task>();
    private int taskCount = 0;
    private String hardDisk = Paths.get("D:","cs2103T_week_2", "Data", "Nah.txt").toString();
    private void greet() {
        System.out.println(greetLine);
    }

    private Command getCommand(String input) {
        String[] command = input.split(" ", 2);
        String cmd = command[0].toLowerCase();

        switch (cmd) {
        case "bye" : {
            return Command.BYE;}
        case "todo" : {
            return Command.TODO;
        }
        case "deadline" : {
            return Command.DEADLINE;
        }
        case "event" : {
            return Command.EVENT;
        }
        case "list" : {
            return Command.LIST;
        }
        case "mark" : {
            return Command.MARK;
        }
        case "unmark" : {
            return Command.UNMARK;
        }
        case "delete" : {
            return Command.DELETE;
        }
        case "dueon" : {
            return Command.DUEON;
        }
        default:
            return Command.UNKNOWN;

        }
    }

    private static void writeToFile(String filePath, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage() +"\n");
        }
    }
    private String dueOn(String time) {
        LocalDateTime due = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        String s = " Here are the tasks in your list that ends before the due:\n";
        int i = 1;
        for (Task t : task) {
            if (t instanceof Deadlines || t instanceof Events) {
                if (t.endTime().isBefore(due)) {
                    s += " " + i + ". " + t.toString() + "\n";
                }
            }
        }
        return s;
    }
    private String readTask() {
        String s = " Here are the tasks in your list:\n";
        for (int i = 1; i <= taskCount; i ++) {
            s += " " + i + ". " + task.get(i - 1).toString() + "\n";
        }
        return s;
    }
    private void rewrite() {
        writeToFile(hardDisk, readTask());
    }
    private void exit() {
        System.out.println(byeLine);
        System.out.println("________________________________________________________________________________\n");

    }

    private String add(Task newTask) {
        task.add(newTask);
        taskCount ++;
        rewrite();
        return " Got it. I've added this task:\n"
                        + "   " + newTask.toString() + "\n"
                        + " Now you have " + taskCount + " tasks in the list.\n";

    }

    private String mark(int i) throws InvalidTaskNumber{
        if (taskCount == 0) {
            throw new InvalidTaskNumber();
        }
        if (i <= 0 || i > taskCount) {
            throw new InvalidTaskNumber(i, taskCount);
        }
        task.get(i - 1).mark();
        rewrite();
        return " Nice! I've marked this task as done:\n"
                        + "   " + task.get(i - 1).toString();

    }

    private String unMark(int i) throws InvalidTaskNumber{
        if (taskCount == 0) {
            throw new InvalidTaskNumber();
        }
        if (i <= 0 || i > taskCount) {
            throw new InvalidTaskNumber(i, taskCount);
        }
        task.get(i - 1).unMark();
        rewrite();
        return " OK, I've marked this task as not done yet:\n"
                + "   " + task.get(i - 1).toString();

    }

    private String delete(int i) throws InvalidTaskNumber{
        if (taskCount == 0) {
            throw new InvalidTaskNumber();
        }
        if (i <= 0 || i >= taskCount) {
            throw new InvalidTaskNumber(i, taskCount);
        }
        taskCount --;
        String s = " Noted. I've removed this task:\n"
                + "   " + task.get(i - 1).toString() + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n";
        task.remove(i - 1);
        rewrite();
        return s;
    }

    public static void main(String[] args) {
        String logo = "| \\   | |      /\\      | |  | |\n"
                + "| |\\  | |     / /\\     | |==| |\n"
                + "| | \\ | |    / /__\\    | |  | |\n"
                + "| |  \\  |   / /    \\   | |  | |\n";


        System.out.println("Hello from\n" + logo);

        Nah nah = new Nah();

        nah.greet();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            System.out.println("________________________________________________________________________________\n");

            Command cmd = nah.getCommand(input);
            String[] command = input.split(" ", 2);
            try {
                switch (cmd) {

                case BYE: {
                    nah.exit();
                    return;
                }
                case LIST: {
                    System.out.println(nah.readTask());
                    break;
                }
                case MARK: {
                    int i = parseInt(command[1]);
                    System.out.println(nah.mark(i));
                    break;
                }
                case UNMARK: {
                    int i = parseInt(command[1]);
                    System.out.println(nah.unMark(i));
                    break;
                }
                case DELETE: {
                    int i = parseInt(command[1]);
                    System.out.println(nah.delete(i));
                    break;
                }

                case DUEON: {
                    if (command.length < 2 || command[1].trim().isEmpty()) {
                        throw new LackDescription(" Nahhh!!! Dueon needs time and date description");
                    }
                    System.out.println(nah.dueOn(command[1]));
                    break;
                }
                case TODO: {
                    if (command.length < 2 || command[1].trim().isEmpty()) {
                        throw new LackDescription(" Nahhhh!!! Todo needs description\n");
                    }
                    System.out.println(nah.add(new ToDos(command[1].trim())));
                    break;
                }
                case DEADLINE: {
                    if (command.length < 2 || command[1].trim().isEmpty()) {
                        throw new LackDescription(" Nahhhh!!! Deadline needs description\n");
                    }
                    String[] des = command[1].split("/by", 2);
                    if (des.length < 2 || des[1].trim().isEmpty()) {
                        throw new LackDescription((" Nahhhhhhh!!! We need deadline for Deadline\n"));
                    }
                    System.out.println(nah.add(new Deadlines(des[0].trim(), des[1].trim())));
                    break;
                }
                case EVENT: {
                    if (command.length < 2 || command[1].trim().isEmpty()) {
                        throw new LackDescription(" Nahhhh!!! Event needs description\n");
                    }
                    String[] des = command[1].split("/from", 2);
                    if (des.length < 2 || des[1].trim().isEmpty()) {
                        throw new LackDescription((" Nahhhhhhh!!! We need starting time for an Event\n"));
                    }
                    String[] time = des[1].split("/to", 2);
                    if (time.length < 2 || time[1].trim().isEmpty()) {
                        throw new LackDescription((" Nahhhhhhhhhhhhhh!!! We need ending time for an Event\n"));
                    }
                    System.out.println(nah.add(new Events(des[0].trim(),time[0].trim(),time[1].trim())));
                    break;
                }
                default:
                    throw new UnknownCommand();


                }
            } catch (LackDescription e) {
                System.out.println(e.getMessage());
            } catch (UnknownCommand e) {
                System.out.println(e.getMessage());
            } catch (InvalidTaskNumber e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(" Nah!!! Please give me a valid ordinal number for the task\n");
            } catch (DateTimeParseException e) {
                System.out.println(" Nahh!!! Time should be in the format yyyy-mm-dd hhmm, with valid date and time\n");
            }

            System.out.println("________________________________________________________________________________\n");
        }

    }
}
