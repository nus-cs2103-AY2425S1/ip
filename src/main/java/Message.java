public class Message {
    String text;

    public Message(String text) {
        this.text = text;
    }

    public Message text(String text) {
        this.text = text;
        return this;
    }

    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        String line = "____________________________________________________________";
        return line
                + "\n"
                + this.text + "-Mooooo"
                + "\n"
                + line
                + "\n";
    }
}
