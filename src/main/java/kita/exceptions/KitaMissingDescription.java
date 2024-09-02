package kita.exceptions;

/**
 * An error for when the description field is missing
 */
public class KitaMissingDescription extends KitaError {
    @Override
    public String toString() {
        return "Your task is missing its description (E.g deadline <task> /by <deadline>) :c";
    }
}
