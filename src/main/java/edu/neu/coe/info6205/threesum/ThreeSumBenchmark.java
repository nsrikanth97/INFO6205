package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Stopwatch;
import edu.neu.coe.info6205.util.TimeLogger;
import edu.neu.coe.info6205.util.Utilities;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;


public class ThreeSumBenchmark {
    public ThreeSumBenchmark(int runs, int n, int m) {
        this.runs = runs;
        this.supplier = new Source(n, m).intsSupplier(10);
        this.n = n;
    }

    public void runBenchmarks() {
        System.out.println("ThreeSumBenchmark: N=" + n);
        benchmarkThreeSum("ThreeSumQuadratic", (xs) -> new ThreeSumQuadratic(xs).getTriples(), n, timeLoggersQuadratic);
        benchmarkThreeSum("ThreeSumQuadrithmic", (xs) -> new ThreeSumQuadrithmic(xs).getTriples(), n, timeLoggersQuadrithmic);
        benchmarkThreeSum("ThreeSumCubic", (xs) -> new ThreeSumCubic(xs).getTriples(), n, timeLoggersCubic);
    }

    public static void main(String[] args) {
        new ThreeSumBenchmark(100, 250, 250).runBenchmarks();
        new ThreeSumBenchmark(100, 500, 500).runBenchmarks();
        new ThreeSumBenchmark(100, 1000, 1000).runBenchmarks();
        new ThreeSumBenchmark(50, 2000, 2000).runBenchmarks();
        new ThreeSumBenchmark(5, 4000, 4000).runBenchmarks();
        new ThreeSumBenchmark(10, 8000, 8000).runBenchmarks();
        new ThreeSumBenchmark(10, 16000, 16000).runBenchmarks();
//        new ThreeSumBenchmark(3, 8000, 8000).runBenchmarks();
//        new ThreeSumBenchmark(2, 16000, 16000).runBenchmarks();
    }

    private void benchmarkThreeSum(final String description, final Consumer<int[]> functio, int n, final TimeLogger[] timeLoggers) {
        if (description.equals("ThreeSumCubic") && n > 4000) return;
        // FIXME
        double startTime;
        double endTime;
        double totalTIme = 0;
        startTime = System.currentTimeMillis();
        for(int i=0; i < runs;i++){
            functio.accept(supplier.get());
        }
        endTime = System.currentTimeMillis();
        totalTIme = (endTime - startTime)/runs;
        timeLoggers[0].log(totalTIme,n);
        timeLoggers[1].log(totalTIme,n);
        // END
    }

    private final static TimeLogger[] timeLoggersCubic = {
            new TimeLogger("Raw time per run (mSec) for Cubic: ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^3) Cubic: ", (time, n) -> time / n / n / n * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadrithmic = {
            new TimeLogger("Raw time per run (mSec) Quadrithmic: ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2 log n) Quadrithmic: ", (time, n) -> time / n / n / Utilities.lg(n) * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadratic = {
            new TimeLogger("Raw time per run (mSec) Quadratic: ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2) Quadratic: ", (time, n) -> time / n / n * 1e6)
    };

    private final int runs;
    private final Supplier<int[]> supplier;
    private final int n;
}
