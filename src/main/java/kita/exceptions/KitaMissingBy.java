package kita.exceptions;

/**
 * An error for when the "/by" attribute is missing
 */
public class KitaMissingBy extends KitaError {
    @Override
    public String toString() {
        return "Your task is missing the '/by <the date>' field :c";
    }
}
