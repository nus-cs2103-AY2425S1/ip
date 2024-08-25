import java.util.ArrayList;

class Ynch {
    String name;
    ArrayList<Task> todoList;

    Ynch() {
        this.name = "YNCH";
        this.todoList = new ArrayList<Task>();
    }

    String addTodo(String task) {
        Todo newTask = new Todo(task);
        this.todoList.add(newTask);
        return "Meow! I've added this task: \n" + newTask + 
            "\n Now you have " + this.todoList.size() + " tasks in the list.";
    }

    String addDeadline(String task, String deadline) {
        Deadline newTask = new Deadline(task, deadline);
        this.todoList.add(newTask);
        return "Meow! I've added this task: \n" + newTask + 
            "\n Now you have " + this.todoList.size() + " tasks in the list."; 
    }

    String addEvent(String task, String from, String to) {
        Event newTask = new Event(task, from, to);
        this.todoList.add(newTask);
        return "Meow! I've added this task: \n" + newTask + 
            "\n Now you have " + this.todoList.size() + " tasks in the list."; 
    }

    String delete(int i) {
        int index = i - 1;
        Task removedTask = this.todoList.get(index);
        this.todoList.remove(index);
        return "Meow! I've removed this task: \n" + removedTask + 
            "\n Now you have " + this.todoList.size() + " tasks in the list.";
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
