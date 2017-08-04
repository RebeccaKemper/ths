package de.turnierhundesport.entity;

import java.io.Serializable;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.util.Set;


/**
 * The persistent class for the ALTERSKLASSE database table.
 * 
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Altersklasse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AK_ID")
	private int akId;

	@Column(name="AK_NAME")
	private String akName;

	//bi-directional many-to-one association to Teilnehmer_nimmt_an_Disziplin_teil
	@OneToMany(mappedBy="altersklasse",cascade = CascadeType.ALL)
	@XmlInverseReference(mappedBy="altersklasse")
	private Set<Teilnehmer_nimmt_an_Disziplin_teil> teilnehmerNimmtAnDisziplinTeils;

	public enum AltersklasseType{
		AK14(1),AK15(2), AK19(3), AK35(4), AK50(5),AK61(6);
		private int id;
		AltersklasseType(int id){
			this.id = id;
		}
		public int getId(){
			return id;
		}
	}
	
    public Altersklasse() {
    }

	public int getAkId() {
		return this.akId;
	}

	public void setAkId(int akId) {
		this.akId = akId;
	}

	public String getAkName() {
		return this.akName;
	}

	public void setAkName(String akName) {
		this.akName = akName;
	}

	public Set<Teilnehmer_nimmt_an_Disziplin_teil> getTeilnehmerNimmtAnDisziplinTeils() {
		return this.teilnehmerNimmtAnDisziplinTeils;
	}

	public void setTeilnehmerNimmtAnDisziplinTeils(Set<Teilnehmer_nimmt_an_Disziplin_teil> teilnehmerNimmtAnDisziplinTeils) {
		this.teilnehmerNimmtAnDisziplinTeils = teilnehmerNimmtAnDisziplinTeils;
	}
	public void addTeilnehmerNimmtAnDisziplinTeil(Teilnehmer_nimmt_an_Disziplin_teil t){
		if (t == null) 
			throw new IllegalArgumentException();
      if (t.getAltersklasse() != null)
           t.getAltersklasse().getTeilnehmerNimmtAnDisziplinTeils().remove(t);
      
      this.getTeilnehmerNimmtAnDisziplinTeils().add(t);
      t.setAltersklasse(this);
	}

	public void removeTeilnehmerNimmtAnDisziplinTeil(
			Teilnehmer_nimmt_an_Disziplin_teil t) {
		if (t == null) 
			throw new IllegalArgumentException();
      if (t.getAltersklasse() != null)
           t.getAltersklasse().getTeilnehmerNimmtAnDisziplinTeils().remove(t);
      
      this.getTeilnehmerNimmtAnDisziplinTeils().remove(t);
      t.setAltersklasse(null);
	}
}