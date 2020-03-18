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
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;

@Controller
@RequestMapping("/")
public class PaymentController {

    public static final String PAYPAL_SUCCESS_URL = "pay/success";
    public static final String PAYPAL_CANCEL_URL = "h5/cancel";

    @Autowired
    private PaypalService paypalService;

    @PostMapping
    @RequestMapping("pay")
    public String pay(HttpServletRequest request) {
        String baseUrl = paypalService.getBaseURl(request);
        String cancelUrl = baseUrl+ "/" + PAYPAL_CANCEL_URL;
        String successUrl = baseUrl + "/" + PAYPAL_SUCCESS_URL;
        try {
            Payment payment = paypalService.createPayment(
                    7.00,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl);
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
    @RequestMapping(PAYPAL_SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                return "redirect:/h5/success";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/h5/index";
    }


    @RequestMapping("paypal/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response){
        System.out.println("---------notify---------");

        try {
            PrintWriter out = response.getWriter();
            Enumeration<String> en = request.getParameterNames();
            String str = "cmd=_notify-validate";
            while (en.hasMoreElements()) {
                String paramName = en.nextElement();
                String paramValue = request.getParameter(paramName);
                //此处的编码一定要和自己的网站编码一致，不然会出现乱码，paypal回复的通知为‘INVALID’
                str = str + "&" + paramName + "=" + URLEncoder.encode(paramValue, "utf-8");
            }
            System.out.println("notify==="+str);
            URL u = new URL("https://www.sandbox.paypal.com/cgi-bin/webscr");
            HttpURLConnection uc = (HttpURLConnection) u.openConnection();
            uc.setRequestMethod("POST");
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setUseCaches(false);
            //设置 HTTP 的头信息
            uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            PrintWriter pw = new PrintWriter(uc.getOutputStream());
            pw.println(str);
            pw.close();

            /**
             * 接受 PayPal 对 IPN 回发的回复信息
             */
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String res = in.readLine();
            in.close();
            System.out.println("res==="+res);
            if (res.equalsIgnoreCase("VERIFIED")) {
                // 交易状态 Completed 代表交易成功
                String paymentStatus = request.getParameter("payment_status");
                // 交易时间
                String paymentDate = request.getParameter("payment_date");
                // 交易id
                String txnId = request.getParameter("txn_id");
                // 父交易id
                String parentTxnId = request.getParameter("parent_txn_id");
                // 交易金额
                String mcGross = request.getParameter("mc_gross");
                // 自定义字段，我们存放的订单ID
                String custom = request.getParameter("custom");
                System.out.println(String.format("paymentStatus=%s，txnId=%s，parentTxnId=%s，mcGross=%s，custom=%s", paymentStatus, txnId, parentTxnId, mcGross, custom));
                System.out.println("status=="+paymentStatus+",txnid=="+txnId);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
