package de.turnierhundesport.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the Gruppe database table.
 * 
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Gruppe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="G_ID")
	private int gId;

	private int g_Bis;

	private int g_Nummer;

	private int g_Von;

	//bi-directional many-to-one association to Pruefung
    @ManyToOne
	@JoinColumn(name="PR_ID")
    @XmlTransient
	private Pruefung pruefung;

	//bi-directional many-to-one association to Teilnehmer_nimmt_an_Disziplin_teil
	@OneToMany(mappedBy="gruppe")
	private List<Teilnehmer_nimmt_an_Disziplin_teil> teilnehmerNimmtAnDisziplinTeils;
	
	//bi-directional many-to-one association to Teilnehmer_nimmt_an_Disziplin_teil
		@OneToMany(mappedBy="gruppe")
		private List<Gruppe_nimmt_an_Disziplin_teil> gruppeNimmtAnDisziplinTeils;

    public Gruppe() {
    }

	public int getGId() {
		return this.gId;
	}

	public void setGId(int gId) {
		this.gId = gId;
	}

	public int getG_Bis() {
		return this.g_Bis;
	}

	public void setG_Bis(int g_Bis) {
		this.g_Bis = g_Bis;
	}

	public int getG_Nummer() {
		return this.g_Nummer;
	}

	public void setG_Nummer(int g_Nummer) {
		this.g_Nummer = g_Nummer;
	}

	public int getG_Von() {
		return this.g_Von;
	}

	public void setG_Von(int g_Von) {
		this.g_Von = g_Von;
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


	public void addTeilnehmerNimmtAnDisziplinTeil(Teilnehmer_nimmt_an_Disziplin_teil td) {
		if (td == null) 
			throw new IllegalArgumentException();
		if (td.getGruppe() != null)
			td.getGruppe().getTeilnehmerNimmtAnDisziplinTeils().remove(td);

		this.getTeilnehmerNimmtAnDisziplinTeils().add(td);
		td.setGruppe(this);	

	}

	public void addGruppeNimmtAnDisziplinTeil(Gruppe_nimmt_an_Disziplin_teil gd) {
		if (gd == null) 
			throw new IllegalArgumentException();
		if (gd.getGruppe() != null)
			gd.getGruppe().getGruppeNimmtAnDisziplinTeils().remove(gd);
		
		if(this.getGruppeNimmtAnDisziplinTeils()==null) this.setGruppeNimmtAnDisziplinTeils(new ArrayList<Gruppe_nimmt_an_Disziplin_teil>());
		this.getGruppeNimmtAnDisziplinTeils().add(gd);
		gd.setGruppe(this);	

	}

	public void removeGruppeNimmtAnDisziplinTeil(
			Gruppe_nimmt_an_Disziplin_teil gd) {
		if (gd == null) 
			throw new IllegalArgumentException();
		if (gd.getGruppe() != null)
			gd.getGruppe().getGruppeNimmtAnDisziplinTeils().remove(gd);

		this.getGruppeNimmtAnDisziplinTeils().remove(gd);
		gd.setGruppe(null);	
		
	}

	public boolean isCSC() {
		for(Gruppe_nimmt_an_Disziplin_teil t : this.getGruppeNimmtAnDisziplinTeils()){
			if (t.getAngebot().getDisziplin().getDId() == Disziplin.DisziplinType.CSC.getId()){
				return true;
			}			
		}
		return false;
	}
	public boolean isShorty() {
		for(Gruppe_nimmt_an_Disziplin_teil t : this.getGruppeNimmtAnDisziplinTeils()){
			if (t.getAngebot().getDisziplin().getDId() == Disziplin.DisziplinType.Shorty.getId()){
				return true;
			}			
		}
		return false;
	}

	
}