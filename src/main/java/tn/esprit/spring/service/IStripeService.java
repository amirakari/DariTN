package tn.esprit.spring.service;

import com.stripe.exception.StripeException;

import tn.esprit.spring.entity.ChargeRequest;

public interface IStripeService {
	public String createStripeCustomer(int idUser);
	public String createCustumorStripe(String customerId, String carta, String expMonth, String expYear, String cvc) throws StripeException;
	public String paymentIntent(ChargeRequest chargerequest) throws StripeException;
	
}
