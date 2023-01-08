package Manager;

import Model.InterestRate;

import java.util.ArrayList;
import java.util.Scanner;

public class InterestRateManager {
    private int countFind=3;
    ArrayList<InterestRate> interestRateList;
    public InterestRateManager() {
        interestRateList = new ArrayList<>();
    }

    public ArrayList<InterestRate> getInterestRateList() {
        return interestRateList;
    }

    public void setInterestRateList(ArrayList<InterestRate> interestRateList) {
        this.interestRateList = interestRateList;
    }

    public InterestRate createInterestRate(Scanner scanner){
        boolean flag=true;
        int id;
        double interestRate=0;
        int level =0;
        if (interestRateList.isEmpty()) {
            id = 1;
        } else {
            id = interestRateList.get(interestRateList.size()-1).getId() + 1;
        }
       do {
           System.out.println("Enter interest rate");
           System.out.println("Loan level 1: 5.0-9.99");
           System.out.println("Loan level 2: 10.0-14.99");
           System.out.println("Loan level 3: 15.0-20");

           String interestRateInput = scanner.nextLine();
           if (UserManager.checkRegex(interestRateInput,"^[5-9]$|^[5-9]\\.[0-9]$|^[1][0-9]$|^[1][0-9]\\.[0-9]$|^20$|^20\\.[0]$")){
               interestRate = Double.parseDouble(interestRateInput);
               flag=false;
           }else {
               System.out.println("Enter again");
           }
       }while (flag);

       if ( interestRate <10){level=1; }
       else if (interestRate <15){level=2;}
       else {level=3;}
       return new InterestRate(id,interestRate,level);
    }
    public InterestRate findInterestRateById(Scanner scanner){
        InterestRate findInterestRate = null;
        boolean flag = true;
        do {
            System.out.println("Enter ID of interest rate :");
            String findInterestIdInput = scanner.nextLine();
            if (UserManager.checkRegex(findInterestIdInput, "^[0-9]{1,3}$")) {
                int findInterestId = Integer.parseInt(findInterestIdInput);
                flag = false;
                for (InterestRate interestRate : interestRateList) {
                    if (interestRate.getId() == findInterestId) {
                        findInterestRate = interestRate;
                        break;
                    }
                } countFind--;
                if (findInterestRate ==null && countFind>0){
                    System.out.println("Out of interest rate list, choose again");
                    flag=true;
                }
            }else {
                System.out.println("Wrong format, enter again");
            }
        }while (flag);

        return findInterestRate;
    }


    public void editInterestRateById(Scanner scanner) {
        InterestRate findInterest = findInterestRateById(scanner);
        if (findInterest != null) {
            InterestRate editInterest = createInterestRate(scanner);
            editInterest.setId(findInterest.getId());
            interestRateList.set(interestRateList.indexOf(findInterest), editInterest);
            System.out.println("Edit success");
        } else {
            System.out.println("Edit fail");
        }
    }
    public void deleteInterestRate (Scanner scanner){
        InterestRate findInterest = findInterestRateById(scanner);
        if (findInterest!=null){
            interestRateList.remove(findInterest);
            System.out.println("Delete success");
        }
        else {
            System.out.println("Delete fail, choose again");
        }
    }
    public void displayInterestRateList(){
        System.out.printf("%15s%25s%20s","ID","INTEREST RATE(%/year)","INTEREST LEVEL");
        System.out.println();
        for (InterestRate interestRate:interestRateList){
            System.out.println(interestRate);
        }
    }

    }
