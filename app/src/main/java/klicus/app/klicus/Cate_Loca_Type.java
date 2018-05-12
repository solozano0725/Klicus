package klicus.app.klicus;

/**
 * Created by Sol Mayra on 04/03/2018.
 */

public class Cate_Loca_Type {

    public int ID;
    public String NAME;

    public Cate_Loca_Type(int ID, String NAME) {
        this.ID = ID;
        this.NAME = NAME;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    @Override
    public String toString() {
        return NAME;
    }

    public int toInt(){
        return ID;
    }

}
