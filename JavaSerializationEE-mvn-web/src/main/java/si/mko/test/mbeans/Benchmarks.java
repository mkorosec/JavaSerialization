/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.mko.test.mbeans;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import si.mko.test.serialize.test.CBenchmarkSuite;

/**
 *
 * @author matej
 */
@ManagedBean
@RequestScoped
public class Benchmarks {

    private int numberOfObjects = 10000;
    private int repeat = 5;
    private Map<String, Double> latestResults = null;

    /**
     * Creates a new instance of Benchmarks
     */
    public Benchmarks() {
    }

    public int getNumberOfObjects() {
        return numberOfObjects;
    }

    public void setNumberOfObjects(int numberOfObject) {
        this.numberOfObjects = numberOfObject;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public Map<String, Double> getLatestResults() {
        return latestResults;
    }

    public void setLatestResults(Map<String, Double> latestResults) {
        this.latestResults = latestResults;
    }

    public String refreshTestRunResults() {
        latestResults = CBenchmarkSuite.DEFAULT.run(numberOfObjects, repeat);
        return "index";
    }

}
