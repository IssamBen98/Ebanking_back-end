package com.example.ebankingbackend.repositories;

import com.example.ebankingbackend.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long>
{
    public List<AccountOperation> findByBankAccountId(String accountId);

    public Page<AccountOperation> findByBankAccountId(String accountId, Pageable pageable);
}
