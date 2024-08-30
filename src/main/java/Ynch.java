import java.util.ArrayList;

class Ynch {
    String name;
    TaskList taskList;

    Ynch() {
        this.name = "YNCH";
        this.taskList = new TaskList();
    }

    void processInput(String userInput) {
        if (userInput.equals("list")) {
            System.out.println(this.list());
        } else if (userInput.startsWith("mark")) {
            int i = Integer.valueOf(userInput.split(" ")[1]);
            System.out.println(this.mark(i));
        } else if (userInput.startsWith("unmark")) {
            int i = Integer.valueOf(userInput.split(" ")[1]);
            System.out.println(this.unmark(i));
        } else if (userInput.startsWith("todo")) {
            System.out.println(this.addTodo(userInput.split(" ", 2)[1]));
        } else if (userInput.startsWith("deadline")) {
            userInput = userInput.split(" ", 2)[1];
            String task = userInput.split("/by")[0];
            String deadline = userInput.split("/by")[1];
            System.out.println(this.addDeadline(task, deadline));
        } else if (userInput.startsWith("event")) {
            userInput = userInput.split(" ", 2)[1];
            String task = userInput.split("/from")[0];
            String fromAndTo = userInput.split("/from")[1];
            String from = fromAndTo.split("/to")[0];
            String to = fromAndTo.split("/to")[1];
            System.out.println(this.addEvent(task, from, to));
        } else if (userInput.startsWith("delete")) {
            int i = Integer.valueOf(userInput.split(" ")[1]);
            System.out.println(this.delete(i));
        }
    }

    String add(String task) {
        this.taskList = this.taskList.add(task);
        return "Meow! I've added this task: \n" + newTask + 
            "\n Now you have " + this.taskList.size() + " tasks in the list.";
    }

    String add(String task, String deadline) {
        this.taskList = this.taskList.add(task);
        return "Meow! I've added this task: \n" + newTask + 
            "\n Now you have " + this.taskList.size() + " tasks in the list."; 
    }

    String add(String task, String from, String to) {
        this.taskList = this.taskList.add(task);
        return "Meow! I've added this task: \n" + newTask + 
            "\n Now you have " + this.taskList.size() + " tasks in the list."; 
    }



    String delete(int i) {
        this.taskList = this.taskList.remove(index);
        return "Meow! I've removed this task: \n" + removedTask + 
            "\n Now you have " + this.taskList.size() + " tasks in the list.";
    }

    String mark(int i) {
        this.taskList = this.taskList.mark(i);
        return "Meow! I've marked this task as done: \n" + taskToMark;
    }

    String unmark(int i) {
        this.taskList = this.taskList.unmark(i);
        return "Meow! I've marked this task as not done yet: \n" + taskToMark;
    }

    String list() {
        return this.taskList.list();
    }

    String greet() {
        return "Meow! I'm YNCH. What can I do for you?";
    }
    
    String exit() {
        return "Bye. Hope to see you again soon meow!";
    }

}
