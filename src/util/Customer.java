package util;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer implements Comparable<Customer> {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String dateOfBirth;

    public Customer(ResultSet resultSet) throws SQLException {
        this.firstName = resultSet.getString(1).replace(" ", "");
        this.lastName = resultSet.getString(2).replace(" ", "");
        this.phone = resultSet.getString(3).replace(" ", "");
        this.email = resultSet.getString(4).replace(" ", "");
        this.address = resultSet.getString(5).replace(" ", "");
        this.city = resultSet.getString(6).replace(" ", "");
        this.dateOfBirth = resultSet.getDate(7).toString().replace(" ", "");
    }

    public Customer(TextField firstName, TextField lastName, TextField phone, TextField email, TextField address, TextField city, DatePicker dateOfBirth) throws IllegalArgumentException {
        this(firstName.getText(), lastName.getText(), phone.getText(), email.getText(), address.getText(), city.getText(), dateOfBirth.getValue().toString());
    }

    public Customer(String firstName, String lastName, String phone, String email, String address, String city, String dateOfBirth) throws IllegalArgumentException {
        boolean allValid = isValidArgument(firstName) && isValidArgument(lastName) && isValidArgument(phone) && isValidArgument(email) && isValidArgument(address) && isValidArgument(city) && isValidArgument(dateOfBirth);
        if (allValid) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.email = email;
            this.address = address;
            this.city = city;
            this.dateOfBirth = dateOfBirth;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int compareTo(Customer o) {
        int i = lastName.compareTo(o.lastName);
        if (i == 0) {
            return firstName.compareTo(o.firstName);
        }
        return i;
    }

    public String getRow() {
        return firstName + " " + lastName;
    }

    private boolean isValidArgument(String argument) {
        return argument != null && !argument.isEmpty();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName;
    }
}
