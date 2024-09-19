package kita.exceptions;

/**
 * An error for when the "/from" attribute is missing
 */
public class KitaMissingFrom extends KitaError {
    @Override
    public String toString() {
        return "Your task is missing the '/from <what date>' field :c";
    }
}
