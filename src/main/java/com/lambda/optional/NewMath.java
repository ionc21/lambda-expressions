package com.lambda.optional;

import java.util.Optional;

public class NewMath {

	public static Optional<Double> inv(final Double d) {
		return d == 0d ? Optional.empty() : Optional.of(1d / d);
	}

	public static Optional<Double> sqrt(final Double d) {
		return d < 0d ? Optional.empty() : Optional.of(Math.sqrt(d));
	}
}
