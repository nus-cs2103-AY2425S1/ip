import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Llama {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Llama() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.taskList = storage.load();
        } catch (IOException e) {
            ui.displayString(e.getMessage()); // TODO: Make own exception for custom message
        }
    }

    /*
    public void addTodo(String remaining, TaskList taskList) throws IOException {
        if (remaining.isBlank()) {
            throw new LlamaException("Empty Todo Task?!? Might as well ask me to not add it in!");
        }

        Task newTask = new Todo(remaining, false);
        taskList.addTask(newTask , ui);
        storage.save(taskList);
    }

    public void addDeadline(String remaining, TaskList taskList) throws IOException{
        if (remaining.isBlank()) {
            throw new LlamaException("Empty Deadline?!? What's the point of keeping track of nothing?");
        }

        String[] substringArr = remaining.split("/by ");
        LocalDateTime deadline;
        try {
            deadline = LocalDateTime.parse(substringArr[1].trim(), FORMATTER);

        } catch (DateTimeParseException e) {
            throw new LlamaException("Invalid date given, please give in the format " +
                    "`deadline <name>/by yyyy-mm-dd HH:mm'");
        }

        Task newTask = new Deadline(substringArr[0], deadline, false);
        taskList.addTask(newTask, ui);
        storage.save(taskList);
    }

    public void addEvent(String remaining, TaskList taskList) throws IOException {

        if (remaining.isBlank()) {
            throw new LlamaException("Empty Event?!? You're planning to go nowhere with no one?");
        }

        String[] substringArr = remaining.split("/");
        String desc = substringArr[0];
        String startTimeStr = substringArr[1].substring(substringArr[1].indexOf(" ") + 1).trim();
        String endTimeStr = substringArr[2].substring(substringArr[2].indexOf(" ") + 1).trim();
        LocalDateTime startTime;
        LocalDateTime endTime;

        try {
            startTime = LocalDateTime.parse(startTimeStr, FORMATTER);
            endTime = LocalDateTime.parse(endTimeStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new LlamaException("Invalid date given, please give in the format " +
                    "`event <name> /from yyyy-MM-dd HH:mm /to  yyyy-MM-dd HH:mm'");
        }

        Task newTask = new Event(desc, startTime, endTime, false);
        taskList.addTask(newTask, ui);
        storage.save(taskList);
    }
    */
    public void run() {
        Scanner sc = new Scanner(System.in);

        // Initializing message
        this.ui.displayWelcome();

        // Get user input
        boolean shouldContinue = true;
        while (shouldContinue) {
            String input = this.ui.getUserInput(sc);


            try {
                Command command = Parser.parse(input);
                // Command returns false if program should stop running
                shouldContinue = command.execute(this.taskList, this.ui, this.storage);
            } catch (IOException | LlamaException e) {
                ui.displayString(e.getMessage());
            }

            /*
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
                this.taskList.listAllTasks(this.ui);
            } else if (command.equals("mark")) {                    // mark task
                int index = Integer.parseInt(remaining);
                try {
                    this.taskList.markTask(index, ui);
                    this.storage.save(this.taskList);
                } catch (InvalidTaskException | IOException e) {
                    this.ui.displayString(e.getMessage());
                }
            } else if (command.equals("unmark")) {                  // unmark task
                int index = Integer.parseInt(remaining);
                try {
                    this.taskList.unmarkTask(index, ui);
                    this.storage.save(this.taskList);
                } catch (InvalidTaskException | IOException e) {
                    this.ui.displayString(e.getMessage());
                }
            } else if (command.equals("delete")) {
                int index = Integer.parseInt(remaining);
                try {
                    this.taskList.deleteTask(index, ui);
                    this.storage.save(this.taskList);
                } catch (InvalidTaskException | IOException e) {
                    this.ui.displayString(e.getMessage());
                }
            } else {
                if (command.equals("todo")) {                       // add todo
                    try {
                        this.addTodo(remaining, this.taskList);
                    } catch(LlamaException | IOException e) {
                        this.ui.displayString(e.getMessage());
                    }
                } else if (command.equals("deadline")) {            // add deadline
                    try {
                        this.addDeadline(remaining, this.taskList);
                    } catch(LlamaException | IOException e) {
                        this.ui.displayString(e.getMessage());
                    }
                } else if (command.equals("event")) {               // add event
                    try {
                        this.addEvent(remaining, this.taskList);
                    } catch(LlamaException | IOException e) {
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
            */
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Llama().run();
    }
}
