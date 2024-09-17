package org.example.hw2;

import kotlin.Pair;

public class EquationsSolverImpl implements EquationsSolver {
    @Override
    public Pair<Float, Float> solve(float a, float b, float c) {
        float disc = b * b - 4 * a * c;
        if (disc < 0) {
            throw new ArithmeticException();
        }
        return new Pair<>((float) ((-b + Math.sqrt(disc)) / (2 * a)), (float) ((-b - Math.sqrt(disc)) / (2 * a)));
    }
}
