package club.emperorws.hotswap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 热部署测试
 *
 * @author: EmperorWS
 * @date: 2023/8/6 0:36
 * @description: HotSwapTest: 热部署测试
 */
@DisplayName("HotSwap热部署相关测试类")
public class HotSwapTest {

    @DisplayName("HotSwap热部署测试")
    @Test
    public void hotSwapDemo() throws InterruptedException {
        MyHotSwapClass myHotSwapClass = new MyHotSwapClass();
        myHotSwapClass.replaceOldClassInJvm();
        DynamicClassTest.printMethod();
        Thread.sleep(30000);
        DynamicClassTest.printMethod();
    }
}
