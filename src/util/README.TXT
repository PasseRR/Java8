集合框架改动

1.Collection
i)forEach,Iterable新增的default方法
ii)removeIf,Collection新增的default方法

2.Stream
i)中间操作
a>filter	排除所有与断言不匹配的元素。
b>map		通过Function对元素执行一对一的转换。
c>flatMap	通过FlatMapper将每个元素转变为无或更多的元素。
d>peek		对每个遇到的元素执行一些操作。主要对调试很有用。
e>distinct	根据.equals行为排除所有重复的元素。这是一个有状态的操作。
f>sorted	确保流中的元素在后续的操作中，按照比较器（Comparator）决定的顺序访问。
			这是一个有状态的操作。
g>limit		保证后续的操作所能看到的最大数量的元素。这是一个有状态的短路的操作。
h>substream	确保后续的操作只能看到一个范围的（根据index）元素。
			像不能用于流的String.substring一样。也有两种形式，一种有一个开始索引，
			一种有一个结束索引。二者都是有状态的操作，有一个结束索引的形式也是一个短路的操作。
ii)末端操作
a>forEach	对流中的每个元素执行一些操作。
b>toArray	将流中的元素倾倒入一个数组。
c>reduce	通过一个二进制操作将流中的元素合并到一起。
d>collect	将流中的元素倾倒入某些容器，例如一个Collection或Map.
e>min		根据一个比较器找到流中元素的最小值。
f>max		根据一个比较器找到流中元素的最大值。
g>count		计算流中元素的数量。
h>anyMatch	判断流中是否至少有一个元素匹配断言。这是一个短路的操作。
i>allMatch	判断流中是否每一个元素都匹配断言。这是一个短路的操作。
j>noneMatch	判断流中是否没有一个元素匹配断言。这是一个短路的操作。
l>findFirst	查找流中的第一个元素。这是一个短路的操作。
m>findAny	查找流中的任意元素，可能对某些流要比findFirst代价低。这是一个短路的操作。