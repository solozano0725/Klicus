package klicus.app.klicus.EventJobs;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable {

    private String TITLE;
    private String SUBTITLE;
    private String DATE;
    private String TIME;
    private String PLACE;
    private String CONTACT;
    private String IMAGE;
    private ArrayList<String> GALLERY;

    public Event(String TITLE, String SUBTITLE, String DATE, String TIME, String PLACE, String CONTACT, String IMAGE, ArrayList<String> GALLERY) {
        this.TITLE = TITLE;
        this.SUBTITLE = SUBTITLE;
        this.DATE = DATE;
        this.TIME = TIME;
        this.PLACE = PLACE;
        this.CONTACT = CONTACT;
        this.IMAGE = IMAGE;
        this.GALLERY = GALLERY;
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

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getPLACE() {
        return PLACE;
    }

    public void setPLACE(String PLACE) {
        this.PLACE = PLACE;
    }

    public String getCONTACT() {
        return CONTACT;
    }

    public void setCONTACT(String CONTACT) {
        this.CONTACT = CONTACT;
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
}
