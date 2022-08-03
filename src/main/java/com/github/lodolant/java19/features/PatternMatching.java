package com.github.lodolant.java19.features;

import com.github.lodolant.java19.model.Arena;
import com.github.lodolant.java19.model.pokemon.Mewtwo;
import com.github.lodolant.java19.model.pokemon.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatternMatching {
    private final static Logger LOGGER = LoggerFactory.getLogger(PatternMatching.class);

    public static String attackTarget(Object target) {
        LOGGER.info("Attacking {}", target);
        String formatted;
        if (target == null) {
            formatted = "null target";
        } else if (target instanceof Mewtwo) {
            formatted = "Un-identified target, don't think you want to attack...";
        } else if (target instanceof Pokemon pokemon) {
            formatted = "Attacking pokemon %s".formatted(pokemon.getClass().getSimpleName());
        } else if (target instanceof Arena arena) {
            if (arena.hasBadge()) {
                formatted = "Cannot attack %s arena, we already own the badge".formatted(arena.championName());
            } else {
                formatted = "Attacking %s arena, with champion %s".formatted(arena.type(), arena.championName());
            }
        } else {
            formatted = "%s is not a valid target".formatted(target.getClass().getSimpleName());
        }
        return formatted;
    }
}
