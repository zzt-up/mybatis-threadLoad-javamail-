##threadLocal   
适合在一个线程的处理流程中保持上下文，避免了同一参数在所有方法中传递
public static final ThreadLocal<Map<String,Integer>> mapThreadLocal = new ThreadLocal<>();
1. 经常与static一起使用
2. 可以写多个
3. 把ThreadLocal看成一个全局Map<Thread, Object>：每个线程获取ThreadLocal变量时，总是使用Thread自身作为key
4. 需要在finally中写.remove
5. 可以封装一个context对象，以通过实现AutoCloseable接口配合try (resource) {...}结构，让编译器自动为我们关闭


##动态代理

在运行期动态创建一个interface实例的方法如下：

定义一个InvocationHandler实例，它负责实现接口的方法调用；
通过Proxy.newProxyInstance()创建interface实例，它需要3个参数：
使用的ClassLoader，通常就是接口类的ClassLoader；
需要实现的接口数组，至少需要传入一个接口进去；
用来处理接口方法调用的InvocationHandler实例。
将返回的Object强制转型为接口。
动态代理实际上是JDK在运行期动态创建class字节码并加载的过程