package klicus.app.klicus.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class News  implements Serializable {

    private String DATE;
    private String DESCRIPTION;
    private ArrayList<String> GALLERY;
    private int ID_LOCATION;
    private String IMAGE;
    private String SUBTITLE;
    private String TITLE;

    public News() {

    }

    public News(String DATE, String DESCRIPTION, ArrayList<String> GALLERY, int ID_LOCATION, String IMAGE, String SUBTITLE, String TITLE) {
        this.DATE = DATE;
        this.DESCRIPTION = DESCRIPTION;
        this.GALLERY = GALLERY;
        this.ID_LOCATION = ID_LOCATION;
        this.IMAGE = IMAGE;
        this.SUBTITLE = SUBTITLE;
        this.TITLE = TITLE;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public ArrayList<String> getGALLERY() {
        return GALLERY;
    }

    public void setGALLERY(ArrayList<String> GALLERY) {
        this.GALLERY = GALLERY;
    }

    public int getID_LOCATION() {
        return ID_LOCATION;
    }

    public void setID_LOCATION(int ID_LOCATION) {
        this.ID_LOCATION = ID_LOCATION;
    }

    public String getSUBTITLE() {
        return SUBTITLE;
    }

    public void setSUBTITLE(String SUBTITLE) {
        this.SUBTITLE = SUBTITLE;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }
}
