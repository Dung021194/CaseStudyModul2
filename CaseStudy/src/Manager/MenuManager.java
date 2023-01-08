package Manager;

import Model.User;

import java.util.Map;
import java.util.Scanner;

public class MenuManager {
    Scanner scanner = new Scanner(System.in);
    UserManager userManager;
    FileManager fileManager;
    String pathAccountMap = "accountMap.txt";

    String pathLoanList = "loanList.txt";

    String pathInterestRateList = "interestRateList.txt";




    public String getPathAccountMap() {
        return pathAccountMap;
    }
    public String getPathLoanList() {
        return pathLoanList;
    }

    public String getPathInterestRateList() {
        return pathInterestRateList;
    }

    public MenuManager(UserManager userManager, FileManager fileManager) {
        this.userManager = userManager;
        this.fileManager = fileManager;
    }

    public void userMenu(Map.Entry<String,User> checkUser){
        int choice=-1;
        do {
            System.out.println("Have a nice day, "+checkUser.getKey()+"!");
            System.out.println("MENU CUSTOMER");
            System.out.println("1. Create a new loan");
            System.out.println("2. Delete loan");
            System.out.println("3. Edit my loan");
            System.out.println("4. Display all loans");
            System.out.println("5. Edit my profile");
            System.out.println("6. Display my information");
            System.out.println("0. Exit");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        userManager.loanManager.addLoan(scanner,userManager.loanManager.createLoan(scanner,checkUser),checkUser);
                        fileManager.writeList(pathAccountMap,pathLoanList,pathInterestRateList,
                                userManager.loanManager.interestRateManager.interestRateList,
                                userManager.loanManager.loanList, userManager.accountManager.accountMap);
                        break;
                    case 2:
                        userManager.loanManager.displayUserLoan(checkUser);
                        userManager.loanManager.deleteLoanById(scanner,checkUser);
                        userManager.loanManager.displayUserLoan(checkUser);
                        fileManager.writeList(pathAccountMap,pathLoanList,pathInterestRateList,
                                userManager.loanManager.interestRateManager.interestRateList,
                                userManager.loanManager.loanList, userManager.accountManager.accountMap);
                        break;
                    case 3:
                        userManager.loanManager.displayUserLoan(checkUser);
                        userManager.loanManager.editLoanById(scanner,checkUser);
                        fileManager.writeList(pathAccountMap,pathLoanList,pathInterestRateList,
                                userManager.loanManager.interestRateManager.interestRateList,
                                userManager.loanManager.loanList, userManager.accountManager.accountMap);


                    case 4:
                        userManager.loanManager.displayUserLoan(checkUser);
                        break;
                    case 5:
                        userManager.displayUser(checkUser);
                        userManager.updateUserInfo(scanner,checkUser);
                        userManager.displayUser(checkUser);
                        fileManager.writeList(pathAccountMap,pathLoanList,pathInterestRateList,
                                userManager.loanManager.interestRateManager.interestRateList,
                                userManager.loanManager.loanList, userManager.accountManager.accountMap);

                        break;

                    case 6:
                        userManager.displayUser(checkUser);
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter choice again");
            }

        }while (choice!=0);
    }
    public void adminMenu(){
        int choice=-1;
        do {
            System.out.println("MENU ADMIN");
            System.out.println("1. Display all account");
            System.out.println("2. Delete account");
            System.out.println("3. Loan approval");
            System.out.println("4. Loan reject");
            System.out.println("5. Display all loans of user");
            System.out.println("6. Interest rate manager");
            System.out.println("0. Exit");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        userManager.accountManager.displayAccount(userManager.accountManager.accountMap);
                        break;
                    case 2:
                       userManager.accountManager.deleteAccount(scanner,userManager.loanManager);
                        fileManager.writeList(pathAccountMap,pathLoanList,pathInterestRateList,
                                userManager.loanManager.interestRateManager.interestRateList,
                                userManager.loanManager.loanList, userManager.accountManager.accountMap);

                        break;
                    case 3:
                        userManager.loanManager.displayAllLoan();
                        userManager.loanApproval(scanner,userManager.loanManager.findLoanById(scanner));
                        fileManager.writeList(pathAccountMap,pathLoanList,pathInterestRateList,
                                userManager.loanManager.interestRateManager.interestRateList,
                                userManager.loanManager.loanList, userManager.accountManager.accountMap);
                        userManager.loanManager.displayAllLoan();
                        break;
                    case 4:
                        userManager.loanManager.displayAllLoan();
                        userManager.loanReject(scanner,userManager.loanManager.findLoanById(scanner));
                        fileManager.writeList(pathAccountMap,pathLoanList,pathInterestRateList,
                                userManager.loanManager.interestRateManager.interestRateList,
                                userManager.loanManager.loanList, userManager.accountManager.accountMap);
                        userManager.loanManager.displayAllLoan();
                        break;
                    case 5:
                        userManager.loanManager.displayAllLoan();
                        break;
                    case 6:
                        interestManagerMenu();

                }
            } catch (NumberFormatException e) {
                System.out.println("Enter choice again");
            }

        }while (choice!=0);

    }
    public void interestManagerMenu(){
        int choice =-1;
        do {
            System.out.println("INTEREST MANAGER");
            System.out.println("1. Create new interest rate");
            System.out.println("2. Edit interest rate");
            System.out.println("3. Delete interest rate");
            System.out.println("4. Display all interest rate");
            System.out.println("0. Exit ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        userManager.loanManager.interestRateManager.interestRateList.add
                                (userManager.loanManager.interestRateManager.createInterestRate(scanner));
                        userManager.loanManager.interestRateManager.displayInterestRateList();
                        fileManager.writeList(pathAccountMap,pathLoanList,pathInterestRateList,
                                userManager.loanManager.interestRateManager.interestRateList,
                                userManager.loanManager.loanList, userManager.accountManager.accountMap);
                        break;
                    case 2:
                        userManager.loanManager.interestRateManager.displayInterestRateList();
                        userManager.loanManager.interestRateManager.editInterestRateById(scanner);
                        userManager.loanManager.interestRateManager.displayInterestRateList();
                        fileManager.writeList(pathAccountMap,pathLoanList,pathInterestRateList,
                                userManager.loanManager.interestRateManager.interestRateList,
                                userManager.loanManager.loanList, userManager.accountManager.accountMap);

                        break;
                    case 3:
                        userManager.loanManager.interestRateManager.displayInterestRateList();
                        userManager.loanManager.interestRateManager.deleteInterestRate(scanner);
                        userManager.loanManager.interestRateManager.displayInterestRateList();
                        fileManager.writeList(pathAccountMap,pathLoanList,pathInterestRateList,
                                userManager.loanManager.interestRateManager.interestRateList,
                                userManager.loanManager.loanList, userManager.accountManager.accountMap);
                         break;
                    case 4:
                        userManager.loanManager.interestRateManager.displayInterestRateList();


                }
            } catch (NumberFormatException e) {
                System.out.println("Enter choice again");
            }

        }while (choice!=0);
    }
    public void checkAdmin(Map.Entry<String,User> checkUser){
        if (checkUser!=null){
            if (checkUser.getKey().equals("admin")){
                adminMenu();
            }else {
                userMenu(checkUser);
            }
        }else {
            System.out.println("Login fail");
            userManager.accountManager.setCount(3);
            System.out.println("Do you wanna create a new account?(Press Y to create, any key to skip)");
            String choiceCreateAccount  = scanner.nextLine();
            if (choiceCreateAccount.equalsIgnoreCase("y")){
                userManager.accountManager.registerAccount(scanner, userManager);
            }
        }
    }
    }
