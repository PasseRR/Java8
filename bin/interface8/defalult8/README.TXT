Java8接口中default方法及static方法

可以在不破坏现有接口实现的前提下添加默认（default）方法。
一个接口不能提供Object类里的任何方法的默认实现。
这意味着不能在接口里提供 equals, hashCode, toString 方法的默认实现。

定义静态（static）方法带来的好处就是可以减少创建工具类的需求了。
比如 java.util.Collection 接口定义了一个集合，
对于此接口实例操作的很多通用方法都是通过工具类 java.util.Collections 来提供的，
现在可以在接口里定义静态方法，我们就可以把工具类里的静态方法直接移到接口类里，
不需要独立创建工具类了。