package Model;

import java.io.Serializable;

public class Password implements Serializable {

    private String password;
    private String securityQuestion;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public Password() {
    }

    public Password(String password, String securityQuestion) {
        this.password = password;
        this.securityQuestion = securityQuestion;
    }

    @Override
    public String toString() {
        return String.format("%15s(%15s)",password,securityQuestion);
    }
}
