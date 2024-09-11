package kitty.kittyexceptions;

public class MarksException extends KittyException {
    @Override
    public String toString() {
        return """
                Invalid input. Mark/Unmark/Delete task by inputting:
                    <command> <task_index_shown_in_list>
                Available commands:
                    'mark', 'unmark' and 'delete'
                Only one index at a time is allowed.""";
    }
}
