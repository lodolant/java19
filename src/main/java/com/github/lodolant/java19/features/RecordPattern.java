package com.github.lodolant.java19.features;

import com.github.lodolant.java19.model.Attack;
import com.github.lodolant.java19.model.PokemonKind;
import com.github.lodolant.java19.model.pokemon.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordPattern {
    private final static Logger LOGGER = LoggerFactory.getLogger(RecordPattern.class);

    public static String makeAttack(Object o) {
        LOGGER.info("Making attack");
        if (o instanceof Attack(PokemonKind type,int strength,String name,Pokemon owner)) {
            return "Attack %s (%s) from %s, strength of %d".formatted(name, type, owner, strength);
        }
        return "Unknown";
    }
}
