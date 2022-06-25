package com.example.ebankingbackend;

import com.example.ebankingbackend.dtos.*;
import com.example.ebankingbackend.entities.*;
import com.example.ebankingbackend.enums.*;
import com.example.ebankingbackend.mappers.*;
import com.example.ebankingbackend.exceptions.*;
import com.example.ebankingbackend.repositories.*;
import com.example.ebankingbackend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    @Autowired
    private BankAccountMapperImpl mapper;

    public static void main(String[] args)
    {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }
    
    //@Bean
    CommandLineRunner start1(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository)
    {
        return args -> {
            Stream.of("Issam", "Ilyas", "Salma").forEach(name -> {
                Customer customer = new Customer();
                customer.setNom(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(cust -> {
                CurrentAccount ca = new CurrentAccount();
                ca.setId(UUID.randomUUID().toString());
                ca.setBalance(Math.random() * 90000);
                ca.setCustomer(cust);
                ca.setStatus(AccountStatus.CREATED);
                ca.setCreatedAt(new Date());
                ca.setOverdraft(10000);
                bankAccountRepository.save(ca);


                SavingAccount sa = new SavingAccount();
                sa.setId(UUID.randomUUID().toString());
                sa.setBalance(Math.random() * 50000);
                sa.setCustomer(cust);
                sa.setStatus(AccountStatus.CREATED);
                sa.setCreatedAt(new Date());
                sa.setInterestRate(5.5);

                bankAccountRepository.save(sa);

            });

            bankAccountRepository.findAll().forEach(a -> {
                for (int i = 0; i < 2; i++)
                {
                    AccountOperation ao = new AccountOperation();
                    ao.setAmount(Math.random() * 1200);
                    ao.setBankAccount(a);
                    ao.setDateOperation(new Date());
                    ao.setType(Math.random() > 0.5 ? OperationType.CREDIT : OperationType.DEBIT);
                    accountOperationRepository.save(ao);
                }
            });
        };
    };

    @Bean
    CommandLineRunner start2(BankAccountService bankAccountService)
    {
        return args -> {
            Stream.of("Issam", "Omar", "Youssef", "Sara", "Ali").forEach(n -> {
                Customer c = new Customer();
                c.setNom(n);
                c.setEmail(n+"@gmail.com");
                bankAccountService.saveCustomer(mapper.fromCustomer(c));
            });

            bankAccountService.listCustomers().forEach(c -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random() * 90000, 10000, c.getId());
                    bankAccountService.saveSavingBankAccount(Math.random() * 50000, 5.5, c.getId());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> accounts = bankAccountService.AccountList();
            for (BankAccountDTO bankAccount: accounts)
            {
                for (int i = 0; i < 10; i++ )
                {
                    String id;
                    if(bankAccount instanceof SavingAccountDTO)
                        id = ((SavingAccountDTO) bankAccount).getId();
                    else
                        id = ((CurrentAccountDTO) bankAccount).getId();

                    bankAccountService.credit(id , 1000 + Math.random() * 9000, "Credit");
                    bankAccountService.debit(id, 1000 + Math.random() * 5000, "Debit");
                }
            }


            };

        };
}

