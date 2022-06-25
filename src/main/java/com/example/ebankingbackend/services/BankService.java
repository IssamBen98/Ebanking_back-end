package com.example.ebankingbackend.services;

import lombok.AllArgsConstructor;
import com.example.ebankingbackend.entities.*;
import com.example.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class BankService {

    private BankAccountRepository bankAccountRepository;
    public void consulter()
    {
        BankAccount account = bankAccountRepository.findById("25e57558-786d-429b-a908-31324514ed34").orElse(null);
        if (account != null)
        {
            System.out.println("----------------------------------------");
            System.out.println("Id : \t" + account.getId());
            System.out.println("Client : \t" + account.getCustomer().getNom());
            System.out.println("Date : \t" + account.getCreatedAt());
            System.out.println("Status : \t" + account.getStatus());
            if (account instanceof CurrentAccount)
                System.out.println("Over Draft \t"+ ((CurrentAccount) account).getOverdraft());
            else if (account instanceof SavingAccount)
                System.out.println("Rate : \t" + ((SavingAccount) account).getInterestRate());

            account.getAccountOperations().forEach(o -> {
                System.out.println("type : \t" + o.getType() + " amount : \t" + o.getAmount() + " Date : \t" + o.getDateOperation());
            });

        }
    }
}
