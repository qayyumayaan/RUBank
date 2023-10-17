package rubank;

public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Constructs a profile object.
     * @param fname
     * @param lname
     * @param dob
     * @author Ayaan Qayyum
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }



    /**
     * Getter for Fname
     * @author Ayaan Qayyum
     * @return first name
     */
    public String getFname() {
        return fname;
    }

    /**
     * Setter for Fname
     * @author Ayaan Qayyum
     * @param fname first name
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * Getter for last name.
     * @author Ayaan Qayyum
     * @return last name.
     */
    public String getLname() {
        return lname;
    }

    /**
     * Setter for last name.
     * @param lname
     * @author Ayaan Qayyum
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * Getter for dob
     * @author Ayaan Qayyum
     * @return Date of birth object in Date class format.
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Setter for dob
     * @param dob Date of birth object in Date class format.
     * @author Ayaan Qayyum
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }


    /**
     * Compares two profiles.
     * @param other the object to be compared.
     * @return if other is the same
     * @author Ayaan Qayyum
     */
    @Override
    public int compareTo(Profile other) {
        int lnameComparison = this.lname.toLowerCase().compareTo(other.lname.toLowerCase());
        if (lnameComparison != 0) {
            return lnameComparison;
        }

        int fnameComparison = this.fname.toLowerCase().compareTo(other.fname.toLowerCase());
        if (fnameComparison != 0) {
            return fnameComparison;
        }

        return this.dob.compareTo(other.dob);
    }

    /**
     * Method determines if obj is equal to object it is being compared to
     * @param obj
     * @return if obj is equal
     * @author Ayaan Qayuum
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Profile profile = (Profile) obj;

        boolean fnameCheck = this.fname.toLowerCase().equals(profile.fname.toLowerCase());
        boolean lnameCheck = this.lname.toLowerCase().equals(profile.lname.toLowerCase());
        boolean dobCheck = this.dob.equals(profile.dob);

        return fnameCheck && lnameCheck && dobCheck;
    }


}
