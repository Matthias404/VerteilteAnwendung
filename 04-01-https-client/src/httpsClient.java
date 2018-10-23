import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.sun.tools.internal.ws.processor.model.Port;

public class httpsClient
{
    public static void main(String[] args)
    {
        get("https://www.bundestag.de/presse");
    }
    public static void get(String url)
    {
        try
        {
            URL u = new URL(url);
            u.getHost();
            int Port = u.getPort();
            u.getFile();
            
            if (u.getPort() == -1 )
            {
                Port = 443;
            }
            // Socket socket = new Socket(u.getHost(), Port);
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(u.getHost(), Port);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            if(socket.isConnected() == true)
            {
                writer.writer("GET" + u.getFile());
                writer.newLine();
                writer.writer("Host : " + u.getHost());
                writer.newLine();
                // ˜writer.flush();
                writer.writer();
                writer.flush();
            }


            System.out.println(u.getHost() + " " + Port + " " + u.getFile());
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
    }
}