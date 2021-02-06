import lombok.Data;

import java.util.concurrent.TimeUnit;

public class VolatileTest2 {

    public static void main(String[] args) {
        final AoBing aoBing = new AoBing();
        aoBing.start();

        for (; ; ) {
            // 进入synchronized代码块前，线程会获得锁，清空工作内存，从主内存拷贝共享变量最新的值到工作内存成为副本
            // 获得锁
//            synchronized (aoBing) {
                // 执行代码
                if (aoBing.isFlag()) {
                    System.out.println("有点东西");
                }
//            }
            // 进入synchronized代码块后，将修改后的副本的值刷新回主内存中，线程释放锁
        }
    }

    @Data
    static class AoBing extends Thread {

//        private boolean flag = false;

        /**
         * volatile保证不同线程对共享变量操作的可见性
         * 也就是说一个线程修改了volatile修饰的变量，当修改写回主内存时，另外一个线程立即看到最新的值。
         */
        private volatile boolean flag = false;

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            flag = true;
            System.out.println("flag = " + flag);
        }
    }
}
