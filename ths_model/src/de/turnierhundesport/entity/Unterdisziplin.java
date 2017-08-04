package de.turnierhundesport.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import java.util.List;


/**
 * The persistent class for the Unterdisziplin database table.
 * 
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Unterdisziplin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="UD_ID")
	private int udId;

	@Column(name="UD_NAME")
	private String udName;

	//bi-directional many-to-one association to Start
	@OneToMany(mappedBy="unterdisziplin")
	private List<Start> starts;
	
	public enum UnterdisziplinType{
		UO(1),Huerde(2), Slalom(3), Hindernis(4), Gelaende(5), CSC(6);
		private int id;
		UnterdisziplinType(int id){
			this.id = id;
		}
		public int getId(){
			return id;
		}
	}


	public static final int VK1_HUERDE_BONUS = 10;
	public static final int VK1_SLALOM_BONUS = 5;
	
    public Unterdisziplin() {
    }

	public int getUdId() {
		return this.udId;
	}

	public void setUdId(int udId) {
		this.udId = udId;
	}

	public String getUdName() {
		return this.udName;
	}

	public void setUdName(String udName) {
		this.udName = udName;
	}

	public List<Start> getStarts() {
		return this.starts;
	}

	public void setStarts(List<Start> starts) {
		this.starts = starts;
	}
	
}