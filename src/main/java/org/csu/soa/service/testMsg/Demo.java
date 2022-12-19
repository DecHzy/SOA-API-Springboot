package org.csu.soa.service.testMsg;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Demo {
    public static void main(String[] args) throws IOException {
        try {
            URL url = new URL("https://api.smsbao.com/sms?u=zdllll&p=cd4a0a6044ab443cb7facf784876b4a8&m=18108450410&c=【短信宝】123123");
            InputStream in =url.openStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufr = new BufferedReader(isr);
            String str;
            while ((str = bufr.readLine()) != null) {
                System.out.println(str);
            }
            bufr.close();
            isr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
