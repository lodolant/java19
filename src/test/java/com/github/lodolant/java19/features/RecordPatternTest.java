package com.github.lodolant.java19.features;

import com.github.lodolant.java19.model.Attack;
import com.github.lodolant.java19.model.PokemonKind;
import com.github.lodolant.java19.model.pokemon.Charmander;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordPatternTest {
    @Test
    public void makeAttack() {
        // Given
        Attack attack = new Attack(PokemonKind.FIRE, 80, "Flamethrower", new Charmander());

        // WHEN
        String attackResult = RecordPattern.makeAttack(attack);

        // THEN
        Assertions.assertEquals("Attack Flamethrower (FIRE) from Charmander, strength of 80", attackResult);
    }

    @Test
    public void makeAttack_Unknown() {
        // Given
        String attack = "Killer move";

        // WHEN
        String attackResult = RecordPattern.makeAttack(attack);

        // THEN
        Assertions.assertEquals("Unknown", attackResult);
    }
}
