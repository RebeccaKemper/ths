package de.turnierhundesport.entity;

import java.io.Serializable;
import java.util.List;
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

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the Teilnehmer database table.
 * 
 */
@NamedQueries({
	@NamedQuery(name = "Teilnehmer.findWithHundMitglied", query = "SELECT t FROM Teilnehmer t "+
			"JOIN t.hund h " +
			"JOIN t.mitglied m " +
			"WHERE h.hId =  :hId "+
			"AND m.mId = :mId"),
			
})
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Teilnehmer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="T_ID")
	private int tId;

	//bi-directional many-to-one association to Hund
    @ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="H_ID")
	private Hund hund;

	//bi-directional many-to-one association to Mitglied
    @ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="M_ID")
	private Mitglied mitglied;

	//bi-directional many-to-one association to Teilnehmer_nimmt_an_Disziplin_teil
	@OneToMany(mappedBy="teilnehmer",cascade=CascadeType.MERGE)
	@XmlInverseReference(mappedBy="teilnehmer")
	private List<Teilnehmer_nimmt_an_Disziplin_teil> teilnehmerNimmtAnDisziplinTeils;

	//bi-directional many-to-one association to Meldestatus_Verein
		@OneToMany(mappedBy="teilnehmer")
		private Set<Meldestatus_Teilnehmer> meldestatusTeilnehmers;
		
    public Teilnehmer() {
    }

	public int getTId() {
		return this.tId;
	}

	public void setTId(int tId) {
		this.tId = tId;
	}

	public Hund getHund() {
		return this.hund;
	}

	public void setHund(Hund hund) {
		this.hund = hund;
	}
	
	public Mitglied getMitglied() {
		return this.mitglied;
	}

	public void setMitglied(Mitglied mitglied) {
		this.mitglied = mitglied;
	}
	
	public List<Teilnehmer_nimmt_an_Disziplin_teil> getTeilnehmerNimmtAnDisziplinTeils() {
		return this.teilnehmerNimmtAnDisziplinTeils;
	}

	public void setTeilnehmerNimmtAnDisziplinTeils(List<Teilnehmer_nimmt_an_Disziplin_teil> teilnehmerNimmtAnDisziplinTeils) {
		this.teilnehmerNimmtAnDisziplinTeils = teilnehmerNimmtAnDisziplinTeils;
	}
	public void addTeilnehmerNimmtAnDisziplinTeil(Teilnehmer_nimmt_an_Disziplin_teil t){
		if (t == null) 
			throw new IllegalArgumentException();
      if (t.getTeilnehmer() != null)
           t.getTeilnehmer().getTeilnehmerNimmtAnDisziplinTeils().remove(t);
      
      this.getTeilnehmerNimmtAnDisziplinTeils().add(t);
      t.setTeilnehmer(this);
	}

	public Set<Meldestatus_Teilnehmer> getMeldestatusTeilnehmers() {
		return meldestatusTeilnehmers;
	}

	public void setMeldestatusTeilnehmers(
			Set<Meldestatus_Teilnehmer> meldestatusTeilnehmers) {
		this.meldestatusTeilnehmers = meldestatusTeilnehmers;
	}

	public void removeTeilnehmerNimmtAnDisziplinTeil(Teilnehmer_nimmt_an_Disziplin_teil t) {
		if (t == null) 
			throw new IllegalArgumentException();
      if (t.getTeilnehmer() != null)
           t.getTeilnehmer().getTeilnehmerNimmtAnDisziplinTeils().remove(t);
      
      this.getTeilnehmerNimmtAnDisziplinTeils().remove(t);
		t.setTeilnehmer(null);
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof Teilnehmer){
			if(((Teilnehmer) o).getTId() == this.getTId()){
				return true;
			}
		}
		return false;
		
	}
}