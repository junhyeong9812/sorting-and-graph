package com.study.sorting.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "benchmark.sorting")
public class BenchmarkConfig {

    private List<Integer> dataSizes;
    private List<String> dataTypes;
    private int warmUpIterations = 3;
    private int measureIterations = 5;

    // Getters and Setters
    public List<Integer> getDataSizes() { return dataSizes; }
    public void setDataSizes(List<Integer> dataSizes) { this.dataSizes = dataSizes; }

    public List<String> getDataTypes() { return dataTypes; }
    public void setDataTypes(List<String> dataTypes) { this.dataTypes = dataTypes; }

    public int getWarmUpIterations() { return warmUpIterations; }
    public void setWarmUpIterations(int warmUpIterations) { this.warmUpIterations = warmUpIterations; }

    public int getMeasureIterations() { return measureIterations; }
    public void setMeasureIterations(int measureIterations) { this.measureIterations = measureIterations; }
}