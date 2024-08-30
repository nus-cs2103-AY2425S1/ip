package twilight;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public String mark(int taskNum) {
        tasks.get(taskNum).done();
        return tasks.get(taskNum).toString();
    }

    public String unmark(int taskNum) {
        tasks.get(taskNum).unDone();
        return tasks.get(taskNum).toString();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public String add(Task t) {
        tasks.add(t);
        return "added: " + tasks.get(tasks.size() - 1).toString() + "\n" + "There are " + tasks.size() + " tasks in the list";
    }

    public String delete(int taskNum) throws InvalidInputException {
        try {
            tasks.remove(taskNum);
            return "The task has been successfully removed leaving: " + tasks.size() + " tasks";
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Task " + (taskNum + 1) + " does not exist");
        }
    }

    /**
     * Queries the tasklist for tasks which match and returns a list of them.
     *
     * @param q The query.
     * @return A list of all entries which match the query.
     */
    public String query(String q) {
        TaskList match = new TaskList();
        for (Task t: this.tasks) {
            if (t.toString().contains(q)) {
                match.add(t);
            }
        }
        if (match.tasks.isEmpty()) {
            return "No matches found";
        } else {
            String results = "";
            int numMatches = match.tasks.size();
            if (numMatches > 2) {
                for (int i = 0; i < match.tasks.size() - 1; i++) {
                    results += (i + 1) + ". " + match.tasks.get(i).toString()+ "\n";
                }
            }
            results += numMatches + ". " + match.tasks.get(numMatches - 1).toString();
            return "Here are the matches:\n" + results;
        }
    }
}

