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
    private final ProgressHandler handler;

    public SocketProgressListener(ProgressHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(0, 0, InetAddress.getByName("localhost"));
            log.info("在 " + server.getLocalSocketAddress() + " 上启动Socket server.");
            while (true) {
                Socket socket = server.accept();
                while (true) {
                    byte[] bytes = new byte[socket.getInputStream().available()];
                    socket.getInputStream().read(bytes);
                    String msg = new String(bytes);
                    if (bytes.length != 0) {
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
                    if (msg.contains("progress=end")) {
                        break;
                    }
                    Thread.sleep(250);
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "内部Socket server异常,进度监听将被停用.", e);
        }
    }
}
