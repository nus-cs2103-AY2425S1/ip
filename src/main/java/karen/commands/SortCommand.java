package karen.commands;

import karen.tasks.TaskList;
import karen.util.Ui;

/**
 * Handles Task sorting logic and displaying
 */
public class SortCommand extends Command {
    private Criteria criteria;
    private Order order;

    /**
     * The sorting criteria
     */
    public enum Criteria {
        DATE,
        ALPHABETICAL
    }

    /**
     * Sorting order
     */
    public enum Order {
        ASCENDING,
        DESCENDING
    }

    /**
     * Initializes the sorting criteria and sorting order
     * @param criteria The sort Criteria
     * @param order The sort Order
     */
    public SortCommand(Criteria criteria, Order order) {
        this.criteria = criteria;
        this.order = order;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        return "";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
