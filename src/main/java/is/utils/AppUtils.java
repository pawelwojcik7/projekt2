package is.utils;

import java.time.format.DateTimeFormatter;

public class AppUtils {
    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss");
    public final static String TXT_FILE_NAME = "katalog.txt";
    public final static String DATA_NOT_LOADED = "Dane nie zostały wczytane";
    public static final String XML_FILE_NAME = "katalog.xml";
}
