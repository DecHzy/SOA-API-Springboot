package org.csu.soa.service.ip;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class findIP {
    public static String findIp() {
        StringBuilder str1 = new StringBuilder();
        try {
            URL url = new URL("http://ip.42.pl/raw");
            InputStream in =url.openStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufr = new BufferedReader(isr);
            String str;
            while ((str = bufr.readLine()) != null) {
//                System.out.println(str);
                str1.append(str);
            }
            bufr.close();
            isr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str1.toString();
    }
}
