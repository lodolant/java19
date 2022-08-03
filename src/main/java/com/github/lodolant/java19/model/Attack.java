package com.github.lodolant.java19.model;

import com.github.lodolant.java19.model.pokemon.Pokemon;

public record Attack(PokemonKind type, int strength, String name, Pokemon owner) {
}
