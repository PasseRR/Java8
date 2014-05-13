Java8 函数接口
具体见jdk1.8 java.util.function
1.Predicate:断言,输入T,返回boolean.
其他Predicate类：
i)BiPredicate:输入T R,返回boolean
ii)DoublePredicate:输入double,返回boolean
iii)IntPredicate:输入int,返回boolean

2.Function:输入T,返回R.
其他Function类：
i)BiFunction:输入T U,返回R
ii)DoubleFunction:输入double,返回R
iii)DoubleToIntFunction:输入double,返回int
iv)DoubleToLongFuntion:输入double,返回long
v)DoubleUnaryOperator:输入double,返回double
vi)DoubleBinaryOperator:输入double double,返回double
vii)IntBinaryOperator:输入int int,返回int
viii)IntFunction:输入int,返回R
ix)IntToDoubleFunction:输入int,返回double
x)IntToLongFunction:输入int,返回long
xi)IntUnaryOperator:输入int,返回int

3.Consumer:输入T,没有返回,只执行某种操作.
其他Consumer类：
i)BiConsumer:输入T R,没有返回
ii)DoubleConsumer:输入double,没有返回
iii)IntConsumer:输入int,没有返回

4.Supplier:没有输入,返回T.
其他Supplier类：
i)DoubleSupplier:没有输入,返回double
ii)IntSupplier:没有输入,返回int