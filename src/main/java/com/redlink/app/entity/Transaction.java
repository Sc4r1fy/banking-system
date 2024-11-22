package com.redlink.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.redlink.app.model.Currency;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "transaction_type")
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "transaction_type" )
@JsonSubTypes({ @JsonSubTypes.Type(value = CardPaymentTransaction.class, name = "CARD_PAYMENT"),
                @JsonSubTypes.Type(value = BankTransferTransaction.class, name = "BANK_TRANSFER"),
                @JsonSubTypes.Type(value = P2PTransferTransaction.class, name = "P2P_TRANSFER") })
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "status", nullable = false)
    private String status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "is_outgoing")
    private Boolean isOutgoing;

    @ManyToOne(targetEntity = User.class, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"accounts"})
    private User user;

    @JsonIgnoreProperties({"user", "transactionList"})
    @ManyToOne(targetEntity = Account.class, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


}