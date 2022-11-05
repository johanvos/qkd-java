//usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.redfx:strange:0.1.1
//DEPS org.redfx:strangefx:0.1.4

import org.redfx.strange.Gate;
import org.redfx.strange.Program;
import org.redfx.strange.Qubit;
import org.redfx.strange.Result;
import org.redfx.strange.Step;
import org.redfx.strange.QuantumExecutionEnvironment;
import org.redfx.strange.gate.Hadamard;
import org.redfx.strange.gate.X;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strangefx.render.Renderer;

public class LowLevelRandom {

    public static void main (String[] args) {

        // Create a simulated quantum computer.
        QuantumExecutionEnvironment simulator = 
                new SimpleQuantumExecutionEnvironment();

        // Create a quantum program with 1 qubit.
        Program program = new Program(1);

        // Add a step to the program.
        // In this step, the 0-indexed qubit has a Hadamard gate.
        Step step = new Step();
        step.addGate(new Hadamard(0));
        program.addStep(step);

        // Run the program and display the results.
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();        
        System.out.println("Value: " + qubits[0].measure());
        System.out.println("Probability of being 1: " + 
                qubits[0].getProbability());

        // Display the circuit.
        Renderer.renderProgram(program);
    }
}
