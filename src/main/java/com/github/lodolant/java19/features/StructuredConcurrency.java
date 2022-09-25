package com.github.lodolant.java19.features;

import com.github.lodolant.java19.model.Arena;
import com.github.lodolant.java19.model.Attack;
import com.github.lodolant.java19.model.GameboyData;
import com.github.lodolant.java19.model.PokemonKind;
import com.github.lodolant.java19.model.pokemon.*;
import jdk.incubator.concurrent.StructuredTaskScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class StructuredConcurrency {
    private final static Logger LOGGER = LoggerFactory.getLogger(StructuredConcurrency.class);

    public static Optional<GameboyData> loadData(boolean shouldFail) {
        Instant begin = Instant.now();
        try (StructuredTaskScope.ShutdownOnFailure scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<List<Pokemon>> pokemons = scope.fork(getPokemons(shouldFail));
            Future<List<Arena>> arenas = scope.fork(getArenas());
            Future<List<Attack>> attacks = scope.fork(getAttacks());
            Future<List<PokemonKind>> kinds = scope.fork(getPokemonKinds());
            scope.join();
            scope.throwIfFailed();
            GameboyData gameboyData = new GameboyData(pokemons.resultNow(), arenas.resultNow(), attacks.resultNow(), kinds.resultNow());
            return Optional.of(gameboyData);
        } catch (Exception e) {
            LOGGER.info("Error encountered : {}", e.getLocalizedMessage());
            return Optional.empty();
        } finally {
            Instant end = Instant.now();
            LOGGER.info("Loading duration = {} ms", Duration.between(begin, end).toMillis());
        }
    }

    private static Callable<List<Pokemon>> getPokemons(boolean shouldFail) {
        return () -> {
            LOGGER.info("Loading pokemons...");
            List<Pokemon> pokemons = new ArrayList<>();
            pokemons.add(new Squirtle());
            pokemons.add(new Bulbasaur());
            pokemons.add(new Mewtwo());
            Thread.sleep(7000);
            pokemons.add(new Charmander());
            if (shouldFail) {
                throw new RuntimeException("Boom");
            }
            return pokemons;
        };
    }

    private static Callable<List<Arena>> getArenas() {
        return () -> {
            LOGGER.info("Loading pokemon arenas...");
            List<Arena> arenas = new ArrayList<>();
            arenas.add(new Arena("Pierre", PokemonKind.ROCK, true));
            arenas.add(new Arena("Ondine", PokemonKind.WATER, false));
            arenas.add(new Arena("Major Bob", PokemonKind.ELECTRIK, false));
            arenas.add(new Arena("Erika", PokemonKind.GRASS, false));
            arenas.add(new Arena("Koga", PokemonKind.POISON, false));
            arenas.add(new Arena("Morgane", PokemonKind.PSY, false));
            arenas.add(new Arena("Auguste", PokemonKind.FIRE, false));
            arenas.add(new Arena("Giovanni", PokemonKind.EARTH, false));
            Thread.sleep(8000);
            return arenas;
        };
    }

    private static Callable<List<Attack>> getAttacks() {
        return () -> {
            LOGGER.info("Loading pokemon attacks...");
            List<Attack> attacks = new ArrayList<>();
            attacks.add(new Attack(PokemonKind.ELECTRIK, 100, "Fatal Foudre", new Bulbasaur()));
            attacks.add(new Attack(PokemonKind.FIRE, 80, "Flamethrower", new Charmander()));
            attacks.add(new Attack(PokemonKind.PSY, 60, "Psy Attack", new Mewtwo()));
            attacks.add(new Attack(PokemonKind.GRASS, 70, "Vine Whip", new Bulbasaur()));
            Thread.sleep(4000);
            return attacks;
        };
    }

    private static Callable<List<PokemonKind>> getPokemonKinds() {
        return () -> {
            LOGGER.info("Loading pokemon kinds...");
            List<PokemonKind> pokemonKinds = new ArrayList<>(Arrays.stream(PokemonKind.values()).toList());
            Thread.sleep(1000L * pokemonKinds.size());
            return pokemonKinds;
        };
    }
}
