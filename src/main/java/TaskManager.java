import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    protected void listTasks() {
        if (this.tasks.isEmpty()) {
            System.out.println("\uD83C\uDF31 No tasks added yet! Why not plant the first seed? \uD83C\uDF31");
        } else {
            System.out.println("Here's a rundown of all your tasks! \uD83D\uDE0A");
            for (int i = 0; i < this.tasks.size(); i++) {
                String formmattedString = String.format(
                        "%d. %s",
                        (i + 1),
                        this.tasks.get(i).toString()
                );
                System.out.println(formmattedString);
            }
            System.out.println("\uD83C\uDFAF You have " + this.tasks.size() + " tasks in the list. Keep going!");
        }
    }

    protected void addTask(String userInput, String taskType) {
        String taskInfo;
        try {
            taskInfo = userInput.split("\\s+", 3)[2];
        } catch(Exception e) {
            System.out.println("\uD83D\uDE15 Hmm, something went wrong. Did you add task correctly? " +
                    "(\uD83D\uDCA1 Tip: Use \"add {Specify Task Type e.g. todo, deadline, or event} " +
                    "/ {Input task description here}\" to add a task)");
            return;
        }

        String taskDescription;
        Task t;
        switch(taskType) {
            case "deadline":
                try {
                    String[] taskInfoArray = taskInfo.split("/", 2);
                    taskDescription = taskInfoArray[0];
                    // Check if the task already exists
                    for (Task task : this.tasks) {
                        if (task.getDescription().equalsIgnoreCase(taskDescription)) {
                            System.out.println("This task is already in your list! " +
                                    "Maybe you can try renaming it and input again?");
                            return;
                        }
                    }
                    t = new Deadline(taskDescription, taskInfoArray[1]);
                } catch (Exception e) {
                    System.out.println("\uD83D\uDE15 Hmm, something went wrong. Did you add task correctly? " +
                            "(\uD83D\uDCA1 Tip: Use \"add {Specify Task Type e.g. todo, deadline, or event} " +
                            "/{Input task description here}\" to add a task)");
                    return;
                }
                break;
            case "event":
                try {
                    String[] taskInfoArray = taskInfo.split("/", 3);
                    taskDescription = taskInfoArray[0];
                    // Check if the task already exists
                    for (Task task : this.tasks) {
                        if (task.getDescription().equalsIgnoreCase(taskDescription)) {
                            System.out.println("This task is already in your list! " +
                                    "Maybe you can try renaming it and input again?");
                            return;
                        }
                    }
                    t = new Event(taskInfoArray[0], taskInfoArray[1], taskInfoArray[2]);
                } catch (Exception e) {
                    System.out.println("\uD83D\uDE15 Hmm, something went wrong. Did you add task correctly? " +
                            "(\uD83D\uDCA1 Tip: Use \"add {Specify Task Type e.g. todo, deadline, or event} " +
                            "/{Input task description here}\" to add a task)");
                    return;
                }
                break;
            default:
                taskDescription = taskInfo;
                // Check if the task already exists
                for (Task task : this.tasks) {
                    if (task.getDescription().equalsIgnoreCase(taskDescription)) {
                        System.out.println("This task is already in your list! " +
                                "Maybe you can try renaming it and input again?");
                        return;
                    }
                }
                t = new Todo(taskInfo);
                break;
        }
        tasks.add(t);
        System.out.println("\uD83C\uDF89 Got it! I've added: \"" + taskDescription + "\" to your list!");
        System.out.println("\uD83C\uDFAF You now have " + this.tasks.size() + " tasks in the list. Keep going!");
    }

    protected void toggleTaskStatus(String userInput, boolean markAsDone) {
        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+", 2)[1]) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                Task taskToMark = tasks.get(taskNumber);
                if (markAsDone) {
                    if (taskToMark.getIsDone()) {
                        System.out.println("You have completed the task \"" +
                                taskToMark.getDescription() +
                                "\" already!");
                    } else {
                        taskToMark.markAsDone();
                        System.out.println("Great work! Knew you would have completed it.");
                    }
                } else {
                    if (!taskToMark.getIsDone()) {
                        System.out.println("Task \"" +
                                taskToMark.getDescription() +
                                "\" is still not done! You can't unmark an undone task!");
                    } else {
                        tasks.get(taskNumber).markAsNotDone();
                        System.out.println("Hey, I have unmarked this task for you. " +
                                "Maybe you should start working on it soon?");
                    }
                }
                System.out.println("  " + tasks.get(taskNumber).toString());
            } else {
                System.out.println("\uD83D\uDEAB Oops! That task number is out of range. " +
                        "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)");
            }
        } catch (Exception e) {
            System.out.println("\uD83D\uDE15 Hmm, something went wrong. Please enter a task number after mark/unmark. " +
                    "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)");
        }
    }


}
