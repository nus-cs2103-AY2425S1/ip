public class todoTask extends Task{
    public todoTask(String status) {
        super(status);
    }

    @Override
    public String toString() {
        String icon = "[T]";
        return icon + super.toString();
    }

}
