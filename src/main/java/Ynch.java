import java.util.ArrayList;

class Ynch {
    String name;
    ArrayList<Task> todoList;

    Ynch() {
        this.name = "YNCH";
        this.todoList = new ArrayList<Task>();
    }

    String addTask(String task) {
        Task newTask = new Task(task);
        this.todoList.add(newTask);
        return task;
    }

    String mark(int i) {
        int index = i - 1;
        Task taskToMark = this.todoList.get(index);
        taskToMark.mark();
        this.todoList.set(index, taskToMark);
        return "Meow! I've marked this task as done: \n" + taskToMark;
    }

    String unmark(int i) {
        int index = i - 1;
        Task taskToMark = this.todoList.get(index);
        taskToMark.unmark();
        this.todoList.set(index, taskToMark);
        return "Meow! I've marked this task as not done yet: \n" + taskToMark;
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

    String greet() {
        return "Meow! I'm YNCH. What can I do for you?";
    }
    
    String exit() {
        return "Bye. Hope to see you again soon meow!";
    }

}
