class TaskList {
    ArrayList<Task> todoList;

    TaskList() {
        this.todoList = new ArrayList<Task>();
    }
    
    TaskList add(String task) {
        Todo newTask = new Todo(task);
        this.todoList.add(newTask);
        return this;
    }

    TaskList add(String task, String deadline) {
        Deadline newTask = new Deadline(task, deadline);
        this.todoList.add(newTask);
        return this;
    }

    TaskList add(String task, String from, String to) {
        Event newTask = new Event(task, from, to);
        this.todoList.add(newTask);
        return this;
    }

    TaskList delete(int i) {
        int index = i - 1;
        Task removedTask = this.todoList.get(index);
        this.todoList.remove(index);
        return this;
    }

    TaskList mark(int i) {
        int index = i - 1;
        Task taskToMark = this.todoList.get(index);
        taskToMark.mark();
        this.todoList.set(index, taskToMark);
        return this;
    }

    TaskList unmark(int i) {
        int index = i - 1;
        Task taskToMark = this.todoList.get(index);
        taskToMark.unmark();
        this.todoList.set(index, taskToMark);
        return this;
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
}
