import java.util.ArrayList;

public class Items {
    ArrayList<Item> items;

    public Items() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void  markItemDone(String input) throws SamException {
        try {
            String[] parts = input.split(" ");
            this.items.get(Integer.parseInt(parts[1])-1).markAsDone();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SamException("Please indicate the item number to mark done");
        } catch (IndexOutOfBoundsException e) {
            throw new SamException("Item number does not exist");
        }
    }

    public void  markItemUndone(String input) throws SamException {
        try {
            String[] parts = input.split(" ");
            this.items.get(Integer.parseInt(parts[1])-1).markAsUndone();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SamException("Please indicate the item number to mark undone");
        } catch (IndexOutOfBoundsException e) {
            throw new SamException("Item number does not exist");
        }
    }

    public void deleteItem(String input) throws SamException {
        try {
            String[] parts = input.split(" ");
            Item task_to_remove = this.items.get(Integer.parseInt(parts[1])-1);
            this.items.remove(task_to_remove);
            System.out.println("Noted, I have removed this task:\n" + task_to_remove.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SamException("Please indicate the item number to delete");
        } catch (IndexOutOfBoundsException e) {
            throw new SamException("Item number does not exist");
        }
    }
    public String getLastAdded() {
        return this.items.get(this.items.size() - 1).toString();
    }

    public int getSize() {
        return this.items.size();
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.items.size(); i++) {
            result.append(i + 1)
                  .append(".")
                  .append(this.items.get(i).toString())
                  .append("\n");
        }
        return result.toString();
    }

}
