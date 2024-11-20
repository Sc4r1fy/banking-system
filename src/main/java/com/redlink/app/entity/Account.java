package com.redlink.app.entity;

import lombok.Getter;
import lombok.Setter;
import com.redlink.app.model.Currency;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;


@Setter
@Getter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "update_date")
    @UpdateTimestamp
    private Instant updateDate;

    @Column(name = "creation_date")
    @CreationTimestamp
    private Instant creationDate;

    @ManyToOne(targetEntity = User.class, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactionList;

}
