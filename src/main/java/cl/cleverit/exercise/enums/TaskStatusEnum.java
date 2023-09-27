package cl.cleverit.exercise.enums;

public enum TaskStatusEnum {
    PENDING("PENDING"),
    PROGRESS("PROGRESS"),
    COMPLETED("COMPLETED");

    private final String name;

    TaskStatusEnum (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }}
