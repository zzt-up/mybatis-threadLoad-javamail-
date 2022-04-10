#1.mybatis入门
从 XML 中构建 SqlSessionFactory
不使用 XML 构建 SqlSessionFactory
从 SqlSessionFactory 中获取 SqlSession
调用mapper中的方式：
###1.1 
try (SqlSession session = sqlSessionFactory.openSession()) {
Blog blog = (Blog) session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);}
###1.2 
 try (SqlSession session = sqlSessionFactory.openSession()) {
  BlogMapper mapper = session.getMapper(BlogMapper.class);
  Blog blog = mapper.selectBlog(101);}
###1.3 不使用xml文件写sql,直接使用java注解写，这个可以在已有的接口dao里面直接写注解方法也适用
public interface BlogMapper {
  @Select("SELECT * FROM blog WHERE id = #{id}")
  Blog selectBlog(int id);}
###1.4 作用域（Scope）和生命周期
###1.5 SqlSessionFactoryBuilder 
最佳作用域是方法作用域（也就是局部方法变量）
###1.6 SqlSessionFactory SqlSessionFactory 
最佳作用域是应用作用域。最简单的就是单例模式或者静态单例模式。
###1.7 SqlSession
每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域，使用之后需要关闭，把这个关闭操作放到 finally 块中。
try (SqlSession session = sqlSessionFactory.openSession()) {
  // 你的应用逻辑代码
}
###1.8 映射器实例
映射器是一些绑定映射语句的接口，映射器接口的实例是从 SqlSession 中获得的，最好将映射器放在方法作用域内
try (SqlSession session = sqlSessionFactory.openSession()) {
  BlogMapper mapper = session.getMapper(BlogMapper.class); // 你的应用逻辑代码 }
#XML 映射配置文件
##properties   
这些属性都是可外部配置且可动态替换的，既可以在典型的 Java 属性文件中配置，亦可通过 properties 元素的子元素来传递 ，属性注入优先级
###settings  
这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为，举例：
useActualParamName，这个在dao接口，参数可以@param不写名称（>3.4.1）, 还有个属性是下划线和驼峰自动转换
允许使用方法签名中的名称作为语句参数名称。 为了使用该特性，你的项目必须采用 Java 8 编译，并且加上 -parameters 选项。（新增于 3.4.1）	true | false	true
###typeAliases   
类型别名是为 Java 类型设置一个短的名字,也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean
已经为许多常见的 Java 类型内建了相应的类型别名。string,integer,int它们都是大小写不敏感的，需要注意的是由基本类型名称重复导致的特殊处理(前面加下划线_int，_long，_integer，这些他们就代表基本类型)
实际使用别名时候：  type-aliases-package: com.zzt.demo.model ，model后面写*就启动报错，实体类上面不写@Alias("tProduct")，mapper文件就会报红。但是能用，而且不区分大小写
###typeHandlers
无论是 MyBatis 在预处理语句（PreparedStatement）中设置一个参数时，还是从结果集中取出一个值时， 都会用类型处理器将获取的值以合适的方式转换成 Java 类型
你可以重写类型处理器或创建你自己的类型处理器来处理不支持的或非标准的类型。 具体做法为：实现 org.apache.ibatis.type.TypeHandler 接口， 或继承一个很便利的类 org.apache.ibatis.type.BaseTypeHandler， 然后可以选择性地将它映射到一个 JDBC 类型
###处理枚举类型
EnumTypeHandler 数据库字符串类型，用以存储枚举的名称（Male, FeMale）；
查询时resultMap 里面接收 <result column="user_id" property="satisfyResultEnum" jdbcType="VARCHAR" typeHandler="org.apache.ibatis.type.EnumTypeHandler" />
插入时： #{satisfyResult,typeHandler="org.apache.ibatis.type.EnumTypeHandler"},
EnumOrdinalTypeHandler 数据库为数值类型，存储枚举的序数值 （位置从上到下0，1，2）全局配置下面，枚举类型默认就使用此策略不需要改代码
mybatis:
  configuration:
    default-enum-type-handler: org.apache.ibatis.type.EnumOrdinalTypeHandler
查询时：<result column="satisfy_result" property="satisfyResultEnum" jdbcType="VARCHAR" typeHandler="org.apache.ibatis.type.EnumOrdinalTypeHandler" />
插入时： #{satisfyResultEnum,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler}
以上两种方法没有实现，将枚举的code存入数据库，再将code取出来的转换器，下面文章有实现
https://blog.csdn.net/weixin_34212189/article/details/88986153
###对象工厂（objectFactory）
在接收和插入某个自定义对象的时候，在中途改变其属性。
https://blog.csdn.net/fageweiketang/article/details/80794847
##插件（plugins）  
PageHelper的实现原理，通过mybatis拦截器 ，MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用 ，修改参数值，数据源切换，数据库读写分离，权限控制，底层拦截sql
找到一个idea的mybatis 日志记录插件 https://blog.csdn.net/qq_22194659/article/details/89011988,不打开特定输出窗口也行
###配置环境（environments） 
MyBatis 可以配置成适应多种环境，这种机制有助于将 SQL 映射应用于多种数据库之中
###事务管理器（transactionManager）
在 MyBatis 中有两种类型的事务管理器（也就是 type="[JDBC|MANAGED]"），如果使用 Spring + MyBatis，则没有必要配置事务管理器， 因为 Spring 模块会使用自带的管理器来覆盖前面的配置，也可以实现对应接口自定义mybatis事务行为
###数据源（dataSource）
dataSource 元素使用标准的 JDBC 数据源接口来配置 JDBC 连接对象的资源。
有三种内建的数据源类型（也就是 type="[UNPOOLED|POOLED|JNDI]"）：
###databaseIdProvider
MyBatis 可以根据不同的数据库厂商执行不同的语句，这种多厂商的支持是基于映射语句中的 databaseId 属性
###映射器（mappers）
告诉 MyBatis 到哪里去找映射文件
#MyBatis XML映射文件
###Mapper XML 文件
select 查看下面具体标签及其含义
Select Attributes
Insert, Update 和 Delete 的属性
如果你的数据库支持自动生成主键的字段（比如 MySQL 和 SQL Server），那么你可以设置 useGeneratedKeys=”true”，然后再把 keyProperty 设置到目标属性上就OK了，（实际设置不设置似乎作用不到，本来就有自增主键了，可以直接不写）
<insert id="insertAuthor" useGeneratedKeys="true" keyProperty="id">
##selectKey
mysql order设置为before获取的时自增前数据库最大的id值，要想获取本次的id值，需要设置为after.
<selectKey keyProperty="id" resultType="java.lang.Integer" order="AFTER">
       SELECT LAST_INSERT_ID() 
    </selectKey>
关于@SelectKey在springboot整合Mybatis时总是返回1,这个返回的时影响的条数，其实获取到的id值已经注入到之前的实体类里面了，直接get就能获取
https://blog.csdn.net/qq_38904904/article/details/105156770
##sql
这个元素可以被用来定义可重用的 SQL 代码段，可以包含在其他语句中。它可以静态地(在加载阶段)参数化。不同的属性值可以在include实例中有所不同，
这个可以嵌套，在过程中也可以使用属性值变量来代替，引用sqlId也可以设置为变量，在最前面调用的时候统一赋值就好了，这个类似java中，传不同的参数调用不同的方法名
##参数（Parameters）
对于数值类型，还有一个小数保留位数的设置，来确定小数点后保留的位数。
###{height,javaType=double,jdbcType=NUMERIC,numericScale=2}
这个还支持数据库存储过程，in,out,inout
##字符串替换
‘#{}’格式的语法会导致 MyBatis 创建预处理语句属性并安全地设置值（比如?）。这样做更安全，更迅速，通常也是首选做法，不过有时你只是想直接在 SQL 语句中插入一个不改变的字符串。比如，像 ORDER BY，
ORDER BY ${columnName}
##Result Maps
resultMap，resultType（幕后自动创建一个 ResultMap）  不能同时存在
##高级结果映射
property  JavaBeans 的属性，可以这样映射一些东西: "username" ,或者映射到一些复杂的东西: "address.street.number" 。
jdbcType  JDBC 类型是仅 仅需要对插入,更新和删除操作可能为空的列进行处理
constructor - 类在实例化时,用来注入结果到构造方法中   用javabean的构造方法赋值
构造方法注入允许你在初始化时 为类设置属性的值,而不用暴露出公有方法
idArg - ID 参数;标记结果作为 ID 可以帮助提高整体效能
arg - 注入到构造方法的一个普通结果
id – 一个 ID 结果;标记结果作为 ID 可以帮助提高整体效能
result – 注入到字段或 JavaBean 属性的普通结果
association – 一个复杂的类型关联;许多结果将包成这种类型  一个  resultMap   javaType
嵌入结果映射 – 结果映射自身的关联,或者参考一个
collection – 复杂类型的集   多个 ofType
<collection property="posts" column="id" ofType="Post" select="selectPostsForBlog"/>
嵌入结果映射 – 结果映射自身的集,或者参考一个
discriminator – 使用结果值来决定使用哪个结果映射
case – 基于某些值的结果映射
嵌入结果映射 – 这种情形结果也映射它本身,因此可以包含很多相 同的元素,或者它可以参照一个外部的结果映射
##id & result
javaType    如果你映射到的是 HashMap,那么你应该明确地指定 javaType 来保证所需的行为
jdbcType  JDBC 类型是仅 仅需要对插入,更新和删除操作可能为空的列进行处理
##构造方法
构造方法注入允许你在初始化时 为类设置属性的值,而不用暴露出公有方法
##关联的嵌套查询
##关联的嵌套结果
##集合
##集合的嵌套查询
##集合的嵌套结果
##鉴别器
有时一个单独的数据库查询也许返回很多不同 (但是希望有些关联) 数据类型的结果集。 鉴别器元素就是被设计来处理这个情况
<resultMap id="carResult" type="Car" extends="vehicleResult">
不写extends引用外部属性，则会只加载此resultMap下面的属性
##自动映射
在简单的场景下，MyBatis 可以替你自动映射查询结果。 如果遇到复杂的场景，你需要构建一个 result map。 但是在本节你将看到，你也可以混合使用这两种策略
##MyBatis 传递多个参数
方法一:使用map接口传递参数，
方法二:使用注解@Param传递多个参数
方法三:通过Java Bean传递多个参数
方法四:混合使用
public List<Role> findByMix(@Param("params") RoleParams roleParams, @Param("page") PageParam PageParam);
<select id="findByMix" resultType="role">
    select id, role_name as roleName, note from t_role
    where role_name like concat('%', #{params.roleName}, '%') and note like concat('%', #{params.note}, '%') limit #{page.start}, #{page.limit}
</select>

通常数据库列使用大写单词命名，单词间用下划线分隔；而java属性一般遵循驼峰命名法。 为了在这两种命名方式之间启用自动映射，需要将 mapUnderscoreToCamelCase设置为 true。
意思：resultMap 虽然有些列没有显式的写映射语句，但是如果满足自动映射规则，那将会先被自动映射。（实际已检测）
自动映射甚至在特定的 result map下也能工作，默认不使用嵌套结果映射（连接）内的结果
通过添加 autoMapping 属性可以忽略自动映射等级配置，你可以启用或者禁用自动映射指定的 ResultMap。
##缓存
默认情况下是没有开启缓存的,除了局部的 session 缓存,可以增强变现而且处理循环 依赖也是必须的。要开启二级缓存,你需要在你的 SQL 映射文件中添加一行:   <cache/>
##使用自定义缓存
##参照缓存
<cache-ref namespace="com.someone.application.data.SomeMapper"/>
#MyBatis 动态SQL
if
choose (when, otherwise)
trim (where, set)   --》
<trim prefix="WHERE" prefixOverrides="AND |OR ">  ... </trim>
<trim prefix="SET" suffixOverrides=","> ...</trim>
foreach    list    array
<foreach item="item" index="index" collection="list"  open="(" separator="," close=")">  </foreach>
bind   元素可以从 OGNL 表达式中创建一个变量并将其绑定到上下文            在元素内部使用，封装参数
_databaseId 
#MyBatis Java API
#MyBatis SQL语句构建器
new SQL(){“sql”}
MyBatis缓存机制 默认一级（当前会话的sqlsession私有），二级需要开启（同一mapper级别）， 都是[namespace:sql:参数] 作为 key ，查询返回的语句作为 value 保存的，出现数据变动之后刷新
mybatis可以使用拦截器分页，创建拦截器，拦截mybatis接口方法id以ByPage结束的语句
或者PageHelper插件
或者 RowBounds   List<User> list = sqlSession.selectList("x.y.selectIf", null, new RowBounds(0, 10));
##实际代码已实现，自定义的分页mybatis 拦截器，自定义typehandler  实现了，枚举的code存数据库，及取出数据库，（官方自带的两个方法是存名字和序号），自定义拦截器来记录sql日志

