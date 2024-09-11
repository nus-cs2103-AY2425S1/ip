package kitty.kittyexceptions;

public class TagException extends KittyException {
    @Override
    public String toString() {
        return """
                Invalid input. Tag task by inputting:
                    tag <task_index_shown_in_list> <tag_name>
                Only one index at a time is allowed.""";
    }
}
