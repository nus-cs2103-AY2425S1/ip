package count.action;

/**
 * The Help class is used to give a message on how to format commands
 */
public class Help extends Action {

    /**
     * Returns the String for Count's UI to print
     * @return String for Count's UI to print
     */
    @Override
    public String run() {
        return ("1.'hello': Prompts me for a greeting!\n Usecase: hello"
                + "\n\n2.'bye': Closes the program\n Usecase: bye"
                + "\n\n3.'list': Lists all tasks added in the order that they were added in\n Usecase: list"
                + "\n\n4.'todo': Adds a task with no date or time attached\n Usecase: todo bake bread"
                + "\n\n5.'deadline': Adds a count.task with a date or time to complete by\n "
                + "Usecase: deadline homework /by 20/03/2024"
                + "\n\n6.'event': Adds a task with from a date or time to another date or time\n "
                + "Usecase: event project meeting /from 20/04/2025 /to 30/04/2025"
                + "\n\n7.'mark': Marks specified task as complete\n Usecase: mark 2"
                + "\n\n8.'unmark': Marks specified task as incomplete\n Usecase: unmark 1"
                + "\n\n9.'delete': Deletes specified task number\n Usecase: delete 3"
                + "\n\n10.'remind': Reminds the user what tasks are due within the number of days\n Usecase: remind 7");
    }
}
