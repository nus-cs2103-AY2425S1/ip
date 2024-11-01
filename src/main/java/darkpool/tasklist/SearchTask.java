package darkpool.tasklist;

import java.util.ArrayList;

import darkpool.DarkpoolException;
import darkpool.task.Task;

/**
 * SearchTask class is used to search for tasks in the task list that contains the search query.
 */
class SearchTask {
    static String searchTask(ArrayList<Task> taskList, String searchQuery) throws DarkpoolException {
        if (taskList.isEmpty()) {
            throw new DarkpoolException("bozo you got no tasks");
        } else if (searchQuery.isEmpty()) {
            throw new DarkpoolException("bozo you gotta tell me what to search for");
        }

        StringBuilder temp = new StringBuilder("fine! here are the matching tasks\n");

        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(searchQuery)) {
                temp.append((i + 1)).append(". ").append(taskList.get(i).toString()).append("\n");
            }
        }

        temp.setLength(temp.length());

        if (temp.toString().equals("fine! here are the matching tasks\n")) {
            return "bozo there are no matching tasks";
        }

        return String.valueOf(temp);
    }

}
