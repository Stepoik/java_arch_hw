package org.example.hw2;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    private Server(){}

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        EquationsSolverImpl solverImpl = new EquationsSolverImpl();

        EquationsSolver solver = (EquationsSolver) UnicastRemoteObject.exportObject(solverImpl, 0);
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("solver", solver);
        System.out.println("Server ready");
    }
}
