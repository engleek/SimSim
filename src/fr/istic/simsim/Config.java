package fr.istic.simsim;

import java.security.Timestamp;
import java.util.Date;

public class Config {
    public static String serviceName = "SimSimService";

    public static String multicastHost = "236.1.1.10";
    public static String rmiHost       = "localhost";

    public static int multicastPort = 2030;
    public static int rmiPort       = 2020;

    public static void log(String source, String message) {
        System.out.println(String.format("%1$#" + 20 + "s", source) + " > " + message);
    }
}
