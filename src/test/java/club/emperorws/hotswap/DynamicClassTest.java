package club.emperorws.hotswap;

/**
 * 热部署的测试类
 *
 * @author: EmperorWS
 * @date: 2023/8/6 0:57
 * @description: DynamicClassTest: 热部署的测试类
 */
public class DynamicClassTest {

    public static String printMethod() {
        String testStr = "I'm old class";
        System.out.println(testStr);
        return testStr;
    }
}
