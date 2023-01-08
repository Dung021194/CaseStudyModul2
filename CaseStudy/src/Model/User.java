package Model;

import java.io.Serializable;

public class User implements Serializable {
    private Password userPassword;
    private String name;
    private int age;
    private String telephoneNumber;
    private String address;
    private long userBalance;


    public Password getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(Password userPassword) {
        this.userPassword = userPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(long userBalance) {
        this.userBalance = userBalance;
    }

    public User() {
    }

    public User(Password userPassword, String name, int age, String telephoneNumber, String address, long userBalance) {
        this.userPassword = userPassword;
        this.name = name;
        this.age = age;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.userBalance = userBalance;
    }

    @Override
    public String toString() {
        return String.format( "%15s %15s %10s %25s %20s", userPassword,name,age,telephoneNumber, address);
    }
}
