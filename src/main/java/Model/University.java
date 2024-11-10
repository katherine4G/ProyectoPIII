package Model;

/**
 *
 * @author kathe
 */
public class University {
    protected int universityId;
    protected String universityName;
    protected String uniCountry;
    protected String uniSede;
    protected String uniAdress;

    public University(int universityId, String universityName, String uniCountry, String uniSede, String uniAdress) {
        this.universityId = universityId;
        this.universityName = universityName;
        this.uniCountry = uniCountry;
        this.uniSede = uniSede;
        this.uniAdress = uniAdress;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniCountry() {
        return uniCountry;
    }

    public void setUniCountry(String uniCountry) {
        this.uniCountry = uniCountry;
    }

    public String getUniSede() {
        return uniSede;
    }

    public void setUniSede(String uniSede) {
        this.uniSede = uniSede;
    }

    public String getUniAdress() {
        return uniAdress;
    }

    public void setUniAdress(String uniAdress) {
        this.uniAdress = uniAdress;
    }
    
}
