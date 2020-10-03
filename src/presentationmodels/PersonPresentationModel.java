package presentationmodels;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.Person;

/**
 * Created by arthur on 17.03.2016.
 */
public class PersonPresentationModel extends PresentationModel {

    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();

    private StringBinding displayName;

    public PersonPresentationModel() {
        super();

        firstName.addListener((s,o,n) -> displayName.invalidate());
        lastName.addListener((s,o,n) -> displayName.invalidate());

        displayName = new StringBinding() {
            @Override
            protected String computeValue() {
                return String.format("%s %s", getFirstName(), getLastName()).trim();
            }
        };
    }

    public PersonPresentationModel(Person model) {
        this();

        refresh(model);
    }

    public void refresh(Person model) {
        firstName.setValue(model.getFirstName());
        lastName.setValue(model.getLastName());
    }

    public void applyChanges(Person model) {
        model.setFirstName(firstName.getValue());
        model.setLastName(lastName.getValue());
    }

    public String getFirstName() {
        return firstName.get() != null ? firstName.get() : "";
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get() != null ? lastName.get() : "";
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getDisplayName() {
        return displayName.get();
    }

    public StringBinding displayNameProperty() {
        return displayName;
    }
}
