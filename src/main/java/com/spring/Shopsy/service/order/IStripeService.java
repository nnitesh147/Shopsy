package com.spring.Shopsy.service.order;

import com.spring.Shopsy.payload.order.StripePaymentDto;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface IStripeService {
    PaymentIntent paymentIntent(StripePaymentDto stripePaymentDto) throws StripeException;
}
