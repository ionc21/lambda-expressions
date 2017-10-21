package com.lambda.executor;

import java.util.concurrent.*;

public class PlayingWithCallablesAndFutures {

	public static void main(final String[] args) throws ExecutionException, InterruptedException {

		Callable<String> task = () -> {
			throw new IllegalStateException("I throw an exception in thread " + Thread.currentThread().getName());
		};

		ExecutorService executor = Executors.newFixedThreadPool(4);

		try {
			for (int i = 0; i < 10; i++) {
				Future<String> future = executor.submit(task);
				System.out.println("I get: " + future.get());
			}
		} finally {
			executor.shutdown();
		}
	}
}
