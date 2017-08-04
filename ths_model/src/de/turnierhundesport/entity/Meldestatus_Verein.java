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
/*@NamedQueries({
			@NamedQuery(name = "Status.findWithVereinPruefung", query = "SELECT s FROM Meldestatus_Verein s "+
					"JOIN s.verein v "+
					"JOIN s.pruefung p " +
					"WHERE p.prId =  :prId "+
					"AND v.vId = :vId "),
})*/
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Meldestatus_Verein implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MSV_ID")
	private int msvId;

	@XmlElement
	private int MS_Status;

	//bi-directional many-to-one association to Pruefung
    @ManyToOne
	@JoinColumn(name="PR_ID")
    @XmlTransient
	private Pruefung pruefung;

	//bi-directional many-to-one association to Verein
    @ManyToOne
	@JoinColumn(name="V_ID")
    @XmlInverseReference(mappedBy="meldestatusVereins")
	private Verein verein;

    public Meldestatus_Verein() {
    }
    
	public int getMsvId() {
		return msvId;
	}

	public void setMsvId(int msvId) {
		this.msvId = msvId;
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
	
	public Verein getVerein() {
		return this.verein;
	}

	public void setVerein(Verein verein) {
		this.verein = verein;
	}
	
}