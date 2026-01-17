package com.study.graph.algorithm;

import com.study.graph.util.Graph;
import java.util.List;

/**
 * 그래프 탐색 알고리즘 공통 인터페이스
 */
public interface GraphTraversal {

    List<Integer> traverse(Graph graph, int startNode);

    String getName();

    default String getTimeComplexity() {
        return "O(V + E)";
    }

    default String getSpaceComplexity() {
        return "O(V)";
    }
}