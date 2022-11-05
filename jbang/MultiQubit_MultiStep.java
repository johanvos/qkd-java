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

public class MultiQubit_MultiStep {

    public static void main (String[] args) {

        // Create a simulated quantum computer.
        QuantumExecutionEnvironment simulator = 
                new SimpleQuantumExecutionEnvironment();

        // Create a quantum program with 2 qubit2.
        Program program = new Program(2);

        // Add a step to the program.
        // In this step, the 0-indexed qubit has a Hadamard gate.
        Step step = new Step();
        step.addGate(new Hadamard(0));
        program.addStep(step);

        // Add another step to the program.
        // In this step, both qubits have NOT gates.
        Step stepB = new Step();
        stepB.addGate(new X(0));
        stepB.addGate(new X(1));
        program.addStep(stepB);

        // Run the program and display the results.
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();        
        for (int i = 0; i < 2; i++) {
            System.out.println("qubit " + i);
            System.out.println("    value: " + qubits[i].measure());
            System.out.println("    probability of being 1: " +
                                    qubits[i].getProbability());
        }

        // Display the circuit.
        Renderer.renderProgram(program);
    }
}
