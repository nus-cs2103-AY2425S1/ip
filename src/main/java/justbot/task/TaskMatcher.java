package justbot.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The TaskMatcher class is responsible for finding tasks that match specific keywords.
 * It iterates through a list of tasks and checks whether their descriptions contain any of the provided keywords.
 */
public class TaskMatcher {

    private TaskList taskList;
    private List<String> keywordList;

    /**
     * Constructs a TaskMatcher object that uses a given TaskList and an array of keywords.
     *
     * @param taskList The TaskList containing the tasks to be matched.
     * @param keywords The array of keywords to be used for matching tasks.
     */
    public TaskMatcher(TaskList taskList, String... keywords) {
        this.taskList = taskList;
        this.keywordList = Arrays.asList(keywords);
    }

    /**
     * Finds and returns a list of tasks that match any of the specified keywords.
     *
     * @return A list of tasks where the task description contains at least one of the provided keywords.
     */
    public List<Task> findMatchingTasks() {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (doesTaskMatchAnyKeyword(task)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Checks if the task description contains any of the specified keywords (case-insensitive).
     *
     * @param task The task to check.
     * @return True if the task description contains any of the keywords, false otherwise.
     */
    public boolean doesTaskMatchAnyKeyword(Task task) {
        String taskDescription = task.getTaskDescription().toLowerCase();
        for (String keyword : keywordList) {
            if (taskDescription.contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
