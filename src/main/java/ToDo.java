public class ToDo extends Item {

    public ToDo(String newname) {
        super(newname);
    }

    @Override
    public String toString() {
        String str = String.format("[T] %s", super.toString());
        return str;
    }
    
}
