package deez;

/**
 * The DeezPersonality class extends the Personality class and implements a
 * Deez-like personality.
 */
public class DeezPersonality extends Personality {
    /**
     * Constructor for the DeezPersonality class, which takes a name as an argument.
     *
     * @param name the name of the person
     */
    public DeezPersonality(String name) {
        super(name);
    }

    /**
     * Gets a greeting for the DeezPersonality.
     *
     * @return a random greeting for the DeezPersonality
     */
    public String getGreeting() {
        String[] greetings = {"Hello!", "Hi!", "Hey!"};
        int random = (int) Math.floor(Math.random() * greetings.length);
        return greetings[random] + " I'm " + super.getName();
    }

    /**
     * Gets a farewell message for the DeezPersonality.
     *
     * @return a random farewell message
     */
    public String getFarewell() {
        String[] farewells = {"Bye!", "See you later!", "Goodbye!"};
        int random = (int) Math.floor(Math.random() * farewells.length);
        return farewells[random];
    }

    /**
     * Gets an error reaction for the DeezPersonality.
     *
     * @return a random error reaction message
     */
    public String getErrorReaction() {
        String[] errorReactions = {"Oh no!", "Oops!", "Yikes!"};
        int random = (int) Math.floor(Math.random() * errorReactions.length);
        return errorReactions[random];
    }

    /**
     * Gets an affirmation message for the DeezPersonality.
     *
     * @return a random affirmation message
     */
    public String getAffirmation() {
        String[] affirmations = {"Yep!", "Yes!", "Absolutely!"};
        int random = (int) Math.floor(Math.random() * affirmations.length);
        return affirmations[random];
    }
}
