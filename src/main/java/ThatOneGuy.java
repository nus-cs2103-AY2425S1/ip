import java.time.format.DateTimeFormatter;
import java.util.*;
import tasks.*;
import java.io.*;
import java.time.*;

public class ThatOneGuy {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String name = "that one guy";
        System.out.println("I'm " + name + ".");
        System.out.println("Make it quick, I don't have much time.");

        ThatOneGuy guy = new ThatOneGuy();
        guy.readData();
        guy.cmd();
    }

    private void readData() {
        try {
            File dir = new File("data/");
            if (!dir.exists()) {
                boolean created = dir.mkdir();
            }

            File f = new File("data/guy.txt");
            if (!f.exists()) {
                boolean created = f.createNewFile();
            }

            Scanner read = new Scanner(f);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            while (read.hasNextLine()) {
                String[] line = read.nextLine().split("\\s*\\|\\s*");
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
            }

            read.close();
        } catch (GuyException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("I got a problem, and it sure as f*** ain't my fault! It says: \n" + e.getMessage());
        }
    }

    private void writeData() {
        try {
            FileWriter writer = new FileWriter("data/guy.txt");
            for (Task task : tasks) {
                writer.write(task.saveFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("I got a problem, and it sure as f*** ain't my fault! It says: \n" + e.getMessage());
        }
    }

    private void cmd() {
        Scanner sc = new Scanner(System.in);
        String cmd, args;
        while (sc.hasNext()) {
            String[] input = splitCmd(sc.nextLine());
            cmd = input[0];
            args = input[1];

            try {
                switch (cmd) {
                    case "bye":
                        System.out.println("Whatever. Hope you never come back.");
                        sc.close();
                        return;
                    case "list":
                        list();
                        break;
                    case "mark":
                        markTask(args);
                        break;
                    case "unmark":
                        unmarkTask(args);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        addTask(cmd, args);
                        break;
                    case "delete":
                        deleteTask(args);
                        break;
                    default:
                        throw new GuyException("Maybe put in an actual command next time, shitass.");
                }
                writeData();
            } catch (GuyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String[] splitCmd(String input) {
        String[] raw = input.split(" ", 2);
        String[] output = {"", ""};
        for (int i = 0; i < raw.length; ++i) {
            output[i] = raw[i].trim();
        }
        output[0] = output[0].toLowerCase();
        return output;
    }

    private void list() {
        int len = tasks.size();
        if (len == 0) {
            System.out.println("You really don't have anything better to do?");
        }
        else {
            System.out.println("Here are your damned tasks. Complete them or something.");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    private void markTask(String input) throws GuyException{
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
            System.out.println(tasks.get(id).toString());
        }
    }

    private void unmarkTask(String input) throws GuyException{
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
            System.out.println(tasks.get(id).toString());
        }
    }

    private void addTask(String cmd, String input) throws GuyException{
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
            System.out.println("Fine. Added this lousy task:");
            System.out.println(task);
            System.out.println("That's " + tasks.size() + " tasks for your ass to handle.");
        } catch (GuyException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeException e) {
            System.out.println("I got a problem, and it sure as f*** ain't my fault! It says: \n" + e.getMessage());
        }
    }

    private void deleteTask(String input) {
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
                System.out.println("There goes this dumb task:");
                System.out.println(task);
                System.out.println("Your ass still needs to handle " + tasks.size() + " more tasks.");
            }
        } catch (GuyException e) {
            System.out.println(e.getMessage());
        }
    }



}
