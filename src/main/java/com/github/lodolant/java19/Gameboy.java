package com.github.lodolant.java19;

import com.github.lodolant.java19.features.StructuredConcurrency;
import com.github.lodolant.java19.model.GameboyData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Gameboy {
    private final static Logger LOGGER = LoggerFactory.getLogger(Gameboy.class);

    public static void main(String[] args) {
        StructuredConcurrency.loadData(false);
        LOGGER.info("Ready to play !");
    }
}