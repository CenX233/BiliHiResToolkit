package ffmpeg;

import logging.LoggerBuilder;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketProgressListener extends Thread {
    private static final Logger log = LoggerBuilder.getLogger("SocketServer");
    private ProgressHandler handler;
    private boolean destroyFlag = true;
    private String url;

    public ProgressHandler getHandler() {
        return handler;
    }

    public void setHandler(ProgressHandler handler) {
        this.handler = handler;
    }

    public void destroy() {
        this.destroyFlag = true;
    }

    /**
     * 获取Socket server监听的url
     * @return 如果Socket server未启动则返回null
     */
    public String getUrl() {
        if (destroyFlag) {
            return null;
        }
        return url;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(0, 0, InetAddress.getByName("localhost"));
            String host = server.getLocalSocketAddress().toString();
            this.url =
                    "tcp:/" +
                    host.substring(
                            host.indexOf('/'),
                            host.indexOf(':')
                    ) + ":" +
                    server.getLocalPort();
            log.info("在 " + url + " 上启动Socket server.");
            destroyFlag = false;
            synchronized (this) {
                this.notifyAll();
            }

            while (!destroyFlag) {
                Socket socket = server.accept();
                log.info("收到来自 " + socket.getRemoteSocketAddress().toString() + " 的连接.");
                while (!socket.isInputShutdown()) {
                    byte[] bytes = new byte[socket.getInputStream().available()];
                    int nByte = socket.getInputStream().read(bytes);
                    String msg = new String(bytes);

//                    log.info(String.valueOf(nByte));
                    if (nByte > 0) {
                        String[] items = msg.split("\n");
                        HashMap<String, String> progress = new HashMap<>();
                        for (String item : items) {
                            String[] kv = item.split("=");
                            progress.put(kv[0], kv[1]);
                        }
                        if (handler != null) {
                            handler.update(progress);
                        }
                        progress.clear();
                    }

                    if (msg.contains("progress=end") || destroyFlag) {
                        break;
                    }
                    Thread.sleep(250);
                }
                log.info("连接中断.");
            }
            server.close();
            log.info("Socket server已停止.");
        } catch (Exception e) {
            log.log(Level.SEVERE, "内部Socket server异常,进度监听将被停用.", e);
        } finally {
            synchronized (this) {
                this.notifyAll();
            }
        }
    }
}
