### Stacks
Stack is a LIFO (Last In, First Out) data structure. Stack allows elements to
be _popped_ and _pushed_ from/at the top. Stack can be imagined as a stack of
plates waiting to be washed. A new plate will naturally be added to the top of
this stack and the person doing the dishes will pop one out, again, from the top
of the stack.

**Both operations (push and pop) have an upper bound of O(1).**

### Stack Overflow
Given a stack _S_ of capacity _n_ and _S.top = n - 1_ (0-indexed), at this point,
an attempt to push an element to the stack will cause the stack to overflow.

### Stack Underflow
Given a stack _S_ of capacity _n_ and _S.top = 0_ (0-indexed), at this point, an
attempt to pop an element from the stack will cause the stack to underflow.

### Implementation
See _com.dsa.data_structures.stacks.StackImpl_