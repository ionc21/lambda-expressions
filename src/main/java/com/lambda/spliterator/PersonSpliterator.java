package com.lambda.spliterator;

import com.lambda.bo.Person;

import java.util.Spliterator;
import java.util.function.Consumer;

public class PersonSpliterator implements Spliterator<Person> {

    private final Spliterator<String> lineSpliterator;
    private String firstName;
    private String lastName;
    private int age;

    public PersonSpliterator(Spliterator<String> lineSpliterator) {
        this.lineSpliterator = lineSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Person> action) {
        if (lineSpliterator.tryAdvance(line -> firstName = line)
                && lineSpliterator.tryAdvance(line -> lastName = line)
                && lineSpliterator.tryAdvance(line -> age = Integer.valueOf(line))) {

            Person p = new Person(firstName, lastName, age);
            action.accept(p);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Spliterator<Person> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return lineSpliterator.estimateSize() / 3;
    }

    @Override
    public int characteristics() {
        return lineSpliterator.characteristics();
    }
}
