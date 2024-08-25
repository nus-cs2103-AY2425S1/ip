import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    protected void listTasks() throws TaskManagerException {
        if (this.tasks.isEmpty()) {
            throw new TaskManagerException("\uD83C\uDF31 No tasks added yet! Why not plant the first seed? \uD83C\uDF31",
                    TaskManagerException.ErrorType.EMPTY_LIST);
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

    protected void addTask(String userInput, String taskType) throws TaskManagerException {
        String taskInfo;
        try {
            taskInfo = userInput.split("\\s+", 3)[2];
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new TaskManagerException("\uD83D\uDE15 Hmm, something went wrong. Did you add task correctly? " +
                    "(\uD83D\uDCA1 Tip: Use \"add {Specify Task Type e.g. todo, deadline, or event} " +
                    "/ {Input task description here}\" to add a task)",
                    TaskManagerException.ErrorType.INVALID_ADD_TASK_NUMBER);
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
                            throw new TaskManagerException("This task is already in your list! " +
                                    "Maybe you can try renaming it and input again?",
                                    TaskManagerException.ErrorType.DUPLICATE_TASK);
                        }
                    }
                    t = new Deadline(taskDescription, taskInfoArray[1], taskType);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                    throw new TaskManagerException("\uD83D\uDE15 Hmm, something went wrong. Did you add task correctly? " +
                            "(\uD83D\uDCA1 Tip: Use \"add {Specify Task Type e.g. todo, deadline, or event} " +
                            "/ {Input task description here}\" to add a task)",
                            TaskManagerException.ErrorType.INVALID_ADD_TASK_NUMBER);
                }
                break;
            case "event":
                try {
                    String[] taskInfoArray = taskInfo.split("/", 3);
                    taskDescription = taskInfoArray[0];
                    // Check if the task already exists
                    for (Task task : this.tasks) {
                        if (task.getDescription().equalsIgnoreCase(taskDescription)) {
                            throw new TaskManagerException("This task is already in your list! " +
                                    "Maybe you can try renaming it and input again?",
                                    TaskManagerException.ErrorType.DUPLICATE_TASK);
                        }
                    }
                    t = new Event(taskInfoArray[0], taskInfoArray[1], taskInfoArray[2], taskType);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                    throw new TaskManagerException("\uD83D\uDE15 Hmm, something went wrong. Did you add task correctly? " +
                            "(\uD83D\uDCA1 Tip: Use \"add {Specify Task Type e.g. todo, deadline, or event} " +
                            "/ {Input task description here}\" to add a task)",
                            TaskManagerException.ErrorType.INVALID_ADD_TASK_NUMBER);
                }
                break;
            default:
                taskDescription = taskInfo;
                // Check if the task already exists
                for (Task task : this.tasks) {
                    if (task.getDescription().equalsIgnoreCase(taskDescription)) {
                        throw new TaskManagerException("This task is already in your list! " +
                                "Maybe you can try renaming it and input again?",
                                TaskManagerException.ErrorType.DUPLICATE_TASK);
                    }
                }
                t = new Todo(taskInfo, taskType);
                break;
        }
        this.tasks.add(t);
        System.out.println("\uD83C\uDF89 Got it! I've added: \"" + taskDescription + "\" to your list!");
        System.out.println("\uD83C\uDFAF You now have " + this.tasks.size() + " tasks in the list. Keep going!");
    }

    protected void toggleTaskStatus(String userInput, boolean markAsDone, boolean isDelete) throws TaskManagerException {
        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+", 2)[1]) - 1;
            if (taskNumber >= 0 && taskNumber < this.tasks.size()) {

                if (isDelete) {
                    Task taskToDelete = this.tasks.remove(taskNumber);
                    System.out.println("Got it! 🗑️ I've waved goodbye to this task:");
                    System.out.println(taskToDelete.toString());
                    System.out.println("Your list just got lighter! 🌟 " +
                            "Now you're down to " + this.tasks.size() + " tasks. Keep up the momentum!");
                    return;
                }

                Task taskToMark = this.tasks.get(taskNumber);
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
                        this.tasks.get(taskNumber).markAsNotDone();
                        System.out.println("Hey, I have unmarked this task for you. " +
                                "Maybe you should start working on it soon?");
                    }
                }
                System.out.println("  " + this.tasks.get(taskNumber).toString());
            } else {
                throw new TaskManagerException("\uD83D\uDEAB Oops! That task number is out of range. " +
                        "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)",
                        TaskManagerException.ErrorType.TASK_OUT_OF_RANGE);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new TaskManagerException("\uD83D\uDE15 Hmm, something went wrong. " +
                    "Please enter a task number after mark/unmark/delete command. " +
                    "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)",
                    TaskManagerException.ErrorType.INVALID_MARK_TASK_NUMBER);
        }
    }

}
