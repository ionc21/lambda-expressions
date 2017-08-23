package com.lambda.map;

import com.lambda.bo.City;
import com.lambda.bo.Person;

import java.util.*;

public class MapFunctions {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(new Person("Ion", "Ciobanu", 31),
                new Person("Vasya", "Marian", 25), new Person("Aliona", "Ignat", 22));

        Map<City, List<Person>> map1 = new HashMap<>();
        Map<City, List<Person>> map2 = new HashMap<>();

        map2.forEach(
                (key, value) ->	map1.merge(
                        key, value,
                        (existingPeople, newPeople) -> {
                            existingPeople.addAll(newPeople);
                            return existingPeople;
                        }
                )
        );

        Person p1 = new Person("Ion", "Ciobanu", 31);
        Person p2 = new Person("Vasya", "Marian", 25);
        Person p3 = new Person("Aliona", "Ignat", 22);

        City london = new City("London");
        City munich = new City("Munich");
        City dressden = new City("Dressden");

        map1.putIfAbsent(london, new ArrayList<>());
        map1.get(london).add(p1);

        map1.computeIfAbsent(munich, city -> new ArrayList<Person>()).add(p2);
    }
}
