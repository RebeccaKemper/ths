package de.turnierhundesport.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Hund database table.
 * 
 */
@NamedQueries({
@NamedQuery(name = "Hund.findAll", query = "SELECT h from Hund h"),
@NamedQuery(name="Hund.findWithNummerName",
query="SELECT h " +
"FROM Hund h " +
"WHERE h.hName =  :name "+
"AND h.h_Chip = :chip")
})
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Hund implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="H_ID")
	private int hId;
	
	//bi-directional many-to-one association to Mitglied
    @ManyToOne
	@JoinColumn(name="M_ID")
	private Mitglied mitglied;
    
  //bi-directional many-to-one association to Teilnehmer
    @OneToMany(mappedBy="hund")
    @XmlInverseReference(mappedBy="hund")
	private List<Teilnehmer> teilnehmers;

    @XmlElement
	private String h_Chip;

    @XmlElement
	private String h_Farbe;
    
    @XmlElement
	private boolean h_Geschlecht;

    @XmlElement
    @Temporal( TemporalType.DATE)
	private Date h_Impfung;

    @XmlElement
	private String h_Lb;

	@Column(name="H_NAME")
	@XmlElement
	private String hName;

	@XmlElement
	private String h_Rasse;

	@XmlElement
    @Temporal( TemporalType.DATE)
	private Date h_Wurftag;

	@XmlElement
	private String h_Zb;

	@XmlElement
	private String h_Zwinger;

    public Hund() {
    }

	public int getHId() {
		return this.hId;
	}

	public void setHId(int hId) {
		this.hId = hId;
	}

	public String getH_Chip() {
		return this.h_Chip;
	}

	public void setH_Chip(String h_Chip) {
		this.h_Chip = h_Chip;
	}

	public String getH_Farbe() {
		return this.h_Farbe;
	}

	public void setH_Farbe(String h_Farbe) {
		this.h_Farbe = h_Farbe;
	}

	public boolean getH_Geschlecht() {
		return this.h_Geschlecht;
	}

	public void setH_Geschlecht(boolean h_Geschlecht) {
		this.h_Geschlecht = h_Geschlecht;
	}

	public Date getH_Impfung() {
		return this.h_Impfung;
	}

	public void setH_Impfung(Date h_Impfung) {
		this.h_Impfung = h_Impfung;
	}

	public String getH_Lb() {
		return this.h_Lb;
	}

	public void setH_Lb(String h_Lb) {
		this.h_Lb = h_Lb;
	}

	public String getHName() {
		return this.hName;
	}

	public void setHName(String hName) {
		this.hName = hName;
	}

	public String getH_Rasse() {
		return this.h_Rasse;
	}

	public void setH_Rasse(String h_Rasse) {
		this.h_Rasse = h_Rasse;
	}

	public Date getH_Wurftag() {
		return this.h_Wurftag;
	}

	public void setH_Wurftag(Date h_Wurftag) {
		this.h_Wurftag = h_Wurftag;
	}

	public String getH_Zb() {
		return this.h_Zb;
	}

	public void setH_Zb(String h_Zb) {
		this.h_Zb = h_Zb;
	}

	public String getH_Zwinger() {
		return this.h_Zwinger;
	}

	public void setH_Zwinger(String h_Zwinger) {
		this.h_Zwinger = h_Zwinger;
	}

	public Mitglied getMitglied() {
		return mitglied;
	}

	public void setMitglied(Mitglied mitglied) {
		this.mitglied = mitglied;
	}
	
	public List<Teilnehmer> getTeilnehmers() {
		return teilnehmers;
	}

	public void setTeilnehmers(List<Teilnehmer> teilnehmers) {
		this.teilnehmers = teilnehmers;
	}

	public String toString(){
		return this.hName;
	}

	
	
}