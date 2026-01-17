package com.study.sorting.service;

import com.study.sorting.algorithm.SortAlgorithm;
import com.study.sorting.benchmark.BenchmarkResult;
import com.study.sorting.benchmark.BenchmarkRunner;
import com.study.sorting.dto.SortRequest;
import com.study.sorting.dto.SortResponse;
import com.study.sorting.util.DataGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SortingService {

    private final Map<String, SortAlgorithm> algorithms;
    private final BenchmarkRunner benchmarkRunner;

    public SortingService(List<SortAlgorithm> algorithmList, BenchmarkRunner benchmarkRunner) {
        this.algorithms = algorithmList.stream()
                .collect(Collectors.toMap(
                        algo -> algo.getName().toLowerCase().replace(" ", ""),
                        Function.identity()
                ));
        this.benchmarkRunner = benchmarkRunner;
    }

    public SortResponse sort(SortRequest request) {
        String key = request.algorithm().toLowerCase().replace(" ", "");
        SortAlgorithm algorithm = algorithms.get(key);

        if (algorithm == null) {
            return SortResponse.error("알고리즘을 찾을 수 없습니다: " + request.algorithm());
        }

        try {
            DataGenerator.DataType dataType = DataGenerator.DataType.valueOf(request.dataType().toUpperCase());
            BenchmarkResult result = benchmarkRunner.run(algorithm, request.dataSize(), dataType);
            return SortResponse.from(result);
        } catch (IllegalArgumentException e) {
            return SortResponse.error("잘못된 데이터 타입: " + request.dataType());
        }
    }

    public List<String> getAvailableAlgorithms() {
        return algorithms.values().stream()
                .map(SortAlgorithm::getName)
                .sorted()
                .toList();
    }

    public List<SortResponse> runAllBenchmarks(int dataSize, String dataType) {
        DataGenerator.DataType type;
        try {
            type = DataGenerator.DataType.valueOf(dataType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return List.of(SortResponse.error("잘못된 데이터 타입: " + dataType));
        }

        return algorithms.values().stream()
                .map(algo -> {
                    BenchmarkResult result = benchmarkRunner.run(algo, dataSize, type);
                    return SortResponse.from(result);
                })
                .sorted((a, b) -> Long.compare(a.executionTimeNs(), b.executionTimeNs()))
                .toList();
    }
}