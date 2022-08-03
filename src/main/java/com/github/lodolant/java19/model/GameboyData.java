package com.github.lodolant.java19.model;

import com.github.lodolant.java19.model.pokemon.Pokemon;

import java.util.List;

public record GameboyData(List<Pokemon> pokemons, List<Arena> arenas, List<Attack> attacks, List<PokemonKind> pokemonKinds) {}
