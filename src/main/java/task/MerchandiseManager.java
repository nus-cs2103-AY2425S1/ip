package task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class MerchandiseManager {
    private final ArrayList<Merchandise> merchandiseList;

    /**
     * Constructs an empty merchandiseList
     */
    public MerchandiseManager() {
        this.merchandiseList = new ArrayList<>();
    }

    /**
     * Adds a merchandise to the list.
     *
     * @param merchandise The merchandise that has to be added
     */
    public void addMerchandise(Merchandise merchandise) {
        merchandiseList.add(merchandise);
    }

    /**
     * Deletes a merchandise from list by its index.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task is index is valid, otherwise null.
     */
    public Merchandise removeMerchandise(int index) {
        if (index >= 0 && index < merchandiseList.size()) {
            return merchandiseList.remove(index);
        }
        return null;
    }

    /**
     * Retrieves the list of merchandise.
     *
     * @return An ArrayList of merchandise
     */
    public ArrayList<Merchandise> getMerchandise() {
        return merchandiseList;
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index if valid, otherwise null.
     */
    public Merchandise getMerchandise(int index) {
        if (index >= 0 && index < merchandiseList.size()) {
            return merchandiseList.get(index);
        }
        return null;
    }

    /**
     * Finds a merchandise by the given name
     * @param name The name of the merchandise to be found
     * @return The list of merchandise which have the same name as specified.
     */
    public ArrayList<Merchandise> findMerchandisebyName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>();
        } else {
            List<Merchandise> matchingTasks = merchandiseList.stream()
                    .filter(task ->
                            task.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
            return new ArrayList<>(matchingTasks);
        }
    }




    /**
     * Updates the description of a merchandise with respect to its id
     *
     * @param id The id whose description has to be modified.
     * @param description The new description
     * @return merchandise, if the description has been modified, else null
     */
    public Merchandise updateMerchandiseDescription(String id, String description) {
        for (Merchandise m : merchandiseList) {
            if (m.getId().equals(id)) {
                m.setDescription(description);
                return m;
            }
        }
        return null;
    }

    /**
     * Updates the name of a merchandise with respect to its id
     *
     * @param id The id whose name has to be modified.
     * @param name The new description
     * @return merchandise, if the name has been modified, else null
     */
    public Merchandise updateMerchandiseName(String id, String name) {
        for (Merchandise m : merchandiseList) {
            if (m.getId().trim().equalsIgnoreCase(id.trim())) {
                m.setName(name);
                return m;
            }
        }
        return null;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return merchandiseList.size();
    }

}

