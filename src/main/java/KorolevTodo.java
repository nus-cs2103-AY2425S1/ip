import utils.EventParser;

public class KorolevTodo extends KorolevTask {
    private String tag;

    public KorolevTodo(String name) {
        super(EventParser.parseName("todo", "", name));
        this.tag = "T";
    }

    @Override
    public String toString() {
        String base = super.toString();
        String head = "[" + this.tag + "]";

        return head + base;
    }
}
