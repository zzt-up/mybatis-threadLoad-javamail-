##Stream的特点：
1. 它可以“存储”有限个或无限个元素。这里的存储打了个引号，是因为元素有可能已经全部存储在内存中，也有可能是根据需要实时计算出来的。

2. Stream的另一个特点是，一个Stream可以轻易地转换为另一个Stream，而不是修改原Stream本身。

3. 最后，真正的计算通常发生在最后结果的获取，也就是惰性计算

reduce()聚合操作，即对Stream的每个元素进行计算，得到一个确定的结果
除了可以对数值进行累积计算外，灵活运用reduce()也可以对Java对象进行操作

reduce()方法将一个Stream的每个元素依次作用于BinaryOperator，并将结果合并。

reduce()是聚合方法，聚合方法会立刻对Stream进行计算。
Map<String, List<String>> groups = list.stream()
                .collect(Collectors.groupingBy(s -> s.substring(0, 1), Collectors.toList()));
                Stream提供的常用操作有：
                
                转换操作：map()，filter()，sorted()，distinct()；
                
                合并操作：concat()，flatMap()；
                
                并行处理：parallel()；
                
                聚合操作：reduce()，collect()，count()，max()，min()，sum()，average()；
                
                其他操作：allMatch(), anyMatch(), forEach()。