/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 12/12/2019 22:26:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NOT NULL,
  `commentabled` bit(1) NOT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `create_time` datetime DEFAULT NULL,
  `first_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK292449gwg5yf7ocdlmswv9w4j`(`type_id`) USING BTREE,
  INDEX `FK8ky5rrsxh01nkhctmo7d48p82`(`user_id`) USING BTREE,
  CONSTRAINT `FK292449gwg5yf7ocdlmswv9w4j` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK8ky5rrsxh01nkhctmo7d48p82` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES (1, b'1', b'1', '### Java基础篇\r\n\r\n- 四大特性\r\n\r\n​		抽象、继承、封装、多态\r\n\r\n抽象：抽象是将一类的共同特征总结出来构造类的特征，这就包括数据抽象和行为抽象。抽象只是关注对象的属性和行为，并不关注这些行为的细节什么\r\n\r\n继承：继承是总已有类得到继承信息创建新的类的过程。提供继承信息的类称为父类（超类，积累）。继承这些的类称为子类（派生类）。封装是程序中可变元素的重要手段。\r\n\r\n封装：通常可以认为封装就是把数据和操作数据的方法绑定起来，对数据的访问只能通过已经定义的接口来访问，封装就是隐藏一切可以隐藏的东西。\r\n\r\n多态：指允许不同类型的对象对同一消息做出不同的响应。实现方式有：方法重载（编译时的多态性）、重写（运行时的多态性）\r\n\r\n- 面向对象开发的六个基本原则\r\n\r\n  1. 单一职责：一个类只做它该做的事情(高内聚)。在面向对象中，如果只让一个类完成它该做的事，而不涉及与它无关的 领域就是践行了高内聚的原则，这个类就只有单一职责。 \r\n  2. 开放封闭：软件实体应当对扩展开放，对修改关闭。要做到开闭有两个要点：①抽象是关键，一个系统中如果没有抽象 类或接口系统就没有扩展点；②封装可变性，将系统中的各种可变因素封装到一个继承结构中，如果多个可变因素混杂 在一起，系统将变得复杂而换乱\r\n  3. 里氏替换：任何时候都可以用子类型替换掉父类型。子类一定是增加父类的能力而不是减少父类的能力，因为子类比父 类的能力更多，把能力多的对象当成能力少的对象来用当然没有任何问题。 \r\n  4. 依赖倒置：面向接口编程。（该原则说得直白和具体一些就是声明方法的参数类型、方法的返回类型、变量的引用类型 时，尽可能使用抽象类型而不用具体类型，因为抽象类型可以被它的任何一个子类型所替代） \r\n  5. 合成聚和复用：优先使用聚合或合成关系复用代码。 \r\n  6. 接口隔离：接口要小而专，绝不能大而全。臃肿的接口是对接口的污染，既然接口表示能力，那么一个接口只应该描述 一种能力，接口也应该是高度内聚的\r\n\r\n- static和final的区别和用途 \r\n\r\n  Static 修饰变量：静态变量随着类加载时被完成初始化，内存中只有一个，且JVM也只会为它分配一次内存，所有类共享静态变量。 \r\n\r\n  修饰方法：在类加载的时候就存在，不依赖任何实例；static方法必须实现，不能用abstract修饰。 \r\n\r\n  修饰代码块：在类加载完之后就会执行代码块中的内容。\r\n\r\n   父类静态代码块->子类静态代码块->父类非静态代码块->父类构造方法->子类非静态代码块->子类构造方法  Final  修饰变量： • 编译期常量：类加载的过程完成初始化，编译后带入到任何计算式中。只能是基本类型\r\n\r\n  运行时常量：基本数据类型或引用数据类型。引用不可变，但引用的对象内容可变。\r\n  修饰方法：不能被继承，不能被子类修改。\r\n\r\n  修饰类：不能被继承。 ○ 修饰形参：final形参不可变 \r\n\r\n- Hash Map和Hash Table的区别，Hash Map中的key可以是任何对象或数据类型吗？HashTable是线程安全的么？ \r\n\r\n  • Hash Map和Hash Table的区别 \r\n\r\n  ○ Hashtable的方法是同步的，HashMap未经同步，所以在多线程场合要手动同步HashMap这个区别就像Vector和 ArrayList一样。\r\n\r\n  ○ Hashtable不允许 null 值(key 和 value 都不可以)，HashMap允许 null 值(key和value都可以)。 \r\n\r\n  ○ 两者的遍历方式大同小异，Hashtable仅仅比HashMap多一个elements方法。 Hashtable 和 HashMap 都能通过values()方法返回一个 Collection ，然后进行遍历处理。 两者也都可以通过 entrySet() 方法返回一个 Set ， 然后进行遍历处理。 \r\n\r\n  ○ HashTable使用Enumeration，HashMap使用Iterator。 ○ 哈希值的使用不同，Hashtable直接使用对象的hashCode。而HashMap重新计算hash值，而且用于代替求模。\r\n\r\n  ○ Hashtable中hash数组默认大小是11，增加的方式是 old*2+1。HashMap中hash数组的默认大小是16，而且一定是2 的指数。 \r\n\r\n  ○ HashTable基于Dictionary类，而HashMap基于AbstractMap类 \r\n\r\n  • Hash Map中的key可以是任何对象或数据类型吗 \r\n\r\n  ○ 可以为null，但不能是可变对象，如果是可变对象的话，对象中的属性改变，则对象HashCode也进行相应的改变，导 致下次无法查找到已存在Map中的数据。 ○ 如果可变对象在HashMap中被用作键，那就要小心在改变对象状态的时候，不要改变它的哈希值了。我们只需要保证 成员变量的改变能保证该对象的哈希值不变即可。 \r\n\r\n  • HashTable是线程安全的么 \r\n\r\n  ○ HashTable是线程安全的，其实现是在对应的方法上添加了synchronized关键字进行修饰，由于在执行此方法的时候 需要获得对象锁，则执行起来比较慢。所以现在如果为了保证线程安全的话，使用CurrentHasxhMap\r\n\r\n- Java序列化，如何实现序列化和反序列化，常见的序列化协议有哪些 ?\r\n\r\n  ​	实现序列化方法 :\r\n\r\n  ​		1.实现Serializable接口	2.实现ExternalSerializable方法\r\n\r\n  常见的序列化协议有哪些 ?\r\n\r\n  1. COM主要用于Windows平台，并没有真正实现跨平台，另外COM的序列化的原理利用了编译器中虚表，使得其学习 成本巨大。\r\n  2. CORBA是早期比较好的实现了跨平台，跨语言的序列化协议。COBRA的主要问题是参与方过多带来的版本过多，版本 之间兼容性较差，以及使用复杂晦涩\r\n  3. XML&SOAP XML是一种常用的序列化和反序列化协议，具有跨机器，跨语言等优点。  SOAP（Simple Object Access protocol） 是一种被广泛应用的，基于XML为序列化和反序列化协议的结构化 消息传递协议。SOAP具有安全、可扩展、跨语言、跨平台并支持多种传输层协议\r\n  4. JSON（Javascript Object Notation） \r\n  5. Thrift是Facebook开源提供的一个高性能，轻量级RPC服务框架，其产生正是为了满足当前大数据量、分布式、跨语 言、跨平台数据通讯的需求。Thrift在空间开销和解析性能上有了比较大的提升，对于对性能要求比较高的分布式系 统，它是一个优秀的RPC解决方案；但是由于Thrift的序列化被嵌入到Thrift框架里面，Thrift框架本身并没有透出序列 化和反序列化接口，这导致其很难和其他传输层协议共同使用 \r\n\r\n- Java实现多线程的方式及三种方式的区别 \r\n\r\n  - 实现多线程的方式\r\n    - 继承Thread类，重写run函数\r\n    - 实现Runnable接口\r\n    - 实现Callable接口 \r\n  - 三种方式的区别\r\n    - 实现Runnable接口可以避免Java单继承特性而带来的局限；增强程序的健壮性，代码能够被多个线程共享，代码与数 据是独立的；适合多个相同程序代码的线程区处理同一资源的情况。 \r\n    - 继承Thread类和实现Runnable方法启动线程都是使用start方法，然后JVM虚拟机将此线程放到就绪队列中，如果有 处理机可用，则执行run方法\r\n    - 实现Callable接口要实现call方法，并且线程执行完毕后会有返回值。其他的两种都是重写run方法，没有返回值。\r\n\r\n- 什么是线程池？如果让你设计一个动态大小的线程池，如何设计，应该有哪些方法？\r\n\r\n  - 线程池：线程池顾名思义就是事先创建若干个可执行的线程放入一个池（容器）中，需要的时候从池中获取线程不用自行创建， 使用完毕不需要销毁线程而是放回池中，从而减少创建和销毁线程对象的开销\r\n  - 设计\r\n    - 一个线程池的四个基本组成部分\r\n      - 线程管理器(ThreadPool)：用于创建并管理线程池，包括创建线程，销毁线程池，添加新任务；\r\n      - 工作线程(PoolWorker)：线程池中线程，在没有任务时处于等待状态，可以循环的执行任务； \r\n      - 任务接口(Task)：每个任务必须实现的接口，以供工作线程调度任务的执行，它主要规定了任务的入口，任务执行 完后的收尾工作，任务的执行状态等；\r\n      - 任务队列(TaskQueue)：用于存放没有处理的任务。提供一种缓冲机制；\r\n    - 包含的方法\r\n      - private ThreadPool()  创建线程池\r\n      - public static ThreadPool getThreadPool()  获得一个默认线程个数的线程池 \r\n      - public void execute(Runnable task)  执行任务,其实只是把任务加入任务队列，什么时候执行由线程池管理器决定 \r\n      - public void execute(Runnable[] task)  批量执行任务,其实只是把任务加入任务队列，什么时候执行有线程池管 理器决定 \r\n      - public void destroy()  销毁线程池,该方法保证在所有任务都完成的情况下才销毁所有线程，否则等待任务完成才销毁 \r\n      - public int getWorkThreadNumber() 返回工作线程的个数 \r\n      - public int getFinishedTasknumber() 返回已完成任务的个数,这里的已完成是只出了任务队列的任务个数，可能 该任务并没有实际执行完成 \r\n      - public void addThread() 在保证线程池中所有线程正在执行，并且要执行线程的个数大于某一值时。增加线程池中线程的个数 \r\n      - public void reduceThread() 在保证线程池中有很大一部分线程处于空闲状态，并且空闲状态的线程在小于某一值时，减少线程池中线程的个数\r\n\r\n- Java是否有内存泄露和内存溢出 ?\r\n\r\n  - 静态集合类，使用Set、Vector、HashMap等集合类的时候需要特别注意。当这些类被定义成静态的时候，由于他们的生命 周期跟应用程序一样长，这时候就有可能发生内存泄漏。 \r\n  - 监听器： 在Java编程中，我们都需要和监听器打交道，通常一个应用中会用到很多监听器，我们会调用一个控件，诸如 addXXXListener()等方法来增加监听器，但往往在释放的时候却没有去删除这些监听器，从而增加了内存泄漏的机会。\r\n  - 物理连接：一些物理连接，比如数据库连接和网络连接，除非其显式的关闭了连接，否则是不会自动被GC 回收的。Java 数据库连接一般用DataSource.getConnection()来创建，当不再使用时必须用Close()方法来释放，因为这些连接是独立于 JVM的。对于Resultset 和Statement 对象可以不进行显式回收，但Connection 一定要显式回收，因为Connection 在任 何时候都无法自动回收，而Connection一旦回收，Resultset 和Statement 对象就会立即为NULL。但是如果使用连接池， 情况就不一样了，除了要显式地关闭连接，还必须显式地关闭Resultset Statement 对象（关闭其中一个，另外一个也会关 闭），否则就会造成大量的Statement 对象无法释放，从而引起内存泄漏。。一般情况下，在try代码块里创建连接，在 finally里释放连接，就能够避免此类内存泄漏\r\n  - 内部类和外部模块等的引用：内部类的引用是比较容易遗忘的一种，而且一旦没释放可能导致一系列的后继类对象没有释 放。在调用外部模块的时候，也应该注意防止内存泄漏，如果模块A调用了外部模块B的一个方法，如： public void register(Object o) 这个方法有可能就使得A模块持有传入对象的引用，这时候需要查看B模块是否提供了出去引用的方法，这种情况容易忽略， 而且发生内存泄漏的话，还比较难察觉。\r\n  - 单例模式：因为单例对象初始化后将在JVM的整个生命周期内存在，如果它持有一个外部对象的（生命周期比较短）引用， 那么这个外部对象就不能被回收，从而导致内存泄漏。如果这个外部对象还持有其他对象的引用，那么内存泄漏更严重。\r\n\r\n#### JavaSe常用Api\r\n\r\n## web篇\r\n\r\n#### HTTP/1.1与HTTP/1.0的区别 \r\n\r\nhttps://blog.csdn.net/forgotaboutgirl/article/details/6936982\r\n\r\n1. 可拓展性\r\n2. 缓存\r\n3. 带宽优化\r\n4. 长链接\r\n5. 消息传递\r\n6. host头域\r\n7. 错误提示\r\n8. 内容协商\r\n9. ', '2019-12-04 22:39:47', 'http://i1.sinaimg.cn/ent/d/2008-06-04/U105P28T3D2048907F326DT20080604225106.jpg', '', b'1', b'1', b'1', 'java面试篇', '2019-12-04 22:39:47', 6, 3, 1, '描述');

-- ----------------------------
-- Table structure for t_blog_tags
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tags`;
CREATE TABLE `t_blog_tags`  (
  `blogs_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  INDEX `FK5feau0gb4lq47fdb03uboswm8`(`tags_id`) USING BTREE,
  INDEX `FKh4pacwjwofrugxa9hpwaxg6mr`(`blogs_id`) USING BTREE,
  CONSTRAINT `FK5feau0gb4lq47fdb03uboswm8` FOREIGN KEY (`tags_id`) REFERENCES `t_tag` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKh4pacwjwofrugxa9hpwaxg6mr` FOREIGN KEY (`blogs_id`) REFERENCES `t_blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_blog_tags
-- ----------------------------
INSERT INTO `t_blog_tags` VALUES (1, 1);
INSERT INTO `t_blog_tags` VALUES (1, 2);

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `blog_id` bigint(20) DEFAULT NULL,
  `parent_comment_id` bigint(20) DEFAULT NULL,
  `admin_comment` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKke3uogd04j4jx316m1p51e05u`(`blog_id`) USING BTREE,
  INDEX `FK4jj284r3pb7japogvo6h72q95`(`parent_comment_id`) USING BTREE,
  CONSTRAINT `FK4jj284r3pb7japogvo6h72q95` FOREIGN KEY (`parent_comment_id`) REFERENCES `t_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKke3uogd04j4jx316m1p51e05u` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, '/images/avatar.png', '写的个鬼', '2019-12-12 21:14:15', '1728313797@qq.com', 'soul', 1, NULL, b'0');
