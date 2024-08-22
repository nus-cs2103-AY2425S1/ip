import java.util.ArrayList;
import java.util.List;

public class Note {
    private ArrayList<Task> myList;
    private int noOfTask;

    public Note() {
        this.myList = new ArrayList<>();
        this.noOfTask = 0;
    }

    public void addToList(String input) throws EmptyDescriptionException, InputErrorException {
        Task task;
        if (input.startsWith("todo")) {
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                throw new EmptyDescriptionException("You can't leave the descriptions empty!.");
            }
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            task = new ToDo(description);
        } else if (input.startsWith("deadline ")) {
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            String[] parts = input.substring(9).split(" /by ");
            task = new Deadline(parts[0], parts[1]);
        } else if (input.startsWith("event ")) {
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            String[] parts = input.substring(6).split(" /from ");
            String description = parts[0];
            String[] eventDetails = parts[1].split(" /to ");
            String from = eventDetails[0];
            String to = eventDetails[1];
            task = new Event(description, from, to);
        } else {
            throw new InputErrorException();
        }
        myList.add(task);
        noOfTask++;
        System.out.println(task.toString());
    }


    public void mark(int number) {
        Task currTask = myList.get(number);
        currTask.markAsDone();
        System.out.println("--------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(currTask.toString());
        System.out.println("--------------------------------------------");
    }

    public void unmark(int number) {
        Task currTask = myList.get(number);
        currTask.markAsUnDone();
        System.out.println("--------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(currTask.toString());
        System.out.println("--------------------------------------------");
    }

    public void delete(int number) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(myList.get(number).toString());
        myList.remove(number);
        this.noOfTask--;
        String result = "Now you have " + noOfTask +  " tasks in the list.";
        System.out.println(result);
        System.out.println("____________________________________________________________");
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < noOfTask; i++) {
            String result = i + 1 + ". " + myList.get(i).toString();
            System.out.println(result);
        }
        System.out.println("--------------------------------------------");
    }

    public int getNumberOfTask() {
        return this.noOfTask;
    }

}
