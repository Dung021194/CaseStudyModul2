package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
 private int id;
 private String borrower;
 private long loanAmount;
 private InterestRate interestRate;
 private int monthLoan;
 private LocalDate startDate;
 private String statusLoan;

 public String getBorrower() {
  return borrower;
 }

 public void setBorrower(String borrower) {
  this.borrower = borrower;
 }

 public long getLoanAmount() {
  return loanAmount;
 }

 public void setLoanAmount(long loanAmount) {
  this.loanAmount = loanAmount;
 }

 public InterestRate getInterestRate() {
  return interestRate;
 }

 public void setInterestRate(InterestRate interestRate) {
  this.interestRate = interestRate;
 }

 public int getMonthLoan() {
  return monthLoan;
 }

 public void setMonthLoan(int monthLoan) {
  this.monthLoan = monthLoan;
 }

 public LocalDate getStartDate() {
  return startDate;
 }

 public void setStartDate(LocalDate startDate) {
  this.startDate = startDate;
 }

 public String getStatusLoan() {
  return statusLoan;
 }

 public void setStatusLoan(String statusLoan) {
  this.statusLoan = statusLoan;
 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public Loan() {
 }

 public Loan(int id, String borrower, long loanAmount, InterestRate interestRate, int monthLoan, LocalDate startDate, String statusLoan) {
  this.id = id;
  this.borrower = borrower;
  this.loanAmount = loanAmount;
  this.interestRate = interestRate;
  this.monthLoan = monthLoan;
  this.startDate = startDate;
  this.statusLoan = statusLoan;
 }
}

