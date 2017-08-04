package de.turnierhundesport.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;



/**
 * The persistent class for the Meldestatus_Verein database table.
 * 
 */
@NamedQueries({
			@NamedQuery(name = "Status.findWithTeilnehmerPruefung", query = "SELECT s FROM Meldestatus_Teilnehmer s "+
					"JOIN s.teilnehmer t "+
					"JOIN s.pruefung p " +
					"WHERE p.prId =  :prId "+
					"AND t.tId = :tId "),
					@NamedQuery(name = "Status.findWithVereinPruefung", query = "SELECT s FROM Meldestatus_Teilnehmer s "+
							"JOIN s.teilnehmer t "+
							"JOIN s.pruefung p " +
							"JOIN t.mitglied m " +
							"JOIN m.verein v " +
							"WHERE p.prId =  :prId "+
							"AND v.vId = :vId "),
})
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Meldestatus_Teilnehmer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MST_ID")
	private int mstId;

	@XmlElement
	private int MS_Status;

	//bi-directional many-to-one association to Pruefung
    @ManyToOne
	@JoinColumn(name="PR_ID")
    @XmlTransient
	private Pruefung pruefung;

	//bi-directional many-to-one association to Verein
    @ManyToOne
	@JoinColumn(name="T_ID")
    @XmlInverseReference(mappedBy="meldestatusTeilnehmers")
	private Teilnehmer teilnehmer;

    public Meldestatus_Teilnehmer() {
    }
    
	public int getMsvId() {
		return mstId;
	}

	public void setMsvId(int msvId) {
		this.mstId = msvId;
	}

	public int getMS_Status() {
		return this.MS_Status;
	}

	public void setMS_Status(int MS_Status) {
		this.MS_Status = MS_Status;
	}

	public Pruefung getPruefung() {
		return this.pruefung;
	}

	public void setPruefung(Pruefung pruefung) {
		this.pruefung = pruefung;
	}
	
	public Teilnehmer getTeilnehmer() {
		return this.teilnehmer;
	}

	public void setTeilnehmer(Teilnehmer teilnehmer) {
		this.teilnehmer = teilnehmer;
	}
	
}