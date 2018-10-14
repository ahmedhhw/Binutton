package com.doublea.binyutton.backgroundTools;

import java.util.LinkedList;

public class Adminstrator {
    private String firstName;
    private String lastName;
    private String PhoneNumber;
    private LinkedList<String> contacts;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    private String UID;
    public Adminstrator(String firstName, String lastName, String PhoneNumber, String UID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.PhoneNumber = PhoneNumber;
        this.UID = UID;
        contacts = new LinkedList<>();
    }
    public Adminstrator(){
        firstName = "";
        lastName = "";
        PhoneNumber = "";
    }
    @Override
    public String toString(){
        return "firstName: " + this.PhoneNumber + "\n" +"lastName:"+ lastName + "\n" +"PhoneNumber:"+ PhoneNumber + "\n" + "uid:" + UID + "\n" + "contacts:" + contacts ;
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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }
    public LinkedList<String> getContacts() {
        return contacts;
    }

    public void setContacts(LinkedList<String> contacts) {
        this.contacts = contacts;
    }

    public boolean matches(String userEntry) {
        for (int i = 0; i < firstName.length();i++){
            if (firstName.substring(0,i+1).equalsIgnoreCase(userEntry))
                return true;
        }
        for (int i = 0; i < lastName.length();i++){
            if (lastName.substring(0,i+1).equalsIgnoreCase(userEntry))
                return true;
        }
        for (int i = 0; i < PhoneNumber.length(); i++){
            if (PhoneNumber.substring(0,i+1).equalsIgnoreCase(userEntry))
                return true;
        }
        return false;
    }
}
