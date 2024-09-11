package task;

/**
 * Represents a merchandise with an id, name, type and description.
 */
public class Merchandise {
    private String id;
    private String name;
    private String description;

    /**
     * Constructs a merchandise with the entered id, name, type and description
     * @param id The id of the merchandise
     * @param name The name of the merchandise
     * @param description The description of the merchandise
     */
    public Merchandise(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the Id of the merchandise
     *
     * @return The id of the merchandise
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the name of the merchandise
     *
     * @return The name of the merchandise
     */
    public String getName() {
        return this.name;
    }


    /**
     * Returns the description of the merchandise
     *
     * @return The description of the merchandise
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Changes the name of the merchandise
     * @param name The name which it has to be changed to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the description of merchandise
     *
     * @param description The description it has to be changed to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\n\t\tID: " + this.id
                + "\n\t\tName: " + this.name
                + "\n\t\tDescription: " + this.description;
    }
}

