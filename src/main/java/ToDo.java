public class ToDo extends Item {

    public ToDo(String newname) {
        super(newname);
    }

    @Override
    public String toData() {
        String str = String.format("T | %s\n", super.toData());
        return str;
    }

    @Override
    public String toString() {
        String str = String.format("[T] %s", super.toString());
        return str;
    }
    
}
