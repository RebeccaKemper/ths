package de.turnierhundesport.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the Verband database table.
 * 
 */
@NamedQueries({
	@NamedQuery(name = "Verband.findAll", query = "SELECT vb from Verband vb"),
	@NamedQuery(name="Verband.findWithName",
		query="SELECT e " +
      "FROM Verband e " +
      "WHERE e.VB_Name = :name" )
})
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Verband implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VB_ID")
	private int vbId;

	private String VB_Name;

	//bi-directional many-to-one association to Verein
	@OneToMany(mappedBy="verband")
	@XmlInverseReference(mappedBy="verband")
	private Set<Verein> vereins;

    public Verband() {
    }

	public int getVbId() {
		return this.vbId;
	}

	public void setVbId(int vbId) {
		this.vbId = vbId;
	}

	public String getVB_Name() {
		return this.VB_Name;
	}

	public void setVB_Name(String VB_Name) {
		this.VB_Name = VB_Name;
	}

	public Set<Verein> getVereins() {
		return this.vereins;
	}

	public void setVereins(Set<Verein> vereins) {
		this.vereins = vereins;
	}
	
}