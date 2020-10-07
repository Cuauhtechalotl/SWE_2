package models;

public class DataTupel {
    String property;
    String value;

    public DataTupel(String prop, String val) {
        property = prop;
        value = val;
    }

    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

