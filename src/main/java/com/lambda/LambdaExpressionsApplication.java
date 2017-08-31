package com.lambda;

import static java.text.MessageFormat.format;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import org.springframework.boot.SpringApplication;

import com.lambda.bo.Person;

public class LambdaExpressionsApplication {

	Comparator<String> stringComparator = (final String s, final String s1) -> Integer.compare(s.length(), s1.length());
	static BinaryOperator<Integer> sum = Integer::sum;
	static BinaryOperator<Integer> max = Integer::max;

	static Comparator<Person> cmpAge = (final Person p, final Person p1) -> p1.getAge() - p.getAge();

	static Function<Person, Integer> f1 = (final Person p) -> p.getAge();
	static Function<Person, String> f2 = (final Person p) -> p.getFirstName();
	static Function<Person, String> f3 = (final Person p) -> p.getLastName();

	static Comparator<Person> cmpAges = Comparator.comparing(f1);
	static Comparator<Person> cmpFirstName = Comparator.comparing(f2);
	static Comparator<Person> cmpLastName = Comparator.comparing(f3);

	public static void main(final String[] args) {
		SpringApplication.run(LambdaExpressionsApplication.class, args);
		System.out.println((sum.apply(2, 4)));

		List<Person> people = Arrays.asList(new Person("Ion", "Ciobanu", 31), new Person("Vasya", "Marian", 25), new Person("Aliona", "Ignat", 22));

		Comparator<Person> cmp = cmpAges.thenComparing(cmpFirstName);
		Comparator<Person> cmpNames = cmpFirstName.thenComparing(cmpLastName);
		Comparator<Person> cmpAll = cmpFirstName.thenComparing(cmpLastName).thenComparing(cmpAges);

		people.sort(cmp);
		System.out.println("compare by ages: " + format("{0}", people));
		people.sort(cmpNames);
		System.out.println("compare by names: " + format("{0}", people));
		people.sort(cmpAll);
		people.forEach(System.out::println);
		// System.out.println("compare by all attributes: " +format("{0}", people));
	}
}
