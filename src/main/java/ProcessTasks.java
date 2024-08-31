package src.main.java;

import src.main.java.KillJoy;
import src.main.java.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Upon suggestion about the code from ChatGPT, I am implementing some of the suggested changes.
//I have divided my Chatbot into two classes. One takes care of the input/output and this takes care of processing.
public class ProcessTasks {
    private KillJoy kj;

    public ProcessTasks(KillJoy kj) {
        this.kj = kj;
    }

    public void processUserInput(String input) {
        String[] inputSplitBySlash = input.split("/");
        String[] inputSplitBySpace = inputSplitBySlash[0].split(" ");
        String typeOfTask = inputSplitBySpace[0];
        Task.TaskType taskType = null;

        try {
            taskType = Task.TaskType.valueOf(typeOfTask.toUpperCase());
        } catch (IllegalArgumentException e) {
            this.printLine();
            System.out.println("    AGWHAHH!!! What'ya sayin' dawgg??");
            this.printLine();
            return;
        }

        if (inputSplitBySpace.length == 1) {
            this.printLine();
            System.out.println("    What siaaa!! Description cannot be empty");
            this.printLine();
            return;
        }

        this.printLine();
        switch (taskType) {
        case TODO: {
            String description = inputSplitBySlash[0].replaceFirst("todo ", "");
            kj.addTask(description);
            break;
        }
        case DEADLINE: {
            String description = inputSplitBySlash[0].replaceFirst("deadline ", "");
            String by = inputSplitBySlash[1].replaceFirst("by ", "");
            kj.addTask(description, by);
            break;
            }
        case EVENT: {
            String description = inputSplitBySlash[0].replaceFirst("event ", "");
            String from = inputSplitBySlash[1].replaceFirst("from ", "");
            String to = inputSplitBySlash[2].replaceFirst("to ", "");
            kj.addTask(description, from, to);
            break;
        }
        }

        System.out.println("    Yo Dawgg!! Added this task:");
        System.out.println("    " + kj.getTask(kj.getTaskCount() - 1));
        if (kj.getTaskCount() == 1) {
            System.out.println("    Now you have " + kj.getTaskCount() + " task in the list.");
        } else {
            System.out.println("    Now you have " + kj.getTaskCount() + " tasks in the list.");
        }
        this.printLine();
    }

    public void markOrDelete(String input) {
        int taskIndex = Integer.MAX_VALUE;

        String[] inputAsList = input.split(" ");
        if (inputAsList.length == 1) {
            this.printLine();
            System.out.println("    MAOHWAHAWK !! Enter the task number too!!!");
            this.printLine();
            return;
        }

        //Regex section referenced from https://jenkov.com/tutorials/java-regex/index.html
        String regex = "^(mark|unmark|delete)(\\s)(\\d+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) { // Check if the input matches the pattern
            this.printLine();
            System.out.println("    DANGER !! Invalid command format");
            this.printLine();
            return;
        }

        String inputCommand = matcher.group(1);
        taskIndex = Integer.parseInt(matcher.group(3)) - 1;

        if (taskIndex + 1 > kj.getTaskCount() || taskIndex < 0) {
            this.printLine();
            System.out.println("    DANGER !! Task does not exist");
            this.printLine();
            return;
        }

        Task task = kj.getTask(taskIndex);
        if (inputCommand.equals("mark")) {
            task.changeStatus();
            this.printLine();
            System.out.println("    " + kj.getMarkString() + "\n        " + task);
            this.printLine();
        } else if (inputCommand.equals("unmark")) {
            task.changeStatus();
            this.printLine();
            System.out.println("    " + kj.getUnmarkString() + "\n        " + task);
            this.printLine();
        } else if (inputCommand.equals("delete")) {
            kj.removeTask(taskIndex);
            kj.decreaseTaskCount();
            this.printLine();
            System.out.println("    " + kj.getDeleteString() + "\n        " + task );
            if (kj.getTaskCount() == 1) {
                System.out.println("    Now you have " + kj.getTaskCount() + " task in the list.");
            } else {
                System.out.println("    Now you have " + kj.getTaskCount() + " tasks in the list.");
            }
            this.printLine();
        }
    }

    public void createTasks(String taskInfo) {
        String[] parts = taskInfo.split("\\|");
        int num = parts.length;

        Task.TaskType taskType = Task.TaskType.valueOf(parts[0]);
        switch (taskType) {
        case TODO: {
            String description = parts[2];
            kj.addTask(description);
            changeStatusOfTask(kj.getTask(kj.getTaskCount() - 1), Integer.valueOf(parts[1]));
            break;
        }
        case DEADLINE: {
            String description = parts[2];
            String by = parts[3];
            kj.addTask(description, by);
            changeStatusOfTask(kj.getTask(kj.getTaskCount() - 1), Integer.valueOf(parts[1]));
            break;
        }
        case EVENT: {
            String description = parts[1];
            String from = parts[3];
            String to = parts[4];
            kj.addTask(description, from, to);
            changeStatusOfTask(kj.getTask(kj.getTaskCount() - 1), Integer.valueOf(parts[1]));
            break;
        }
        }
    }

    private void changeStatusOfTask(Task task, int i) {
        if (i == 1) {
            task.changeStatus();
        }
    }

    public void printLine() {
        System.out.println("    ------------------------------------");
    }

    public void printTaskList() {
        this.printLine();
        System.out.println("    Here are your tasks lah!! Don't die:");
        for (int i = 0; i < kj.getTaskCount(); i++) {
            System.out.println("    " + (i + 1) + ". " + kj.getTask(i));
        }
        this.printLine();
    }
}