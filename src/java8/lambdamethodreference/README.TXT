Lambda表达式包含三个部分
1.一个括号内用逗号分隔的形式参数，参数是函数式接口里面方法的参数
2.一个箭头符号：->
3.方法体，可以是表达式和代码块，方法体函数式接口里面方法的实现，如果是代码块，
则必须用{}来包裹起来，且需要一个return 返回值，但有个例外，
若函数式接口里面方法返回值是void，则无需{}
总体看起来像这样
(parameters) -> expression 或者 (parameters) -> { statements; }

方法引用
一般方法的引用格式是
1.如果是静态方法，则是ClassName::methodName。如 Object ::equals
2.如果是实例方法，则是Instance::methodName。如Object obj=new Object();obj::equals;
3.构造函数.则是ClassName::new

如果你觉得lambda的方法体会很长，影响代码可读性，方法引用就是个解决办法