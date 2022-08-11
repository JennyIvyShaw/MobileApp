package com.example.finalproject;
/**
 *This class create amd constructs basic information about a first time visitor
 *
 * @auther jennyShaw
 */
public class VisitorItem {
    private String firstName;
    private String lastName;

    /**
     * This Constructs and initializes the first and last name
     *
     * @param firstName First name of visitor
     * @param lastName Last name of visitor
     */
    public VisitorItem(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Method returns the firstName
     *
     * @return First name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method returns last name
     *
     * @return Last Name
     */
    public String getLastName() {
        return lastName;
    }
}
