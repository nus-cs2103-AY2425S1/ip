class AddCommand extends Command {
    public AddCommand(Calebyyy calebyyy) {
        super(calebyyy);
    }

    @Override
    public void execute(String input) {
        String task = input.substring(input.indexOf(' ') + 1); // Extract the task from the input
        calebyyy.addTask(task);
        System.out.println("____________________________________________________________");
        System.out.println(" added: " + task);
        System.out.println("____________________________________________________________");
    }
}