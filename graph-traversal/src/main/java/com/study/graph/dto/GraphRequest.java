package com.study.graph.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record GraphRequest(
        @NotBlank(message = "알고리즘은 필수입니다")
        String algorithm,

        @Min(value = 1, message = "노드 수는 1 이상이어야 합니다")
        int nodeCount,

        @NotBlank(message = "그래프 타입은 필수입니다")
        String graphType,

        String density,

        int startNode
) {
    public GraphRequest {
        if (density == null || density.isBlank()) {
            density = "SPARSE";
        }
    }
}