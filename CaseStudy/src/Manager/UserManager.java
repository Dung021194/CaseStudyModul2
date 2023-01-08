package Manager;

import Model.InterestRate;
import Model.Loan;
import Model.Password;
import Model.User;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager {
    public AccountManager accountManager;
    public LoanManager loanManager;

    public UserManager() {
        this.loanManager = new LoanManager();
        this.accountManager = new AccountManager();
    }

    public static boolean checkRegex(String checkInput,String compileRegex){
        Pattern pattern = Pattern.compile(compileRegex);
        Matcher matcher = pattern.matcher(checkInput);
        return matcher.matches();
    }


    public User createUser(Scanner scanner) {
        boolean check = false;
        int age=-1;
        String telephoneNumber = null;
        Password newUserPassword = accountManager.passwordManager.createPassword(scanner);
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        do {
            System.out.println("Enter your age:");
            String newAge = scanner.nextLine();
            if (checkRegex(newAge, "^[0-9]{1,2}$")) {
                age = Integer.parseInt(newAge);
                check = true;
            }else {
                System.out.println("Wrong format, enter again!!!");
            }
        }while (!check);
        do {
        System.out.println("Enter your telephone number: (10 numbers)");
        String newTelephoneNumber = scanner.nextLine();
       if (checkRegex(newTelephoneNumber,"^[0-9]{10}$")){
           telephoneNumber = newTelephoneNumber;
           check = false;
       } else {
           System.out.println("Wrong format, enter again!!!");
       }
        }while (check);
        System.out.println("Enter your address:");
        String address = scanner.nextLine();
        long userBalance=0;
        System.out.println("Register account success");

        return new User(newUserPassword, name, age, telephoneNumber, address, userBalance);
    }


    public void updateUserInfo(Scanner scanner, Map.Entry<String,User> checkUser) {
        User updateInfoUser = new User();
        boolean flag = true;
        System.out.println("Do you want to change password? (press Y to change, press any key to skip)");
        String choicePassword = scanner.nextLine();
        if (choicePassword.equalsIgnoreCase("y"))
        {
            updateInfoUser.setUserPassword(accountManager.passwordManager.createPassword(scanner));

        }else {
            updateInfoUser.setUserPassword(checkUser.getValue().getUserPassword());
        }
        System.out.println("Enter name new name:( enter to skip)");
        String newName = scanner.nextLine();
        if (!newName.equals("")){
            updateInfoUser.setName(newName);
        }else {
            updateInfoUser.setName(checkUser.getValue().getName());
        }
        System.out.println("Do you wanna change age? (press Y to change, press any key to skip)");
        String choiceAge = scanner.nextLine();
        if (choiceAge.equalsIgnoreCase("y")){
            do {
                System.out.println("Enter your new age:");
                String newAge = scanner.nextLine();
                if (checkRegex(newAge, "^[0-9]{1,2}$")) {
                   updateInfoUser.setAge(Integer.parseInt(newAge));
                   flag = false;
                }else {
                    System.out.println("Wrong format, enter again!!!");
                }
            }while (flag);
        }else {
            updateInfoUser.setAge(checkUser.getValue().getAge());
        }
        System.out.println("Do you wanna change telephone number? (press Y to change, press any key to skip)");
        String choiceTelephoneNumber = scanner.nextLine();
        if (choiceTelephoneNumber.equalsIgnoreCase("y")){
            do {
                System.out.println("Enter new your telephone number: (10 numbers)");
                String newTelephoneNumber = scanner.nextLine();
                if (checkRegex(newTelephoneNumber,"^[0-9]{10}$")){
                     updateInfoUser.setTelephoneNumber(newTelephoneNumber);
                    flag = false;
                } else {
                    System.out.println("Wrong format, enter again!!!");
                }
            }while (flag);
        }else {
            updateInfoUser.setTelephoneNumber(checkUser.getValue().getTelephoneNumber());
        }
        System.out.println("Enter name new address:(enter to skip)");
        String newAddress = scanner.nextLine();
        if (!newAddress.equals("")){
            updateInfoUser.setAddress(newAddress);
        }else {
            updateInfoUser.setAddress(checkUser.getValue().getAddress());
        }
        updateInfoUser.setUserBalance(checkUser.getValue().getUserBalance());
        accountManager.accountMap.put(checkUser.getKey(),updateInfoUser);

        }
        public void displayUser(Map.Entry<String,User>entry){
        accountManager.title();
        System.out.printf("|%15s| %15s| %15s| %10s| %20s| %15s|%15s|", entry.getKey(),
                 entry.getValue().getUserPassword().getPassword(), entry.getValue().getName(),
                    entry.getValue().getAge(), entry.getValue().getTelephoneNumber(),
                    entry.getValue().getAddress(),entry.getValue().getUserBalance() + " VND");
            System.out.println();
            accountManager.displayLine();

        }
        public void loanApproval(Scanner scanner,Loan loan){
        if (loan!=null){
            System.out.println("Do you agree to approve this loan?(Press y to approve, any key to cancel)");
            String choiceApprove = scanner.nextLine();
            if (choiceApprove.equalsIgnoreCase("y")){
                loan.setStatusLoan("Approved");
                System.out.println("Approve success");
                long beforeBalance = accountManager.accountMap.get(loan.getBorrower()).getUserBalance();
                accountManager.accountMap.get(loan.getBorrower()).setUserBalance(beforeBalance + loan.getLoanAmount());
            }
        }
        else {
            System.out.println("Approve fail");
        }

    }
    public void loanReject(Scanner scanner,Loan loan){
        if (loan!=null){
            System.out.println("Do you agree to Reject this loan?(Press y to reject, any key to cancel)");
            String choiceReject = scanner.nextLine();
            if (choiceReject.equalsIgnoreCase("y") && loan.getStatusLoan().equals("Pending")){
                loan.setStatusLoan("Rejected");
                System.out.println("Reject success");
            }
        }
        else {
            System.out.println("Reject fail");
        }

    }
    public void setTest(){
        User admin = new User();
        Password adminPassword = new Password("Admin","Admin");
        admin.setUserPassword(adminPassword);
        User test = new User();
        Password testPassword = new Password("Test123","Test123");
        test.setUserPassword(testPassword);

        accountManager.accountMap.put("admin",admin);
        accountManager.accountMap.put("test123",test);

        InterestRate interestRate = new InterestRate(1,8,1);
        if (loanManager.getInterestRateManager().getInterestRateList().isEmpty()){
        loanManager.getInterestRateManager().getInterestRateList().add(interestRate);
        }else {
            loanManager.getInterestRateManager().getInterestRateList().set(0,interestRate);
        }

    }

}
