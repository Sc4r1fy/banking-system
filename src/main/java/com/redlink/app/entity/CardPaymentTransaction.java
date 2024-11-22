package com.redlink.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@DiscriminatorValue("CARD_PAYMENT")
public class CardPaymentTransaction extends Transaction {

    @Column(name = "payment_id", nullable = false)
    private String paymentId;

    @Column(name = "card_id", nullable = false)
    private String cardId;

    @Embedded
    private Merchant merchant;

    @Column(name = "mcc_code", nullable = false)
    private int mccCode;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Getter
    @Setter
    @Embeddable
    public static class Merchant {
        @Column(name = "merchant_name", nullable = false)
        private String name;

        @Column(name = "merchant_id", nullable = false)
        private String merchantId;
    }
}
