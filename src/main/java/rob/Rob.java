package rob;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rob {
    private static final String FILE_PATH = "./data/robsaved.txt";
    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;
    private Parser parser;

    public Rob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks(tasks));
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.greet();

        while (true) {
            String input = scanner.nextLine();

            parser = new Parser(input);
            parser.checkString();
            String command = parser.getCommand();

            // exit
            if (Objects.equals(command, "bye")) {
                ui.exit();
                break;
            } else if (Objects.equals(command, "list")) {
                ui.showList(tasks);
            } else if (Objects.equals(command, "mark")) {
                try {
                    int taskNum = findTaskNum(input);
                    tasks.getTask(taskNum - 1).markAsDone();
                    ui.mark(tasks, taskNum);
                    storage.saveTasks(tasks);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (Objects.equals(command, "unmark")) {
                try {
                    int taskNum = findTaskNum(input);
                    ui.unmark(tasks,taskNum);
                    tasks.getTask(taskNum - 1).unmark();
                    storage.saveTasks(tasks);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (Objects.equals(command, "delete")) {
                try {
                    int taskNum = findTaskNum(input);
                    ui.delete(tasks, taskNum);
                    tasks.removeTask(taskNum - 1);
                    storage.saveTasks(tasks);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    if (Objects.equals(command, "deadline")) {
                        String desc = parser.getDesc();
                        String day = parser.getDay();
                        tasks.getTasks().add(new Deadline(desc, day));
                        storage.saveTasks(tasks);

                    } else if (Objects.equals(command, "event")) {
                        String desc = parser.getDesc();
                        String from = parser.getFrom();
                        String to = parser.getTo();
                        tasks.getTasks().add(new Event(desc, from, to));
                        storage.saveTasks(tasks);

                    } else if (Objects.equals(command, "todo")) {
                        String desc = parser.getDesc();
                        tasks.getTasks().add(new Todo(desc));
                        storage.saveTasks(tasks);

                    } else {
                        throw new DukeException("I'm sorry... I don't seem to understand.");
                    }
                    ui.printText(tasks);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Rob(FILE_PATH).run();
    }

    /**
     * Finds and returns the last integer found in the input string,
     * which is assumed to be a task number.
     *
     * @param input The input string that contains the task number.
     * @return The task number extracted from the input string.
     * @throws DukeException If no number is found in the input string or if the task number is out of range.
     */
    public int findTaskNum(String input) throws DukeException {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String lastNum = null;

        while (matcher.find()) {
            lastNum = matcher.group();
        }

        if (lastNum == null) {
            throw new DukeException("Invalid task number...");
        } else {
            int taskNum = Integer.parseInt(lastNum);
            if (taskNum < 1 || taskNum > tasks.len()) {
                throw new DukeException("Invalid task number");
            } else {
                return taskNum;
            }
        }
    }



}
