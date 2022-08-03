package com.github.lodolant.java19.model.pokemon;

public abstract sealed class Pokemon permits Bulbasaur, Charmander, Squirtle, Mewtwo {
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
