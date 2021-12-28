package Utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketUtils {
    /**
     * 入口
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // 为了简单起见，所有的异常信息都往外抛
        int port = 9876;
        // 定义一个ServiceSocket监听在端口8899上
        ServerSocket server = new ServerSocket(port);
        System.out.println("等待与客户端建立连接...");
        while (true) {
            // server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
            Socket socket = server.accept();
            /**
             * 我们的服务端处理客户端的连接请求是同步进行的， 每次接收到来自客户端的连接请求后，
             * 都要先跟当前的客户端通信完之后才能再处理下一个连接请求。 这在并发比较多的情况下会严重影响程序的性能，
             * 为此，我们可以把它改为如下这种异步处理与客户端通信的方式
             */
            // 每接收到一个Socket就建立一个新的线程来处理它
            new Thread(new Task(socket)).start();

        }
        // server.close();
    }


    /**
     * 处理Socket请求的线程类
     */
    static class Task implements Runnable {

        private static Socket socket;

        /**
         * 构造函数
         */
        public Task(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                handlerSocket();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /**
         * 跟客户端Socket进行通信
         *
         * @throws IOException
         */
        private void handlerSocket() throws Exception {
            // 跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了
            /**
             * 在从Socket的InputStream中接收数据时，像上面那样一点点的读就太复杂了，
             * 有时候我们就会换成使用BufferedReader来一次读一行
             *
             * BufferedReader的readLine方法是一次读一行的，这个方法是阻塞的，直到它读到了一行数据为止程序才会继续往下执行，
             * 那么readLine什么时候才会读到一行呢？直到程序遇到了换行符或者是对应流的结束符readLine方法才会认为读到了一行，
             * 才会结束其阻塞，让程序继续往下执行。
             * 所以我们在使用BufferedReader的readLine读取数据的时候一定要记得在对应的输出流里面一定要写入换行符（
             * 流结束之后会自动标记为结束，readLine可以识别），写入换行符之后一定记得如果输出流不是马上关闭的情况下记得flush一下，
             * 这样数据才会真正的从缓冲区里面写入。
             */
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String temp;
            int index;
            while ((temp = br.readLine()) != null) {
                if ((index = temp.indexOf("eof")) != -1) { // 遇到eof时就结束接收
                    sb.append(temp.substring(0, index));
                    break;
                }
                sb.append(temp);
            }
            String xml=sb.toString();
            System.out.println("Form Cliect[port:" + socket.getPort()
                    + "] 消息内容:" + sb.toString());
            /**
             * 在这里去读取客户端发过来的xml文件,也就是我们之前所写的一个XmlReadType的方法
             */
            XmlReadType.readTypeForServer(xml);
//            // 回应一下客户端
//            Writer writer = new OutputStreamWriter(socket.getOutputStream(),
//                    "UTF-8");
//            writer.write(String.format("Hi,%d.天朗气清，惠风和畅！", socket.getPort()));
//            writer.flush();
//            writer.close();
//            System.out.println(
//                    "To Cliect[port:" + socket.getPort() + "] 回复客户端的消息发送成功");
//
//            br.close();
//            socket.close();
        }
        public static void sendToClientXml(String xml){
            System.out.println(xml);
            Writer writer = null;
            try {
                writer = new OutputStreamWriter(socket.getOutputStream(),
                        "UTF-8");
                writer.write(xml);
                writer.flush();
                writer.close();


                //br.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
