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
        return switch (target) {
            case null -> "null target";
            case Mewtwo mewtwo -> "Un-identified target, don't think you want to attack...";
            case Pokemon pokemon -> "Attacking pokemon %s".formatted(pokemon.getClass().getSimpleName());
            case Arena arena when arena.hasBadge() -> "Cannot attack %s arena, we already own the badge".formatted(arena.championName());
            case Arena arena -> "Attacking %s arena, with champion %s".formatted(arena.type(), arena.championName());
            default -> "%s is not a valid target".formatted(target.getClass().getSimpleName());
        };
    }
}
