package klicus.app.klicus.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Clasif implements Serializable {
    public String TITLE;
    public String SUBTITLE;
    public String PLACE;
    public String DESCRIPTION;
    public String DATE;
    public String IMAGE;
    public ArrayList<String> GALLERY;
    public String CONTACT;
    public String ID_LOCATION;

    public Clasif(String TITLE, String SUBTITLE, String PLACE, String DESCRIPTION, String DATE, String IMAGE, ArrayList<String> GALLERY, String CONTACT) {
        this.TITLE = TITLE;
        this.SUBTITLE = SUBTITLE;
        this.PLACE = PLACE;
        this.DESCRIPTION = DESCRIPTION;
        this.DATE = DATE;
        this.IMAGE = IMAGE;
        this.GALLERY = GALLERY;
        this.CONTACT = CONTACT;
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

    public String getPLACE() {
        return PLACE;
    }

    public void setPLACE(String PLACE) {
        this.PLACE = PLACE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    public ArrayList<String> getGALLERY() {
        return GALLERY;
    }

    public void setGALLERY(ArrayList<String> GALLERY) {
        this.GALLERY = GALLERY;
    }

    public String getCONTACT() {
        return CONTACT;
    }

    public void setCONTACT(String CONTACT) {
        this.CONTACT = CONTACT;
    }

    public String getID_LOCATION() {
        return ID_LOCATION;
    }

    public void setID_LOCATION(String ID_LOCATION) {
        this.ID_LOCATION = ID_LOCATION;
    }
}
