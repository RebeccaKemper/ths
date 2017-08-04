package de.turnierhundesport.entity;


import java.io.Serializable;

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@NamedQueries({
	@NamedQuery(name = "GruppeDisziplin.findAll", query = "SELECT t FROM Gruppe_nimmt_an_Disziplin_teil t "+
			"JOIN t.angebot a " +
			"JOIN a.pruefung p " +
			"WHERE p.prId =  :id "),
			@NamedQuery(name = "GruppeDisziplin.findWithPruefungStartNr", query = "SELECT t FROM Gruppe_nimmt_an_Disziplin_teil t "+
					"JOIN t.angebot a " +
					"JOIN a.pruefung p " +
					"WHERE p.prId =  :id "+
					"AND t.tm_StartNr =:startNr"),
					@NamedQuery(name = "GruppeDisziplin.findWithAngebot", query = "SELECT t FROM Gruppe_nimmt_an_Disziplin_teil t "+
							"JOIN t.angebot a " +
							"WHERE a.aId = :angebot " ),
							@NamedQuery(name = "GruppeDisziplin.findWithTeilnehmer", query = "SELECT t FROM Gruppe_nimmt_an_Disziplin_teil t "+
									"JOIN t.sektion1 s1 " +
									"JOIN t.sektion2 s2 " +
									"JOIN t.sektion3 s3 " +
									"JOIN t.sektion4 s4 " +
									"WHERE s1.tndId = :tId OR s2.tndId = :tId OR (s3 != null and s3.tndId = :tId) OR (s4 != null and s4.tndId = :tId)" )
})
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Gruppe_nimmt_an_Disziplin_teil implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TM_ID")
	@XmlElement
	private int tmId;

	//bi-directional many-to-one association to Disziplin
    @ManyToOne
	@JoinColumn(name="A_ID")
    private Angebot angebot;

	private String tmName;
	
	private int tm_StartNr;
	private int tm_Platz;
    
	private double tmPunkte;

	//bi-directional many-to-one association to Disziplin
    @ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="Sektion1")
	private Teilnehmer_nimmt_an_Disziplin_teil sektion1;

  //bi-directional many-to-one association to Disziplin
    @ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="Sektion2")
	private Teilnehmer_nimmt_an_Disziplin_teil sektion2;

  //bi-directional many-to-one association to Disziplin
    @ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="Sektion3")
	private Teilnehmer_nimmt_an_Disziplin_teil sektion3;
    
    //bi-directional many-to-one association to Disziplin
    @ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="Sektion4")
	private Teilnehmer_nimmt_an_Disziplin_teil sektion4;
	
  //bi-directional many-to-one association to Gruppe
  	@ManyToOne(cascade=CascadeType.MERGE)
  	@JoinColumn(name="G_ID")
  	private Gruppe gruppe;
  	
  	private boolean jugend;
  	
	public Gruppe_nimmt_an_Disziplin_teil() {
	}


	public int getTmId() {
		return tmId;
	}


	public void setAngebot(Angebot angebot) {
		this.angebot = angebot;
	}

	public Angebot getAngebot() {
		return this.angebot;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName;
	}

	public String getTmName() {
		return this.tmName;
	}

	public void setTmPunkte(double tmPunkte) {
		this.tmPunkte = tmPunkte;
	}

	public double getTmPunkte() {
		return this.tmPunkte;
	}

	public void setSektion1start(Teilnehmer_nimmt_an_Disziplin_teil sektion1start) {
		this.sektion1 = sektion1start;
	}

	public Teilnehmer_nimmt_an_Disziplin_teil getSektion1start() {
		return this.sektion1;
	}


	public void setSektion2start(Teilnehmer_nimmt_an_Disziplin_teil sektion2start) {
		this.sektion2 = sektion2start;
	}

	public Teilnehmer_nimmt_an_Disziplin_teil getSektion2start() {
		return this.sektion2;
	}

	public void setSektion3start(Teilnehmer_nimmt_an_Disziplin_teil sektion3start) {
		this.sektion3 = sektion3start;
	}

	public Teilnehmer_nimmt_an_Disziplin_teil getSektion3start() {
		return this.sektion3;
	}

	public void setSektion4start(Teilnehmer_nimmt_an_Disziplin_teil sektion4start) {
		this.sektion4 = sektion4start;
	}

	public Teilnehmer_nimmt_an_Disziplin_teil getSektion4start() {
		return this.sektion4;
	}

	public int getTm_StartNr() {
		return tm_StartNr;
	}


	public void setTm_StartNr(int tm_StartNr) {
		this.tm_StartNr = tm_StartNr;
	}


	public Gruppe getGruppe() {
		return gruppe;
	}


	public void setGruppe(Gruppe gruppe) {
		this.gruppe = gruppe;
	}


	public boolean isJugend() {
		return jugend;
	}


	public void setJugend(boolean jugend) {
		this.jugend = jugend;
	}
	public void delete(){
		//Beziehung zu Angebot lösen
		this.getAngebot().removeGruppeNimmtAnDisziplinTeil(this);
		//Beziehung zu Gruppe lösen
		this.getGruppe().removeGruppeNimmtAnDisziplinTeil(this);
	}


	public int getTm_Platz() {
		return tm_Platz;
	}


	public void setTm_Platz(int tm_Platz) {
		this.tm_Platz = tm_Platz;
	}


}
