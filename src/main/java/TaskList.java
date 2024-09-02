/**
 * Manages an ArrayList<Task> and executes operations to be performed on the list.
 *
 */

class TaskList {
    ArrayList<Task> todoList;

    TaskList() {
        this.todoList = new ArrayList<Task>();
    }
    
    Task add(String task) {
        Todo newTask = new Todo(task);
        this.todoList.add(newTask);
        return newTask;
    }

    Task add(String task, String deadline) {
        Deadline newTask = new Deadline(task, deadline);
        this.todoList.add(newTask);
        return newTask;
    }

    Task add(String task, String from, String to) {
        Event newTask = new Event(task, from, to);
        this.todoList.add(newTask);
        return newTask;
    }

    Task delete(int i) {
        int index = i - 1;
        Task removedTask = this.todoList.get(index);
        this.todoList.remove(index);
        return removedTask;
    }

    Task mark(int i) {
        int index = i - 1;
        Task taskToMark = this.todoList.get(index);
        taskToMark.mark();
        this.todoList.set(index, taskToMark);
        return taskToMark;
    }

    Task unmark(int i) {
        int index = i - 1;
        Task taskToMark = this.todoList.get(index);
        taskToMark.unmark();
        this.todoList.set(index, taskToMark);
        return taskToMark;
    }

    String list() {
        String list = "";
        int index = 0;
        for (int i = 0; i < this.todoList.size(); i++) {
            index += 1;
            list += index + ". " + this.todoList.get(i) + "\n";
        }
        return list;
    }

    String toString() {
        // each Task will be converted to e.g. T0|task description|deadline or from date]|[to date]
    }
}
