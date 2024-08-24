import java.util.ArrayList;

class Ynch {
    String name;
    ArrayList<String> todoList;

    Ynch() {
        this.name = "YNCH";
        this.todoList = new ArrayList<String>();
    }

    String addTask(String task) {
        this.todoList.add(task);
        return task;
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
