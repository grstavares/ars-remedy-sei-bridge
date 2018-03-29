package br.gov.mec.bridge;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

public class StaticUtils {

    public static HashMap<String,String> parseArgs(String[] args) {

        HashMap<String, String> options = new HashMap<String, String>();

        for (int i = 0; i < args.length; i++) {

            switch (args[i].charAt(0)) {

                case '-':
                    if (args[i].length() < 2) {throw new IllegalArgumentException("Argumento Inválido: " +args[i]);}
                    if (args[i].charAt(1) == '-') {if (args[i].length() < 3) {throw new IllegalArgumentException("Argumento Inválido: " + args[i]);}
                    } else {

                        if (args.length -1 == i) {throw new IllegalArgumentException("valor esperado após: " + args[i]);}
                        String key = args[i].replace("-", "");
                        options.put(key, args[i+1]);
                        i++;
                    }

                    break;

                default:
                    break;
            }
        }

        return options;

    }

    public static HashMap<String, String> loadCredentials(String filename) {

        HashMap<String, String> options = new HashMap<String, String>();

        if (!filename.isEmpty()) {

            try {

            Properties properties = new Properties();
            InputStream fileinput = StaticUtils.class.getClassLoader().getResourceAsStream(filename);
            if (fileinput == null) {
                System.out.println("Arquivo de configurações não encontrado!");
                return options;
            }

            properties.load(fileinput);
            properties.stringPropertyNames().forEach(p -> options.put(p, properties.get(p).toString()));

            } catch (IOException e) {
                System.out.println("Arquivo de configurações não encontrado!");;
            }

        }

        return options;

    }

    public static String trim(String string){
        char[] space = " ".toCharArray();
        return trim(string, space[0]);
    }

    public static String trim(String string, char ch){
        return trim(string, ch, ch);
    }

    public static String trim(String string, char leadingChar, char trailingChar){
        return string.replaceAll("^["+leadingChar+"]+|["+trailingChar+"]+$", "");
    }

    public static String trim(String string, String regex){
        return trim(string, regex, regex);
    }

    public static String trim(String string, String leadingRegex, String trailingRegex){
        return string.replaceAll("^("+leadingRegex+")+|("+trailingRegex+")+$", "");
    }

}
