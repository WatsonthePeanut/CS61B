package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThree() {
        AListNoResizing<Integer> a = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();
        for (int i = 4; i <= 6; ++i) {
            a.addLast(i);
            b.addLast(i);
        }
        assertEquals(a.size(), b.size());
        assertEquals(a.removeLast(), b.removeLast());
        assertEquals(a.removeLast(), b.removeLast());
        assertEquals(a.removeLast(), b.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; ++i) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
            } else if (operationNumber == 1) {
                assertEquals(broken.size(), correct.size());
            } else if (operationNumber == 2) {
                if (correct.size() == 0) {
                    continue;
                } else {
                    assertEquals(broken.getLast(), correct.getLast());
                }
            } else if (operationNumber == 3) {
                if (correct.size() == 0) {
                    continue;
                } else {
                    assertEquals(broken.removeLast(), correct.removeLast());
                }
            }
        }
    }
}
