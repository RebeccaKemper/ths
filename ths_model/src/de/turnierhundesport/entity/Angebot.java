package de.turnierhundesport.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
 * The persistent class for the ANGEBOT database table.
 * 
 */
@NamedQueries({
	@NamedQuery(name = "Angebot.findWithPruefung", query = "SELECT a from Angebot a JOIN a.pruefung p WHERE p.prId = :pId"),
	@NamedQuery(name = "Angebot.findWithPruefungAndDisziplin", query = "SELECT a from Angebot a "+
			"JOIN a.pruefung p "+
			"JOIN a.disziplin d "+
			"WHERE p.prId = :pId "+	
			"AND d.dId = :dId"),

})
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Angebot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="A_ID")
	private int aId;

	//bi-directional many-to-one association to Disziplin
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="D_ID")
	private Disziplin disziplin;

	//bi-directional many-to-one association to Pruefung
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="PR_ID")
	@XmlInverseReference(mappedBy="angebots")
	private Pruefung pruefung;

	//bi-directional many-to-one association to Richter
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="R_ID")
	private Richter richter;
	
	private int startNr;
	
	private double preis;


	//bi-directional many-to-one association to Teilnehmer_nimmt_an_Disziplin_teil
	@OneToMany(mappedBy="angebot",cascade = CascadeType.MERGE)
	private List<Teilnehmer_nimmt_an_Disziplin_teil> teilnehmerNimmtAnDisziplinTeils;

	//bi-directional many-to-one association to Teilnehmer_nimmt_an_Disziplin_teil
	@OneToMany(mappedBy="angebot", cascade = CascadeType.MERGE)
	private List<Gruppe_nimmt_an_Disziplin_teil> gruppeNimmtAnDisziplinTeils;

	public Angebot() {
	}

	public int getAId() {
		return this.aId;
	}

	public void setAId(int aId) {
		this.aId = aId;
	}

	public Disziplin getDisziplin() {
		return this.disziplin;
	}

	public void setDisziplin(Disziplin disziplin) {
		this.disziplin = disziplin;
	}
	public void setStartNr(int startNr) {
		this.startNr = startNr;
	}
	public int getStartNr(){
		return this.startNr;
	}
	public Pruefung getPruefung() {
		return this.pruefung;
	}

	public void setPruefung(Pruefung pruefung) {
		this.pruefung = pruefung;
	}

	public List<Teilnehmer_nimmt_an_Disziplin_teil> getTeilnehmerNimmtAnDisziplinTeils() {
		return this.teilnehmerNimmtAnDisziplinTeils;
	}

	public void setTeilnehmerNimmtAnDisziplinTeils(List<Teilnehmer_nimmt_an_Disziplin_teil> teilnehmerNimmtAnDisziplinTeils) {
		this.teilnehmerNimmtAnDisziplinTeils = teilnehmerNimmtAnDisziplinTeils;
	}


	public List<Gruppe_nimmt_an_Disziplin_teil> getGruppeNimmtAnDisziplinTeils() {
		return gruppeNimmtAnDisziplinTeils;
	}

	public void setGruppeNimmtAnDisziplinTeils(
			List<Gruppe_nimmt_an_Disziplin_teil> gruppeNimmtAnDisziplinTeils) {
		this.gruppeNimmtAnDisziplinTeils = gruppeNimmtAnDisziplinTeils;
	}

	public Richter getRichter() {
		return richter;
	}

	public void setRichter(Richter richter) {
		this.richter = richter;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}

	public void addTeilnehmerNimmtAnDisziplinTeil(Teilnehmer_nimmt_an_Disziplin_teil t) {
		if (t == null) 
			throw new IllegalArgumentException();
		if (t.getAngebot() != null)
			t.getAngebot().getTeilnehmerNimmtAnDisziplinTeils().remove(t);

		this.getTeilnehmerNimmtAnDisziplinTeils().add(t);
		t.setAngebot(this);		
	}

	public void removeTeilnehmerNimmtAnDisziplinTeil(Teilnehmer_nimmt_an_Disziplin_teil t) {
		if (t == null) 
			throw new IllegalArgumentException();
		if (t.getAngebot() != null)
			t.getAngebot().getTeilnehmerNimmtAnDisziplinTeils().remove(t);

		this.getTeilnehmerNimmtAnDisziplinTeils().remove(t);
		t.setAngebot(null);		
		
	}
	public void addGruppeNimmtAnDisziplinTeil(Gruppe_nimmt_an_Disziplin_teil t) {
		if (t == null) 
			throw new IllegalArgumentException();
		if (t.getAngebot() != null)
			t.getAngebot().getGruppeNimmtAnDisziplinTeils().remove(t);
		if(this.getGruppeNimmtAnDisziplinTeils()== null)this.setGruppeNimmtAnDisziplinTeils(new ArrayList<>());
		this.getGruppeNimmtAnDisziplinTeils().add(t);
		t.setAngebot(this);		
	}

	public void removeGruppeNimmtAnDisziplinTeil(Gruppe_nimmt_an_Disziplin_teil t) {
		if (t == null) 
			throw new IllegalArgumentException();
		if (t.getAngebot() != null)
			t.getAngebot().getGruppeNimmtAnDisziplinTeils().remove(t);

		this.getGruppeNimmtAnDisziplinTeils().remove(t);
		t.setAngebot(null);			
	}
	public void addGruppeNimmtAnDisziplinTeils(List<Gruppe_nimmt_an_Disziplin_teil> gruppeTeilnehmerNew) {
		List<Gruppe_nimmt_an_Disziplin_teil> gruppeTeilnehmerOld = new ArrayList<Gruppe_nimmt_an_Disziplin_teil>(this.getGruppeNimmtAnDisziplinTeils().size());
		for(Gruppe_nimmt_an_Disziplin_teil g: this.getGruppeNimmtAnDisziplinTeils()){
			gruppeTeilnehmerOld.add(g);
		}

		for(Gruppe_nimmt_an_Disziplin_teil g: gruppeTeilnehmerOld){
			if(!gruppeTeilnehmerNew.contains(g)){
				this.removeGruppeNimmtAnDisziplinTeil(g);
			}
		}
		for(Gruppe_nimmt_an_Disziplin_teil g: gruppeTeilnehmerNew){
			if(!gruppeTeilnehmerOld.contains(g)){
				this.addGruppeNimmtAnDisziplinTeil(g);
			}
		}
	}

	public void addTeilnehmerNimmtAnDisziplinTeils(
			List<Teilnehmer_nimmt_an_Disziplin_teil> teilnehmerNew) {
		List<Teilnehmer_nimmt_an_Disziplin_teil> teilnehmerOld = new ArrayList<Teilnehmer_nimmt_an_Disziplin_teil>(this.getTeilnehmerNimmtAnDisziplinTeils().size());
		for(Teilnehmer_nimmt_an_Disziplin_teil g: this.getTeilnehmerNimmtAnDisziplinTeils()){
			teilnehmerOld.add(g);
		}

		for(Teilnehmer_nimmt_an_Disziplin_teil g: teilnehmerOld){
			if(!teilnehmerNew.contains(g)){
				this.removeTeilnehmerNimmtAnDisziplinTeil(g);
			}
		}
		for(Teilnehmer_nimmt_an_Disziplin_teil g: teilnehmerNew){
			if(!teilnehmerOld.contains(g)){
				this.addTeilnehmerNimmtAnDisziplinTeil(g);
			}
		}
		
	}




}