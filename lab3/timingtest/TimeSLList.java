package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<Integer>();
        AList<Double> times = new AList<Double>();
        AList<Integer> opCounts = new AList<Integer>();
        for (int temp = 1000; temp <= 128000; temp *= 2) {
            Ns.addLast(temp);
        }
        for (int i = 0; i < Ns.size(); ++i) {
            SLList<Integer> L = new SLList<Integer>();
            for (int j = 0; j < Ns.get(i); ++j) {
                L.addLast(1);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < 10000; ++j) {
                L.getLast();
            }
            times.addLast(sw.elapsedTime());
            opCounts.addLast(10000);
        }
        printTimingTable(Ns, times, opCounts);
    }

}
