package com.github.lodolant.java19.features;

import com.github.lodolant.java19.model.Arena;
import com.github.lodolant.java19.model.Attack;
import com.github.lodolant.java19.model.GameboyData;
import com.github.lodolant.java19.model.PokemonKind;
import com.github.lodolant.java19.model.pokemon.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class StructuredConcurrencyTest {
    private static Optional<GameboyData> gameboyDataSuccess;
    private static Optional<GameboyData> gameboyDataFailure;

    @BeforeAll
    static void loadData() {
        gameboyDataSuccess = StructuredConcurrency.loadData(false);
        gameboyDataFailure = StructuredConcurrency.loadData(true);
    }

    @Test
    public void canLoadData() {
        // THEN
        Assertions.assertTrue(gameboyDataSuccess.isPresent());
    }

    @Test
    public void canFailToLoadData() {
        // THEN
        Assertions.assertTrue(gameboyDataFailure.isEmpty());
    }

    @Test
    public void canLoadPokemons() {
        // THEN
        List<Pokemon> pokemons = gameboyDataSuccess.get().pokemons();
        Assertions.assertEquals(4, pokemons.size());
    }

    @Test
    public void canLoadArenas() {
        // THEN
        List<Arena> arenas = gameboyDataSuccess.get().arenas();
        Assertions.assertEquals(8, arenas.size());
    }

    @Test
    public void canLoadKinds() {
        // THEN
        List<PokemonKind> pokemonKinds = gameboyDataSuccess.get().pokemonKinds();
        Assertions.assertEquals(8, pokemonKinds.size());
    }

    @Test
    public void canLoadAttacks() {
        // THEN
        List<Attack> attacks = gameboyDataSuccess.get().attacks();
        Assertions.assertEquals(4, attacks.size());
    }
}
