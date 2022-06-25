package com.example.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@DiscriminatorValue("SAV")

public class SavingAccount extends BankAccount
{
    private double interestRate;
}
