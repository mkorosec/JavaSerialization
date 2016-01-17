/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.mko.test.serialize.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author matej
 */
public enum CBenchmarkSuite {

    EMPTY_INSTANCE, DEFAULT("");

    private final Collection<IBenchmarkRun> benchmarks;
    private final Collection<String> benchmarkNames;

    private CBenchmarkSuite() {
        benchmarks = new ArrayList<>();
        benchmarkNames = new ArrayList<>();
    }

    private CBenchmarkSuite(String s) {
        this();
        registerBenchmark(new InvokeEjbEmptyExternalizable());
        registerBenchmark(new InvokeEjbStandardSerialization());
        registerBenchmark(new InvokeServletKryo());
        registerBenchmark(new InvokeServletJacksonSmile());
    }

    public void registerBenchmark(IBenchmarkRun b) {
        benchmarkNames.add(b.getClass().getSimpleName());
        benchmarks.add(b);
    }

    public Collection<String> getAllBenchmarks() {
        return benchmarkNames;
    }

    public Map<String, Double> run(int n, int repeatRuns) {
        Map<String, Double> result = new HashMap<>();
        for (IBenchmarkRun b : benchmarks) {
            double avgTimePerCall = b.run(n, repeatRuns);
            result.put(b.getClass().getSimpleName(), avgTimePerCall);
        }
        return result;
    }
    
    public static void main(String args[]) {
        Map<String, Double> run = CBenchmarkSuite.DEFAULT.run(10000, 10);
        System.out.println(run.toString());
    }
}

