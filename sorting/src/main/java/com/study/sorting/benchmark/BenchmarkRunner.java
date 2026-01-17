package com.study.sorting.benchmark;

import com.study.sorting.algorithm.SortAlgorithm;
import com.study.sorting.util.DataGenerator;
import org.springframework.stereotype.Component;

@Component
public class BenchmarkRunner {

    private final DataGenerator dataGenerator;

    public BenchmarkRunner(DataGenerator dataGenerator) {
        this.dataGenerator = dataGenerator;
    }

    public BenchmarkResult run(SortAlgorithm algorithm, int dataSize, DataGenerator.DataType dataType) {
        // 데이터 생성
        int[] data = dataGenerator.generate(dataSize, dataType);

        // GC 실행하여 메모리 측정 정확도 향상
        System.gc();

        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        // 정렬 실행 및 시간 측정
        long startTime = System.nanoTime();
        algorithm.sort(data);
        long endTime = System.nanoTime();

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = memoryAfter - memoryBefore;

        return BenchmarkResult.of(
                algorithm.getName(),
                dataSize,
                dataType.name(),
                endTime - startTime,
                Math.max(0, memoryUsed)
        );
    }

    /**
     * 워밍업 실행 (JIT 컴파일 최적화를 위해)
     */
    public void warmUp(SortAlgorithm algorithm, int iterations) {
        for (int i = 0; i < iterations; i++) {
            int[] data = dataGenerator.generate(1000, DataGenerator.DataType.RANDOM);
            algorithm.sort(data);
        }
    }
}