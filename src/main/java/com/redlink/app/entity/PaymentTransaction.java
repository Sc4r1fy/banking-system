package com.redlink.app.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("PAYMENT")
public class PaymentTransaction extends Transaction {

    @Column(name = "payment_id", nullable = false)
    private String paymentId;

    @Column(name = "card_id", nullable = false)
    private String cardId;

    @Column(name = "merchant_name", nullable = false)
    private String merchantName;

    @Column(name = "merchant_id", nullable = false)
    private String merchantId;

    @Column(name = "mcc_code", nullable = false)
    private int mccCode;


}

