package com.study.sorting.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record SortRequest(
        @NotBlank(message = "알고리즘은 필수입니다")
        String algorithm,

        @Min(value = 1, message = "데이터 크기는 1 이상이어야 합니다")
        int dataSize,

        @NotBlank(message = "데이터 타입은 필수입니다")
        String dataType
) {}