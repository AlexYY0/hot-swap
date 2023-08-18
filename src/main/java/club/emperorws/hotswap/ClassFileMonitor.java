package club.emperorws.hotswap;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * 文件监视器
 *
 * @author: EmperorWS
 * @date: 2023/3/8 17:10
 * @description: ClassFileMonitor: 文件监视器
 */
public class ClassFileMonitor {

    private FileAlterationMonitor monitor;

    /**
     * 监视器构造器
     *
     * @param interval 监视间隔
     */
    public ClassFileMonitor(long interval) {
        this.monitor = new FileAlterationMonitor(interval);
    }

    /**
     * 给文件夹添加监听器
     *
     * @param path     文件夹路径
     * @param listener 监听器
     */
    public void monitor(File path, FileAlterationListener listener) {
        FileAlterationObserver observer = new FileAlterationObserver(path);
        monitor.addObserver(observer);
        observer.addListener(listener);
    }

    public void stop() throws Exception {
        monitor.stop();
    }

    public void start() throws Exception {
        monitor.start();
    }
}
