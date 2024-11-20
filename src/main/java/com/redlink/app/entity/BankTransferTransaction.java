package com.redlink.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("BANK_TRANSFER")
public class BankTransferTransaction extends Transaction {

    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @Column(name = "bank_code", nullable = false)
    private String bankCode;

    @Column(name = "recipient_account", nullable = false)
    private String recipientAccount;

}
