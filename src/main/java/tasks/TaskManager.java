package tasks;

import java.time.*;
import java.time.format.DateTimeFormatter;
import exception.GuyException;
import java.util.*;

import storage.Storage;

public class TaskManager {
    private static TaskManager tm;
    private final List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public static TaskManager getInstance() {
        if (tm == null) {
            tm = new TaskManager();
        }
        return tm;
    }

    public void loadData(String data) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String[] line = data.split("\\s*\\|\\s*");
            String type = line[0];

            Task task = switch (type) {
                case "T" -> new ToDo(line[2]);
                case "D" -> new Deadline(line[2], LocalDateTime.parse(line[3], dtf));
                case "E" -> new Event(line[2], LocalDateTime.parse(line[3], dtf), LocalDateTime.parse(line[4], dtf));
                default -> throw new GuyException("Why did you give me a file with an invalid line, you dingus...");
            };

            if (line[1].equals("1")) {
                task.mark();
            }
            tasks.add(task);

        } catch (GuyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void list() {
        int len = tasks.size();
        if (len == 0) {
            System.out.println("You really don't have anything better to do?");
        }
        else {
            System.out.println("Here are your damned tasks. Complete them or something.");
            for (int i = 0; i < len; i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public void markTask(String input) throws GuyException {
        if (tasks.isEmpty()) {
            throw new GuyException("You have nothing to do. You lazy or what?");
        } else if (input.isEmpty()) {
            throw new GuyException("What do you want me to mark, you moron!?");
        }
        String rest = input.replaceAll("\\D+", "");
        if (rest.isEmpty()) {
            throw new GuyException("I can't work without a valid input. Screw you.");
        }
        int id = Integer.parseInt(rest) - 1;
        if (id < 0 || id >= tasks.size()) {
            throw new GuyException("Consider picking a number that's actually in range.");
        } else {
            if (tasks.get(id).isComplete()) {
                System.out.println("You dingus. This task was already done:");
            } else {
                tasks.get(id).mark();
                System.out.println("Eh. Consider this task done:");
            }
            Storage.saveData();
            System.out.println(tasks.get(id).toString());
        }
    }

    public void unmarkTask(String input) throws GuyException {
        if (tasks.isEmpty()) {
            throw new GuyException("You have nothing to do. You lazy or what?");
        } else if (input.isEmpty()) {
            throw new GuyException("What do you want me to unmark, you moron!?");
        }
        String rest = input.replaceAll("\\D+", "");
        if (rest.isEmpty()) {
            throw new GuyException("I can't work without a valid input. Screw you.");
        }
        int id = Integer.parseInt(rest) - 1;
        if (id < 0 || id >= tasks.size()) {
            throw new GuyException("Consider picking a number that's actually in range.");
        } else {
            if (!tasks.get(id).isComplete()) {
                System.out.println("You dingus. This task still hasn't been done:");
            } else {
                tasks.get(id).unmark();
                System.out.println("Sucks to be you. Looks like you haven't done this task:");
            }
            Storage.saveData();
            System.out.println(tasks.get(id).toString());
        }
    }

    public void addTask(String cmd, String input) throws GuyException {
        try {
            if (input.isEmpty()) {
                throw new GuyException("You really think I can add an EMPTY TASK!?");
            }
            Task task;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            switch (cmd) {
                case "todo":
                    task = new ToDo(input);
                    break;
                case "deadline":
                    if (!input.contains("/by") || input.indexOf("/by") == input.length() - 3) {
                        throw new GuyException("That isn't even a valid description!");
                    }
                    String[] splitted = input.split("/by", 2);
                    task = new Deadline(splitted[0].trim(), LocalDateTime.parse(splitted[1].trim(), dtf));
                    break;
                case "event":
                    if (
                            !input.contains("/from") ||
                                    !input.contains("/to") ||
                                    input.indexOf("/from") == input.length() - 5 ||
                                    input.indexOf("/to") == input.length() - 2
                    ) throw new GuyException("That isn't even a valid description!");
                    String[] splitFrom = input.split("/from", 2);
                    String[] splitTo = splitFrom[1].split("/to", 2);
                    task = new Event(splitFrom[0].trim(),
                            LocalDateTime.parse(splitTo[0].trim(), dtf),
                            LocalDateTime.parse(splitTo[1].trim(), dtf));
                    break;
                default:
                    throw new GuyException("That's not even a task type!");
            }
            tasks.add(task);
            Storage.saveData();
            System.out.println("Fine. Added this lousy task:");
            System.out.println(task);
            System.out.println("That's " + tasks.size() + " tasks for your ass to handle.");
        } catch (GuyException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeException e) {
            System.out.println("I got a problem, and it sure as f*** ain't my fault! It says: \n" + e.getMessage());
        }
    }

    public void deleteTask(String input) {
        try {
            if (tasks.isEmpty()) {
                throw new GuyException("You have nothing to delete. You dumb or what?");
            } else if (input.isEmpty()) {
                throw new GuyException("What do you want me to delete, you moron!?");
            }
            String rest = input.replaceAll("\\D+", "");
            if (rest.isEmpty()) {
                throw new GuyException("I can't work without a valid input. Screw you.");
            }
            int id = Integer.parseInt(rest) - 1;
            if (id < 0 || id >= tasks.size()) {
                throw new GuyException("Consider picking a number that's actually in range.");
            } else {
                Task task = tasks.get(id);
                tasks.remove(id);
                Storage.saveData();
                System.out.println("There goes this dumb task:");
                System.out.println(task);
                System.out.println("Your ass still needs to handle " + tasks.size() + " more tasks.");
            }
        } catch (GuyException e) {
            System.out.println(e.getMessage());
        }
    }
}
