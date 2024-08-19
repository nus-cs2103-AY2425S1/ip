/**
 * TodoList represents a list containing to-do entries
 */
public class TodoList {

    /** Array to hold the to-do items **/
    private final TodoItem[] todoList = new TodoItem[100];
    private int index = 0;

    /**
     * Add an entey to the to-do list with provided content
     *
     * @param content The content to be set in the to-do item.
     */
    public boolean addItem(String content) {
        if (index == todoList.length) {
            return false;
        }

        TodoItem item = new TodoItem(content);
        todoList[index] = item;
        index++;
        return true;
    }

    /**
     * List out all the items in the to-do list
     *
     * @return return an array of stings representing each entry in the to-do list
     */
    public String[] listItems() {
        String[] items = new String[index];
        for (int i = 0; i < index; i++) {
            items[i] = String.format("%d. %s", i + 1, todoList[i].toString());
        }
        return items;
    }
}
