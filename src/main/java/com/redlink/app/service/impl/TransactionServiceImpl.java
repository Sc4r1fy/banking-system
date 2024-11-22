package com.redlink.app.service.impl;

import com.redlink.app.entity.*;
import com.redlink.app.repository.AccountRepository;
import com.redlink.app.repository.TransactionRepository;
import com.redlink.app.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> findById(Integer id) {
        return transactionRepository.findById(id);
    }



    @Override
    public Transaction save(Transaction transaction) {

        System.out.println("checking transaction parameters");

        Account account = accountRepository.findById(transaction.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        BigDecimal amount = transaction.getAmount();
        boolean isValid = true;

        // acá valido que tenemos saldo suficiente
        if (account.getBalance().compareTo(amount) < 0) {
            transaction.setStatus("FAILED");
            isValid = false;
        }

        // uso switch cases porque con ifs iba a quedar horrible jaja
        if (isValid) {
            switch (transaction.getClass().getSimpleName()) {
                case "BankTransferTransaction":
                    BankTransferTransaction bankTransferTransaction = (BankTransferTransaction) transaction;
                    // chequeo detalles del banco
                    if (bankTransferTransaction.getBankCode().isEmpty() ||
                            bankTransferTransaction.getRecipientAccount().isEmpty()) {
                        transaction.setStatus("FAILED");
                        isValid = false;
                    }
                    break;

                case "P2PTransferTransaction":
                    P2PTransferTransaction p2pTransferTransaction = (P2PTransferTransaction) transaction;
                    // acá valido detalles del destinatario
                    if (p2pTransferTransaction.getRecipientId().isEmpty() ||
                            p2pTransferTransaction.getTransferId().isEmpty()) {
                        transaction.setStatus("FAILED");
                        isValid = false;
                    }
                    break;

                case "CardPaymentTransaction":
                    CardPaymentTransaction cardPaymentTransaction = (CardPaymentTransaction) transaction;
                    // acá valido que la ID de la tarjeta no es vacía.
                    if (cardPaymentTransaction.getCardId().isEmpty()) {
                        transaction.setStatus("FAILED");
                        isValid = false;
                    }
                    break;

                default:
                    transaction.setStatus("FAILED");
                    isValid = false;
                    break;
            }
        }

        // si todas las validaciones están OK se descuenta el saldo y se completa la transacción
        if (isValid) {
            account.setBalance(account.getBalance().subtract(amount));
            transaction.setStatus("COMPLETED");
        }

        // guardo la transaccion
        transaction.setAccount(account);
        transaction.setUser(account.getUser());
        return transactionRepository.save(transaction);
    }





    @Override
    public void deleteById(Integer id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Page<Transaction> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return transactionRepository.findAll(pageable);
    }
}
