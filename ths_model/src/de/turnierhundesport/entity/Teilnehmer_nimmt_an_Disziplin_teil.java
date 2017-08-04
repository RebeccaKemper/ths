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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the Teilnehmer_nimmt_an_Disziplin_teil database table.
 * 
 */
@NamedQueries({
	@NamedQuery(name = "TeilnehmerDisziplin.findAll", query = "SELECT t FROM Teilnehmer_nimmt_an_Disziplin_teil t "+
			"JOIN t.angebot a " +
			"JOIN a.pruefung p " +
			"WHERE p.prId =  :id "
			),
			@NamedQuery(name = "TeilnehmerDisziplin.findWithPruefung", query = "SELECT t FROM Teilnehmer_nimmt_an_Disziplin_teil t "+
					"JOIN t.teilnehmer x "+
					"JOIN t.angebot a " +
					"JOIN a.pruefung p " +
					"WHERE p.prId =  :prId "+
					"AND x.tId = :tId "),
					@NamedQuery(name = "TeilnehmerDisziplin.findWithTeilnehmerAngebot", query = "SELECT t FROM Teilnehmer_nimmt_an_Disziplin_teil t "+
							"JOIN t.teilnehmer x "+
							"JOIN t.angebot a " +
							"WHERE x.tId = :tId " +
							"AND a.aId = :aId"),
							@NamedQuery(name = "TeilnehmerDisziplin.findWithTeilnehmerOhneAngebot", query = "SELECT t FROM Teilnehmer_nimmt_an_Disziplin_teil t "+
									"JOIN t.teilnehmer x "+
									"JOIN t.angebot a " +
									"WHERE x.tId = :tId " +
									"AND a.aId IS NULL"),
							@NamedQuery(name = "TeilnehmerDisziplin.findWithPruefungStartNr", query = "SELECT t FROM Teilnehmer_nimmt_an_Disziplin_teil t "+
									"JOIN t.angebot a " +
									"JOIN a.pruefung p " +
									"WHERE p.prId =  :id "+
									"AND t.TND_StartNr =:startNr"),
									@NamedQuery(name = "Teilnehmer.findWithPruefungVerein", query = "SELECT t.teilnehmer FROM Teilnehmer_nimmt_an_Disziplin_teil t "+
											"JOIN t.teilnehmer x "+
											"JOIN x.mitglied m "+
											"JOIN m.verein v "+
											"JOIN t.angebot a " +
											"JOIN a.pruefung p " +
											"WHERE v.vId = :vId " +
											"AND p.prId = :prId"),
											@NamedQuery(name = "TeilnehmerDisziplin.findWithPruefungVerein", query = "SELECT t FROM Teilnehmer_nimmt_an_Disziplin_teil t "+
													"JOIN t.teilnehmer x "+
													"JOIN x.mitglied m "+
													"JOIN m.verein v "+
													"JOIN t.angebot a " +
													"JOIN a.pruefung p " +
													"WHERE v.vId = :vId " +
													"AND p.prId = :prId"),
											@NamedQuery(name = "TeilnehmerDisziplin.findWithAngebotAltersklasseGeschlecht", query = "SELECT t FROM Teilnehmer_nimmt_an_Disziplin_teil t "+
													"JOIN t.angebot a " +
													"JOIN t.altersklasse ak " +
													"JOIN t.teilnehmer tn " +
													"JOIN tn.mitglied m " +
													"JOIN m.person p " +
													"WHERE a.aId = :angebot " +
													"AND ak.akId = :altersklasse "+
													"AND p.p_Geschlecht = :geschlecht")
})
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Teilnehmer_nimmt_an_Disziplin_teil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TND_ID")
	private int tndId;

	//bi-directional many-to-one association to Gruppe
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="G_ID")
	private Gruppe gruppe;


	private int TND_Platz;

	private double TND_Punkte;

	private int TND_StartNr;

	//bi-directional many-to-one association to Start
	@OneToMany(mappedBy="teilnehmerNimmtAnDisziplinTeil", cascade=CascadeType.REMOVE)
	private List<Start> starts;

	//bi-directional many-to-one association to Altersklasse
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="AK_ID")
	private Altersklasse altersklasse;

	//bi-directional many-to-one association to Angebot
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="A_ID")
	@XmlInverseReference(mappedBy="teilnehmerNimmtAnDisziplinTeils")
	private Angebot angebot;

	//bi-directional many-to-one association to Teilnehmer
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="T_ID")
	private Teilnehmer teilnehmer;
	
	//bi-directional many-to-one association to Unterordnung
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="UO_ID")
	private Unterordnung unterordnung;

	@OneToMany
	private List<Gruppe_nimmt_an_Disziplin_teil> gruppeNimmtAnDisziplinTeils1;
	
	@OneToMany
	private List<Gruppe_nimmt_an_Disziplin_teil> gruppeNimmtAnDisziplinTeils2;
	@OneToMany
	private List<Gruppe_nimmt_an_Disziplin_teil> gruppeNimmtAnDisziplinTeils3;
	
	public Teilnehmer_nimmt_an_Disziplin_teil() {
	}

	public int getTndId() {
		return this.tndId;
	}

	public void setTndId(int tndId) {
		this.tndId = tndId;
	}

	public int getTND_Platz() {
		return this.TND_Platz;
	}

	public void setTND_Platz(int TND_Platz) {
		this.TND_Platz = TND_Platz;
	}

	public double getTND_Punkte() {
		return this.TND_Punkte;
	}

	public void setTND_Punkte(double d) {
		this.TND_Punkte = d;
	}

	public List<Start> getStarts() {
		return this.starts;
	}

	public void setStarts(List<Start> starts) {
		this.starts = starts;
	}
	public void addStart(Start s){
		s.setTeilnehmerNimmtAnDisziplinTeil(this);
		starts.add(s);
	}

	public Altersklasse getAltersklasse() {
		return this.altersklasse;
	}

	public void setAltersklasse(Altersklasse altersklasse) {
		this.altersklasse = altersklasse;
	}

	public Angebot getAngebot() {
		return this.angebot;
	}

	public void setAngebot(Angebot angebot) {
		this.angebot = angebot;
	}

	public Teilnehmer getTeilnehmer() {
		return this.teilnehmer;
	}

	public void setTeilnehmer(Teilnehmer teilnehmer) {
		this.teilnehmer = teilnehmer;
		
	}

	public Gruppe getGruppe() {
		return gruppe;
	}

	public void setGruppe(Gruppe gruppe) {
		this.gruppe = gruppe;
	}

	public int getTND_StartNr() {
		return TND_StartNr;
	}

	public void setTND_StartNr(int tND_StartNr) {
		TND_StartNr = tND_StartNr;
	}
	public Unterordnung getUnterordnung() {
		return unterordnung;
	}

	public void setUnterordnung(Unterordnung unterordnung) {
		this.unterordnung = unterordnung;
	}
	public String toString(){
		Person p = teilnehmer.getMitglied().getPerson();
		return p.getPVorname()+" "+p.getP_Name();
	}

}