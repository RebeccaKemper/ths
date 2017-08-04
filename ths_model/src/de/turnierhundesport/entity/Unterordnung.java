package de.turnierhundesport.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.util.List;


/**
 * The persistent class for the Unterordnung database table.
 * 
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Unterordnung implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="UO_ID")
	private int uoId;

	@Column(name="UEB_1")
	private int ueb1;

	@Column(name="UEB_2")
	private int ueb2;

	@Column(name="UEB_3")
	private int ueb3;

	@Column(name="UEB_4")
	private int ueb4;

	private int UEB_Gesamt;

	//bi-directional many-to-one association to Teilnehmer_nimmt_an_Disziplin_teil
	@OneToMany(mappedBy="unterordnung")
	@XmlInverseReference(mappedBy="unterordnung")
	private List<Teilnehmer_nimmt_an_Disziplin_teil> teilnehmerNimmtAnDisziplinTeils;

    public Unterordnung() {
    }

	public int getUoId() {
		return this.uoId;
	}

	public void setUoId(int uoId) {
		this.uoId = uoId;
	}

	public int getUeb1() {
		return this.ueb1;
	}

	public void setUeb1(int ueb1) {
		this.ueb1 = ueb1;
	}

	public int getUeb2() {
		return this.ueb2;
	}

	public void setUeb2(int ueb2) {
		this.ueb2 = ueb2;
	}

	public int getUeb3() {
		return this.ueb3;
	}

	public void setUeb3(int ueb3) {
		this.ueb3 = ueb3;
	}

	public int getUeb4() {
		return this.ueb4;
	}

	public void setUeb4(int ueb4) {
		this.ueb4 = ueb4;
	}

	public int getUEB_Gesamt() {
		return this.UEB_Gesamt;
	}

	public void setUEB_Gesamt(int UEB_Gesamt) {
		this.UEB_Gesamt = UEB_Gesamt;
	}

	public List<Teilnehmer_nimmt_an_Disziplin_teil> getTeilnehmerNimmtAnDisziplinTeils() {
		return this.teilnehmerNimmtAnDisziplinTeils;
	}

	public void setTeilnehmerNimmtAnDisziplinTeils(List<Teilnehmer_nimmt_an_Disziplin_teil> teilnehmerNimmtAnDisziplinTeils) {
		this.teilnehmerNimmtAnDisziplinTeils = teilnehmerNimmtAnDisziplinTeils;
	}
	
}