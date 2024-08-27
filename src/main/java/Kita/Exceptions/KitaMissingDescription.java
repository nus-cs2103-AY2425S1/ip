package Kita.Exceptions;

public class KitaMissingDescription extends KitaError {
    @Override
    public String toString() {
        return "Your task is missing its description (E.g deadline <task> /by <deadline>) :c";
    }
}
