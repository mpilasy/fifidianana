package org.njarasoa.fifidianana.util;

import org.njarasoa.fifidianana.ValimpifidiananaProcessor;

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
import java.util.HashMap;
import java.util.List;

public class RemoteHelper {
    private static final String CENI_URL = "https://presidentielle.ceni-madagascar.mg";
    public static final String FIVONDRONANA_URL = CENI_URL + "/district/chargedistrict/";
    public static final String FIRAISANA_URL = CENI_URL + "/commune/chargecommune/";
    public static final String FOKONTANY_URL = CENI_URL + "/fokontany/chargefokontany/";
    public static final String TOERAMPIFIDIANANA_URL = CENI_URL + "/cv/chargecv/";
    public static final String EFITRA_FIFIDIANANA_URL = CENI_URL + "/bv/chargebv/";

    public static List<String> getURL(String _url) {
        long start = System.currentTimeMillis();
        someMethod();

        List<String> strs = new ArrayList<>();
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
            String msg = e.getMessage();
            System.out.println(_url + " caused [" + e.getMessage() + "]");
            long count = 0;
            if (errors.containsKey(msg)) {
                count = errors.get(msg);
            }
            errors.put(msg, ++count);
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
        long duration = end - start;
        totalIOTime += duration;
        ValimpifidiananaProcessor.timeLogs.add("It took " + duration + "ms to retrieve [" + _url + "]");
        return strs;
    }

    public static long totalIOTime = 0;
    public static HashMap<String, Long> errors = new HashMap<>();

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
}
