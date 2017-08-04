package de.turnierhundesport.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * The persistent class for the Start database table.
 * 
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Start implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="S_ID")
	private int sId;

	private int l_GesamtFehlerpunkte;

	private double l_Gesamtzeit;

	private int l1_Bonus;

	private int l1_Fehlerpunkte;

	private double l1_Zeit;

	private int l2_Bonus;
	
	private int l2_Fehlerpunkte;

	private double l2_Zeit;

	//bi-directional many-to-one association to Teilnehmer_nimmt_an_Disziplin_teil
    @ManyToOne
	@JoinColumn(name="TND_ID")
	private Teilnehmer_nimmt_an_Disziplin_teil teilnehmerNimmtAnDisziplinTeil;

	//bi-directional many-to-one association to Unterdisziplin
    @ManyToOne
	@JoinColumn(name="UD_ID")
	private Unterdisziplin unterdisziplin;

    public Start() {
    }

	public int getSId() {
		return this.sId;
	}

	public void setSId(int sId) {
		this.sId = sId;
	}

	public int getL_GesamtFehlerpunkte() {
		return this.l_GesamtFehlerpunkte;
	}

	public void setL_GesamtFehlerpunkte(int l_GesamtFehlerpunkte) {
		this.l_GesamtFehlerpunkte = l_GesamtFehlerpunkte;
	}

	public double getL_Gesamtzeit() {
		return this.l_Gesamtzeit;
	}

	public void setL_Gesamtzeit(double l_Gesamtzeit) {
		this.l_Gesamtzeit = l_Gesamtzeit;
	}

	public int getL1_Bonus() {
		return this.l1_Bonus;
	}

	public void setL1_Bonus(int l1_Bonus) {
		this.l1_Bonus = l1_Bonus;
	}

	public int getL1_Fehlerpunkte() {
		return this.l1_Fehlerpunkte;
	}

	public void setL1_Fehlerpunkte(int l1_Fehlerpunkte) {
		this.l1_Fehlerpunkte = l1_Fehlerpunkte;
	}

	public double getL1_Zeit() {
		return this.l1_Zeit;
	}

	public void setL1_Zeit(double l1_Zeit) {
		this.l1_Zeit = l1_Zeit;
	}

	public int getL2_Bonus() {
		return this.l2_Bonus;
	}

	public void setL2_Bonus(int l2_Bonus) {
		this.l2_Bonus = l2_Bonus;
	}

	public int getL2_Fehlerpunkte() {
		return this.l2_Fehlerpunkte;
	}

	public void setL2_Fehlerpunkte(int l2_Fehlerpunkte) {
		this.l2_Fehlerpunkte = l2_Fehlerpunkte;
	}

	public double getL2_Zeit() {
		return this.l2_Zeit;
	}

	public void setL2_Zeit(double l2_Zeit) {
		this.l2_Zeit = l2_Zeit;
	}

	public Teilnehmer_nimmt_an_Disziplin_teil getTeilnehmerNimmtAnDisziplinTeil() {
		return this.teilnehmerNimmtAnDisziplinTeil;
	}

	public void setTeilnehmerNimmtAnDisziplinTeil(Teilnehmer_nimmt_an_Disziplin_teil teilnehmerNimmtAnDisziplinTeil) {
		this.teilnehmerNimmtAnDisziplinTeil = teilnehmerNimmtAnDisziplinTeil;
	}
	
	public Unterdisziplin getUnterdisziplin() {
		return this.unterdisziplin;
	}

	public void setUnterdisziplin(Unterdisziplin unterdisziplin) {
		this.unterdisziplin = unterdisziplin;
	}
	public boolean isHuerde(){
		return getUnterdisziplin().getUdId()==Unterdisziplin.UnterdisziplinType.Huerde.getId();
	}
	public boolean isSlalom(){
		return getUnterdisziplin().getUdId()==Unterdisziplin.UnterdisziplinType.Slalom.getId();
	}
	public boolean isHindernis(){
		return getUnterdisziplin().getUdId()==Unterdisziplin.UnterdisziplinType.Hindernis.getId();
	}
	public boolean isGelaende() {
		return getUnterdisziplin().getUdId()==Unterdisziplin.UnterdisziplinType.Gelaende.getId();
	}
	
}