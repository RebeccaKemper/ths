package de.turnierhundesport.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the Mitglied database table.
 * 
 */
@NamedQueries({
	@NamedQuery(name = "Mitglied.findAll", query = "SELECT m from Mitglied m"),
	@NamedQuery(name="Mitglied.findWithNameVornameNr",
	query="SELECT e " +
			"FROM Mitglied e JOIN e.person p " +
			"WHERE p.p_Name =  :name "+
			"AND p.pVorname = :vorname "+
			"AND e.m_Nummer = :nr")
})
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Mitglied implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="M_ID")
	@XmlElement
	private int mId;

	@XmlElement
	private int m_Nummer;

	//bi-directional many-to-one association to Person
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="P_ID")
	private Person person;

	//bi-directional many-to-one association to Verein
	@ManyToOne
	@JoinColumn(name="V_ID")

	private Verein verein;

	//bi-directional many-to-one association to Teilnehmer
	@OneToMany(mappedBy="mitglied")
	@XmlInverseReference(mappedBy="mitglied")
	private Set<Teilnehmer> teilnehmers;

	//bi-directional many-to-one association to Hund
	@OneToMany(mappedBy="mitglied")

	private Set<Hund> hunds;

	public Mitglied() {
	}

	public int getMId() {
		return this.mId;
	}

	public void setMId(int mId) {
		this.mId = mId;
	}

	public int getM_Nummer() {
		return this.m_Nummer;
	}

	public void setM_Nummer(int m_Nummer) {
		this.m_Nummer = m_Nummer;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Verein getVerein() {
		return this.verein;
	}

	public void setVerein(Verein verein) {
		this.verein = verein;
	}

	public Set<Teilnehmer> getTeilnehmers() {
		return this.teilnehmers;
	}

	public void setTeilnehmers(Set<Teilnehmer> teilnehmers) {
		this.teilnehmers = teilnehmers;
	}

	public Set<Hund> getHunds() {
		return hunds;
	}

	public void setHunds(Set<Hund> hunds) {
		this.hunds = hunds;
	}


}