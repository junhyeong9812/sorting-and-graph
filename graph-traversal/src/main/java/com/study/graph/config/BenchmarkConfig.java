package com.study.graph.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "benchmark.graph")
public class BenchmarkConfig {

    private List<Integer> nodeCounts;
    private List<String> densities;
    private List<String> structures;
    private int warmUpIterations = 3;
    private int measureIterations = 5;

    // Getters and Setters
    public List<Integer> getNodeCounts() { return nodeCounts; }
    public void setNodeCounts(List<Integer> nodeCounts) { this.nodeCounts = nodeCounts; }

    public List<String> getDensities() { return densities; }
    public void setDensities(List<String> densities) { this.densities = densities; }

    public List<String> getStructures() { return structures; }
    public void setStructures(List<String> structures) { this.structures = structures; }

    public int getWarmUpIterations() { return warmUpIterations; }
    public void setWarmUpIterations(int warmUpIterations) { this.warmUpIterations = warmUpIterations; }

    public int getMeasureIterations() { return measureIterations; }
    public void setMeasureIterations(int measureIterations) { this.measureIterations = measureIterations; }
}