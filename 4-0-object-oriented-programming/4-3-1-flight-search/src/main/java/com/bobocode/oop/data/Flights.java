package com.bobocode.oop.data;

import java.util.Set;

public interface Flights {

    boolean register(String flightNumber);

    Set<String> findAll();
}
