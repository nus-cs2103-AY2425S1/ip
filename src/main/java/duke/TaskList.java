package duke;

import java.util.ArrayList;

public class TaskList {
    /**
     * Returns a TaskList object
     *
     * @param ui a ui object for printing to the terminal when
     * the TaskList object is modified
     */
    TaskList(Ui ui) {
        this.tasks = new ArrayList<Task>();
        this.ui = ui;
    }

    /**
     * Returns a TaskList object
     *
     * @param tasks an array list of Task objects which we
     * can directly copy into our tasks field
     * @param ui a ui object for printing to the terminal when
     * the TaskList object is modified
     */
    TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Returns a TaskList object
     *
     * @param taskList an existing taskList object that we can
     * copy into this object
     * @param ui a ui object for printing to the terminal when
     * the TaskList object is modified
     */
    TaskList(TaskList taskList, Ui ui) {
        this.tasks = taskList.tasks;
        this.ui = ui;
    }

    /**
     * Returns void, just adds task to our array of tasks
     *
     * @param task the task to add
     */
    String addTask(Task task) {
        tasks.add(task);
        return ui.printTaskAdded(task, tasks.size());
    }

    void addTaskWithoutPrinting(Task task) {
        tasks.add(task);
    }

    /**
     * Returns void, just deletes a task from our array of tasks
     *
     * @param rank the 1-indexed index of the task we wish to remove
     */
    String deleteTask(int rank) {
        assert rank >= 1;
        assert rank <= tasks.size();

        if(rank < 1 || rank > tasks.size()) {
            System.out.println("Error: The task number is out of bounds. Terminating program.");
            System.exit(0);
        }

        Task toRemove = tasks.get(rank-1);
        tasks.remove(rank-1);

        return ui.printTaskDeleted(toRemove, tasks.size());
    }

    /**
     * Returns void, prints all the tasks out to the terminal
     */
    String listTasks() {
        int numberOfMessages = tasks.size();
        String response = "";
        response += ui.horizontalLine;
        ui.printHorizontalLine();

        for(int i=0; i<numberOfMessages; i++) {
            response += tasks.get(i).print(i+1);
            response += '\n';
        }

        ui.printHorizontalLine();
        response += ui.horizontalLine;
        return response;
    }

    /**
     * Returns void, just marks a task in our array of tasks
     *
     * @param rank the 1-indexed index of the task we wish to mark
     * in our array of tasks
     */
    String markTask(int rank) {
        assert rank >= 1;
        assert rank <= tasks.size();

        if(rank < 1 || rank > tasks.size()) {
            System.out.println("Error: The task number is out of bounds. Terminating program.");
            System.exit(0);
        }
        Task task = tasks.get(rank - 1);

        task.mark();

        return ui.printTaskMarked(task);
    }

    /**
     * Returns void, just unmarks a task in our array of tasks
     *
     * @param rank the 1-indexed index of the task we wish to unmark
     * in our array of tasks
     */
    String unmarkTask(int rank) {
        assert rank >= 1;
        assert rank <= tasks.size();

        if(rank < 1 || rank > tasks.size()) {
            System.out.println("Error: The task number is out of bounds. Terminating program.");
            System.exit(0);
        }

        Task task = tasks.get(rank - 1);

        task.unmark();

        return ui.printTaskUnmarked(task);
    }

    private boolean isMatch(String query, Task task) {
        String taskName = task.name;
        int qlen = query.length();
        int tlen = taskName.length();

        if(qlen > tlen) {
            return false;
        }

        int startPtr = 0;
        int endPtr = qlen-1;

        while(endPtr < tlen) {
            if(taskName.substring(startPtr, endPtr + 1).equals(query)) {
                return true;
            }

            ++startPtr;
            ++endPtr;
        }

        return false;
    }

    /**
     * Returns void, prints out all tasks that match the search query
     *
     * @param query the search query used to filter out tasks
     */
    String fetchQuery(String query) {
        String response = "";

        int len = tasks.size();
        ArrayList<Task> matchingTasks = new ArrayList<Task>();

        ui.printHorizontalLine();
        response += ui.horizontalLine + '\n';

        System.out.println("Here are the matching tasks in your list:");
        response += "Here are the matching tasks in your list:\n";

        for(int i=0; i<len; ++i) {
            Task task = this.tasks.get(i);
            if(isMatch(query, task)) {
                matchingTasks.add(task);
            }
        }

        TaskList matchingTaskList = new TaskList(matchingTasks, this.ui);
        response += matchingTaskList.listTasks() + '\n';

        return response;
    }

    ArrayList<Task> tasks;
    Ui ui;
}
