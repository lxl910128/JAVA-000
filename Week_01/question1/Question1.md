# Question1
## 问题
自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后
自己分析一下对应的字节码，有问题群里讨论。

## 解答
### HelloWord.java类
编写HelloWord.java类，该类有3个成员变量，分别为有未赋值的私有String变量p1，公有int型静态常量p2值为2,公有long型变量p3值为3L。
1个add方法，实现功能是接受int传参先与p2相加，再与p3相乘并将结果返回。
如空main方式，先实例化HelloWord类，然后调用add方法并传参4，将方法返回打印。
源码如下：
```java
package club.gaiaproject.geekbang;

class HelloWord {
    private String p1;
    public static final int p2 = 2;
    public long p3 = 3L;

    public long add(int arg) {
        int add = arg + p2;
        long mul = add * p3;
        return mul;
    }

    public static void main(String[] args) {
        HelloWord test = new HelloWord();
        System.out.println(test.add(4));
    }
}
```
### 编译及反编译
1. 使用 `javac HelloWord.java` 编译代码生成HelloWord.class
2. 使用 `javap -c  -verbose -protected HelloWord  >HelloWord1.txt` 反编译生成字节码文件HelloWord.txt
3. 相关步骤注解如下
```java
Classfile /Users/luoxiaolong/workspace/JAVA-000/Week_01/question1/HelloWord.class  // 原文件位置
  Last modified 2020-10-18; size 645 bytes  // 最后修改时间和大小
  MD5 checksum f5de1afb251ed02e0d40fba92ea252db // md5
  Compiled from "HelloWord.java"
class club.gaiaproject.geekbang.HelloWord
  minor version: 0
  major version: 52 //java版本
  flags: ACC_SUPER // 表示当用invokespecial指令来调用父类的方法时需要特殊处理。
Constant pool: // 常量池相关的数据项  常量池中存放了文字字符串，常量值，当前类的类名，字段名， 方法名， 各个字段和方法的描述符，对当前类的字段和方法的引用信息，当前类中对其他类的引用信息等等
   #1 = Methodref          #10.#29        // java/lang/Object."<init>":()V  方法引用 object的初始化方法 ，是调用#10（object）的#29方法
   #2 = Long               3l             // 我们定义的p3 是public且初始化就设定了值
   #4 = Fieldref           #5.#30         // club/gaiaproject/geekbang/HelloWord.p3:J
   #5 = Class              #31            // club/gaiaproject/geekbang/HelloWord  本类
   #6 = Methodref          #5.#29         // club/gaiaproject/geekbang/HelloWord."<init>":()V
   #7 = Fieldref           #32.#33        // java/lang/System.out:Ljava/io/PrintStream;
   #8 = Methodref          #5.#34         // club/gaiaproject/geekbang/HelloWord.add:(I)J
   #9 = Methodref          #35.#36        // java/io/PrintStream.println:(J)V
  #10 = Class              #37            // java/lang/Object  // 父 object类
  #11 = Utf8               p1               // 文本字符串
  #12 = Utf8               Ljava/lang/String;
  #13 = Utf8               p2
  #14 = Utf8               I
  #15 = Utf8               ConstantValue
  #16 = Integer            2
  #17 = Utf8               p3
  #18 = Utf8               J
  #19 = Utf8               <init>
  #20 = Utf8               ()V
  #21 = Utf8               Code
  #22 = Utf8               LineNumberTable
  #23 = Utf8               add
  #24 = Utf8               (I)J
  #25 = Utf8               main
  #26 = Utf8               ([Ljava/lang/String;)V
  #27 = Utf8               SourceFile
  #28 = Utf8               HelloWord.java
  #29 = NameAndType        #19:#20        // "<init>":()V
  #30 = NameAndType        #17:#18        // p3:J
  #31 = Utf8               club/gaiaproject/geekbang/HelloWord
  #32 = Class              #38            // java/lang/System System类
  #33 = NameAndType        #39:#40        // out:Ljava/io/PrintStream;
  #34 = NameAndType        #23:#24        // add:(I)J
  #35 = Class              #41            // java/io/PrintStream PrintStream类
  #36 = NameAndType        #42:#43        // println:(J)V
  #37 = Utf8               java/lang/Object
  #38 = Utf8               java/lang/System
  #39 = Utf8               out
  #40 = Utf8               Ljava/io/PrintStream;
  #41 = Utf8               java/io/PrintStream
  #42 = Utf8               println
  #43 = Utf8               (J)V
{
  public static final int p2;
    descriptor: I // int 类型
    flags: ACC_PUBLIC, ACC_STATIC, ACC_FINAL  //是否为Public类型；是否被声明为final;是否被声明为STATIC
    ConstantValue: int 2        //final关键字定义的常量池

  public long p3;
    descriptor: J // long类型
    flags: ACC_PUBLIC // Public类型

  public long add(int);
    descriptor: (I)J // 传参是int 返回是long
    flags: ACC_PUBLIC
    Code: // 代码
      stack=4, locals=5, args_size=2  // 需要深度为4的栈  本地变量表大小需要5
         0: iload_1     //将int型局部变量1号槽位加载到操作数栈  应该是方法的接收的参数arg的值
         1: iconst_2    // 将int常量2加载到操作数栈
         2: iadd        // 相加
         3: istore_2    // 结果保存到局部变量2号槽位
         4: iload_2     // 加载int类型2号槽位的局部变量
         5: i2l         // int 转 long
         6: aload_0     // 加载引用对象类型到操作数站  应该是 add 变量所代表的的值
         7: getfield      #4                  // Field p3:J   获取属性p3的值
        10: lmul        // 相乘
        11: lstore_3    // 保存到型局部变量3号槽位
        12: lload_3     // 加载型局部变量3号槽位到操作数栈
        13: lreturn     // 返回
      LineNumberTable:  // 与源码对应表
        line 9: 0
        line 10: 4
        line 11: 12

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V // 传参为string数组，无返回
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=3, locals=2, args_size=1
         0: new           #5                // class club/gaiaproject/geekbang/HelloWord  实力换HelloWord
         3: dup                             //复制操作数栈顶值，并将其压入栈顶，也就是说此时操作数栈上有连续相同的两个对象地址；
         4: invokespecial #6                // 调用  Method "<init>":()V   invokespecial：用于调用一些需要特殊处理的实例方法，包括实例初始化方法、私有方法和父类方法。
         7: astore_1                        // 保存new object() 创建的对象到槽位1
         8: getstatic     #7                //访问类字段（static字段，被称为类变量） Field java/lang/System.out:Ljava/io/PrintStream;
        11: aload_1                         // 加载创建的HelloWord
        12: iconst_4                        // 加载数字常量4到操作数栈
        13: invokevirtual #8                // 调用刚实例化的helloWord对象的add方法  Method add:(I)J
        16: invokevirtual #9                // 调用PrintStream对象的add方法 Method java/io/PrintStream.println:(J)V
        19: return
      LineNumberTable:
        line 15: 0
        line 16: 8
        line 17: 19
}
SourceFile: "HelloWord.java"
```
## 知识点总结
1. javap根据class字节码文件生成的文件（汇编文件?）主要包括：当前类对应的code区（汇编指令）、本地变量表、异常表和代码行偏移量映射表、常量池、类基本信息等等信息
2. 如果要在汇编文件中显示本地变量表需要在编译时增加`-g`参数，在反编译时增加`-l`
3. 本地变量表中0号槽位一般是方法所有的类实例，然后是方法的传参
4. Code部分是我们主要关注的部分，首先会指出运行该方法所需的栈的大小，本地变量表的大小，传参的大小
5. 常量池中有很多UTF8类型的字符串，这些字符串是类名、方法名、变量名等
7. JVM主要操作助记符可查看：https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-7.html
