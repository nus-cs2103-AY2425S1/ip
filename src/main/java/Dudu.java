import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Dudu {
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, HELP
    }

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String welcomeMessage = LineWrapper.wrap("Hello! I'm dudu\n"
                                                + "What can I do for you?");
        String goodbyeMessage = LineWrapper.wrap("Bye. Hope to see you again soon!");
        System.out.println(welcomeMessage);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            try {
                String[] input = sc.nextLine().split(" ", 2);
                Command command = Command.valueOf(input[0].trim().toUpperCase());
                switch (command) {
                    case BYE: {
                        System.out.println(goodbyeMessage);
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
                    }
                    case DEADLINE: {
                        String content = getContent(input);
                        if (!content.matches(".*/by.*")) {
                            throw new InvalidFormatException("Please use deadline [description] /by [deadline]");
                        }
                        String[] details = content.split("/by", 2);
                        if (details[0].trim().isEmpty()) {
                            throw new MissingDescriptionException("Missing description after deadline");
                        }
                        if (details.length == 1 || details[1].trim().isEmpty()) {
                            throw new MissingDateTimeException("Missing by time");
                        }
                        Deadline task = new Deadline(details[0].trim(), details[1].trim());
                        addTask(task);
                        break;
                    }
                    case EVENT: {
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
                            throw new MissingDateTimeException("Missing from / to time");
                        }
                        String[] date = details[1].split("/to", 2);
                        if (date.length <= 1 || date[0].trim().isEmpty() || date[1].trim().isEmpty()) {
                            throw new MissingDateTimeException("Missing from / to time");
                        }
                        Event task = new Event(description, date[0].trim(), date[1].trim());
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
            }
        }

        System.out.println(goodbyeMessage);
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

    public static void addTask(Task task) {
        tasks.add(task);
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

    public static void markTask(int index) {
        tasks.get(index).markCompleted();
        String output = LineWrapper.wrap(String.format("Nice! I've marked this task as done:\n    %s", tasks.get(index)));
        System.out.println(output);
    }

    public static void unmarkTask(int index) {
        tasks.get(index).markUncompleted();
        String output = LineWrapper.wrap(String.format("OK, I've marked this task as not done yet:\n    %s", tasks.get(index)));
        System.out.println(output);
    }

    public static void deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        String output = LineWrapper.wrap(String.format("Noted. I've removed this task:\n    %s", task));
        System.out.println(output);
    }
}
