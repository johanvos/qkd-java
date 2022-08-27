//usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.redfx:strange:0.1.1
//DEPS org.redfx:strangefx:0.1.4

import org.redfx.strange.*;
import org.redfx.strange.QuantumExecutionEnvironment;
import org.redfx.strange.gate.*;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strangefx.render.Renderer;


public class SuperpositionData {

    public static void main (String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(8);
        Step data = new Step();
        data.addGates(new X(0), new X(2), new X(3), new X(6));
        program.addStep(data);
        Step aliceH = new Step();
        Step bobH = new Step();
        for (int i = 0; i < 8; i++) {
            aliceH.addGate(new Hadamard(i));
            bobH.addGate(new Hadamard(i));
        }
        program.addStep(aliceH);
        program.addStep(bobH);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Renderer.renderProgram(program);
    }
}
