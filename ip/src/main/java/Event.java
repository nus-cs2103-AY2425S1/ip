public class Event extends Task{
    String start;
    String end;
    public Event(String name, String start, String end){
        super(name);
        this.start = start;
        this.end = end;
    }
    public String toString(){
        String checkbox = this.done ? "[X]" : "[ ]";
        String result = String.format(" %s %s\n", checkbox, this.name);
        return "[E]" + result;
    }
}
