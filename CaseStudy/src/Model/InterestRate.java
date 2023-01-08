package Model;

import java.io.Serializable;

public class InterestRate implements Serializable {
    int id;
    private double interestRate;

    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public InterestRate() {
    }

    public InterestRate(int id, double interestRate, int level) {
        this.id = id;
        this.interestRate = interestRate;
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format("%15s%25s%20s",id,interestRate,level);
    }
}
