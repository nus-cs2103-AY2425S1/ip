public class InvalidCommandException extends Exception {
    String desc = "";

    public InvalidCommandException(String desc) {
        this.desc = desc;
    }

    public void print() {
        System.out.println("The command is not valid: " + desc);
    }
}
