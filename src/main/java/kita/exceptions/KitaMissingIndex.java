package kita.exceptions;

/**
 * An error for when the `index` field is missing
 */
public class KitaMissingIndex extends KitaError {
    @Override
    public String toString() {
        return "Please specify what index this action should be carried out on (E.g unmark <index>) :c";
    }
}
