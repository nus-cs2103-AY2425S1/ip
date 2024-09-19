package kita.exceptions;

/**
 * An error for when the "/to" attribute is missing
 */
public class KitaMissingTo extends KitaError {
    @Override
    public String toString() {
        return "Your task is missing the '/to <what date>' field :c";
    }
}
