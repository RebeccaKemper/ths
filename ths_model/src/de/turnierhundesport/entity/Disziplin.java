package de.turnierhundesport.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the Disziplin database table.
 * 
 */
@NamedQueries({
	@NamedQuery(name = "Disziplin.findAll", query = "SELECT d from Disziplin d"),
	@NamedQuery(name="Disziplin.findWithName",
		query="SELECT e " +
      "FROM Disziplin e " +
      "WHERE e.d_Name =  :name ")
})
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Disziplin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="D_ID")
	private int dId;

	private String d_Name;

	//bi-directional many-to-one association to Angebot 
	@OneToMany(mappedBy="disziplin")
	@XmlInverseReference(mappedBy="disziplin")
	private Set<Angebot> angebots;

	//bi-directional many-to-many association to Unterdisziplin
	@ManyToMany
	@JoinTable(
			name="Diszplin_hat_Unterdisziplinen"
			, joinColumns={
				@JoinColumn(name="D_ID")
				}
			, inverseJoinColumns={
				@JoinColumn(name="UD_ID")
				}
			)
	@XmlElement
	private List<Unterdisziplin> unterdisziplins;

	@Column(name="D_MaxPunkte")
	private int d_MaxPunkte;
	
	public enum DisziplinType{
		VK1(1),
		VK2(2), 
		VK3(3), 
		Hindernislauf(4), 
		Dreikampf(5), 
		Gelaende1000(6), 
		Gelaende2000(7),
		Gelaende5000(8), 
		CSC(9),
		Shorty(10),
		Cup(11);
	
		
		private int id;
		DisziplinType(int id){
			this.id = id;
		}
		public int getId(){
			return id;
		}
	}
    public Disziplin() {
    }

	public int getDId() {
		return this.dId;
	}

	public void setDId(int dId) {
		this.dId = dId;
	}

	public String getD_Name() {
		return this.d_Name;
	}

	public void setD_Name(String d_Name) {
		this.d_Name = d_Name;
	}

	public Set<Angebot> getAngebots() {
		return this.angebots;
	}

	public void setAngebots(Set<Angebot> angebots) {
		this.angebots = angebots;
	}
	
	public List<Unterdisziplin> getUnterdisziplins() {
		return this.unterdisziplins;
	}

	public void setUnterdisziplins(List<Unterdisziplin> unterdisziplins) {
		this.unterdisziplins = unterdisziplins;
	}

	public int getD_MaxPunkte() {
		return d_MaxPunkte;
	}

	public void setD_MaxPunkte(int d_MaxPunkte) {
		this.d_MaxPunkte = d_MaxPunkte;
	}	
	
}