/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parallel.processing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roaam
 */
public class ParallelProcessing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ATM page = new ATM();
            Thread startPageThread = new Thread(page, "Thread-" + (i + 1));
            startPageThread.start();
            threads.add(startPageThread);
        }
        for (Thread thread : threads) {
            thread.join(); // Wait for this thread to finish
        }

        System.out.println("All threads have completed their execution.");
    }
}


