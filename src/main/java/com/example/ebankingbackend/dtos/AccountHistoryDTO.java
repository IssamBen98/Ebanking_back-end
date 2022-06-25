package com.example.ebankingbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AccountHistoryDTO {
    private String id;
    private double Balance;
    private String type;
    private int currantPage;
    private int totalePages;
    private int pageSize;
    private List<AccountOperationDTO> operations;
}
