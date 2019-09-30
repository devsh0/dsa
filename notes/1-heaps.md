### Heap

**Heap is a nearly complete binary tree whose node values are subsequent 
elements of an array.**

![heap-diagram](../static/heaps.jpeg)
<p style="text-align: center"><strong>Fig (1)</strong></p>

_fig(a) is the heap formed of elements of the array shown in fig(b). Each circle
represents a node and the number within it is the value that this node stores.
The number outside the circle is the corresponding index for this value in the
array in fig(b), assuming that the array is 1-indexed._

### Types of Heaps
There are two types of heaps: max and min. A max heap has the largest node 
(i.e.: node containing the highest value) at its root whereas in a min heap, 
the root contains the lowest value in the entire heap. Beside this, min and max heap satisfy 
a more general heap property. In a max heap, a given node _i_ has a value less
than or equal to the value of its parent. In a min heap, a given node _i_ has
a value greater than or equal to the value of its parent. The figure above 
shows a max heap.

```
Min Heap: A[PARENT(i)] <= A[i]
Max Heap: A[PARENT(i)] >= A[i]
```

**Given a node at _ith_ position in a heap, we can calculate its parent, left 
and right children's index in the array quite easily:**

```
PARENT(i) = floor(i/2)
LEFT(i) = 2i
RIGHT(i) = 2i + 1
```

These calculations can be carried out in any modern CPU in one or two instructions.
For example, shifting the bits one position to the left will result in the original
number being doubled, which is essentially what we do to find the left children
of _i_. Similarly, shifting the bits right by one position halves the original
number and, given that the number is being treated as an integer, the fractional
part will be dropped. This will give us the parent for node at _i_.