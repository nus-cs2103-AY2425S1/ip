import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MoiMoi {

    private static final String logo = "               __\n" +
            "              / >)\n" +
            "     _.----._/ /\n" +
            "    /         /\n" +
            " __/ (  | (  |\n" +
            "/__.-'|_|--|_|\n\n";
    private static final String moiMoiHeader = "⋆⭒˚.⋆MoiMoi⋆⭒˚.⋆\n";
    private static final String userHeader = "⋆⭒˚.⋆User⋆⭒˚.⋆";
    private static final String greeting = MoiMoi.logo + MoiMoi.moiMoiHeader
            + "Hello, master! How may I help you today? ><\n\n" + MoiMoi.userHeader;
    private static final String exitMessage = MoiMoi.moiMoiHeader
            + "I'll always be here for you~ See ya, master! ^^\n";
    private Storage storage;
    private ArrayList<Task> tasks;
    private Scanner sc;

    public MoiMoi(String path) {
        try {
            storage = new Storage(path);
            tasks = storage.load();
        } catch (MoiMoiException e) {
            System.out.println(e.getMessage());
            tasks = new ArrayList<Task>();
        }
        sc = new Scanner(System.in);
    }

    public void run() {

        System.out.println(MoiMoi.greeting);

        String input = sc.nextLine();
        String command = Parser.inputToCommand(input);

        while (!command.equals("bye")) {
            try {
                System.out.print("\n" + MoiMoi.moiMoiHeader);
                switch (command) {
                case "todo":
                    this.todo(Parser.inputToArgs(input));
                    break;
                case "deadline":
                    this.deadline(Parser.inputToArgs(input));
                    break;
                case "event":
                    this.event(Parser.inputToArgs(input));
                    break;
                case "delete":
                    this.delete(Parser.inputToArgs(input));
                    break;
                case "mark":
                    this.mark(Parser.inputToArgs(input));
                    break;
                case "unmark":
                    this.unmark(Parser.inputToArgs(input));
                    break;
                case "list":
                    this.list();
                    break;
                case "filter":
                    this.filter(Parser.inputToArgs(input));
                    break;
                default:
                    throw new InvalidCommandException();
                }
            } catch (MoiMoiException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("\n" + MoiMoi.userHeader);
            input = sc.nextLine();
            command = Parser.inputToCommand(input);
        }

        try {
            storage.save(tasks);
        } catch (StorageIOException e) {
            System.out.println(e.getMessage());
        }

        System.out.print("\n" + MoiMoi.exitMessage);

    }

    public void todo(String description) {
        Todo task = new Todo(description);
        this.tasks.add(task);
        System.out.println("Aight! Todo task added: " + task.stringUI()
                + "\nWe have " + tasks.size() + " tasks in the bag~");
    }

    public void deadline(String description) throws MoiMoiException {
        try {
            String[] descBy = description.split(" /by ", 2);
            String desc = descBy[0];
            String byString = descBy[1];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime by = LocalDateTime.parse(byString, formatter);
            Deadline task = new Deadline(desc, by);

            this.tasks.add(task);
            System.out.println("Got it! Deadline task added: " + task.stringUI()
                    + "\nWe have " + tasks.size() + " tasks in the bag~");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("date-time");
        }
    }

    public void event(String description) throws MoiMoiException {
        try {
            String[] descFromTo = description.split(" /from ", 2);
            String desc = descFromTo[0];
            String[] fromTo = descFromTo[1].split(" /to ", 2);
            String fromString = fromTo[0];
            String toString = fromTo[1];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime from = LocalDateTime.parse(fromString, formatter);
            LocalDateTime to = LocalDateTime.parse(toString, formatter);
            Event task = new Event(desc, from, to);

            this.tasks.add(task);
            System.out.println("Here you go! Event task added: " + task.stringUI()
                    + "\nWe have " + tasks.size() + " tasks in the bag~");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("date-time");
        }
    }

    public void delete(String index) throws InvalidIndexException {
        try {
            int i = Integer.parseInt(index) - 1;
            Task task = tasks.get(i);
            tasks.remove(i);
            System.out.println("Aju nice! I've got rid of this task: " + task.stringUI()
                    + "\nWe have " + tasks.size() + " tasks left in the bag~");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    public void mark(String index) throws InvalidIndexException {
        try {
            Task task = tasks.get(Integer.parseInt(index) - 1);
            task.mark();
            System.out.println("YAY!! One down!!\n" + task.stringUI());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    public void unmark(String index) throws InvalidIndexException {
        try {
            Task task = tasks.get(Integer.parseInt(index) - 1);
            task.unmark();
            System.out.println("Oof, it's OK! Let's get it done soon ;)\n" + task.stringUI());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    public void list() {
        System.out.println("Here's your list of tasks!");
        int index = 1;
        for (Task task : this.tasks) {
            System.out.println(index + ". " + task.stringUI());
            index = index + 1;
        }
    }

    public void filter(String dateString) throws InvalidDateTimeException {
        try {
            LocalDate date = LocalDate.parse(dateString);
            System.out.println("Here's your list of tasks, occurring on "
                    + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "!");
            int index = 1;
            for (Task task : this.tasks) {
                if (task.occurringOn(date)) {
                    System.out.println(index + ". " + task.stringUI());
                }
                index = index + 1;
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("date");
        }
    }

    public static void main(String[] args) {
        new MoiMoi("data/moimoi.txt").run();
    }

}
