package com.example.ebankingbackend.mappers;

import com.example.ebankingbackend.dtos.*;
import com.example.ebankingbackend.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer)
    {
        CustomerDTO customerDTO = new CustomerDTO();
        // customerDTO.setNom(customer.getNom());
        // customerDTO.setId(customer.getId());
        // customerDTO.setEmail(customer.getEmail());
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }
    public Customer fromCustomerDTO(CustomerDTO customerDTO)
    {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public SavingAccountDTO fromSavingAccount(SavingAccount account)
    {
        SavingAccountDTO savingAccountDTO = new SavingAccountDTO();
        BeanUtils.copyProperties(account, savingAccountDTO);
        savingAccountDTO.setCustomer(fromCustomer(account.getCustomer()));
        savingAccountDTO.setType(account.getClass().getSimpleName());
        return savingAccountDTO;
    }

    public SavingAccount fromSavingAccountDTO(SavingAccountDTO savingAccountDTO)
    {
        SavingAccount account = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO, account);
        account.setCustomer(fromCustomerDTO(savingAccountDTO.getCustomer()));
        return account;
    }


    public CurrentAccountDTO fromCurrentAccount(CurrentAccount account)
    {
        CurrentAccountDTO currentAccountDTO = new CurrentAccountDTO();
        BeanUtils.copyProperties(account, currentAccountDTO);
        currentAccountDTO.setCustomer(fromCustomer(account.getCustomer()));
        currentAccountDTO.setType(account.getClass().getSimpleName());
        return currentAccountDTO;
    }

    public CurrentAccount fromCurrentAccountDTO(CurrentAccountDTO currentAccountDTO)
    {
        CurrentAccount account = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDTO, account);
        account.setCustomer(fromCustomerDTO(currentAccountDTO.getCustomer()));
        return account;
    }

    public AccountOperationDTO fromAccounOperation(AccountOperation accountOperation)
    {
        AccountOperationDTO operationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, operationDTO);
        return operationDTO;
    }

}
