# JavaSort
A class library of sorting algorithms written in Java.

The sorting methods in this library all use generic array parameters which means arrays of any class can be sorted.

The sorting algorithms can all be found in: [`dev.jeffreybender.sort`](https://github.com/jeffreybender/JavaSort/tree/master/src/main/java/dev/jeffreybender/sort "`dev.jeffreybender.sort`")

This is a [Maven](https://maven.apache.org/ "Maven") project.

------------

### How to use this library

#### Sorting

To use any of the sorting algorithms from this library in your project you just need to `import` the class for the sorting algorithm you wish to use from the `dev.jeffreybender.sort` package and use one of the two methods listed below.

All of the sort classes have 2 `public` methods:

1- `public static <T> void sort(T[] array, Comparator<? super T> comparator)`

Sorts an array of objects in the order determined by `comparator`.
- If `comparator` is `null` the method will try to sort `array` according to the implemented [`compareTo(T o)`](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html#compareTo-T- "`compareTo(T o)`") method for the class of its objects.
- If the `array.length` is greater than `1`, `comparator` is `null`, and the class for the objects in `array` (or a `super` class of it) does not implement [`Comparable`](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html "`Comparable`") a [`ClassCastException`](https://docs.oracle.com/javase/8/docs/api/java/lang/ClassCastException.html "`ClassCastException`") will be thrown.

2- `public static <T extends Comparable<? super T>> void sort(T[] array)`

Sorts an array of objects whose class (or a `super` class of it) implements [`Comparable`](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html "`Comparable`") in the order determined by the implemented [`compareTo(T o)`](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html#compareTo-T- "`compareTo(T o)`") method.

#### Other
Other functionality like the ability to test if an array is sorted can be found in [`dev.jeffreybender.sort.SortUtils.java`](https://github.com/jeffreybender/JavaSort/blob/master/src/main/java/dev/jeffreybender/sort/SortUtils.java "`dev.jeffreybender.sort.SortUtils.java`").

------------

### Currently implemented sorting algorithms
- [Bubble Sort](https://github.com/jeffreybender/JavaSort/blob/master/src/main/java/dev/jeffreybender/sort/BubbleSort.java "Bubble Sort")