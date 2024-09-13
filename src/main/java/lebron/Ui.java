package lebron;

public class Ui {

    public String showLoadingError() {
        return "There was an error in loading the chatbot";
    }

    public String showWelcomeMessage() {
        return "Wassup! I'm lebron";
    }

    public String showGoodbyeMessage() {
        return "Bye! I'm leaving now.";
    }

    public String showTaskAdded(Task task, int size) {
        String taskDetails = "Gotchu, added the task: \n" + task.toString();
        String taskCount = String.format("Now you have %d tasks in the list", size);
        return taskDetails + "\n" + taskCount;
    }

    public String showTaskDeleted(Task task) {
        return "Alright bro, I've deleted that task.\n" + task.toString();
    }

    public String showTaskMarked(Task task) {
        return "Alright bro, I've marked that task\n" + task.toString();
    }

    public String showTaskUnmarked(Task task) {
        return "Alright bro, I've unmarked that task\n" + task.toString();
    }

    public String showTaskRescheduled(Task task) {
        return "Alright bro, I've rescheduled that task\n" + task.toString();
    }

    public String showTaskList(TaskList taskList) throws LeBronException {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.getTask(i);
            result.append(String.format("%d. %s\n", i, task.toString()));
        }
        return result.toString();
    }

    public String showMatchingTasks(TaskList taskList, String keyword) throws LeBronException {
        StringBuilder result = new StringBuilder();
        result.append("Here's what I've got bro\n");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(keyword)) {
                result.append(String.format("%d. %s\n", i, task.toString()));
            }
        }
        return result.toString();
    }

}
