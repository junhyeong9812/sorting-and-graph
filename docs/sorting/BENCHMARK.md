# 정렬 알고리즘 벤치마크 결과

## 테스트 환경

- **CPU**: 제한 환경 (1~4 cores)
- **Memory**: 256MB ~ 1GB
- **Java**: 21
- **Spring Boot**: 3.5.0

---

## 1. 데이터 크기별 성능 비교

### 10,000개 (RANDOM)

| 순위 | 알고리즘 | 실행 시간 | 메모리 사용 | 비고 |
|-----|---------|----------|------------|------|
| 🥇 1 | Merge Sort | 1.6ms | 1,008KB | 추가 메모리 사용 |
| 🥈 2 | Quick Sort | 1.9ms | 89KB | 메모리 효율적 |
| 🥉 3 | Heap Sort | 2.1ms | 0KB | In-place |
| 4 | Insertion Sort | 29.7ms | 0KB | **15배 느림** |
| 5 | Selection Sort | 42.2ms | 0KB | **22배 느림** |
| 6 | Bubble Sort | 54.1ms | 131KB | **28배 느림** |

### 50,000개 (RANDOM)

| 순위 | 알고리즘 | 실행 시간 | 메모리 사용 | 비고 |
|-----|---------|----------|------------|------|
| 🥇 1 | Quick Sort | 3.5ms | 0KB | |
| 🥈 2 | Merge Sort | 4.6ms | 5,056KB | |
| 🥉 3 | Heap Sort | 6.1ms | 0KB | |
| 4 | Insertion Sort | 379ms | 117KB | **100배 느림** |
| 5 | Selection Sort | 997ms | 206KB | **280배 느림** |
| 6 | Bubble Sort | 3,238ms | 248KB | **925배 느림** |

### 100,000개 (RANDOM) - Low 리소스 (256MB, 1 core)

| 순위 | 알고리즘 | 실행 시간 | 메모리 사용 | 비고 |
|-----|---------|----------|------------|------|
| 🥇 1 | Quick Sort | 7.6ms | 0KB | |
| 🥈 2 | Merge Sort | 9.8ms | 10,502KB | |
| 🥉 3 | Heap Sort | 10.9ms | 0KB | |
| 4 | Insertion Sort | 463ms | 0KB | |
| 5 | Selection Sort | 4,089ms | 206KB | **4초** |
| 6 | Bubble Sort | 14,033ms | 206KB | **14초** |

### 100,000개 (RANDOM) - High 리소스 (1GB, 4 cores)

| 순위 | 알고리즘 | 실행 시간 | 메모리 사용 | 비고 |
|-----|---------|----------|------------|------|
| 🥇 1 | Merge Sort | 12.9ms | 10,460KB | |
| 🥈 2 | Quick Sort | 13.1ms | 450KB | |
| 🥉 3 | Heap Sort | 13.7ms | 0KB | |
| 4 | Insertion Sort | 1,436ms | 1,074KB | |
| 5 | Selection Sort | 3,991ms | 1,074KB | |
| 6 | Bubble Sort | 14,025ms | 4,318KB | |

---

## 2. 데이터 상태별 성능 비교 (10,000개)

### RANDOM vs NEARLY_SORTED vs REVERSED

| 알고리즘 | RANDOM | NEARLY_SORTED | REVERSED | 분석 |
|---------|--------|---------------|----------|------|
| Merge Sort | 1.6ms | 0.4ms | 0.3ms | 항상 안정적 |
| Quick Sort | 1.9ms | 0.5ms | 0.3ms | 항상 안정적 |
| Heap Sort | 2.1ms | 0.7ms | 0.7ms | 항상 안정적 |
| **Insertion Sort** | 29.7ms | **3.8ms** | 15.8ms | 거의 정렬된 데이터에서 빛남! |
| Selection Sort | 42.2ms | 34.3ms | 28.1ms | 데이터 상태 무관 |
| Bubble Sort | 54.1ms | 28.7ms | 72.4ms | 역순에서 최악 |

### 핵심 인사이트

**Insertion Sort의 특성**
```
RANDOM:        29.7ms (느림)
NEARLY_SORTED:  3.8ms (7.8배 빨라짐!) ← 거의 정렬된 데이터에 강함
REVERSED:      15.8ms (중간)
```

**Bubble Sort의 특성**
```
RANDOM:        54.1ms
NEARLY_SORTED: 28.7ms (최적화 효과)
REVERSED:      72.4ms (최악) ← 모든 원소 교환 필요
```

---

## 3. 시간복잡도 실측 검증

### O(n²) vs O(n log n) 증가율

| 데이터 크기 | Quick Sort | Bubble Sort | 배율 차이 |
|------------|------------|-------------|----------|
| 10,000 | 1.9ms | 54ms | 28배 |
| 50,000 | 3.5ms | 3,238ms | 925배 |
| 100,000 | 7.6ms | 14,033ms | 1,846배 |

**이론 vs 실측**
```
n = 10,000 → 50,000 (5배 증가)

이론:
  O(n log n): 5 × log(5) ≈ 5.8배 증가
  O(n²):      5² = 25배 증가

실측:
  Quick Sort: 1.9ms → 3.5ms (1.8배) ← 캐시 효율로 더 좋음
  Bubble Sort: 54ms → 3,238ms (60배) ← 이론보다 더 나쁨
```

---

## 4. 메모리 사용량 비교

| 알고리즘 | 이론 | 100,000개 실측 | 특징 |
|---------|------|---------------|------|
| Bubble Sort | O(1) | ~200KB | In-place |
| Selection Sort | O(1) | ~200KB | In-place |
| Insertion Sort | O(1) | 0KB | In-place |
| Heap Sort | O(1) | 0KB | In-place |
| Quick Sort | O(log n) | ~450KB | 재귀 스택 |
| **Merge Sort** | **O(n)** | **~10MB** | 추가 배열 필요 |

---

## 5. 결론 및 선택 가이드

### 상황별 추천

| 상황 | 추천 알고리즘 | 이유 |
|-----|-------------|------|
| 일반적인 경우 | **Quick Sort** | 평균 가장 빠름, 메모리 효율적 |
| 메모리 제한 심함 | **Heap Sort** | O(1) 공간, 안정적 성능 |
| 안정 정렬 필요 | **Merge Sort** | 안정 + O(n log n) 보장 |
| 거의 정렬된 데이터 | **Insertion Sort** | O(n)에 가깝게 동작 |
| 소규모 데이터 (<50) | **Insertion Sort** | 오버헤드 적음 |

### 절대 피해야 할 조합

| 조합 | 이유 |
|-----|------|
| Bubble Sort + 대용량 | 100,000개에 14초 |
| Selection Sort + 대용량 | 100,000개에 4초 |
| Merge Sort + 메모리 제한 | 10MB+ 추가 메모리 |
