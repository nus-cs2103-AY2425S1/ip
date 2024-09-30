package deez;

/**
 * This abstract class represents a personality with different reactions.
 */
public abstract class Personality {
    private String name;
    /**
     * Initializes the name of this personality.
     *
     * @param name the name to set
     */
    public Personality(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this personality.
     *
     * @return the name of this personality
     */
    public String getName() {
        return name;
    }

    /**
     * This method should be implemented by subclasses to return a greeting message for this personality.
     *
     * @return a greeting message
     */
    public abstract String getGreeting();

    /**
     * This method should be implemented by subclasses to return a farewell message for this personality.
     *
     * @return a farewell message
     */
    public abstract String getFarewell();

    /**
     * This method should be implemented by subclasses to return an error reaction message for this personality.
     *
     * @return an error reaction message
     */
    public abstract String getErrorReaction();

    /**
     * This method should be implemented by subclasses to return a affirmation message for this personality.
     *
     * @return a affirmation message
     */
    public abstract String getAffirmation();
}
