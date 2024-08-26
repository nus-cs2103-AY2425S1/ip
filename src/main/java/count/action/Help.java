package count.action;

public class Help extends Action {
    @Override
    public String run() {
        return ("1.'hello': Prompts me for a greeting!\n Usecase: hello" +
                "\n\n2.'bye': Closes the program\n Usecase: bye" +
                "\n\n3.'list': Lists all tasks added in the order that they were added in\n Usecase: list" +
                "\n\n4.'todo': Adds a count.task with no date or time attached\n Usecase: todo bake bread" +
                "\n\n5.'deadline': Adds a count.task with a date or time to complete by\n Usecase: deadline homework /by 20/03/2024" +
                "\n\n6.'event': Adds a count.task with from a date or time to another date or time\n Usecase: event project meeting /from 20/04/2025 /to 30/04/2025" +
                "\n\n7.'mark': Marks specified count.task as complete\n Usecase: mark 2" +
                "\n\n8.'unmark': Marks specified count.task as incomplete\n Usecase: unmark 1" +
                "\n\n9.'delete': Deletes specified count.task number\n Usecase: delete 3");
    }
}
