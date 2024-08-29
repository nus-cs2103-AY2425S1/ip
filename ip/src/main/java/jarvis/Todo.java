package jarvis;

import jarvis.Task;

public class Todo extends Task {
    public Todo(String name){
        super(name);
    }
    public String toString(){
        String checkbox = this.isDone() ? "[X]" : "[ ]";
        String result = String.format(" %s %s\n", checkbox, this.getName());
        return "[T]" + result;
    }
}
