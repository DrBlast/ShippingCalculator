package ru.shippingcalc;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;

@DisplayName("Calculate shipping cost")
@RunWith(DataProviderRunner.class)
public class CalcTest {

    private final Steps steps = new Steps();

    @DataProvider
    public static Object[][] testData() {
        return new Object[][]{
                {1, true, true, Workload.INCREASED, 660},

                {2, true, true, Workload.INCREASED, 660},
                {2, true, false, Workload.VERY_HIGH, 400},
                {2, false, true, Workload.HIGH, 630},
                {2, false, false, Workload.LOW, 400},

                {10, false, false, Workload.LOW, 400},
                {10, true, false, Workload.HIGH, 420},
                {10, false, true, Workload.VERY_HIGH, 800},
                {10, true, true, Workload.INCREASED, 720},

                {30, false, true, Workload.INCREASED, 720},
                {30, true, false, Workload.LOW, 400},
                {30, true, true, Workload.HIGH, 980},
                {30, false, false, Workload.VERY_HIGH, 480},

                {31, true, false, Workload.LOW, 500},
                {31, false, false, Workload.VERY_HIGH, 640}

        };
    }

    @Test
    @UseDataProvider("testData")
    public void testCalcShippingCost(int distance, boolean isOversized, boolean isFragile, Workload workload, int result) {
        steps.verifyCalc(distance, isOversized, isFragile, workload, result);
    }

    @Test()
    public void testFragileShippingOnHighDistanse(){
        steps.assertError(31, false, true, Workload.LOW,"We do not deliver fragile goods on distance over 30km");
    }

    @Test()
    public void distanceShouldBePositive(){
        steps.assertError(-1, false, true, Workload.LOW, "Distance should be greater than 0");
    }

    @Test()
    public void distanceShouldBeNotZero(){
        steps.assertError(0, false, true, Workload.LOW, "Distance should be greater than 0");
    }
}
