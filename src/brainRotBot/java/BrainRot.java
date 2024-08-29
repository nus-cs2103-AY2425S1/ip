import exceptions.*;

import java.io.IOException;
import java.util.Scanner;

public class BrainRot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public BrainRot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            System.out.println(filePath);
            tasks = new TaskList(storage.load());
        } catch (UnknownLoadingError e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        Scanner reader = new Scanner(System.in);

        while (true) {
            String userInput = reader.nextLine();
            String[] parsedInput = Parser.parse(userInput);
            String action = parsedInput[0];
            String details = parsedInput[1];

            try {
                switch (action) {
                    case "list":
                        listTasks();
                        break;
                    case "bye":
                        exit();
                        return;
                    case "mark":
                        markTask(details);
                        break;
                    case "unmark":
                        unmarkTask(details);
                        break;
                    case "delete":
                        deleteTask(details);
                        break;
                    case "add":
                        addTask(details);
                        break;
                    default:
                        throw new UnknownCommandException("as");
                }

            } catch (UnknownCommandException | UnknownActivityException | IOException e) {
                ui.showCommandError();
            }
        }
    }

    private void listTasks() {
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i).toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    private void markTask(String details) throws IOException {
        int markIndex = Integer.parseInt(details) - 1;
        tasks.getTask(markIndex).mark();
        storage.save(tasks.getTasks());
        System.out.println("Nice! I've marked this task as done:\n  " + tasks.getTask(markIndex).toString());
    }

    private void unmarkTask(String details) throws IOException {
        int unmarkIndex = Integer.parseInt(details) - 1;
        tasks.getTask(unmarkIndex).unmark();
        storage.save(tasks.getTasks());
        System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.getTask(unmarkIndex).toString());
    }

    private void deleteTask(String details) throws IOException {
        int deleteIndex = Integer.parseInt(details) - 1;
        System.out.println("Noted. I've removed this task:\n  " + tasks.getTask(deleteIndex).toString());
        tasks.removeTask(deleteIndex);
        storage.save(tasks.getTasks());
    }

    private void addTask(String details) throws UnknownCommandException, UnknownActivityException, IOException {
        Task newTask;
        if (details.startsWith("todo")) {
            newTask = new ToDo(details.substring(5).trim());
        } else if (details.startsWith("deadline")) {
            String[] parts = details.split("/by");
            newTask = new Deadline(parts[0].substring(9).trim(), parts[1].trim());
        } else if (details.startsWith("event")) {
            String[] parts = details.split("/to");
            newTask = new Event(parts[0].substring(6).trim(), parts[1].trim(),parts[2].trim());
        } else {
            throw new UnknownCommandException("unknown command");
        }
        tasks.addTask(newTask);
        storage.save(tasks.getTasks());
        System.out.println("Got it. I've added this task:\n  " + newTask);
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        ui.showExit();
        System.exit(0);
    }
}
