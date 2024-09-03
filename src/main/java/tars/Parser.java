package tars;

import java.util.ArrayList;

public class Parser {
    static String line = "    _____________________________________________";

    public Parser(){

    }
    public void checkEntry(String[] entryParts, String entry, TaskList tasks){
        try {
            if (entry.equals("todo") || entry.equals("deadline") || entry.equals("event")) {
                throw new TarsException(line + "\n" + "    OOPS! Describe the task/event/deadline/todo or list"
                        + "\n" + line);
            } else if (entryParts[0].equals("list")) {
                this.listPrint(tasks);
            } else {
                throw new TarsException(line + "\n" + "    OOPS! Only accept a task/event/deadline/todo as input"
                        + "\n" + line);
            }
        } catch(TarsException e){
            System.out.println(e.getMessage());
        }
    }

    public void listPrint(TaskList tasks) {
        if (tasks.getList().size() == 0) {
            System.out.println("No tasks added to list. Please add events/deadline/todos!");
        } else {
            System.out.println(line + "\n" + "    Here are the tasks in your list:");
            for (int i = 0; i < tasks.getList().size(); i++) {
                System.out.println("    " + (i + 1) + ". " + tasks.getList().get(i));
            }
            System.out.println(line);
        }
    }

    public void findTask(String[] entryParts, TaskList tasks){
        ArrayList<Task> resultList = new ArrayList<>();
        String taskPrefix = entryParts[1];

        for (int i = 0; i < tasks.getList().size(); i++) {
            String[] j = tasks.getList().get(i).toString().split(" ");

            for (int k = 0; k < j.length; k++) {
                if (j[k].equals(taskPrefix)) {
                    resultList.add(tasks.getList().get(i));
                }
            }
        }

        System.out.println(line + "\n" + "Here are the matching tasks in your list");
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getList().get(i));
        }
        System.out.println(line);
    }
}
