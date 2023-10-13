package rubank;

public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;


    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    // Getter for fname
    public String getFname() {
        return fname;
    }

    // Setter for fname
    public void setFname(String fname) {
        this.fname = fname;
    }

    // Getter for lname
    public String getLname() {
        return lname;
    }

    // Setter for lname
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * Getter for dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Setter for dob
     * @param dob
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }


    /**
     * Compares two profiles.
     * @param other the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Profile other) {
        int lnameComparison = this.lname.compareTo(other.lname);
        if (lnameComparison != 0) {
            return lnameComparison;
        }

        int fnameComparison = this.fname.compareTo(other.fname);
        if (fnameComparison != 0) {
            return fnameComparison;
        }

        return this.dob.compareTo(other.dob);
    }
}
