package enums;

// Enum to represent buttons

public enum MenuEnum {

    READ_EMPLOYEES(1, "Read all Employees and print to screen"),
    SHOW_PROGRAMMERS(2, "Show staff proficient in a Programming Language"),
    SHOW_TESTER_SALARY(3, "Show Tester has a higher salary than"),
    SHOW_HIGHEST_SALARY(4, "Show Employee’s highest salary"),
    SHOW_LEADER(5, "Show Leader of the Team has most Employees"),
    SORT_SALARY(6, "Sort Employees salary (DESC)"),
    SHOW_ALL_EMPLOYEES(7, "Show All Employees"),
    WRITE_FILE(8, "Write File"),
    EXIT(9, "Exit");

    private final int key;
    private final String value;

    MenuEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
