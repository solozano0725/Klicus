package klicus.app.klicus.commons;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CommonConstants {

    public static FirebaseDatabase db  = FirebaseDatabase.getInstance("https://klicus-4b8a7.firebaseio.com");

    public static String[]  arrayAds = new String[]{"NAME", "PRO", "DESCRIPTION", "DIRE", "DOMICILIO", "EMAIL", "WEB", "FB", "IG", "TIME", "TEL","CEL", "IMAGE_URL", "GALLERY"};

    public static String[] arrayEvent = new String[]{"TITLE", "SUBTITLE", "DATE", "TIME", "PLACE", "CONTACT", "IMAGE", "GALLERY"};

    public static String[] arrayJob = new String[]{"TITLE", "SUBTITLE", "DESCRIPTION", "PLACE", "TEL", "EMAIL", "IMAGE"};

    public static String[] arrayNews = new String[]{"DATE", "DESCRIPTION", "GALLERY", "ID_LOCATION", "IMAGE", "SUBTITLE", "TITLE"};

    public static String[] arrayPromo = new String[]{"DESCRIPTION", "SUBTITLE", "TITLE", "IMAGE_URL"};

    public static String [] arrayClasif = new String[]{"TITLE", "SUBTITLE", "PLACE", "DESCRIPTION", "DATE", "IMAGE", "GALLERY", "CONTACT", "ID_LOCATION"};

    public static String[] arrayDBCategory = new String[]{"ADS", "CLASI", "EVENT", "JOB", "NOTICIAS", "PROMO"};

    public static String[] arrayDBLocations = new String[]{"LOCATION_ADS", "CATEGORY_ADS"};

}
