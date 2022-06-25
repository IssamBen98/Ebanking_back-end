package com.example.ebankingbackend.services;

import com.example.ebankingbackend.entities.*;
import com.example.ebankingbackend.exceptions.*;
import com.example.ebankingbackend.dtos.*;

import java.util.List;

// Service : manipulation des classes DAO


public interface BankAccountService {
        CustomerDTO saveCustomer(CustomerDTO customerDTO);
        CustomerDTO updateCustomer(CustomerDTO customerDTO);
        void deleteCustomer(Long customerId);
        CurrentAccountDTO saveCurrentBankAccount(double initSolde, double overDraft, Long customerId) throws CusttomerNotFoundException;
        SavingAccountDTO saveSavingBankAccount(double initSolde, double interestRate, Long customerId) throws CusttomerNotFoundException;
        List<CustomerDTO> listCustomers();


        List<CustomerDTO> searchCustomers(String keyWord);

        BankAccountDTO getAccount(String accountId) throws BankAccountNotFoundException;
        List<BankAccountDTO> AccountList();
        CustomerDTO getCustomer(Long id) throws CusttomerNotFoundException;
        void debit(String accountId, double amount, String desc) throws BankAccountNotFoundException, BalanceNotSufficientException;
        void credit(String accountId, double amount, String desc) throws BalanceNotSufficientException, BankAccountNotFoundException;
        void transfer(String accountIdS, String accountIdD, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

        List<AccountOperationDTO> accountHistory(String id);

        AccountHistoryDTO getAccountHistory(String id, int page, int size) throws BankAccountNotFoundException;

/*
    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentBankAccount(double initSolde, double overDraft, Long customerId) throws CusttomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initSolde, double interestRate, Long customerId) throws CusttomerNotFoundException;
    List<Customer> listCustomers();
    BankAccount getAccount(String accountId) throws BankAccountNotFoundException;
    List<BankAccount> AccountList();
    void debit(String accountId, double amount, String desc) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String desc) throws BalanceNotSufficientException, BankAccountNotFoundException;
    void tranfser(String accountIdS, String accountIdD, double amount, String desc) throws BankAccountNotFoundException, BalanceNotSufficientException;

*/
}