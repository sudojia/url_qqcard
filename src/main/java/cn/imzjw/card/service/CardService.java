package cn.imzjw.card.service;

import cn.imzjw.card.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author https://blog.imzjw.cn
 * @date 2021/8/10 20:23
 */
public class CardService {

    /**
     * 获取日志记录器对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CardService.class);

    /**
     * 这是啥？
     */
    private static final String URL = "https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshareget_urlinfo?url=";

    /**
     * 下载远程文件。
     *
     * @param remoteFileUrl 要下载的远程文件地址。
     * @param saveFileName  下载后保存的文件名。
     * @param saveDir       下载后保存的文件路径。
     * @return 文件保存的地址。
     */
    public static String downRemoteFile(String remoteFileUrl, String saveFileName, String saveDir) {
        HttpURLConnection conn = null;
        OutputStream oputstream = null;
        InputStream iputstream = null;
        try {
            // 创建保存文件的目录
            File savePath = new File(saveDir);
            // 创建保存的文件
            File file = new File(savePath + "/" + saveFileName);
            URL url = new URL(remoteFileUrl);
            // 将 url 以 open 方法返回的 urlConnection 连接强转为 HttpURLConnection 连接(标识一个 url 所引用的远程对象连接)
            // 此时 cnnection 只是为一个连接对象,待连接中
            conn = (HttpURLConnection) url.openConnection();
            // 设置是否要从 URL连接读取数据,默认为就 true
            conn.setDoInput(true);
            // 建立连接
            // (请求未开始,直到 connection.getInputStream() 方法调用时才发起,以上各个参数设置需在此方法之前进行)
            conn.connect();
            // 连接发起请求,处理服务器响应 (从连接获取到输入流)
            iputstream = conn.getInputStream();
            // 创建文件输出流，用于保存下载的远程文件
            oputstream = new FileOutputStream(file);
            // 用来存储响应数据
            byte[] buffer = new byte[4 * 1024];
            int byteRead = -1;
            // 循环读取流
            while ((byteRead = (iputstream.read(buffer))) != -1) {
                oputstream.write(buffer, 0, byteRead);
            }
            // 输出完成后刷新并关闭流
            oputstream.flush();
        } catch (Exception e) {
            LOGGER.info("文件下载失败 -> " + e);
        } finally {
            try {
                // 重要且易忽略步骤 (关闭流,切记!)
                if (iputstream != null) {
                    iputstream.close();
                }
                if (oputstream != null) {
                    oputstream.close();
                }
                // 销毁连接
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 返回保存后的文件路径
        return saveDir + "/" + saveFileName;
    }

    /**
     * 读取文件
     *
     * @param path 文件路径
     */
    public static void readFile(String path) {
        File file = new File(path);
        try {
            // 构造一个 BufferedReader 类来读取文件
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String s = null;
            // 使用 readLine 方法，一次读一行
            while ((s = br.readLine()) != null) {
                LOGGER.info(HttpUtils.sendGet(URL + s));
            }
            // 关流
            br.close();
        } catch (Exception e) {
            LOGGER.info("读取文件失败 -> ", e);
        } finally {
            // 请求完所有 URL 直接删除文件。不要问我为什么
            if (file.delete()) {
                LOGGER.info(file.getName() + " 文件已删除！");
            } else {
                LOGGER.info("文件删除失败！");
            }
        }
    }
}
