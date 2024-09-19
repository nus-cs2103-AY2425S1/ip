package darkpool.storage;

import darkpool.DarkpoolException;

/**
 * Represents the type of task.
 */
public enum TaskType {
    EVENT("E"), DEADLINE("D"), AFTER("A"), TODO("T");

    private final String code;

    TaskType(String code) {
        this.code = code;
    }

    static TaskType fromCode(String code) throws DarkpoolException {
        for (TaskType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new DarkpoolException("Unknown task type: " + code);
    }
}
