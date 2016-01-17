/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.mko.test.serialize.test;

/**
 *
 * @author matej
 */
public interface IBenchmarkRun {

    /**
     * Single run : Create numberOfObject, serialize, transfer and deserialize.
     * Return timeSpent/repeatRuns.
     *
     * @param numberOfObject how many transfer object to create
     * @param repeatRuns no. of times to repeat single run
     * @return
     */
    double run(int numberOfObject, int repeatRuns);
}
