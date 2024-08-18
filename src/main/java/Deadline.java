public class Deadline extends Input {
    String deadline;

    public Deadline(String input) {
        super(input.split(" ",2)[1].split("/",2)[0]);
        this.deadline = input.split("/by")[1];

    }

    @Override
    public String toString() {
        String str = "[D] " + super.toString() + "(by: " + this.deadline + ")";
        return str;
    }

}
