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

    // Use of enum class to detect specific errors
    private enum Type {DESC, DEADLINE, FROM, TO};

    public static void main(String[] args) {

        System.out.println(divider);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(divider);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        // File object initialised with specified path
        File referenceFile = new File(relativePath);

        // Check if the folder exists
        File directory = referenceFile.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Creates the data file if it doesn't exist
        try {
            if (!referenceFile.exists()) {
                referenceFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reads the files contents
        try {
            readFile(referenceFile, taskList);
        } catch (YapperException e) {
            errorCaught(e.getMessage());
            return;
        }

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
                    System.out.println(divider);
                    System.out.println("Bye bye!");
                    System.out.println(divider);
                    break;
                } else if (command.equals("hi") || command.equals("hello")) {
                    System.out.println(divider);
                    System.out.println("Hello! :)");
                    System.out.println(divider);
                } else if (command.equals("list")) {
                    System.out.println(divider);
                    System.out.println("Your task list currently has " + Task.getTotalTasks() + " tasks");
                    for (int i = 1; i <= taskList.size(); i++) {
                        System.out.println(i + "." + taskList.get(i - 1));
                    }
                    System.out.println(divider);
                } else if (command.equals("mark") || command.equals("unmark")) {
                    Task task = null;
                    try {
                        int taskNumber = Integer.parseInt(split[1]);
                        task = taskList.get(taskNumber - 1);
                    } catch (NumberFormatException e) {
                        System.out.println(divider);
                        System.out.println("That was NOT a valid number.");
                        System.out.println(divider);
                        continue;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(divider);
                        System.out.println("Oopsie! Couldn't find that one! :)");
                        System.out.println(divider);
                        continue;
                    }
                    String message = "";
                    if (command.equals("mark")) {
                        message = "This task has been marked as completed:";
                        task.mark();
                    } else {
                        message = "This task has been reopened:";
                        task.unmark();
                    }
                    updateReferenceFile(taskList);

                    System.out.println(divider);
                    System.out.println(message);
                    System.out.println(" " + task);
                    System.out.println(divider);
                } else if (command.equals("delete")) {
                    int taskNumber = Integer.parseInt(split[1]);
                    try {
                        Task task = taskList.get(taskNumber - 1);
                        taskList.remove(taskNumber - 1);
                        Task.setTotalTasks(Task.getTotalTasks() - 1);

                        updateReferenceFile(taskList);

                        System.out.println(divider);
                        System.out.println("The following task has been removed form the lise:");
                        System.out.println("  " + task);
                        System.out.println("A total of " + Task.getTotalTasks() + " " + taskPlural(Task.getTotalTasks()) + " are still left.");
                        System.out.println(divider);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(divider);
                        System.out.println("That task is not here, sorry! :(");
                        System.out.println(divider);
                        continue;
                    }
                } else if (command.equals("todo")) {
                    try {
                        String desc = join(split, 1, split.length, Type.DESC);
                        Task task = new ToDo(desc);
                        addTask(taskList, task);
                    } catch (YapperException e) {
                        errorCaught(e.getMessage());
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
                        String desc = join(split, 1, byIndex, Type.DESC);
                        String deadline = join(split, byIndex + 1, split.length, Type.DEADLINE);
                        Task task = new Deadline(desc, deadline);
                        addTask(taskList, task);
                    } catch (YapperException e) {
                        errorCaught(e.getMessage());
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
                        String desc = join(split, 1, fromIndex, Type.DESC);
                        String from = join(split, fromIndex + 1, toIndex, Type.FROM);
                        String to = join(split, toIndex + 1, split.length, Type.TO);
                        Task task = new Event(desc, from, to);
                        addTask(taskList, task);
                    } catch (YapperException e) {
                        errorCaught(e.getMessage());
                    }
                } else {
                    throw new YapperException("Invalid command");
                }
            } catch (YapperException e) {
                errorCaught(e.getMessage());
            }
        }
    }

    public static void readFile(File referenceFile, ArrayList<Task> taskList) throws YapperException {
        try (Scanner fileScanner = new Scanner(referenceFile)) {
            while (fileScanner.hasNextLine()) {
                String[] taskInfo = fileScanner.nextLine().split("\\|");
                if (taskInfo.length < 4) {
                    throw new YapperFileFormatException("File is corrupted in one way or another");
                }
                String taskType = taskInfo[1].trim();
                String taskStatus = taskInfo[2].trim();
                String taskDesc = taskInfo[3].trim();
                Task task = null;
                switch (taskType) {
                case "T":
                    task = new ToDo(taskDesc);
                    break;
                case "D":
                    task = new Deadline(taskDesc, taskInfo[4].trim());
                    break;
                case "E":
                    String[] timeRange = taskInfo[4].trim().split("-----");
                    if (timeRange.length != 2) {
                        throw new YapperFileFormatException("Invalid event range detected");
                    }
                    task = new Event(taskDesc, timeRange[0], timeRange[1]);
                    break;
                default:
                    throw new YapperFileFormatException("Task type not recognised: " + taskType);
                }
                if ("X".equals(taskStatus)) {
                    task.mark();
                } else if (!taskStatus.isEmpty()) {
                    throw new YapperFileFormatException("Task status not recognised: " + taskStatus);
                }
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new YapperFileFormatException("File not found");
        }
    }


    public static void errorCaught(String errorMessage) {
        System.out.println(divider);
        System.out.println(errorMessage);
        System.out.println(divider);
    }

    public static void addTask(ArrayList<Task> taskList, Task task) {
        taskList.add(task);
        updateReferenceFile(taskList);

        System.out.println(divider);
        System.out.println("Task has been added:");
        System.out.println("  " + task);
        System.out.println("A total of " + Task.getTotalTasks() + " " + taskPlural(taskList.size()) + " are on the list.");
        System.out.println(divider);
    }

    public static String taskPlural(int taskCount) {
        String taskMessage = "task";
        if (taskCount != 1) {
            taskMessage += "s";
        }
        return taskMessage;
    }

    public static String join(String[] s, int start, int end, Type timeType) throws YapperException {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(s[i]);
            if (i != end - 1) {
                sb.append(' ');
            }
        }
        if (sb.isEmpty()) {
            String message = null;
            switch (timeType) {
                case DESC:
                    throw new EmptyDescException("(E.g. todo [DESC], deadline [DESC] /by [DEADLINE], etc)");
                case DEADLINE:
                    throw new EndingTimeException("(Format: deadline [DESC] /by [DEADLINE])");
                case FROM:
                    throw new StartingTimeException("(Format: event [DESC] /from [FROM] /to [TO])");
                case TO:
                    throw new EndingTimeException("(Format: event [DESC] /from [FROM] /to [TO])");
                default:
                    message = "(Something went wrong)";
                    break;
            }
            throw new YapperFormatException(message);
        }
        return sb.toString();
    }

    public static void updateReferenceFile(ArrayList<Task> taskList) {
        try (FileWriter fileWriter = new FileWriter(relativePath)) {
            for (Task t : taskList) {
                fileWriter.write(t.getDesc() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
