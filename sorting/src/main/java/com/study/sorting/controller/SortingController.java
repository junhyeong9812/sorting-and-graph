package com.study.sorting.controller;

import com.study.sorting.dto.SortRequest;
import com.study.sorting.dto.SortResponse;
import com.study.sorting.service.SortingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sort")
public class SortingController {

    private final SortingService sortingService;

    public SortingController(SortingService sortingService) {
        this.sortingService = sortingService;
    }

    /**
     * 단일 정렬 실행
     */
    @PostMapping
    public ResponseEntity<SortResponse> sort(@Valid @RequestBody SortRequest request) {
        SortResponse response = sortingService.sort(request);
        return response.success()
                ? ResponseEntity.ok(response)
                : ResponseEntity.badRequest().body(response);
    }

    /**
     * 전체 알고리즘 벤치마크
     */
    @GetMapping("/benchmark")
    public ResponseEntity<List<SortResponse>> benchmark(
            @RequestParam(defaultValue = "10000") int dataSize,
            @RequestParam(defaultValue = "RANDOM") String dataType) {
        return ResponseEntity.ok(sortingService.runAllBenchmarks(dataSize, dataType));
    }

    /**
     * 지원 알고리즘 목록
     */
    @GetMapping("/algorithms")
    public ResponseEntity<List<String>> getAlgorithms() {
        return ResponseEntity.ok(sortingService.getAvailableAlgorithms());
    }
}