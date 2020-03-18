package com.tao.demo.paypal.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.tao.demo.paypal.config.PaypalPaymentIntent;
import com.tao.demo.paypal.config.PaypalPaymentMethod;
import com.tao.demo.paypal.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class PaymentController {

    public static final String PAYPAL_SUCCESS_URL = "success";
    public static final String PAYPAL_CANCEL_URL = "cancel";

    @Autowired
    private PaypalService paypalService;

    @PostMapping
    @RequestMapping("pay")
    public String pay(HttpServletRequest request) {
        String baseUrl = paypalService.getBaseURl(request);
        String cancelUrl = baseUrl+ "/" + PAYPAL_CANCEL_URL;
        String successUrl = baseUrl + "/" + PAYPAL_SUCCESS_URL;
        System.out.println("successUrl=="+successUrl);
        try {
            Payment payment = paypalService.createPayment(
                    7.00,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl);
            System.out.println("pay=="+payment);
            for (Links links: payment.getLinks()) {
                if(links.getRel().equals("approval_url")){
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping
    @RequestMapping(PAYPAL_CANCEL_URL)
    public String cancelPay(){
        return "cancel";
    }

    @GetMapping
    @RequestMapping(PAYPAL_SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
        try {
            System.out.println("paymentId=="+paymentId+"---payerId=="+payerId);
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println("payment==="+payment.getState());
            if(payment.getState().equals("approved")){
                return "success";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @RequestMapping("paypal/notify")
    public String notify(HttpServletRequest request){
        System.out.println("---------notify---------");
        return "notify";
    }

}
