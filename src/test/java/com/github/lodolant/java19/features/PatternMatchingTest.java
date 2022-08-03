package com.github.lodolant.java19.features;

import com.github.lodolant.java19.model.*;
import com.github.lodolant.java19.model.pokemon.Bulbasaur;
import com.github.lodolant.java19.model.pokemon.Mewtwo;
import com.github.lodolant.java19.model.pokemon.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatternMatchingTest {
    @Test
    public void attack_Pokemon() {
        Pokemon pokemon = new Bulbasaur();
        String attack = PatternMatching.attackTarget(pokemon);
        Assertions.assertEquals("Attacking pokemon Bulbasaur", attack);
    }

    @Test
    public void attack_Arena() {
        Arena arena = new Arena("Erika", PokemonKind.GRASS, false);
        String attack = PatternMatching.attackTarget(arena);
        Assertions.assertEquals("Attacking GRASS arena, with champion Erika", attack);
    }

    @Test
    public void attack_Arena_WithBadge() {
        // GIVEN
        Arena arena = new Arena("Erika", PokemonKind.GRASS, true);

        // WHEN
        String attack = PatternMatching.attackTarget(arena);

        // THEN
        Assertions.assertEquals("Cannot attack Erika arena, we already own the badge", attack);
    }

    @Test
    public void attack_invalidTarget() {
        // GIVEN
        PokemonKind kind = PokemonKind.FIRE;

        // WHEN
        String attack = PatternMatching.attackTarget(kind);

        // THEN
        Assertions.assertEquals("PokemonKind is not a valid target", attack);
    }

    @Test
    public void attack_orNot() {
        // GIVEN
        Pokemon pokemon = new Mewtwo();

        // WHEN
        String attack = PatternMatching.attackTarget(pokemon);

        // THEN
        Assertions.assertEquals("Un-identified target, don't think you want to attack...", attack);
    }

    @Test
    public void attack_null() {
        // WHEN
        String attack = PatternMatching.attackTarget(null);

        // THEN
        Assertions.assertEquals("null target", attack);
    }
}
