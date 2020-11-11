package club.gaiaproject.homework;

import cn.hutool.core.io.FileUtil;
import javafx.concurrent.Task;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Phoenix Luo
 * @version 2020/11/11
 **/
public class Test {
    public static Integer value;

    public static void main(String[] args) {
        Test t = new Test();
        try {
            t.test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test() throws Exception {
        // 第一种 callable
        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future<Integer> retFuture = pool.submit(() -> Test.sum());
        System.out.println(retFuture.get());

        // 第二种 初始化线程时传对象
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Result ret = new Result();
        new Thread(new ThreadB(ret, countDownLatch)).start();
        countDownLatch.await();
        System.out.println(ret.getValue());

        // 第三种 修改静态变量
        new Thread(new ThreadC()).start();
        System.out.println(Test.value);

        // 第四种 FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(() -> Test.sum());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

        // 第五种 闭包 修改外出原子类
        AtomicInteger atomicReference = new AtomicInteger();
        new Thread(() -> {
            atomicReference.set(Test.sum());
        }).start();
        System.out.println(atomicReference.get());

        // 第六种 lambdas
        System.out.println(pool.submit(() -> Test.sum()).get());

        // 第七种 Task
        System.out.println(new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                return Test.sum();
            }
        }.get());

        // 第八种 CompletableFuture
        CompletableFuture.supplyAsync(() -> Test.sum()).thenAccept(System.out::println);

        // 第九种 写到文件里，然后再去读这个文件
        final File file = new File("/tmp/cache.txt");
        file.createNewFile();
        Thread run = new Thread(() -> {
            FileUtil.writeString(Test.sum() + "", file, "UTF-8");
        });
        run.start();
        run.join();
        System.out.println(FileUtil.readLines(file, "UTF-8"));

        // 第10种 睡等
        final Result result = new Result();
        new Thread(() -> {
            result.setValue(Test.sum());
        }).start();
        while (null == result.getValue()) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
        System.out.println(result.getValue());
    }


    class ThreadA implements Callable<Integer> {

        @Override
        public Integer call() {
            return Test.sum();
        }
    }

    class ThreadB implements Runnable {

        private Result value;

        private CountDownLatch countDownLatch;

        public ThreadB(Result value, CountDownLatch countDownLatch) {
            this.value = value;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            this.value.setValue(Test.sum());
            countDownLatch.countDown();
        }
    }

    class ThreadC implements Runnable {
        @Override
        public void run() {
            Test.value = Test.sum();
        }
    }


    class Result {
        private Integer value;

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return this.value;
        }

    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

}
