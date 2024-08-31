public class Events extends Task{

    String start;
    String end;

    public Events(String taskDes, String start, String end) {
        super(taskDes);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString(){
        return "[E] " + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String getAdd(){
        return " /from " + start + " /to " + end;
    }

    @Override
    public String getTypeLetter() {
        return "E";
    }

}
