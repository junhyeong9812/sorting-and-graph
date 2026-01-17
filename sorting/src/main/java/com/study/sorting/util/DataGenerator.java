package com.study.sorting.util;

import org.springframework.stereotype.Component;
import java.util.Random;

/**
 * 벤치마크용 테스트 데이터 생성기
 */
@Component
public class DataGenerator {

    private final Random random = new Random();

    public enum DataType {
        RANDOM,         // 무작위
        NEARLY_SORTED,  // 거의 정렬됨 (90%)
        REVERSED,       // 역순
        SORTED          // 이미 정렬됨
    }

    public int[] generate(int size, DataType type) {
        return switch (type) {
            case RANDOM -> generateRandom(size);
            case NEARLY_SORTED -> generateNearlySorted(size);
            case REVERSED -> generateReversed(size);
            case SORTED -> generateSorted(size);
        };
    }

    private int[] generateRandom(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size * 10);
        }
        return arr;
    }

    private int[] generateNearlySorted(int size) {
        int[] arr = generateSorted(size);
        int swapCount = size / 10;
        for (int i = 0; i < swapCount; i++) {
            int idx1 = random.nextInt(size);
            int idx2 = random.nextInt(size);
            int temp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = temp;
        }
        return arr;
    }

    private int[] generateReversed(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }

    private int[] generateSorted(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    /**
     * 배열 복사 (벤치마크에서 동일 데이터로 여러 알고리즘 테스트용)
     */
    public int[] copy(int[] arr) {
        int[] copied = new int[arr.length];
        System.arraycopy(arr, 0, copied, 0, arr.length);
        return copied;
    }
}