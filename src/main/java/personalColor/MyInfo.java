package personalColor;

import java.net.Socket;

public class MyInfo
{
    public static boolean socketConnect = false;
    public static Socket socket = null;

    public static void setSocketConnect(boolean state)
    {
        socketConnect = state;
    }

    public static void setSocket(Socket s)
    {
        socket = s;
    }
}
