package jarvis;

import jarvis.logic.TaskList;
import jarvis.logic.Todo;

public class TaskListStub extends TaskList {
    public String list() {
        return "hello";
    }
    public String add(String input){
        this.getList().add(new Todo("hi"));
        return "add";
    }

    public void unmark(int i) {

    }

    public void mark(int i) {

    }

    public String acknowledge() {
        return "hi";
    }

    public void handleDelete(int i) {
        System.out.println("____________________________________________________________");
    }





}
