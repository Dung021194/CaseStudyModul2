package Manager;

import Model.InterestRate;
import Model.Loan;
import Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager {
    public FileManager() {

    }

    public ArrayList<Loan> read(String path){
        ArrayList<Loan> loanList=new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()){
                file.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(path);
            if (fileInputStream.available()>0){
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                loanList =(ArrayList<Loan>) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return loanList;
    }


    public Map<String, User> readMap(String path){
        Map<String,User> accountMap = new HashMap<>();
        try {
            File file = new File(path);
            if (!file.exists()){
                file.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(path);
            if (fileInputStream.available()>0){
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                accountMap = (Map<String, User>) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return accountMap;
    }
    public ArrayList<InterestRate> readInterest(String path){
        ArrayList<InterestRate> interestRateList=new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()){
                file.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(path);
            if (fileInputStream.available()>0){
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                interestRateList =(ArrayList<InterestRate>) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return interestRateList;
    }
    public void writeList(String pathAccount,String pathLoanList,String pathInterestRateList,ArrayList<InterestRate> interestRateList,ArrayList<Loan> loanList,Map<String,User> accountMap){
        try{
            FileOutputStream fosAccount = new FileOutputStream(pathAccount);
            FileOutputStream fosLoan = new FileOutputStream(pathLoanList);
            FileOutputStream fosInterest = new FileOutputStream(pathInterestRateList);
            ObjectOutputStream objAccount = new ObjectOutputStream(fosAccount);
            ObjectOutputStream objLoan = new ObjectOutputStream(fosLoan);
            ObjectOutputStream objInterest = new ObjectOutputStream(fosInterest);
            objAccount.writeObject(accountMap);
            objLoan.writeObject(loanList);
            objInterest.writeObject(interestRateList);
            objInterest.close();
            fosInterest.close();
            objLoan.close();
            objAccount.close();
            fosLoan.close();
            fosAccount.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
