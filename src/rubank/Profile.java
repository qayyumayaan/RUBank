package rubank;

public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;

    /**
     *
     * @param fname
     * @param lname
     * @param dob
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }



    /**
     * Getter for Fname
     * @return
     */
    public String getFname() {
        return fname;
    }

    /**
     * Setter for Fname
     * @param fname
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * getter for Lname
     * @return
     */
    public String getLname() {
        return lname;
    }

    /**
     * setter for lname
     * @param lname
     */
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


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Profile profile = (Profile) obj;

        return this.fname.equals(profile.fname) &&
                this.lname.equals(profile.lname) &&
                this.dob.equals(profile.dob);
    }


}
