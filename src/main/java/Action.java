import java.util.ArrayList;

public class Action {
    public void drawLine() {
        System.out.println("\t -------------------------------------------------------");
    }

    public void greet() {
        drawLine();
        System.out.println("\t Hello! I'm Elon");
        System.out.println("\t What can I do for you?");
        drawLine();
    }

    public void exit() {
        drawLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        drawLine();
    }

    public void numOfTasks(int size) {
        drawLine();
        System.out.println(String.format("\t Now you have %d tasks in the list.", size));
        drawLine();
    }

    public void listTasks(ArrayList<Task> list) {
        drawLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("\t %d.", i + 1) + list.get(i).toString());
        }
        drawLine();
    }

    public void markTask(int index, ArrayList<Task> list) {
        drawLine();
        System.out.println("\t Nice! I've marked this task as done:");
        list.get(index).setDone();
        System.out.println("\t " + list.get(index).toString());
        drawLine();
    }

    public void unmarkTask(int index, ArrayList<Task> list) {
        drawLine();
        System.out.println("\t OK, I've marked this task as not done yet:");
        list.get(index).setNotDone();
        System.out.println("\t " + list.get(index).toString());
        drawLine();
    }

    public void startAddTask() {
        drawLine();
        System.out.println("\t Got it. I've added this task:");
    }

    public void endAddTask(int size) {
        System.out.println(String.format("\t Now you have %d tasks in the list.", size));
        drawLine();
    }

    public void addToDo(String[] inputArr, ArrayList<Task> list) {
        String task = "";
        for (int i = 1; i < inputArr.length; i++) {
            task += inputArr[i] + " ";
        }
        task = task.strip();
        ToDo todo = new ToDo(task);
        list.add(todo);
        System.out.println("\t " + todo.toString());
    }

    public void addDeadline(String[] inputArr, ArrayList<Task> list) {
        int i = 1;
        String task = "";
        while (!inputArr[i].equals("/by")) {
            task += inputArr[i] + " ";
            i++;
        }
        task = task.strip();
        String by = "";
        for (int j = i+1; j < inputArr.length; j++) {
            by += inputArr[j] + " ";
        }
        by = by.strip();
        Deadline deadline = new Deadline(task, by);
        list.add(deadline);
        System.out.println("\t " + deadline.toString());
    }

    public void addEvent(String[] inputArr, ArrayList<Task> list) {
        int i = 1;
        String task = "";
        while (!inputArr[i].equals("/from")) {
            task += inputArr[i] + " ";
            i++;
        }
        task = task.strip();
        i++;
        String from = "";
        while (!inputArr[i].equals("/to")) {
            from += inputArr[i] + " ";
            i++;
        }
        from = from.strip();
        i++;
        String to = "";
        for (int j = i; j < inputArr.length; j++) {
            to += inputArr[j] + " ";
        }
        to = to.strip();
        Event event = new Event(task, from, to);
        list.add(event);
        System.out.println("\t " + event.toString());
    }
}
