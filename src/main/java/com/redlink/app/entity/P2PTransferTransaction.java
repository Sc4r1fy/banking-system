package com.redlink.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("P2P_TRANSFER")
public class P2PTransferTransaction extends Transaction {

    @Column(name = "transfer_id", nullable = false)
    private String transferId;

    @Column(name = "sender_id", nullable = false)
    private String senderId;

    @Column(name = "recipient_id", nullable = false)
    private String recipientId;

    @Column(name = "note")
    private String note;

}
