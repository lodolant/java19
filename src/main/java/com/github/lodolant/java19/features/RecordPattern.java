package com.github.lodolant.java19.features;

import com.github.lodolant.java19.model.Attack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordPattern {
    private final static Logger LOGGER = LoggerFactory.getLogger(RecordPattern.class);

    public static String makeAttack(Object o) {
        LOGGER.info("Making attack");
        if (o instanceof Attack attack) {
            return "Attack %s (%s) from %s, strength of %d".formatted(attack.name(), attack.type(), attack.owner(), attack.strength());
        }
        return "Unknown";
    }
}
