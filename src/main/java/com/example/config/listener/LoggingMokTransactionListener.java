package com.example.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.TransactionExecution;
import org.springframework.transaction.TransactionExecutionListener;

@Slf4j
public class LoggingMokTransactionListener implements TransactionExecutionListener {
    @Override
    public void afterBegin(TransactionExecution transaction, Throwable beginFailure) {
        log.info("mok: TX id started");
        TransactionExecutionListener.super.afterBegin(transaction, beginFailure);
    }

    @Override
    public void beforeCommit(TransactionExecution transaction) {
        log.info("mok: TX id before commit");
        TransactionExecutionListener.super.beforeCommit(transaction);
    }

    @Override
    public void afterCommit(TransactionExecution transaction, Throwable commitFailure) {
        log.info("mok: TX id ended with status COMMIT");
        TransactionExecutionListener.super.afterCommit(transaction, commitFailure);
    }

    @Override
    public void beforeRollback(TransactionExecution transaction) {
        log.info("mok: TX id before rollback");
        TransactionExecutionListener.super.beforeRollback(transaction);
    }

    @Override
    public void afterRollback(TransactionExecution transaction, Throwable rollbackFailure) {
        log.info("mok: TX id ended with status ROLLBACK");
        TransactionExecutionListener.super.afterRollback(transaction, rollbackFailure);
    }
}
