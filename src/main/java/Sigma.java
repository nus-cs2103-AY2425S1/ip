import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sigma {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> items = new ArrayList<>();

        load();
        File data = new File("data.txt");

        // Read data from file and input into items
        items = read(data, items);

        enum Commands {
            TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE
        }

        print("Hello! I'm SIGMA! \nLooking forward to slaying with you!\nWhat do you need today?" +
                "\nYou can add tasks with \"todo\", \"deadline\", \"event\" or view tasks with \"list\"." +
                "\nYou can also mark tasks as done with \"mark\" or \"unmark\" them." +
                "\nYou can also delete tasks with \"delete\"." +
                "\nIf you're done, just type \"bye\" to exit.");

        String userPrompt = input.nextLine();
        while (!userPrompt.equalsIgnoreCase("bye")) {
            try {
                String[] split = userPrompt.split(" ", 2);
                String command = split[0].toUpperCase(); // Convert input to uppercase to match enum
                Commands cmd;
                try {
                    cmd = Commands.valueOf(command); // Convert to Commands enum
                } catch (IllegalArgumentException e) {
                    throw new SigmaException("What the sigma? I don't understand! Try again! Enter " +
                            "\"todo\", \"deadline\", \"event\", \"list\", \"mark\", \"unmark\" or \"bye\"!");
                }

                switch (cmd) {
                case BYE:
                    exit();
                    System.exit(0);
                    break;
                case LIST:
                    StringBuilder s = new StringBuilder();
                    for (int i = 1; i <= items.size(); i++) {
                        s.append(i + ". " + items.get(i - 1).toString() + "\n");
                    }
                    if (items.size() == 0) {
                        print("What the sigma? You have no tasks!");
                    } else {
                        print("You want a list? You got it!\n" + s.toString());
                    }
                    break;
                case MARK:
                    if (split.length > 1) {
                        int index = Integer.parseInt(split[1]);
                        if (index > 0 && index <= items.size()) {
                            Task item = items.get(index - 1);
                            if (item.getStatusString() == "X") {
                                throw new SigmaException("What the sigma? Task already marked!");
                            }
                            item.setStatus(true);
                            print(String.format("SLAYYY! I'm going to mark this done for you:\n [%s] %s", item.getStatusString(), item.getDesc()));
                        } else {
                            throw new SigmaException("Invalid task number!");
                        }
                    } else {
                        throw new SigmaException("Bro's dreaming. Add a number bozo!");
                    }
                    break;
                case UNMARK:
                    if (split.length > 1) {
                        int index = Integer.parseInt(split[1]);
                        if (index > 0 && index <= items.size()) {
                            Task item = items.get(index - 1);
                            if (item.getStatusString() == " ") {
                                throw new SigmaException("What the sigma? Task already unmarked!");
                            }
                            item.setStatus(false);
                            print(String.format("Dang, I'm going to unmark this for you:\n [%s] %s", item.getStatusString(), item.getDesc()));
                        } else {
                            throw new SigmaException("What the skibidi? Invalid task number!");
                        }
                    } else {
                        throw new SigmaException("Bro's dreaming. Add a number bozo!");
                    }
                    break;
                case TODO:
                    if (split.length < 2) {
                        throw new SigmaException("???? You're missing the task! Write \"todo <task>\"!");
                    }
                    ToDoTask toDoTask = new ToDoTask(split[1]);
                    items.add(toDoTask);
                    print("Productive! Added: \n" + toDoTask.toString()
                            + "\nNow you have " + items.size() + " tasks in the list!");
                    break;

                case DEADLINE:
                    if (split.length < 2) {
                        throw new SigmaException("What the sigma? You're missing the task! " +
                                "Write \"deadline <task> /by <deadline>\"!");
                    }
                    String[] deadlineSplit = split[1].split(" /by ");
                    if (deadlineSplit.length < 2) {
                        throw new SigmaException("What the sigma? You're missing the deadline! " +
                                "Write \"deadline <task> /by <deadline>\"!");
                    }
                    LocalDateTime dateTime = parse(deadlineSplit[1]);
                    DeadlineTask deadlineTask = new DeadlineTask(deadlineSplit[0], dateTime);
                    items.add(deadlineTask);
                    print("Wow! Keeping yourself busy! Added: \n" + deadlineTask.toString()
                            + "\nNow you have " + items.size() + " tasks in the list!");
                    break;
                case EVENT:
                    if (split.length < 2) {
                        throw new SigmaException("What the sigma? You're missing the task! " +
                                "Write \"event <task> /from <start time> /to <end time>\"!");
                    }
                    String[] eventSplit = split[1].split(" /from ");
                    if (eventSplit.length < 2) {
                        throw new SigmaException("What the sigma? You're missing the timing!");
                    }
                    String[] timing = eventSplit[1].split(" /to ");
                    if (timing.length < 2) {
                        throw new SigmaException("What the sigma? You're missing the end timing!");
                    }
                    LocalDateTime from = parse(timing[0]);
                    LocalDateTime to = parse(timing[1]);
                    EventTask eventTask = new EventTask(eventSplit[0], from, to);
                    items.add(eventTask);
                    print("You're a busy bee! Added: \n" + eventTask.toString()
                            + "\nNow you have " + items.size() + " tasks in the list!");
                    break;

                case DELETE:
                    if (split.length < 2) {
                        throw new SigmaException("What the sigma? You're missing the task! " +
                                "Write \"delete <task>\"!");
                    }
                    int index = Integer.parseInt(split[1]);
                    if (index > 0 && index <= items.size()) {
                        Task item = items.get(index - 1);
                        items.remove(index - 1);
                        print(String.format("Dang, I'm going to delete this for you:\n %s\n " +
                                "Now you have %d tasks!", item.toString(), items.size()));
                    } else {
                        throw new SigmaException("What the skibidi? Invalid task number!");
                    }
                    break;

                default:
                    throw new SigmaException("That's crazy - I don't understand! Try again! Enter " +
                            "\"todo\", \"deadline\", \"event\", \"list\", \"mark\", \"unmark\" or \"bye\"!");
                }
            } catch (SigmaException e) {
                print(e.getMessage());
            } catch (NumberFormatException e) {
                print("What the sigma? I need a number!");
            } finally {
                // Do nothing
            }

            // Write data to file
            write(data, items);

            userPrompt = input.nextLine();
        }
        exit();
    }

    private static void exit() {
        print("What the sigma? You're leaving so soon? Bye chat, see you again!");
    }

    private static void print(String message) {
        line();
        System.out.println(message);
        line();
    }

    private static void line() {
        String line = "----------------------------------------------------------------------------------------------";
        System.out.println(line);
    }

    private static Task createTask(String type, String desc, String date) throws SigmaException {
        Task item;
        switch (type) {
        case "T":
            item = new ToDoTask(desc);
            break;
        case "D":
            LocalDateTime dateTime;
            try {
                dateTime = LocalDateTime.parse(date.strip(), DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
            } catch (DateTimeParseException e) {
                throw new SigmaException("What the sigma? File contains invalid date format!");
            }
            item = new DeadlineTask(desc, dateTime);
            break;
        case "E":
            String[] dates = date.split("-");
            System.out.println(dates[0]);
            System.out.println(dates[1]);
            LocalDateTime from;
            LocalDateTime to;
            try {
                from = LocalDateTime.parse(dates[0].strip(), DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
                to = LocalDateTime.parse(dates[1].strip(), DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
            } catch (DateTimeParseException e) {
                throw new SigmaException("What the sigma? File not saved correctly, invalid date format!");
            }
            item = new EventTask(desc, from, to);
            break;
        default:
            throw new SigmaException("What the sigma? Invalid task type!");
        }
        return item;
    }


    private static void load() {
        // Creates a file to store data if none exists
        try {
            Files.createFile(Paths.get("data.txt"));
        } catch (IOException e) {
            System.out.println("SLAY! Loading up your saved tasks!");
        }
    }

    private static void write(File data, ArrayList<Task> items) {
        try {
            FileWriter writer = new FileWriter(data);
            for (Task item : items) {
                writer.write(String.format("%s | %s | %s | %s \n",
                        item.getTaskType(), item.getStatusString(), item.getDesc(),
                        item instanceof DeadlineTask
                                ? ((DeadlineTask) item).getDate()
                                : item instanceof EventTask
                                ? ((EventTask) item).getFrom() + " - " + ((EventTask) item).getTo()
                                : ""));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }

    private static LocalDateTime parse(String timing) throws SigmaException {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(timing, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new SigmaException("What the sigma? Invalid date format! " +
                    "Write the date in the format YYYY-MM-DD HHmm!");
        }
        return dateTime;
    }

    private static ArrayList<Task> read(File data, ArrayList<Task> items) {
        try {
            Scanner fileInput = new Scanner(data);
            while (fileInput.hasNextLine()) {
                // Loading from file
                String line = fileInput.nextLine();
                String[] split = line.split(" \\| ");
                String type = split[0];
                boolean status = split[1].equals("X");
                String desc = split[2];
                String date = split[3];
                Task item = createTask(type, desc, date);
                item.setStatus(status);
                items.add(item);
            }
            fileInput.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading file.");
            e.printStackTrace();
        } catch (SigmaException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }
}
