package org.njarasoa.fifidianana;

import org.njarasoa.fifidianana.model.Faritra;
import org.njarasoa.fifidianana.model.Fokontany;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        List<Faritra> faritraList = Faritra.AnaranaFaritraList.parallelStream().map(i -> new Faritra(i)).collect(Collectors.toList());

        
        long end = System.currentTimeMillis();
        System.out.println("It took " + (end - start) + "ms to run he whole thing.");
        System.out.println("We made " + timeLogs.size() + " calls to the server.");

    }

    public static List<String> getURL(String _url) {
        long start = System.currentTimeMillis();
        someMethod();

        List<String> strs = new ArrayList<String>();
        InputStream is = null;

        try {
            URL url = new URL(_url);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            is = conn.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("onclick")) {
                    line = line.trim();
                    int pos0 = line.indexOf(">", 3) + 1;
                    int pos1 = line.indexOf("<", 3);
                    String word = line.substring(pos0, pos1);
                    // System.out.println(word);
                    strs.add(word);
                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        timeLogs.add("It took " + (end - start) + "ms to retrieve [" + _url + "]");
        return strs;
    }

    // private static List<Fokontany> fokontanyList = new ArrayList<>();
    private static List<String> timeLogs = new ArrayList<String>();
    private static void someMethod() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final String SEPARATOR = "-";
}