INSERT INTO `t_comment` VALUES (2, '/images/avatar.png', '憨批', '2019-12-12 21:14:23', '1728313797@qq.com', 'soul', 1, 1, b'0');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (1, 'JS事件');
INSERT INTO `t_tag` VALUES (2, 'springboot缓存');
INSERT INTO `t_tag` VALUES (3, 'Ajax');
INSERT INTO `t_tag` VALUES (4, 'Javaweb');
INSERT INTO `t_tag` VALUES (5, 'mysql');
INSERT INTO `t_tag` VALUES (6, '缓存');
INSERT INTO `t_tag` VALUES (7, '编程所悟');
INSERT INTO `t_tag` VALUES (8, '小技巧');
INSERT INTO `t_tag` VALUES (9, 'idea');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (2, 'Java');
INSERT INTO `t_type` VALUES (3, '前端学习');
INSERT INTO `t_type` VALUES (4, '架构');
INSERT INTO `t_type` VALUES (5, '数据库');
INSERT INTO `t_type` VALUES (6, '人工智能');
INSERT INTO `t_type` VALUES (7, '游戏开发');
INSERT INTO `t_type` VALUES (8, 'vue');
INSERT INTO `t_type` VALUES (9, 'ES6、7、8');
INSERT INTO `t_type` VALUES (10, '个人心得');
INSERT INTO `t_type` VALUES (11, '其他');
INSERT INTO `t_type` VALUES (12, '随手记');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'http://b-ssl.duitang.com/uploads/item/201509/25/20150925110828_iMnGx.jpeg', '2019-11-26 23:13:13', '1728313797@qq.com', '符浩灵', '202cb962ac59075b964b07152d234b70', 1, '2019-11-19 23:13:51', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
