//usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.redfx:strange:0.1.1
//DEPS org.redfx:strangefx:0.1.4

import java.util.Random;

import org.redfx.strange.*;
import org.redfx.strange.QuantumExecutionEnvironment;
import org.redfx.strange.gate.*;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strangefx.render.Renderer;

public class QKD {

    static final int N = 8;
    static Random random = new Random();

    public static void main (String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(N);
        Step data = new Step();
        data.addGates(new X(0), new X(2), new X(3), new X(6));
        program.addStep(data);
        boolean[] aliceRandom = new boolean[N];
        boolean[] bobRandom = new boolean[N];
        Step aliceH = new Step();
        Step bobH = new Step();
        Step aliceBase = new Step();
        Step bobBase = new Step();
        for (int i = 0; i < N; i++) {
            aliceH.addGate(new Hadamard(i));
            bobH.addGate(new Hadamard(i));
            aliceRandom[i] = random.nextBoolean();
            bobRandom[i] = random.nextBoolean();
            if (aliceRandom[i]) aliceBase.addGate(new Hadamard(i));
            if (bobRandom[i]) bobBase.addGate(new Hadamard(i));
        }
        program.addStep(aliceBase);
        program.addStep(aliceH);
        program.addStep(bobH);
        program.addStep(bobBase);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        for (int i = 0; i < N; i++) {
            if (aliceRandom[i] == bobRandom[i]) {
                System.err.println("Index " + i+" has a usable bit: " + qubits[i].measure());
            } else {
                System.err.println("Bob and Alice used a different base for measuring qubit "+i+", discard.");
            }
        }
        Renderer.renderProgram(program);
    }
}
