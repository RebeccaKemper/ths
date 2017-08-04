package de.turnierhundesport.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the Person database table.
 * 
 */
@NamedQueries({
	@NamedQuery(name = "Person.findAll", query = "SELECT p from Person p"),
	@NamedQuery(name="Person.findWithNameVorname",
		query="SELECT e " +
      "FROM Person e " +
      "WHERE e.p_Name =  :name "+
      "AND e.pVorname = :vorname")
})
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="P_ID")
	private int pId;

	@Column(name="P_Adresse")
	private String p_Adresse;

	@Column(name="P_Email")
	private String p_Email;

	@Column(name="P_GebDatum")
    @Temporal( TemporalType.DATE)
	private Date p_GebDatum;
	
	@Column(name="P_Geschlecht")
	private Boolean p_Geschlecht;

	@Column(name="P_Name")
	private String p_Name;

	@Column(name="P_Tel")
	private String p_Tel;

	@Column(name="P_VORNAME")
	private String pVorname;

	@OneToMany
	@XmlInverseReference(mappedBy="person")
	private List<Mitglied> mitglieds;
	
	@OneToMany
	@XmlTransient
	private List<Pruefung> pruefungs;
	
    public Person() {
    	p_Geschlecht = false;
    }

	public int getPId() {
		return this.pId;
	}

	public void setPId(int pId) {
		this.pId = pId;
	}

	public String getP_Adresse() {
		return this.p_Adresse;
	}

	public void setP_Adresse(String p_Adresse) {
		this.p_Adresse = p_Adresse;
	}

	public String getP_Email() {
		return this.p_Email;
	}

	public void setP_Email(String p_Email) {
		this.p_Email = p_Email;
	}

	public Date getP_GebDatum() {
		return this.p_GebDatum;
	}

	public void setP_GebDatum(Date p_GebDatum) {
		this.p_GebDatum = p_GebDatum;
	}

	public String getP_Name() {
		return this.p_Name;
	}

	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}

	public String getP_Tel() {
		return this.p_Tel;
	}

	public void setP_Tel(String p_Tel) {
		this.p_Tel = p_Tel;
	}

	public String getPVorname() {
		return this.pVorname;
	}

	public void setPVorname(String pVorname) {
		this.pVorname = pVorname;
	}

	public boolean isP_Geschlecht() {
		return p_Geschlecht;
	}

	public void setP_Geschlecht(boolean p_Geschlecht) {
		this.p_Geschlecht = p_Geschlecht;
	}
	public String toString(){
		return p_Name+", "+pVorname;
	}

	
	
}