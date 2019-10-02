### Set
Set is elementary to all other data structures in computer science. Unlike math, sets
in cs can grow or shrink in size. Hence, they are called dynamic sets. A **dictionary**
is a set supporting these basic operations: insert (insert an element to the set), delete
(remove an element from the set), and test (test the membership of an element in the
set).

### Elements of Sets
Typically, elements of a set are objects whose pointer allows modifications to their
attributes. Some dynamic sets assume that one of the property of the set is a key
that can be used to identify the set. Given that the keys are unique across all the
elements in the set, we can treat the set as a set of key-value pairs.

### Operations on Sets
Depending on the application requirements, a dynamic set may support all or a subset
of operations listed below:

**Search (S, k):** Given a set _S_, return an element _x_ such as _x.key = k_. If
no such element is found, return _NULL_.

**INSERT (S, x):** Given a set _S_ and an already initialized object pointed by the
pointer _x_, modify the set to include the object that _x_ points to.

**DELETE (S, x):** Given a set _S_ and a pointer _x_ pointing to an element in _S_,
modify the set to exclude the element that _x_ points to.

**MINIMUM (S):** Given a set _S_, return a pointer to the element with the smallest key.

**MAXIMUM (S):** Given a set _S_, return a pointer to the element with the largest key.