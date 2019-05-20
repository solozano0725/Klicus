package klicus.app.klicus.entity;

import java.io.Serializable;

public class Job implements Serializable {

    private String TITLE;
    private String SUBTITLE;
    private String DESCRIPTION;
    private String PLACE;
    private String TEL;
    private String EMAIL;
    private String IMAGE;

    public Job(String TITLE, String SUBTITLE, String DESCRIPTION, String PLACE, String TEL, String EMAIL, String IMAGE) {
        this.TITLE = TITLE;
        this.SUBTITLE = SUBTITLE;
        this.DESCRIPTION = DESCRIPTION;
        this.PLACE = PLACE;
        this.TEL = TEL;
        this.EMAIL = EMAIL;
        this.IMAGE = IMAGE;
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

    public String getPLACE() {
        return PLACE;
    }

    public void setPLACE(String PLACE) {
        this.PLACE = PLACE;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }
}
