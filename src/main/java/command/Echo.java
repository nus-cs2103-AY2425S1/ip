package command;

public class Echo extends Command{

    public Echo(String input) {
        super(input);
    }

    @Override
    public void execute() {
        System.out.println(this);
    }
}
