//usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.redfx:strange:0.1.1
//DEPS org.redfx:strangefx:0.1.4

import org.redfx.strange.*;
import org.redfx.strange.QuantumExecutionEnvironment;
import org.redfx.strange.gate.*;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strangefx.render.Renderer;


public class InverseHadamard {

    public static void main (String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(2);
        Step prep = new Step();
        prep.addGate(new X(0));
        Step step1 = new Step();
        step1.addGates(new Hadamard(0), new Hadamard(1));
        Step step2 = new Step();
        step2.addGates(new Hadamard(0), new Hadamard(1));
        program.addSteps(prep, step1, step2);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Qubit zero = qubits[0];
        int value = zero.measure();
        System.out.println("After applying a Hadamard gate to a qubit in state 0, its measured state = "+value);
        Renderer.renderProgram(program);
    }
}
