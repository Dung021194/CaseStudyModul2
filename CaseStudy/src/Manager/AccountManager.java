package Manager;

import Model.Loan;
import Model.Password;
import Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountManager{
    private int count = 3;
    private int countLogin = 3;
    Map<String,User> accountMap;

    public Map<String, User> getAccountMap() {
        return accountMap;
    }

    public void setAccountMap(Map<String, User> accountMap) {
        this.accountMap = accountMap;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCountLogin(int countLogin) {
        this.countLogin = countLogin;
    }

    public int getCountLogin() {
        return countLogin;
    }
    PasswordManager passwordManager;

    public AccountManager( ) {
        accountMap = new HashMap<>();
        this.passwordManager = new PasswordManager();
    }


    public void registerAccount(Scanner scanner, UserManager userManager) {
        boolean flag = true;
        do {
        System.out.println("Enter new username:\n" + "(8-15 characters except special characters)");
        String userName = scanner.nextLine();
        if (UserManager.checkRegex(userName, "^[a-zA-Z0-9]{8,15}$")){
            if (!accountMap.containsKey(userName))
            {
            flag = false;
            User newUser = userManager.createUser(scanner);
            accountMap.put(userName, newUser);
        }
            else {
                System.out.println("User name is exist, choose other username");
            }
        }else {
            System.out.println("Wrong format, enter again!!!");
        }
    }while (flag);
    }
    public Map.Entry<String,User> loginAccount(Scanner scanner) {
        Map.Entry<String, User> entry=null;
        boolean flag = true;
        System.out.println("Enter your username:\n" + "(8-15 characters except special characters)");
        String loginUserName = scanner.nextLine();
        if (accountMap.containsKey(loginUserName)) {
            do {
                System.out.println("Enter your password : \n"+"( 8-15 characters except special characters,capital first character)");
                String loginPassword = scanner.nextLine();
                if (accountMap.get(loginUserName).getUserPassword().getPassword().equals(loginPassword)) {
                    for (Map.Entry<String, User> entryCheck : accountMap.entrySet()) {
                        if (entryCheck.getKey().equals(loginUserName)) {
                              entry = entryCheck;
                        }
                    }
                    flag = false;
                    System.out.println("Login success");

                } else {
                    countLogin--;
                    if (countLogin >0 ){
                        System.out.println("Wrong password, you have " + countLogin + " times to enter again");
                }else {
                        System.out.println("Do you forget password? (Press Y to reset password, any key to cancel)");
                        String choiceResetPassword = scanner.nextLine();
                        if (choiceResetPassword.equalsIgnoreCase("y")){
                            forgetPassword(scanner);
                        }

                    }
            }
            }
            while (flag && countLogin>0);
        } else if (countLogin>0){
            count--;
            System.out.println("Username is not exist ");
            System.out.println("You have " + count + " times to enter again");
            if (count > 0) {
                entry = loginAccount(scanner);
            }

        }
        return entry;
    }
    public void forgetPassword(Scanner scanner) {
        System.out.println("Enter your username: ");
        String inputUsername = scanner.nextLine();
        if (accountMap.containsKey(inputUsername)&&!inputUsername.equals("admin")) {
           passwordManager.resetPassword(scanner,accountMap.get(inputUsername).getUserPassword());
        }else {
        System.out.println("Username is not exist!");}
    }
    public void deleteAccount(Scanner scanner,LoanManager loanManager){
        ArrayList<Loan> delLoanList = new ArrayList<>();
        displayAccount(accountMap);
        System.out.println("Enter username you wanna delete:");
        String delUsername = scanner.nextLine();
        if (accountMap.containsKey(delUsername) && !delUsername.equals("admin")){
            System.out.println("Are you sure delete this account? (Press Y to ok, any key to cancel)");
            String choiceDelete = scanner.nextLine();
            if (choiceDelete.equalsIgnoreCase("y")){
                for (Loan loan: loanManager.loanList){
                    if (loan.getBorrower().equals(delUsername)){
                        delLoanList.add(loan);
                    }
                }
                for (Loan delLoan:delLoanList){
                    loanManager.loanList.remove(delLoan);
                }
                accountMap.remove(delUsername);
                System.out.println(" Delete success");
            }
        }else {
            System.out.println("Account is not exist!!!");
        }
    }
    public void displayAccount(Map<String,User> accountMap){
        title();
        for (Map.Entry<String, User> entry: accountMap.entrySet()) {
            if (!entry.getKey().equals("admin")) {
                System.out.printf("|%15s| %15s| %15s| %10s| %20s| %15s|%15s|", entry.getKey(),
                        entry.getValue().getUserPassword().getPassword(), entry.getValue().getName(),
                        entry.getValue().getAge(), entry.getValue().getTelephoneNumber(),
                        entry.getValue().getAddress(),entry.getValue().getUserBalance()+" VND");
                System.out.println();
            }
        }
        displayLine();
    }
    public void title (){
         displayLine();
        System.out.printf("|%15s| %15s| %15s| %10s| %20s| %15s|%15s|",
                "USERNAME ","PASSWORD ","NAME ","AGE ",
                "TELEPHONE NUMBER ","ADDRESS ","BALANCE");
        System.out.println();
         displayLine();
    }
    public void displayLine(){
        System.out.print("*");
        for (int i =0;i<=115;i++ ){
            System.out.print("-");
        }
        System.out.println("*");
    }

}
