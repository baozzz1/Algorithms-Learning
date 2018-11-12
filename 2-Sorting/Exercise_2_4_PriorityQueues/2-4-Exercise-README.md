Exercises 2.4
==
### 2.4.1
    Input:  P   R   I   O    *   R    *   *   I   *   T   *   Y   *   *   *   Q   U   E   *   *   *   U   *   E
    Output: /   /   /   /    R   /    R   P   /   O   /   T   /   Y   I   I   /   /   /   U   Q   E   /   U   /
    Queue:  P   RP  RPI RPOI POI RPOI POI OI  OII II  TII II  YII II  I   /   Q   UQ  UQE QE  E   /   U   /   E
### 2.4.2
记录插入的最大元素并在找出最大元素时返回它的值：
1. 只用一个栈记录最大元素：这种做法在不断插入元素的时候是可行的，但是在删除次元素并要求返回当前最大元素时需要遍历数组；
2. 用一个队列记录最大元素：变成了插入排序。
### 2.4.3
---
    数据结构        插入元素      删除最大元素
    无序数组            1           N
    有序数组            N           1
    无序链表            1           N
    有序链表            N           1
---
### 2.4.4
是
### 2.4.5
                 Y
          T             U
      S       Q       N   E
    A   S   I   O   E
### 2.4.6
    Input:  P   R   I   O    *   R    *   *   I   *   T   *   Y   *   *   *   Q   U   E   *   *   *   U   *   E
    Output: /   /   /   /    R   /    R   P   /   O   /   T   /   Y   I   I   /   /   /   U   Q   E   /   U   /
    Queue:  P   RP  RPI RPIO POI RPIO POI OI  OII II  TII II  YII II  I   /   Q   UQ  UQE QE  E   /   U   /   E
#### 2.4.7
* 第2大的元素可能出现在: 2, 3；
* 第3大的元素可能出现在: 2, 3, 4, 5, 6 ,7；
* 第4大的元素可能出现在: 3, 4, 5, 6 ,7, 8, 9, 10, 11, 12, 13, 14, 15。
### 2.4.8
* 第2小的元素可能出现在: 16-31；
* 第3小的元素可能出现在: 16-31, 8-15；
* 第4小的元素可能出现在: 16-31, 8-15；
* ...
* 第7小的元素可能出现在: 16-31, 8-15, 4-7。
### 2.4.9
            E
          D   A
        B   C
1. A, B, C三者可互相交换, 共6种；

           E
         C   D
       A   B
2. A, B可互相交换, 共2种；<br>
ABCDE合计8种情况。<br>

            B
          A   B
        A   A
    
            B
          B   A
        A   A
 AAABB共2种情况。
 ### 2.4.10
 `pq[k]`的父结点在`pq[(k+1)/2-1]`处；<br>
 子结点在`pq[2k+1]`和`pq[2k+2]`处。
 ### 2.4.11
 应用中有大量的插入元素的操作，但只有若干删除最大元素的操作。若记插入元素操作`i`次，删除最大元素操作`d`次：<br>
 堆：用时` (i + d)* logN `次；<br>
 无序数组：用时` i + d * N `次；<br>
 有序数组：用时` i * N + d `次。<br>
 若` i >> d `，则应选用无序数组。<br>
 ### 2.4.12
 应用中有大量的找出最大元素的操作，但插入元素和删除最大元素的操作较少。若记插入元素操作`i`次，删除最大元素操作`d`次，找出最大元素操作`f`次：<br>
 堆：用时` (i + d)* logN + f`次；<br>
 无序数组：用时` i + d * N + f * N`次；<br>
 有序数组：用时` i * N + d + f`次。<br>
 若` f >> i, d `，则应选用有序数组。<br>
 ### 2.4.13
 在sink中避免检查`j < N`：
 ```Java
private void sinkRewrite(int k) {
    while (2 * k < N) {
        int j = 2 * k;
        if (less(j, j + 1)) j++;
        if (!less(k, j))    break;
        exch(k, j);
        k = j;
    }
    if (2 * k == N && less(k, 2 * k))
        exch(k, 2 * k);
}
 ```
 ### 2.4.14
 对于没有重复元素的大小为`N`的堆，一次删除最大元素的操作中最少要交换`2`次；<br>
 条件是数组最后一项的值为` MaxValue - ⌊ logN ⌋ `，从根结点到此项的数列为` MaxValue - 2, MaxValue - 3, ...,   MaxValue - ⌊ logN ⌋ + 1`，
 且根结点另外一个子结点的值为` MaxValue - 1 `。
 
                 *15*
         *14*            *13*
      10      9       8      *12*
    1   2   3   4   6   7   8   *11*
    第一次: exch(1,15);    //交换15和11
    第二次：exch(1,2);     //交换11和14
 对于没有重复元素的大小为`N`的堆，连续两次删除最大元素的操作中最少要交换`2`次；<br>
 ### 2.4.15
 ```Java
 // is pq[1..N] a min heap?
private boolean isMinHeap() {
    return isMinHeap(1);
}

// is subtree of pq[1..n] rooted at k a min heap?
private boolean isMinHeap(int k) {
    if (k > n) return true;
    int left = 2*k;
    int right = 2*k + 1;
    if (left  <= n && greater(k, left))  return false;
    if (right <= n && greater(k, right)) return false;
    return isMinHeap(left) && isMinHeap(right);
}
````
### 2.4.19
```Java
//Exercise 2.3.19
public MaxPQ(Key[] keys) {
    for (int i = 0; i < keys.length; i++) {
        pq[++N] = keys[i];
        swim(N);
    }
}
```
See as [MaxPQ.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/2-Sorting/Exercise_2_4_PriorityQueues/MaxPQ.java)<br>
# Creative Problems
### 2.4.22
```Java
// Exercise 2.4.22
public void resize(int n) {
    assert n > N;
    Key[] temp = (Key[]) new Comparable[n];
    for (int i = 1; i < n; i++)
        temp[i] = pq[i];
    pq = temp;
}
```
See as [MaxPQ.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/2-Sorting/Exercise_2_4_PriorityQueues/MaxPQ.java)<br>
### 2.4.25




