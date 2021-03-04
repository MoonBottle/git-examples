import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * https://mp.weixin.qq.com/s?__biz=MzAwNDA2OTM1Ng==&mid=2453142004&idx=1&sn=81ccddb6c8b37114c022c4ad50368ecf&chksm=8cf2db77bb855261b761f11025728f9d56cc7828d9f174752875d4a188e196b4e8494006f7f8&scene=178&cur_album_id=1343706145583857665#rd
 */
public class VolatileTest {

    public static void main(String[] args) {
        final AoBing aoBing = new AoBing();
        aoBing.start();

        for (; ; ) {
            if (aoBing.isFlag()) {
                // 永远不会执行
                System.out.println("有点东西");
            }
        }
    }

    @Data
    static class AoBing extends Thread {

        private boolean flag = false;

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
