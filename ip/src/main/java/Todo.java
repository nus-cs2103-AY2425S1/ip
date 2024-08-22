public class Todo extends Task{
    public Todo(String name){
        super(name);
    }
    public String toString(){
        String checkbox = this.done ? "[X]" : "[ ]";
        String result = String.format(" %s %s\n", checkbox, this.name);
        return "[T]" + result;
    }
}
