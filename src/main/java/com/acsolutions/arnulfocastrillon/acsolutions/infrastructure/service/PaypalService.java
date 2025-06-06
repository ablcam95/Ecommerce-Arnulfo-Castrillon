package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.service.CurrencyExchangeService;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PaypalService {
    private final APIContext apiContext;
    private final CurrencyExchangeService currencyExchangeService;

    public PaypalService(APIContext apiContext, CurrencyExchangeService currencyExchangeService) {
        this.apiContext = apiContext;
        this.currencyExchangeService = currencyExchangeService;
    }

    public Payment createPaymentFromCop(
            Double totalCop,
            String method,
            String intent,
            String description,
            String successUrl,
            String cancelUrl
    ) throws PayPalRESTException {
        double usdToCopRate = currencyExchangeService.getUsdToCopRate();
        double totalUsd = totalCop / usdToCopRate;
        totalUsd = Math.round(totalUsd * 100.0) / 100.0; // Redondeo a 2 decimales

        return createPayment(
                totalUsd,
                "USD",
                method,
                intent,
                description,
                successUrl,
                cancelUrl

        );
    }

    public Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String successUrl,
            String cancelUrl
    ) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format(Locale.forLanguageTag(currency),"%.2f",total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl(successUrl);
        redirectUrls.setCancelUrl(cancelUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext) ;
    }


    public  Payment executePayment(

            String paymentId,

            String payerId

    ) throws PayPalRESTException {

        Payment payment = new Payment();

        payment.setId(paymentId);



        PaymentExecution paymentExecution = new PaymentExecution();

        paymentExecution.setPayerId(payerId);

        return  payment.execute(apiContext,paymentExecution);

    }
}
