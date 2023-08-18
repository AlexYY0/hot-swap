package club.emperorws.hotswap;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

/**
 * 文件夹监听器
 *
 * @author: EmperorWS
 * @date: 2023/3/8 17:10
 * @description: ClassFileListener: 文件夹监视器
 */
public class ClassFileListener extends FileAlterationListenerAdaptor {

    /**
     * 文件创建
     *
     * @param file 被创建的文件
     */
    @Override
    public void onFileCreate(File file) {
        HotSwapClass.redefineClass(file);
    }

    /**
     * 文件修改
     *
     * @param file 被修改的文件
     */
    @Override
    public void onFileChange(File file) {
        HotSwapClass.redefineClass(file);
    }
}
