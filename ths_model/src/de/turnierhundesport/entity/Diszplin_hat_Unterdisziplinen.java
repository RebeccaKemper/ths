package de.turnierhundesport.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * The persistent class for the Diszplin_hat_Unterdisziplinen database table.
 * 
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)

public class Diszplin_hat_Unterdisziplinen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DUD_ID")
	private int dudId;

	//bi-directional many-to-one association to Disziplin
    @ManyToOne
	@JoinColumn(name="D_ID")
	private Disziplin disziplin;

	//bi-directional many-to-one association to Unterdisziplin
    @ManyToOne
	@JoinColumn(name="UD_ID")
	private Unterdisziplin unterdisziplin;

    public Diszplin_hat_Unterdisziplinen() {
    }

	public int getDudId() {
		return this.dudId;
	}

	public void setDudId(int dudId) {
		this.dudId = dudId;
	}

	public Disziplin getDisziplin() {
		return this.disziplin;
	}

	public void setDisziplin(Disziplin disziplin) {
		this.disziplin = disziplin;
	}
	
	public Unterdisziplin getUnterdisziplin() {
		return this.unterdisziplin;
	}

	public void setUnterdisziplin(Unterdisziplin unterdisziplin) {
		this.unterdisziplin = unterdisziplin;
	}
	
}