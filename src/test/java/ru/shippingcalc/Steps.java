package ru.shippingcalc;

import io.qameta.allure.Step;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class Steps {
    @Step
    public void verifyCalc(int distance, boolean isOversized, boolean isFragile, Workload workload, int result) {
        ShippingCalc sc = new ShippingCalc();
        assertEquals(sc.calc(distance, isOversized, isFragile, workload), result);
    }

    @Step
    public void assertError(int distance, boolean isOversized, boolean isFragile, Workload workload, String errorMessage){
        ShippingCalc sc = new ShippingCalc();
        Throwable t = assertThrows(IllegalArgumentException.class, () -> sc.calc(distance, isOversized, isFragile, workload));
        assertEquals(errorMessage, t.getMessage());
    }
}
