package org.example.hw2;

import kotlin.Pair;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EquationsSolver extends Remote {
    Pair<Float, Float> solve(float a, float b, float c) throws RemoteException;
}
