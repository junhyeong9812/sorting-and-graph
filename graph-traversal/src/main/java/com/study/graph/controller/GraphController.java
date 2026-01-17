package com.study.graph.controller;

import com.study.graph.dto.GraphRequest;
import com.study.graph.dto.TraversalResponse;
import com.study.graph.service.GraphService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/graph")
public class GraphController {

    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    /**
     * 단일 탐색 실행
     */
    @PostMapping("/traverse")
    public ResponseEntity<TraversalResponse> traverse(@Valid @RequestBody GraphRequest request) {
        TraversalResponse response = graphService.traverse(request);
        return response.success()
                ? ResponseEntity.ok(response)
                : ResponseEntity.badRequest().body(response);
    }

    /**
     * 전체 알고리즘 벤치마크
     */
    @GetMapping("/benchmark")
    public ResponseEntity<List<TraversalResponse>> benchmark(
            @RequestParam(defaultValue = "1000") int nodeCount,
            @RequestParam(defaultValue = "RANDOM") String graphType,
            @RequestParam(defaultValue = "SPARSE") String density) {
        return ResponseEntity.ok(graphService.runAllBenchmarks(nodeCount, graphType, density));
    }

    /**
     * 지원 알고리즘 목록
     */
    @GetMapping("/algorithms")
    public ResponseEntity<List<String>> getAlgorithms() {
        return ResponseEntity.ok(graphService.getAvailableAlgorithms());
    }
}