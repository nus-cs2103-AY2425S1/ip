package jarvis;

import jarvis.logic.TaskList;

public class TaskListStub extends TaskList {
    public String list() {
        return "hello";
    }
    public String add(String input){
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
