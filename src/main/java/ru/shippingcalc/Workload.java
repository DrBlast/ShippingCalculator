package ru.shippingcalc;

public enum Workload {

    VERY_HIGH(16),
    HIGH(14),
    INCREASED(12),
    LOW(10);

    public final int coefficient;

    Workload(int coefficient) {
        this.coefficient = coefficient;
    }
}
