package org.adoptopenjdk.jcountdown.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * This entity represents the data used to generate the world map showing the
 * global adoption of Java 7.
 *
 * @author AdoptOpenJDK
 */
@Entity(value = "jdkadoption", noClassnameStored = true)
public class CountryAdoption {

    private static final int VERSION_SEVEN = 7;
    private static final int VERSION_EIGHT = 8;

    @Id
    private ObjectId id;
    private String country;
    private int version;
    private int total;
    private int percentage;

    /**
     * Updates the totals for this country and calculates the percentage.
     * <p>
     * TODO I think we want a map of versions to totals?
     *
     * @param visit
     */
    public void updateTotals(Visit visit) {
        setTotal(getTotal() + 1);

        if (visit.getVersion() == VERSION_SEVEN || visit.getVersion() == VERSION_EIGHT) {
            setVersion(getVersion() + 1);
        }

        this.setPercentage(Math.round(((float) getVersion() / getTotal()) * 100));
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + percentage;
        result = prime * result + total;
        result = prime * result + version;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CountryAdoption other = (CountryAdoption) obj;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (percentage != other.percentage)
            return false;
        if (total != other.total)
            return false;
        if (version != other.version)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "JdkAdoption [country=" + country + ", version=" + version + ", total=" + total + ", percentage="
                + percentage + "]";
    }

}
