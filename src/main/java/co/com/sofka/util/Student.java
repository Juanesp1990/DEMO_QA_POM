package co.com.sofka.util;

public enum Student {
    PHOTO("\\src\\test\\resources\\images\\fondo.jpg");

    private final String value;

    public String getValue() {
        return value;
    }

    Student(String value) {
        this.value = value;
    }
}

