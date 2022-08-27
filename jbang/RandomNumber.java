//usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.redfx:strange:0.1.1

import org.redfx.strange.algorithm.Classic;

public class RandomNumber {

    public static void main (String[] args) {
        int randomBit = Classic.randomBit();
        System.out.println("Generate one random bit, which can be 0 or 1. Result = "+randomBit);
    }
}
