import java.util.ArrayList;
import java.util.List;

public class Note {
    private Task[] myList;
    private int noOfTask;

    public Note() {
        this.myList = new Task[100];
        this.noOfTask = 0;
    }

    public void addToList(String input) {
        Task task;
        if (input.startsWith("todo ")) {
            System.out.println("Got it. I've added this task:");
            task = new ToDo(input.substring(5));
        } else if (input.startsWith("deadline ")) {
            System.out.println("Got it. I've added this task:");
            String[] parts = input.substring(9).split(" /by ");
            task = new Deadline(parts[0], parts[1]);
        } else if (input.startsWith("event ")) {
            System.out.println("Got it. I've added this task:");
            String[] parts = input.substring(6).split(" /from ");
            String description = parts[0];
            String[] eventDetails = parts[1].split(" /to ");
            String from = eventDetails[0];
            String to = eventDetails[1];
            task = new Event(description, from, to);
        } else {
            System.out.println("Unknown task type!");
            return;
        }
        myList[noOfTask] = task;
        noOfTask++;
        System.out.println(task.toString());
    }



    public void mark(int number) {
        Task currTask = myList[number];
        currTask.markAsDone();
        System.out.println("--------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(currTask.toString());
        System.out.println("--------------------------------------------");
    }

    public void unmark(int number) {
        Task currTask = myList[number];
        currTask.markAsUnDone();
        System.out.println("--------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(currTask.toString());
        System.out.println("--------------------------------------------");
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < noOfTask; i++) {
            String result = i + 1 + ". " + this.myList[i].toString();
            System.out.println(result);
        }
        System.out.println("--------------------------------------------");
    }

    public int getNumberOfTask() {
        return this.noOfTask;
    }

}
