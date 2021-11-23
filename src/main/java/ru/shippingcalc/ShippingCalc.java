package ru.shippingcalc;

public class ShippingCalc {

    private static final int MINIMAL_COST = 400;
    private static final int FRAGILE_COST = 300;
    private static final int OVERSIZED_COST = 200;
    private static final int STANDARD_SIZE_COST = 100;

    private int getAdditionalCostByDistance(int distance) {
        if (distance <= 2)
            return 50;
        else if (distance <= 10)
            return 100;
        else if (distance <= 30)
            return 200;
        else return 300;
    }

    /**
     * Calculate shipping cost based on
     * @param distance to destination point, km
     * @param isOversized are goods oversized
     * @param isFragile are goods fragile
     * @param workload of delivery service, possible values (LOW, INCREASED, HIGH, VERY_HIGH)
     * @return calculated shipping cost
     * @throws Error
     */
    public int calc(int distance, boolean isOversized, boolean isFragile, Workload workload) throws IllegalArgumentException {
        int calculatedCost = 0;
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance should be greater than 0");
        }

        if (isFragile && distance > 30) {
            throw new IllegalArgumentException("We do not deliver fragile goods on distance over 30km");
        }

        if (isFragile)
            calculatedCost += FRAGILE_COST;

        if (isOversized)
            calculatedCost += OVERSIZED_COST;
        else
            calculatedCost += STANDARD_SIZE_COST;

        calculatedCost += getAdditionalCostByDistance(distance);
        calculatedCost *= workload.coefficient;

        calculatedCost = calculatedCost / 10;

        if (calculatedCost < MINIMAL_COST)
            calculatedCost = MINIMAL_COST;

        return calculatedCost;
    }
}
