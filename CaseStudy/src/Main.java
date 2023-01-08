import Manager.FileManager;
import Manager.MenuManager;
import Manager.UserManager;
import Model.User;

import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        UserManager userManager = new UserManager();
        FileManager fileManager=new FileManager();
        MenuManager menuManager = new MenuManager(userManager,fileManager);
        Scanner scanner = new Scanner(System.in);

       userManager.loanManager.setLoanList(fileManager.read(menuManager.getPathLoanList()));
        userManager.accountManager.setAccountMap(fileManager.readMap(menuManager.getPathAccountMap()));
        userManager.loanManager.getInterestRateManager().setInterestRateList(fileManager.readInterest(menuManager.getPathInterestRateList()));
        userManager.setTest();
        do {
            System.out.println("WELCOME TO CODEGYM FINANCE");
            System.out.println("LOGIN MANAGER");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Forget password?");
            System.out.println("0. Exit ");
            System.out.println("Please enter your choice");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                      Map.Entry<String,User> checkUser =  userManager.accountManager.loginAccount(scanner);
                      if (userManager.accountManager.getCountLogin()>0){
                      menuManager.checkAdmin(checkUser);}
                      else {
                          userManager.accountManager.setCountLogin(3);
                      }
                       break;
                    case 2:
                        userManager.accountManager.registerAccount(scanner, userManager);
                        fileManager.writeList(menuManager.getPathAccountMap(), menuManager.getPathLoanList(),
                                menuManager.getPathInterestRateList(), userManager.loanManager.getInterestRateManager().getInterestRateList(),
                                userManager.loanManager.getLoanList(),userManager.accountManager.getAccountMap());

                        break;
                    case 3:
                        userManager.accountManager.forgetPassword(scanner);
                        fileManager.writeList(menuManager.getPathAccountMap(), menuManager.getPathLoanList(),
                                menuManager.getPathInterestRateList(), userManager.loanManager.getInterestRateManager().getInterestRateList(),
                                userManager.loanManager.getLoanList(),userManager.accountManager.getAccountMap());


                        break;
                    case 0:
                        System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter choice again");
            }

                }while (true);
        }

    }