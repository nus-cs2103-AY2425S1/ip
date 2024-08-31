public class helpCommand implements Command {

    @Override
    public boolean execute(Storage storage, TaskList master, UI ui) {
        System.out.println("""
                        Friday > Hello! I'm Friday! Your personal chatbot for ensuring you get things done by Friday ;)
                        To create a new task, type "add <task name>" and follow the instructions.
                        
                        Other commands:
                        help            displays this page
                        list            lists all tasks available
                        mark <index>    mark a task as completed
                        unmark <index>  mark a task as incomplete
                        remove <index>  remove task from list
                        bye             exit program
                        """);
        UI.printLine();
        return false;
    }
}
