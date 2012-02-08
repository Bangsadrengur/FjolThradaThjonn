import java.net.*;
import java.io.*;
import java.util.Random;
class Connection extends Thread
{
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public void run()
    {
        try 
        {
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            handleRequest(); 
        } catch (IOException e) { System.out.println("Connection:" + e.getMessage()); }
    }

    public Connection(Socket aClientSocket) {
        clientSocket = aClientSocket;
    }

    public void handleRequest() 
    {
        try { 
            for(int i=1; i!=10000000; i++)
            {
                IsPrime(i);
            }
            String data = in.readUTF(); // read a line of data from the stream
            out.writeUTF(data);
        } catch (EOFException e) { System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) { System.out.println("readline:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {/* close failed */
            }
        }
    }

    public boolean IsPrime(int n) 
    {
        if(n<=1) {return false;}
        if (n==2) {return false;}
        if (n%2==0) {return false;}
        int m = (int)Math.round(Math.sqrt(n));
        for(int i= 3; i<=m; i+=2)
        {
            if(n%i==0)
            {
                return false;
            }
        }
        return true;
    }
}
