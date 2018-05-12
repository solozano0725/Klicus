package klicus.app.klicus.Promo;

/**
 * Created by Sol Mayra on 04/03/2018.
 */

public class Promo {

    private String DESCRIPTION;
    private String SUBTITLE;
    private String TITLE;
    private String IMAGE_URL;

    public Promo(String DESCRIPTION, String SUBTITLE, String TITLE, String IMAGE_URL) {
        this.DESCRIPTION = DESCRIPTION;
        this.SUBTITLE = SUBTITLE;
        this.TITLE = TITLE;
        this.IMAGE_URL = IMAGE_URL;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getSUBTITLE() {
        return SUBTITLE;
    }

    public void setSUBTITLE(String SUBTITLE) {
        this.SUBTITLE = SUBTITLE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getIMAGE_URL() {
        return IMAGE_URL;
    }

    public void setIMAGE_URL(String IMAGE_URL) {
        this.IMAGE_URL = IMAGE_URL;
    }
}
