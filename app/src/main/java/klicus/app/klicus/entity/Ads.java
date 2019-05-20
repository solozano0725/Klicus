package klicus.app.klicus.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sol Mayra on 04/03/2018.
 */

public class Ads implements Serializable {

    private String NAME;
    private String PROPIET;
    private String DESCRIPTION;
    private String DIRE;
    private String DOMICILIO;
    private String EMAIL;
    private String WEB;
    private String FB;
    private String IG;
    private String TIME;
    private String TEL;
    private String CEL;
    private String IMAGE_URL;
    private ArrayList<String> GALLERY;
    private int ID_LOCATION;

    public Ads(String NAME, String PROPIET, String DESCRIPTION, String DIRE, String DOMICILIO, String EMAIL, String WEB, String FB, String IG, String TIME, String TEL, String CEL, String IMAGE_URL, ArrayList<String> GALLERY, int ID_LOCATION) {
        this.NAME = NAME;
        this.PROPIET = PROPIET;
        this.DESCRIPTION = DESCRIPTION;
        this.DIRE = DIRE;
        this.DOMICILIO = DOMICILIO;
        this.EMAIL = EMAIL;
        this.WEB = WEB;
        this.FB = FB;
        this.IG = IG;
        this.TIME = TIME;
        this.TEL = TEL;
        this.CEL = CEL;
        this.IMAGE_URL = IMAGE_URL;
        this.GALLERY = GALLERY;
        this.ID_LOCATION = ID_LOCATION;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPROPIET() {
        return PROPIET;
    }

    public void setPROPIET(String PROPIET) {
        this.PROPIET = PROPIET;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getDIRE() {
        return DIRE;
    }

    public void setDIRE(String DIRE) {
        this.DIRE = DIRE;
    }

    public String getDOMICILIO() {
        return DOMICILIO;
    }

    public void setDOMICILIO(String DOMICILIO) {
        this.DOMICILIO = DOMICILIO;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getWEB() {
        return WEB;
    }

    public void setWEB(String WEB) {
        this.WEB = WEB;
    }

    public String getFB() {
        return FB;
    }

    public void setFB(String FB) {
        this.FB = FB;
    }

    public String getIG() {
        return IG;
    }

    public void setIG(String IG) {
        this.IG = IG;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getTEL() {
        return TEL;
    }

    public String getCEL() {
        return CEL;
    }

    public void setCEL(String CEL) {
        this.CEL = CEL;
    }

    public String getIMAGE_URL() {
        return IMAGE_URL;
    }

    public void setIMAGE_URL(String IMAGE_URL) {
        this.IMAGE_URL = IMAGE_URL;
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
}
