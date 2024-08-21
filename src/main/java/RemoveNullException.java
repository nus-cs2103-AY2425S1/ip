public class RemoveNullException extends Exception {

    String desc;

    public RemoveNullException(String desc) {
        this.desc = desc;
    }

    public void print() {
        System.out.println("Can not remove a task using an invalid index: " + desc);
    }
}
