public class ToDo  extends Task{

    public ToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        String str = "";
        str = str + "[T]";
        str = str + super.toString();
        return str;
    }
}
