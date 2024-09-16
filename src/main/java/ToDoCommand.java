public class ToDoCommand extends AddCommand {

    public ToDoCommand(String info) {
        super(new ToDo(info));
    }
}
