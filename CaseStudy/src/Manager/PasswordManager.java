package Manager;

import Model.Password;

import java.util.Scanner;

public class PasswordManager {


    public PasswordManager() {

    }
    public Password createPassword(Scanner scanner){
        Password password = new Password();
        boolean check = true;
        do {
            System.out.println("Enter new password :\n" + "( 8-15 characters except special characters,capital first character)");
            String inputPassword = scanner.nextLine();
            if (UserManager.checkRegex(inputPassword,"^[A-Z][a-zA-Z0-9]{7,14}$")){
                password.setPassword(inputPassword);
                check = false;
            }else {
                System.out.println("Wrong format, enter again!!!");
            }
        }while (check);
        System.out.println("Enter your security question (Remember to reset your password)");
        System.out.println("What is your primary school's name:");
        String securityQuestion = scanner.nextLine();
        password.setSecurityQuestion(securityQuestion);
        return password;
    }

    public void resetPassword(Scanner scanner,Password password){
        int count =3;
        boolean check = true;
        do {
            System.out.println("Enter your security question");
            System.out.println("What is your primary school's name:");
            String securityAnswer = scanner.nextLine();
            if (password.getSecurityQuestion().equals(securityAnswer)) {
                do {
                    System.out.println("Enter new password:\n" + "( 8-15 characters except special characters,capital first character)");
                    String inputPassword = scanner.nextLine();
                    if (UserManager.checkRegex(inputPassword,"^[A-Z][a-zA-Z0-9]{7,14}$")){
                        password.setPassword(inputPassword);
                        System.out.println("Reset password success!");
                        check = false;
                        count=0;
                    }else {
                        System.out.println("Wrong format, enter again!!!");
                    }
                }while (check);

            }else {
                count--;
                System.out.println("Wrong answer, you have "+count+" times to answer again ");
            }
        }while (count>0);
    }
}
