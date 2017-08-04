package de.turnierhundesport.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the Richter database table.
 * 
 */
@NamedQueries({
	@NamedQuery(name = "Richter.findAll", query = "SELECT r from Richter r"),
	@NamedQuery(name="Richter.findWithNameVorname",
		query="SELECT e " +
      "FROM Richter e JOIN e.person p " +
      "WHERE p.p_Name =  :name "+
      "AND p.pVorname = :vorname")
})
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Richter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @XmlID
	@Column(name="R_ID")
	private int rId;

	//bi-directional many-to-many association to Richter
    @ManyToMany
    @XmlInverseReference(mappedBy="richters")
	private List<Pruefung> pruefungs;

	//bi-directional many-to-one association to Person
    @ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="P_ID")
	private Person person;

  //bi-directional many-to-one association to Angebot
  	@OneToMany(mappedBy="richter")
    @XmlInverseReference(mappedBy="richter")
  	@XmlElement
  	private List<Angebot> angebots;
  	
    public Richter() {
    }

	public int getRId() {
		return this.rId;
	}

	public void setRId(int rId) {
		this.rId = rId;
	}
	
	public List<Pruefung> getPruefungs() {
		return pruefungs;
	}

	public void setPruefungs(List<Pruefung> pruefungs) {
		this.pruefungs = pruefungs;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Angebot> getAngebots() {
		return angebots;
	}

	public void setAngebots(List<Angebot> angebots) {
		this.angebots = angebots;
	}
	public String toString(){
		return person.toString();
	}

	public void addAngebot(Angebot a) {
		if (a == null) 
			throw new IllegalArgumentException();
		if (a.getRichter() != null)
			a.getRichter().getAngebots().remove(a);

		this.getAngebots().add(a);
		a.setRichter(this);	
		
	}
	public void removeAngebot(Angebot a) {
		if (a == null) 
			throw new IllegalArgumentException();
		if (a.getRichter() != null)
			a.getRichter().getAngebots().remove(a);

		this.getAngebots().remove(a);
		a.setRichter(null);	
		
	}
	
}