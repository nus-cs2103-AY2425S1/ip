import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Yapper {

    private static String divider = "------------------------------------------------------------------";
    private static String name = "Yapper";
    private static String relativePath = "./YappingData/YappingSession.txt";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Yapper(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.readFile(), filePath);
    }

    public void run() {
        ui.intro();
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String input = "";
                if (sc.hasNextLine()) {
                    input = sc.nextLine();
                } else {
                    break;
                }
                String[] split = input.split(" ");
                String command = split[0];

                if (command.equals("bye")) {
                    Ui.wrapText("Bye bye! :)");
                    break;
                } else if (command.equals("hi") || command.equals("hello")) {
                    Ui.wrapText("Hello! :)");
                } else if (command.equals("list")) {
                    taskList.listTasks();
                } else if (command.equals("mark") || command.equals("unmark")) {
                    String taskNumber = split[1];
                    taskList.markTask(command, taskNumber);
                } else if (command.equals("delete")) {
                    String taskNumber = split[1];
                    taskList.deleteTask(taskNumber);
                } else if (command.equals("todo")) {
                    try {
                        String desc = StringJoiner.join(split, 1, split.length, YapperConcern.DESC);
                        Task task = new ToDo(desc);
                        taskList.addTask(task);
                    } catch (YapperException e) {
                        Ui.errorCaught(e.getMessage());
                    }
                } else if (command.equals("deadline")) {
                    try {
                        int byIndex = -1;
                        for (int i = 0; i < split.length; i++) {
                            if (split[i].equals("/by")) {
                                byIndex = i;
                            }
                        }
                        if (byIndex == -1) {
                            throw new YapperFormatException("(Format: deadline [DESC] /by [DEADLINE])");
                        }
                        String desc = StringJoiner.join(split, 1, byIndex, YapperConcern.DESC);
                        String deadline = StringJoiner.join(split, byIndex + 1, split.length, YapperConcern.DEADLINE);
                        Task task = new Deadline(desc, deadline);
                        taskList.addTask(task);
                    } catch (YapperException e) {
                        Ui.errorCaught(e.getMessage());
                    }
                } else if (command.equals("event")) {
                    try {
                        int fromIndex = -1;
                        int toIndex = -1;
                        for (int i = 0; i < split.length; i++) {
                            if (split[i].equals("/from")) {
                                fromIndex = i;
                            } else if (split[i].equals("/to")) {
                                toIndex = i;
                            }
                        }
                        if (fromIndex == -1 || toIndex == -1) {
                            throw new YapperFormatException("(Format: event [DESC] /from [FROM] /to [TO])");
                        }
                        String desc = StringJoiner.join(split, 1, fromIndex, YapperConcern.DESC);
                        String from = StringJoiner.join(split, fromIndex + 1, toIndex, YapperConcern.FROM);
                        String to = StringJoiner.join(split, toIndex + 1, split.length, YapperConcern.TO);
                        Task task = new Event(desc, from, to);
                        taskList.addTask(task);
                    } catch (YapperException e) {
                        Ui.errorCaught(e.getMessage());
                    }
                } else {
                    throw new YapperException("Invalid command");
                }
            } catch (YapperException e) {
                Ui.errorCaught(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Yapper("./YappingData/YappingSession.txt").run();
    }

    // in TaskList
    public static String taskPlural(int taskCount) {
        String taskMessage = "task";
        if (taskCount != 1) {
            taskMessage += "s";
        }
        return taskMessage;
    }
}
