package impl.ui;

import exceptions.InvalidCommandException;
import impl.storage.TaskList;
import interfaces.Deadlines;
import interfaces.Events;
import interfaces.Task;
import interfaces.ToDos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    TaskList list;

    /**
     * Initialises Parser.
     *
     * @param list TaskList used to store tasks.
     */
    public Parser(TaskList list) {
        this.list = list;
    }

    public static Command checkCommand(String in, int size) throws InvalidCommandException {
        String test = in.toLowerCase().split(" ")[0];
        switch (test) {
        case "tag":
            handleIntInput(in, size);
            return Command.Tag;
        case "list":
            return Command.List;
        case "todo":
            checkTodo(in);
            return Command.Todo;
        case "deadline":
            checkDeadline(in);
            return Command.Deadline;
        case "event":
            checkEvent(in);
            return Command.Event;
        case "mark":
            handleIntInput(in, size);
            return Command.Mark;
        case "unmark":
            handleIntInput(in, size);
            return Command.Unmark;
        case "delete":
            handleIntInput(in, size);
            return Command.Delete;
        case "find":
            return Command.Find;
        default:
            return Command.Unknown;
        }
    }

    private static void handleIntInput(String in, int size) throws InvalidCommandException {
        int i = Integer.parseInt(in.split(" ")[1]) - 1;
        if (i >= size) {
            throw new InvalidCommandException("Out of index");
        }
    }

    private static void checkTodo(String in) throws InvalidCommandException {
        if (in.length() <= 4) {
            throw new InvalidCommandException("Please Enter Something for Todo!");
        }
    }

    private static void checkDeadline(String in) throws InvalidCommandException {
        if (in.split("/by ").length < 2) {
            throw new InvalidCommandException("Wrong Syntax! Use /by for Deadlines!");
        }
    }

    private static void checkEvent(String in) throws InvalidCommandException {
        String[] eventSplit = in.split("/from ");
        if (eventSplit.length < 2 || eventSplit[1].split("/to ").length < 2) {
            throw new InvalidCommandException("Wrong Syntax! Use /from and /to for Events!");
        }
    }

    /**
     * Handles all String input by the user.
     * Creates/mark/unmark/delete Tasks based on input
     *
     * @param in Input String.
     * @return
     */
    public String handleInput(String in) {
        assert !in.isEmpty();
        Command cmd;
        try {
            cmd = Parser.checkCommand(in, list.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            cmd = Parser.Command.Error;
        }

        switch (cmd) {
        case Tag -> {
            return handleTag(in);
        }
        case List -> {
            return handleList();
        }
        case Todo -> {
            return handleToDo(in);
        }
        case Deadline -> {
            return handleDeadline(in);
        }
        case Event -> {
            return handleEvent(in);
        }
        case Mark -> {
            return handleMark(in);
        }
        case Unmark -> {
            return handleUnmark(in);
        }
        case Delete -> {
            return handleDelete(in);
        }
        case Find -> {
            return handleFind(in);
        }
        case Error -> {
            System.out.println("Error! Please try again :(");
            return "Error! Please try again :(";
        }
        default -> {
            System.out.println("Unknown Command Detected!");
            return "Unknown Command Detected!";
        }
        }
    }


    private String handleList() {
        System.out.println("These are all your unfinished tasks:");
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            text.append((i + 1)).append(".").append(list.get(i).toString()).append("\n");
        }
        System.out.println(text);
        return text.toString();
    }

    private String handleToDo(String in) {
        String description = in.substring(5);
        Task add = new ToDos(description);
        list.add(add);
        System.out.println("added: " + add.toString());
        return "added: " + add.toString();
    }

    private String handleDeadline(String in) {
        String[] deadlineSplit = in.split(" /by ");
        String description = deadlineSplit[0];
        String by = handleDate(deadlineSplit[1]);
        Task add = new Deadlines(description, by);
        list.add(add);
        System.out.println("added: " + add.toString());
        return "added: " + add.toString();
    }

    private String handleEvent(String in) {
        String[] eventSplit = in.split(" /from ");
        String description = eventSplit[0];
        String from = handleDate(eventSplit[1].split(" /to ")[0]);
        String by = handleDate(eventSplit[1].split("/to ")[1]);
        Task add = new Events(description, by, from);
        list.add(add);
        System.out.println("added: " + add.toString());
        return "added: " + add.toString();
    }

    private String handleMark(String in) {
        int i = Integer.parseInt(in.split(" ")[1]) - 1;
        list.get(i).setDone();
        String text = "I have marked this task as done\n" + (i + 1) + "." + list.get(i).toString();
        System.out.println(text);
        return text;
    }

    private String handleUnmark(String in) {
        int i = Integer.parseInt(in.split(" ")[1]) - 1;
        list.get(i).setUnDone();
        String text = "I have marked this task as undone\n" + (i + 1) + "." + list.get(i).toString();
        System.out.println(text);
        return text;
    }

    private String handleDelete(String in) {
        int i = Integer.parseInt(in.split(" ")[1]) - 1;
        String text = "I have removed this task from the list\n" + (i + 1) + "." + list.get(i).toString();
        System.out.println(text);
        list.remove(i);
        return text;
    }

    private String handleFind(String in) {
        String find = in.split(" ", 2)[1];
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDescription().toLowerCase().contains(find.toLowerCase())) {
                // The description contains what we are finding
                text.append((i + 1)).append(".").append(list.get(i).toString()).append("\n");
            }
        }
        System.out.println(text);
        return text.toString();
    }

    private String handleDate(String date) {
        try {
            LocalDate d1 = LocalDate.parse(date);
            date = d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (Exception e) {
            System.out.println("Not in accepted date format, saving it as is");
        }
        return date;
    }

    private String handleTag(String in) {
        try {
            String[] tags = in.split(" ", 3);
            int i = Integer.parseInt(tags[1]) - 1;
            list.setTag(i, tags[2]);
            return "Added tag " + tags[2] + " to " + list.get(i).toString();
        } catch (Exception e) {
            System.out.println("Tag format not correct. Must be in format: tag 1 XXX");
            return "Tag format not correct. Must be in format: tag 1 XXX";
        }

    }

    enum Command {
        List,
        Todo,
        Deadline,
        Event,
        Mark,
        Unmark,
        Delete,
        Find,
        Error,
        Unknown,
        Tag
    }


}
