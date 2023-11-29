package de.alice.limitsservice.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Limits {
    private int minimum;
    private int maximum;

    public Limits() {
    }

    public Limits(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }
}
