package Components;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class StorageList {
    private ArrayList<Task> storageList;

    public StorageList() {
        this.storageList = new ArrayList<>();
    }

    public void addTask(String str) {
        if (str.startsWith("todo")) {
            String textString = str.substring(4);

            try {
                Todo newToDo = Todo.createNewTodo(textString);
                this.addToList(newToDo);
                System.out.println("Task added:\n  " + newToDo + "\nNow you have " + storageList.size() + " tasks in the list. HAVE FUN ^o^");
            } catch(InputMismatchException e) {
                System.out.println("Please ensure your todo has a description TT");
            }

        } else if (str.startsWith("event")) {
            String textString = str.substring(5);

            try {
                Event newEvent = Event.createNewEvent(textString);
                this.addToList(newEvent);
                System.out.println("Task added:\n  " + newEvent + "\nNow you have " + storageList.size() + " tasks in the list. HAVE FUN ^o^");
            } catch(InputMismatchException e) {
                System.out.println("Please ensure your event has a description, a start and end time TT");
            }
            
        } else {    /* remaining case is deadline task cases */
            String textString = str.substring(8);

            try {
                Deadline newDeadline = Deadline.createNewDeadline(textString);
                this.addToList(newDeadline);
                System.out.println("Task added:\n  " + newDeadline + "\nNow you have " + storageList.size() + " tasks in the list. HAVE FUN ^o^");
            } catch(InputMismatchException e) {
                System.out.println("Please ensure your deadline has a description and a end date TT");
            }
        }
    }

    public void announceItems() {
        //i is used to print out the index of each item so it has to be incremented
        for (int i = 0; i < storageList.size(); i++) {
            System.out.println((i + 1) + ". " + storageList.get(i) + "\n");
        }
    }

    public void mark(int index) throws IndexOutOfBoundsException {
        //index - 1 because task list displayed to user starts from 1
        storageList.get(index - 1).completeTask();
        System.out.println("I've marked the task as done ^o^ :\n  " + storageList.get(index - 1).toString());
    }

    public void unmark(int index) throws IndexOutOfBoundsException {
        //index - 1 because task list displayed to user starts from 1
        storageList.get(index - 1).uncompleteTask();
        System.out.println("I've marked the task as not done T.T :\n  " + storageList.get(index - 1).toString());
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException{
        Task targetTask = storageList.get(index - 1);
        storageList.remove(targetTask);
        System.out.println("Task removed:\n  " + targetTask + "\nNow you have " + storageList.size() + " tasks in the list. SOLDIER ON! ^o^");
    }

    private void addToList(Task task) {
        storageList.add(task);
    }
}
