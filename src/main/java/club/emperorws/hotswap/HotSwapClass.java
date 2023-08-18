package club.emperorws.hotswap;

import club.emperorws.aop.DoAspect;
import club.emperorws.aop.constant.Constants;
import javassist.CtClass;
import javassist.util.HotSwapAgent;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;

/**
 * 紧急热部署class
 *
 * @author: EmperorWS
 * @date: 2023/3/6 13:51
 * @description: HotSwapClass: 紧急热部署class
 */
public abstract class HotSwapClass {

    private static final Logger logger = LoggerFactory.getLogger(HotSwapClass.class);

    private static final String CLASS_SUFFIX_FULL = ".class";

    /**
     * 热部署的执行代码
     */
    public void replaceOldClassInJvm() {
        try {
            //1. 创建文件监听器
            createFileMonitor();
        } catch (Exception e) {
            logger.error("HotSwapClass's replaceOldClassInJvm method has an error.", e);
        }
    }

    /**
     * 创建文件目录监听器
     *
     * @return 文件目录监听器
     * @throws IOException 异常
     */
    private ClassFileMonitor createFileMonitor() throws Exception {
        //如果文件夹不存在，则创建
        File classFileDic = new File(getNewClassFilePath());
        FileUtils.forceMkdir(classFileDic);
        ClassFileMonitor fileDicMonitor = new ClassFileMonitor(getInterval());
        fileDicMonitor.monitor(classFileDic, new ClassFileListener());
        fileDicMonitor.start();
        return fileDicMonitor;
    }

    /**
     * 热部署class文件
     *
     * @param file 变更文件
     */
    public static void redefineClass(File file) {
        if (!file.getName().endsWith(CLASS_SUFFIX_FULL)) {
            return;
        }
        //1. 开始执行热部署
        CtClass newCtClass = null;
        try (InputStream classFileIs = new BufferedInputStream(Files.newInputStream(file.toPath()))) {
            newCtClass = Constants.POOL.makeClass(classFileIs, false);
            //有切面的，需要切面AOP编程
            DoAspect.compileOneClass(newCtClass);
            Class<?> oldClass = Class.forName(newCtClass.getName());
            HotSwapAgent.redefine(oldClass, newCtClass);
            logger.info("old class [{}] has been redefined.", newCtClass.getName());
        } catch (Exception e) {
            logger.error("HotSwapClass's redefineClass method has an error.", e);
        } finally {
            if (Objects.nonNull(newCtClass)) {
                newCtClass.detach();
            }
        }
    }

    /**
     * 获取用于替换旧class的新class文件地址（位于共享磁盘）
     *
     * @return 用于替换旧class的新class文件地址（位于共享磁盘）
     */
    abstract String getNewClassFilePath();

    /**
     * 获取文件夹监视间隔
     *
     * @return 监视间隔
     */
    abstract long getInterval();
}
