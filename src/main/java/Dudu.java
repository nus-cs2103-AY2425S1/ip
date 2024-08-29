import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

public class Dudu {
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, HELP
    }

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Storage s = new Storage("./data/dudu.txt");
        tasks = s.load();

        String welcomeMessage = LineWrapper.wrap("Hello! I'm dudu\n"
                + "What can I do for you?");
        String goodbyeMessage = LineWrapper.wrap("Bye. Hope to see you again soon!");
        System.out.println(welcomeMessage);

        Scanner sc = new Scanner(System.in);
        boolean terminate = false;

        while (!terminate) {
            try {
                String[] input = sc.nextLine().split(" ", 2);
                Command command = Command.valueOf(input[0].trim().toUpperCase());
                switch (command) {
                    case BYE: {
                        System.out.println(goodbyeMessage);
                        terminate = true;
                        break;
                    } case LIST: {
                        printTasks();
                        break;
                    } case HELP: {
                        String output = LineWrapper.wrap("hehe not done");
                        System.out.println(output);
                        break;
                    } case TODO: {
                        String description = getContent(input);
                        ToDo task = new ToDo(description);
                        addTask(task);
                        break;
                    } case DEADLINE: {
                        String content = getContent(input);
                        if (!content.matches(".*/by.*")) {
                            throw new InvalidFormatException("WHAT Please use deadline [description] /by [time]");
                        }
                        String[] details = content.split("/by", 2);
                        if (details[0].trim().isEmpty()) {
                            throw new MissingDescriptionException("Missing description after deadline");
                        }
                        if (details.length == 1 || details[1].trim().isEmpty()) {
                            throw new MissingDateTimeException("Missing by time");
                        }
                        LocalDate by = LocalDate.parse(details[1].trim());
                        Deadline task = new Deadline(details[0].trim(), by);
                        addTask(task);
                        break;
                    } case EVENT: {
                        String content = getContent(input);
                        if (!content.matches(".*/from.*/to.*")) {
                            throw new InvalidFormatException("Please use event [description] /from [time] /to [time]");
                        }
                        String[] details = content.split("/from", 2);
                        if (details[0].trim().isEmpty()) {
                            throw new MissingDescriptionException("Missing description after event");
                        }
                        String description = details[0].trim();
                        if (details.length == 1 || details[1].trim().isEmpty()) {
                            throw new MissingDateTimeException("Missing from/to time");
                        }
                        String[] date = details[1].split("/to", 2);
                        if (date.length <= 1 || date[0].trim().isEmpty() || date[1].trim().isEmpty()) {
                            throw new MissingDateTimeException("Missing from/to time");
                        }
                        LocalDate from = LocalDate.parse(date[0].trim());
                        LocalDate to = LocalDate.parse(date[1].trim());
                        Event task = new Event(description, from, to);
                        addTask(task);
                        break;
                    } case MARK: {
                        int index = getIndex(input);
                        markTask(index);
                        break;
                    } case UNMARK: {
                        int index = getIndex(input);
                        unmarkTask(index);
                        break;
                    } case DELETE: {
                        int index = getIndex(input);
                        deleteTask(index);
                        break;
                    } default: {
                        String output = LineWrapper.wrap("Please use help to get the list of commands");
                        System.out.println(output);
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            } catch (MissingDescriptionException e) {
                System.out.println(e);
            } catch (MissingDateTimeException e) {
                System.out.println(e);
            } catch (InvalidFormatException e) {
                System.out.println(e);
            } catch (java.io.IOException e) {
                System.out.println(e);
            }
        }
    }

    public static String getContent(String[] input) throws MissingDescriptionException {
        if (input.length <= 1 || input[1].trim().isEmpty()) {
            throw new MissingDescriptionException("Please include a description");
        }
        return input[1].trim();
    }

    public static void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println(LineWrapper.wrap("No tasks"));
        } else {
            StringBuilder output = new StringBuilder("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                output.append("\n" + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println(LineWrapper.wrap(output.toString()));
        }
    }


    public static void addTask(Task task) throws java.io.IOException {
        tasks.add(task);
        FileWriter fw = new FileWriter("./data/dudu.txt", true);
        fw.write("\n" + task.formatString());
        fw.close();
        String output = LineWrapper.wrap(String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list.", task, tasks.size()));
        System.out.println(output);
    }

    public static int getIndex(String[] input) throws MissingDescriptionException {
        if (input.length <= 1 || input[1].replaceAll("\\D+", "").isEmpty()) {
            throw new MissingDescriptionException("Please input a number");
        }
        int index = Integer.parseInt(input[1].replaceAll("\\D+", "")) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Please input a valid count");
        }
        return index;
    }

    public static void markTask(int index) throws IOException {
        tasks.get(index).markCompleted();
        rewriteFile();
        String output = LineWrapper.wrap(String.format("Nice! I've marked this task as done:\n    %s", tasks.get(index)));
        System.out.println(output);
    }

    public static void unmarkTask(int index) throws IOException {
        tasks.get(index).markUncompleted();
        rewriteFile();
        String output = LineWrapper.wrap(String.format("OK, I've marked this task as not done yet:\n    %s", tasks.get(index)));
        System.out.println(output);
    }

    public static void deleteTask(int index) throws IOException {
        Task task = tasks.get(index);
        tasks.remove(index);
        rewriteFile();
        String output = LineWrapper.wrap(String.format("Noted. I've removed this task:\n    %s", task));
        System.out.println(output);
    }

    public static void rewriteFile() throws IOException {
        FileWriter fw = new FileWriter("./data/dudu.txt");
        for (Task task : tasks) {
            fw.write(String.format("%s\n", task.formatString()));
        }
        fw.close();
    }
}
