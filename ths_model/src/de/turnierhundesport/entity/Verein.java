package de.turnierhundesport.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.util.Set;


/**
 * The persistent class for the Verein database table.
 * 
 */
@NamedQueries({
	@NamedQuery(name = "Verein.findAll", query = "SELECT v from Verein v"),
	@NamedQuery(name="Verein.findWithName",
		query="SELECT e " +
      "FROM Verein e " +
      "WHERE e.vName = :name" )
})

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Verein implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="V_ID")
	private int vId;

	@Column(name="V_NAME")
	private String vName;

	//bi-directional many-to-one association to Mitglied
	@OneToMany(mappedBy="verein")
	@XmlInverseReference(mappedBy="verein")
	private Set<Mitglied> mitglieds;

	//bi-directional many-to-one association to Pruefung
	@OneToMany(mappedBy="verein")
    @XmlInverseReference(mappedBy="verein")
	private Set<Pruefung> pruefungs;

	//bi-directional many-to-one association to Verband
    @ManyToOne
	@JoinColumn(name="VB_ID")
	private Verband verband;

	//bi-directional many-to-one association to Meldestatus_Verein
	@OneToMany(mappedBy="verein")
	private Set<Meldestatus_Verein> meldestatusVereins;
    public Verein() {
    }

	public int getVId() {
		return this.vId;
	}

	public void setVId(int vId) {
		this.vId = vId;
	}

	public String getVName() {
		return this.vName;
	}

	public void setVName(String vName) {
		this.vName = vName;
	}

	public Set<Mitglied> getMitglieds() {
		return this.mitglieds;
	}

	public void setMitglieds(Set<Mitglied> mitglieds) {
		this.mitglieds = mitglieds;
	}
	
	public Set<Pruefung> getPruefungs() {
		return this.pruefungs;
	}

	public void setPruefungs(Set<Pruefung> pruefungs) {
		this.pruefungs = pruefungs;
	}
	
	public Verband getVerband() {
		return this.verband;
	}

	public void setVerband(Verband verband) {
		this.verband = verband;
	}

	public Set<Meldestatus_Verein> getMeldestatusVereins() {
		return meldestatusVereins;
	}

	public void setMeldestatusVereins(Set<Meldestatus_Verein> meldestatusVereins) {
		this.meldestatusVereins = meldestatusVereins;
	}
	
	
}