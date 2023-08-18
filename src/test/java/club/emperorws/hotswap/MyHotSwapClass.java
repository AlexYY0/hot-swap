package club.emperorws.hotswap;

/**
 * 热部署启动类
 *
 * @author: EmperorWS
 * @date: 2023/8/6 0:38
 * @description: MyHotSwapClass: 热部署启动类
 */
public class MyHotSwapClass extends HotSwapClass {

    @Override
    String getNewClassFilePath() {
        return "/project/hotSwap/class";
    }

    @Override
    long getInterval() {
        return 5000;
    }
}
