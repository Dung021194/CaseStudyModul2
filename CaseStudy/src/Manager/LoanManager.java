package Manager;

import Model.InterestRate;
import Model.Loan;
import Model.User;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class LoanManager {
    private int countFind =3;
    ArrayList<Loan> loanList;
    InterestRateManager interestRateManager;

    public InterestRateManager getInterestRateManager() {
        return interestRateManager;
    }

    public ArrayList<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(ArrayList<Loan> loanList) {
        this.loanList = loanList;
    }

    public LoanManager() {
        this.loanList = new ArrayList<>();
        this.interestRateManager=new InterestRateManager();

    }

    public Loan createLoan(Scanner scanner, Map.Entry<String,User> entry) {
        boolean flag = true;
        Loan newLoan = new Loan();
        int id;
        if (loanList.isEmpty()) {
            id = 1;
        } else {
            id = loanList.get(loanList.size() - 1).getId() + 1;
        }
        newLoan.setId(id);
        String borrower = entry.getKey();
        newLoan.setBorrower(borrower);
        String status = "Pending";
        newLoan.setStatusLoan(status);
        InterestRate interestRate;
        do {
            System.out.println("Choose interest rate of loan: ");
            System.out.println("Level of loan:");
            System.out.println("Level 1: Range of borrow amount is 1.000.000-49.999.999 VND");
            System.out.println("Level 2: Range of borrow amount is 50.000.000-99.9999.999 VND");
            System.out.println("Level 3: Range of borrow amount is 100.000.000-149.999.999 VND");
            interestRateManager.displayInterestRateList();
            interestRate = interestRateManager.findInterestRateById(scanner);
        } while (null == interestRate);
        newLoan.setInterestRate(interestRate);
        do {
            String amountBorrow;
            switch (interestRate.getLevel()) {
                case 1:
                    System.out.println("Enter the amount you need to borrow: (1.000.000-49.999.999 VND)");
                    amountBorrow = scanner.nextLine();
                    if (UserManager.checkRegex(amountBorrow, "^(?:[1-9][0-9]{6}|[1-4][0-9]{7})$")) {
                        newLoan.setLoanAmount(Long.parseLong(amountBorrow));
                        flag =false;
                    }else {
                        System.out.println("Out range of your loan level, enter again");
                    }
                    break;
                case 2:
                    System.out.println("Enter the amount you need to borrow: (50.000.000-99.999.999 VND)");
                    amountBorrow = scanner.nextLine();
                    if (UserManager.checkRegex(amountBorrow, "^[5-9][0-9]{7}$")) {
                        newLoan.setLoanAmount(Long.parseLong(amountBorrow));
                        flag =false;
                    }else {
                        System.out.println("Out range of your loan level, enter again");
                    }
                    break;
                case 3:
                    System.out.println("Enter the amount you need to borrow: (100.000.000-149.999.999 VND)");
                    amountBorrow = scanner.nextLine();
                    if (UserManager.checkRegex(amountBorrow, "^(1[0-4][0-9]{7})$")) {
                        newLoan.setLoanAmount(Long.parseLong(amountBorrow));
                        flag =false;
                    }else {
                        System.out.println("Out range of your loan level, enter again");
                    }
                }
             }
            while (flag) ;

            do {
                System.out.println("Enter times do you wanna borrow:(1-36 months)");
                String monthBorrow = scanner.nextLine();
                if (UserManager.checkRegex(monthBorrow, "^(?:[1-9]|[1-2][0-9]|[3][0-6])$")) {
                    newLoan.setMonthLoan(Integer.parseInt(monthBorrow));
                    flag = true;
                } else {
                    System.out.println("Wrong format, enter again!");
                }
            } while (!flag);
            LocalDate startDate = LocalDate.now();
            newLoan.setStartDate(startDate);


        return newLoan;
    }
    public void addLoan(Scanner scanner,Loan loan,Map.Entry<String,User> entry){
        System.out.println("Are you sure you want to create this loan?");
        System.out.println("(Press Y to create, press any key to cancel)");
        String choiceCreateLoan = scanner.nextLine();
        if (choiceCreateLoan.equalsIgnoreCase("y")){
            loanList.add(loan);
            System.out.println("Create new loan success");
            displayUserLoan(entry);
        }else {
            System.out.println("Cancel success");
        }

    }
    public void displayLoan(Loan loan){
        if (loan!=null) {
            DecimalFormat df = new DecimalFormat("#.##");
            double monthlyPayment = loan.getLoanAmount() * (1 + (((loan.getInterestRate().getInterestRate() / 100)/12)* loan.getMonthLoan())) / loan.getMonthLoan();
            LocalDate endDate = loan.getStartDate().plusMonths(loan.getMonthLoan());
            System.out.printf("|%5s|%15s|%15s|%15s|%15s|%25s|%15s|%15s|%15s|", loan.getId(), loan.getBorrower(), loan.getLoanAmount()+" VND",
                    loan.getInterestRate().getInterestRate(), loan.getMonthLoan(), df.format(monthlyPayment)+" VND", loan.getStartDate(), endDate, loan.getStatusLoan());
            System.out.println();
        }
    }
    public void displayAllLoan(){
        tittleLoan();
        for (Loan loan:loanList){
            displayLoan(loan);
        }
        displayLine();
    }
    public void displayUserLoan(Map.Entry<String,User> entry){
        tittleLoan();
        for (Loan loan:loanList){
            if (loan.getBorrower().equals(entry.getKey())){
                displayLoan(loan);
            }
        }
      displayLine();

    }
    private void tittleLoan(){
       displayLine();
        System.out.printf("|%5s|%15s|%15s|%15s|%15s|%25s|%15s|%15s|%15s|","ID","BORROWER","BORROW AMOUNT","INTEREST RATE",
                "MONTH BORROW","MONTHLY PAYMENT","START DATE","END DATE","STATUS");
        System.out.println();
        displayLine();

    }

    public Loan findLoanById(Scanner scanner){
        Loan findLoan = null;
        boolean flag = true;
        do {
            System.out.println("Enter ID of loan :");
            String findLoanId = scanner.nextLine();
            if (UserManager.checkRegex(findLoanId, "^[0-9]{1,3}$")) {
                int findId = Integer.parseInt(findLoanId);
                flag = false;
                for (Loan loan : loanList) {
                    if (loan.getId() == findId) {
                        findLoan = loan;
                        break;
                    }
                }
                countFind--;
                if (findLoan==null&&countFind>0){
                    System.out.println("ID out of Loan list, you have "+ countFind+" times to choose a again");
                    flag=true;
                }
            } else {
                System.out.println("Wrong format, enter again");
            }
        }while (flag);
        countFind=3;
        return findLoan;
    }
    public void deleteLoanById(Scanner scanner,Map.Entry<String,User> entry){
        Loan findLoan = findLoanById(scanner);
        System.out.println("Are you sure you want to create this loan?");
        System.out.println("(Press Y to create, press any key to cancel)");
        String choiceDelLoan = scanner.nextLine();
        if (choiceDelLoan.equalsIgnoreCase("y")){
        if (findLoan.getBorrower().equals(entry.getKey())&& !findLoan.getStatusLoan().equals("Approved")) {
            loanList.remove(findLoan);
            System.out.println("Delete success");
          }
        }
         else {
            System.out.println("Delete fail, please choose again");
        }
    }
    public void editLoanById(Scanner scanner,Map.Entry<String,User>entry){
        Loan findLoan = findLoanById(scanner);
        if (findLoan != null && findLoan.getStatusLoan().equals("Pending")&&findLoan.getBorrower().equals(entry.getKey())){
        Loan newLoan = createLoan(scanner, entry);
        newLoan.setId(findLoan.getId());
        loanList.set(loanList.indexOf(findLoan),newLoan);
        System.out.println("Edit success");
    }else {
            System.out.println("You just can edit pending loan or the loan you choose is not exist, please choose again");
        }
    }
    private void displayLine(){
        System.out.print("*");
        for (int i =0;i<=142;i++ ){
            System.out.print("-");
        }
        System.out.println("*");
    }


}
