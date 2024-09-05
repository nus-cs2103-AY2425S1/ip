import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDateTime;

public class Kafka {

    public ArrayList<Task> tasks = new ArrayList<>();

    public void greet() {
        String message = """
              Hello. Kafka here.
              We meet again.
            """;
        System.out.println(message);
    }

    public void goodbye() {
        String message = "  Farewell. I look forward to our next meeting, wherever destiny may lead us.";
        System.out.println(message);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("  Got it. I've added this task.");
        System.out.println("    " + task);
        System.out.println("  Now you have " + this.tasks.size() + " task(s) in the list.");
    }

    public void getList() {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = this.tasks.get(i);
            String listMessage = "  " + (i + 1) + "." + t;
            System.out.println(listMessage);
        }
    }

    public void mark(int taskNumber) {
        Task t = this.tasks.get(taskNumber - 1);
        t.markAsDone();
        String message = "  Good work on this task. Want a prize?:\n"
                + "    " + t;
        System.out.println(message);
    }

    public void unmark(int taskNumber) {
        Task t = this.tasks.get(taskNumber - 1);
        t.markAsNotDone();
        String message = "  Hurry up. This task is necessary for Elio's script:\n"
                + "    " + t;
        System.out.println(message);
    }

    public void delete(int taskNumber) {
        if (this.tasks.isEmpty()) {
            return;
        }
        Task t = this.tasks.get(taskNumber - 1);
        this.tasks.remove(taskNumber - 1);
        String message = "  I've removed this task:\n"
                + "    " + t;
        System.out.println(message);
        System.out.println("  Now you have " + this.tasks.size() + " task(s) in the list.");
    }

    public void getNewFile(String filePath) {
        File f = new File(filePath);
        try {
            f.getParentFile().mkdirs();
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String logo = """
                   __  __            __     _
                  |  |/  /  ____   _/  /_  | |      ____
                  |     /  / _  | |_    _| | |/ /  / _  |
                  |     \\ | |_| |   |  |   |   <  | |_| |
                  |__|\\__\\ \\____|   |__|   |_|\\ \\  \\____|
                """;
        Scanner scanner = new Scanner(System.in);
        Kafka kafka = new Kafka();
        boolean isExitChat = false;
        String filePath = "C:/Users/Nicholas/Downloads/Kafka.txt";
        Ui ui = new Ui();
        try {
            kafka.getNewFile(filePath);
            KafkaTextReader.printFileContents(filePath, kafka.tasks);
        } catch (FileNotFoundException e) {
            System.out.println("  " + e.getMessage());
        }

        ui.greet();
        while (!isExitChat) {
            String[] userInput = scanner.nextLine().trim().split(" ", 2);
            try {
                if (userInput[0] == null) {
                    continue;
                }
                if (userInput[0].equalsIgnoreCase("bye")) {
                    isExitChat = true;
                } else if (userInput[0].equalsIgnoreCase("list")) {
                    kafka.getList();
                } else if (userInput[0].equalsIgnoreCase("mark")) {
                    int taskNumber = Integer.parseInt(userInput[1]);
                    kafka.mark(taskNumber);
                    KafkaTextWriter.writeToFile(filePath, kafka.tasks);
                } else if (userInput[0].equalsIgnoreCase("unmark")) {
                    int taskNumber = Integer.parseInt(userInput[1]);
                    kafka.unmark(taskNumber);
                    KafkaTextWriter.writeToFile(filePath, kafka.tasks);
                } else if (userInput[0].equalsIgnoreCase("delete")) {
                    int taskNumber = Integer.parseInt(userInput[1]);
                    kafka.delete(taskNumber);
                    KafkaTextWriter.writeToFile(filePath, kafka.tasks);
                } else if (userInput[0].equalsIgnoreCase("todo")) {
                    if (userInput.length < 2) {
                        throw new KafkaException("It seems you've left the details blank. Even the simplest tasks need some direction, don't you think?");
                    }
                    Task todo = new Todo(userInput[1], false);
                    kafka.addTask(todo);
                    KafkaTextWriter.writeToFile(filePath, kafka.tasks);
                } else if (userInput[0].equalsIgnoreCase("deadline")) {
                    if (userInput.length < 2) {
                        throw new KafkaException("It seems you've left the details blank. Even the simplest tasks need some direction, don't you think?");
                    }
                    String[] deadlineSplit = userInput[1].split("/by ");
                    if (deadlineSplit.length < 2) {
                        throw new KafkaException("It appears the details for this deadline task are off. Let's give it another go, shall we?");
                    }
                    LocalDateTime by = LocalDateTimeConverter.getLocalDateTime(deadlineSplit[1]);
                    Task deadline = new Deadline(deadlineSplit[0], by, false);
                    kafka.addTask(deadline);
                    KafkaTextWriter.writeToFile(filePath, kafka.tasks);
                } else if (userInput[0].equalsIgnoreCase("event")) {
                    if (userInput.length < 2) {
                        throw new KafkaException("It seems you've left the details blank. Even the simplest tasks need some direction, don't you think?");
                    }
                    String[] eventSplit = userInput[1].split("/from | /to ");
                    if (eventSplit.length < 3) {
                        throw new KafkaException("It appears the details for this event task are off. Let's give it another go, shall we?");
                    }
                    LocalDateTime from = LocalDateTimeConverter.getLocalDateTime(eventSplit[1]);
                    LocalDateTime to = LocalDateTimeConverter.getLocalDateTime(eventSplit[2]);
                    Task event = new Event(eventSplit[0], from, to, false);
                    kafka.addTask(event);
                    KafkaTextWriter.writeToFile(filePath, kafka.tasks);
                } else {
                    throw new KafkaException("Hmm... I'm not sure what you're getting at. Care to enlighten me?");
                }
            } catch (KafkaException | IOException e) {
                ui.showError(e);
            } catch (DateTimeParseException e) {
                ui.incorrectDateDetails();
            }
        }
        kafka.goodbye();
    }
}
