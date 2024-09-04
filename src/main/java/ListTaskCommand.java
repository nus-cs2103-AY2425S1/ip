public class ListTaskCommand extends Command {
    public ListTaskCommand() {
        super("list");
    }

    @Override
    public void run(String[] arguments) {
        Alexer alexer = Alexer.getInstance();
        Prompter prompter = alexer.getPrompter();

        prompter.printResponse(alexer.getTaskManager().toString());
    }
}
