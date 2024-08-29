package Tasks;

public class ToDo  extends Task{

    public ToDo(String s) {
        super(s);
    }

    public String infoForFile() {
        String str =  "[T] / " + super.getDetails();
        return str;
    }

    @Override
    public String toString() {
        String str = "";
        str = str + "[T]";
        str = str + super.toString();
        return str;
    }
}
