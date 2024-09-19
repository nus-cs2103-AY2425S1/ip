package kita.exceptions;

/**
 * An error for when the `index` field is missing
 */
public class KitaMissingIndex extends KitaError {
    @Override
    public String toString() {
        return "Please specify index(es) this action should be carried out on (E.g unmark <index> <index?>) :c";
    }
}
