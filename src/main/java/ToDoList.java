import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    List<Task> list = new ArrayList<Task>();

    public String addToList(String str) {
        list.add(new Task(str));
        return "Successfully added " + str + " to list.";
    }

//    public String deleteFromList(String str) {
//        if (list.contains(str)) {
//            list.remove(str);
//            return "Successfully deleted " + str + " from list.";
//        } else {
//            return "Failed to delete " + str + " from list.";
//        }
//    }

    public void markTask(int num) {
        list.get(num).mark();
        System.out.println("Successfully marked task " + (num + 1) + " as done.");
        System.out.println(list.get(num).fullToString());
    }

    public void unmarkTask(int num) {
        list.get(num).unmark();
        System.out.println("Successfully marked task " + (num + 1) + " as not done yet.");
        System.out.println(list.get(num).fullToString());
    }

    public void listToString() {
        System.out.println("`````\n" + "List:");
        int i = 1;
        for (Task item : list) {
            System.out.print(i + ". " + item.fullToString() + "\n");
            i++;
        }
        System.out.println("`````");
    }
}
