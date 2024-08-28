import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Llama {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Storage storage;
    private Ui ui;

    public Llama() {
        this.ui = new Ui();
        this.storage = new Storage();
    }

    public static void addTodo(String remaining, Storage tasks) {
        if (remaining.isBlank()) {
            throw new LlamaException("Empty Todo Task?!? Might as well ask me to not add it in!");
        }

        tasks.addTask(new Todo(remaining, false));
    }

    public static void addDeadline(String remaining, Storage tasks) {
        if (remaining.isBlank()) {
            throw new LlamaException("Empty Deadline?!? What's the point of keeping track of nothing?");
        }

        String[] substringArr = remaining.split("/by ");
        try {
            LocalDateTime deadline = LocalDateTime.parse(substringArr[1].trim(), FORMATTER);
            tasks.addTask(new Deadline(substringArr[0], deadline, false));
        } catch (DateTimeParseException e) {
            throw new LlamaException("Invalid date given, please give in the format " +
                    "`deadline <name>/by yyyy-mm-dd HH:mm'");
        }
    }

    public static void addEvent(String remaining, Storage tasks) {
        if (remaining.isBlank()) {
            throw new LlamaException("Empty Event?!? You're planning to go nowhere with no one?");
        }

        String[] substringArr = remaining.split("/");
        String desc = substringArr[0];
        String startTimeStr = substringArr[1].substring(substringArr[1].indexOf(" ") + 1).trim();
        String endTimeStr = substringArr[2].substring(substringArr[2].indexOf(" ") + 1).trim();
        try {
            LocalDateTime startTime = LocalDateTime.parse(startTimeStr, FORMATTER);
            LocalDateTime endTime = LocalDateTime.parse(endTimeStr, FORMATTER);
            tasks.addTask(new Event(desc, startTime, endTime, false));
        } catch (DateTimeParseException e) {
            throw new LlamaException("Invalid date given, please give in the format " +
                    "`event <name> /from yyyy-MM-dd HH:mm /to  yyyy-MM-dd HH:mm'");
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        // Initializing message
        this.ui.displayWelcome();

        // Get user input
        boolean shouldContinue = true;
        while (shouldContinue) {
            String input = this.ui.getUserInput(sc);

            // Split input into command and remaining
            String command = input;
            String remaining = "";
            if (input.contains(" ")) {
                command = input.substring(0, input.indexOf(" "));
                remaining = input.substring(input.indexOf(" ") + 1);
            }

            if (command.equals("bye")) {                            // end program
                shouldContinue = false;
                sc.close();
            } else if (command.equals("list")) {                    // list out tasks
                this.storage.listAllTasks();
            } else if (command.equals("mark")) {                    // mark task
                int index = Integer.parseInt(remaining);
                try {
                    this.storage.markTask(index);
                } catch (InvalidTaskException e) {
                    this.ui.displayString(e.getMessage());
                }
            } else if (command.equals("unmark")) {                  // unmark task
                int index = Integer.parseInt(remaining);
                try {
                    this.storage.unmarkTask(index);
                } catch (InvalidTaskException e) {
                    this.ui.displayString(e.getMessage());
                }
            } else if (command.equals("delete")) {
                int index = Integer.parseInt(remaining);
                try {
                    this.storage.deleteTask(index);
                } catch (InvalidTaskException e) {
                    this.ui.displayString(e.getMessage());
                }
            } else {
                if (command.equals("todo")) {                       // add todo
                    try {
                        addTodo(remaining, this.storage);
                    } catch(LlamaException e) {
                        this.ui.displayString(e.getMessage());
                    }
                } else if (command.equals("deadline")) {            // add deadline
                    try {
                        addDeadline(remaining, this.storage);
                    } catch(LlamaException e) {
                        this.ui.displayString(e.getMessage());
                    }
                } else if (command.equals("event")) {               // add event
                    try {
                        addEvent(remaining, this.storage);
                    } catch(LlamaException e) {
                        this.ui.displayString(e.getMessage());
                    }
                } else {
                    try {
                        throw new LlamaException("Command not found, try again."); // really?
                    } catch (LlamaException e){
                        this.ui.displayString(e.getMessage());
                    }
                }
            }
        }

        // Exit message
        this.ui.displayBye();
    }

    public static void main(String[] args) {
        new Llama().run();
    }
}
