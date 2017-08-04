package de.turnierhundesport.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the PRUEFUNG database table.
 * 
 */
@NamedQuery(name = "Pruefung.findAll", query = "SELECT p from Pruefung p")
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Pruefung implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PR_ID")
	private int prId;

	@Column(name="PR_ANTRAG")
	private String prAntrag;

	@Temporal( TemporalType.DATE)
	@Column(name="PR_DATUM")
	private Date prDatum;

	@Column(name="PR_NAME")
	private String prName;

	//bi-directional many-to-one association to Angebot
	@OneToMany(cascade = CascadeType.ALL)
	private List<Angebot> angebots;

	//bi-directional many-to-one association to Verein
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="V_ID")
	private Verein verein;

	//bi-directional many-to-one association to Person
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="PR_PRUEFUNGSLEITER")
	private Person pruefungsleiter;

	//bi-directional many-to-many association to Richter
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name="RICHTET"
			, joinColumns={
					@JoinColumn(name="PR_ID")
			}
			, inverseJoinColumns={
					@JoinColumn(name="R_ID")
			}
			)
	@XmlInverseReference(mappedBy="pruefungs")
	@XmlElement
	private List<Richter> richters;

	//bi-directional many-to-one association to Gruppe
	@OneToMany(cascade = CascadeType.ALL)
	private List<Gruppe> gruppes;

	//bi-directional many-to-one association to Meldestatus_Verein
	@OneToMany
	private List<Meldestatus_Verein> meldestatusVereins;

	@OneToOne (cascade = CascadeType.MERGE)
	private Angebot defaultAngebot;

	public Pruefung() {
	}

	public int getPrId() {
		return this.prId;
	}

	public void setPrId(int prId) {
		this.prId = prId;
	}

	public String getPrAntrag() {
		return this.prAntrag;
	}

	public void setPrAntrag(String prAntrag) {
		this.prAntrag = prAntrag;
	}

	public Date getPrDatum() {
		return this.prDatum;
	}

	public void setPrDatum(Date prDatum) {
		this.prDatum = prDatum;
	}

	public String getPrName() {
		return this.prName;
	}

	public void setPrName(String prName) {
		this.prName = prName;
	}

	public List<Angebot> getAngebot() {
		return this.angebots;
	}

	public void setAngebot(List<Angebot> angebot) {
		this.angebots = angebot;
	}

	public Verein getVerein() {
		return this.verein;
	}

	public void setVerein(Verein verein) {
		this.verein = verein;
	}

	public Person getPruefungsleiter() {
		return pruefungsleiter;
	}

	public void setPruefungsleiter(Person person) {
		this.pruefungsleiter = person;
	}

	public List<Richter> getRichters() {
		return richters;
	}

	public void setRichters(List<Richter> richters) {
		this.richters = richters;
	}

	public List<Gruppe> getGruppes() {
		return gruppes;
	}

	public void setGruppes(List<Gruppe> gruppes) {
		this.gruppes = gruppes;
	}

	public List<Meldestatus_Verein> getMeldestatusVereins() {
		return meldestatusVereins;
	}

	public void setMeldestatusVereins(List<Meldestatus_Verein> meldestatusVereins) {
		this.meldestatusVereins = meldestatusVereins;
	}

	public Angebot getDefaultAngebot() {
		return defaultAngebot;
	}

	public void setDefaultAngebot(Angebot defaultAngebot) {
		this.defaultAngebot = defaultAngebot;
	}

	public String toString(){
		return this.prName;
	}

	public void addAngebot(Angebot a) {
		if (a == null) 
			throw new IllegalArgumentException();
		if (a.getPruefung() != null)
			a.getPruefung().getAngebot().remove(a);

		this.getAngebot().add(a);
		a.setPruefung(this);	

	}

	public void removeAngebot(Angebot a) {
		if (a == null) 
			throw new IllegalArgumentException();
		if (a.getPruefung() != null)
			a.getPruefung().getAngebot().remove(a);

		this.getAngebot().remove(a);
		a.setPruefung(null);	

	}
	public void addRichter(Richter r) {
		if (r == null) 
			throw new IllegalArgumentException();
		if(r.getPruefungs()!=null){
			int i = r.getPruefungs().indexOf(this);
			if(i>=0)r.getPruefungs().get(i).getRichters().remove(r);
		}
		this.getRichters().add(r);

		if(this.getRichters()!=null){
			int i = this.getRichters().indexOf(r);
			if(i>=0)this.getRichters().get(i).getPruefungs().remove(this);
		}

		r.getPruefungs().add(this);

	}

	public void removeRichter(Richter r) {
		if (r == null) 
			throw new IllegalArgumentException();
		int i = r.getPruefungs().indexOf(this);
		if(i>=0)r.getPruefungs().get(i).getRichters().remove(r);

		this.getRichters().remove(r);

		i = this.getRichters().indexOf(r);
		if(i>=0)this.getRichters().get(i).getPruefungs().remove(this);

		r.getPruefungs().remove(this);
		
		List<Angebot> angebotOld = new ArrayList<Angebot>(this.getAngebot().size());
		for(Angebot a: this.getAngebot()){
			angebotOld.add(a);
		}
		//Beziehung zu Angebote bearbeiten
		for(Angebot a : angebotOld){
			if(a.getPruefung().getPrId() == prId){
				r.removeAngebot(a);
			}
		}
		System.out.println(this.getAngebot().get(0).getRichter());

	}

	public void addAngebots(List<Angebot> angebotNew) {

		List<Angebot> angebotOld = new ArrayList<Angebot>(this.getAngebot().size());
		for(Angebot a: this.getAngebot()){
			angebotOld.add(a);
		}

		for(Angebot a: angebotOld){
			if(!angebotNew.contains(a)){
				this.removeAngebot(a);
			}
		}
		for(Angebot a: angebotNew){
			if(!angebotOld.contains(a)){
				this.addAngebot(a);
			}
		}

	}

	public void addRichters(List<Richter> richterNew) {
		List<Richter> richterOld = new ArrayList<Richter>(this.getRichters().size());
		for(Richter r: this.getRichters()){
			richterOld.add(r);
		}

		for(Richter r: richterOld){
			if(!richterNew.contains(r)){
				this.removeRichter(r);
			}
		}
		for(Richter r: richterNew){
			if(!richterOld.contains(r)){
				this.addRichter(r);
			}
		}
	}

	public void addGruppes(List<Gruppe> gruppeNew) {
		List<Gruppe> gruppeOld = new ArrayList<Gruppe>(this.getGruppes().size());
		for(Gruppe g: this.getGruppes()){
			gruppeOld.add(g);
		}

		for(Gruppe g: gruppeOld){
			if(!gruppeNew.contains(g)){
				this.removeGruppe(g);
			}
		}
		for(Gruppe g: gruppeNew){
			if(!gruppeOld.contains(g)){
				this.addGruppe(g);
			}
		}
		
	}

	public void addGruppe(Gruppe g) {
		if (g == null) 
			throw new IllegalArgumentException();
		if (g.getPruefung() != null)
			g.getPruefung().getGruppes().remove(g);

		this.getGruppes().add(g);
		g.setPruefung(this);	

	}

	public void removeGruppe(Gruppe g) {
		if (g == null) 
			throw new IllegalArgumentException();
		if (g.getPruefung() != null)
			g.getPruefung().getGruppes().remove(g);

		this.getGruppes().remove(g);
		g.setPruefung(null);	

	}


}