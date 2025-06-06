package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.rest;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.DataPayment;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.URLPaypalResponse;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/payments")
public class PaypalController {

    private final PaypalService paypalService;
    private final String SUCCESS_URL = "http://localhost:8085/api/v1/payments/success";
    private final String CANCEL_URL = "http://localhost:8085/api/v1/payments/cancel";

    @PostMapping
    public URLPaypalResponse createPayment(@RequestBody DataPayment dataPayment){
        try {
            Payment payment;
            if ("COP".equalsIgnoreCase(dataPayment.getCurrency())) {
                // Si la moneda es COP, convierte a USD antes de enviar a PayPal
                payment = paypalService.createPaymentFromCop(
                        Double.valueOf(dataPayment.getAmount()),
                        dataPayment.getMethod(),
                        "SALE",
                        dataPayment.getDescription(),
                        SUCCESS_URL,
                        CANCEL_URL
                );
            } else {
                // Si ya viene en USD u otra moneda soportada por PayPal, no conviertas
                payment = paypalService.createPayment(
                        Double.valueOf(dataPayment.getAmount()),
                        dataPayment.getCurrency(),
                        dataPayment.getMethod(),
                        "SALE",
                        dataPayment.getDescription(),
                        SUCCESS_URL,
                        CANCEL_URL
                );
            }
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return new URLPaypalResponse(links.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();

        }

        return new URLPaypalResponse("http://localhost:4200");
    }


    @GetMapping("/success")
    public RedirectView paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId
    ){
        try {
            Payment payment = paypalService.executePayment(paymentId,payerId);
            if(payment.getState().equals("approved")){
                return new RedirectView("http://localhost:4200/payment/success");

            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();

        }
        return new RedirectView("http://localhost:4200");
    }

    @GetMapping("/cancel")
    public RedirectView paymentCancel(){
        return new RedirectView("http://localhost:4200");
    }
}
