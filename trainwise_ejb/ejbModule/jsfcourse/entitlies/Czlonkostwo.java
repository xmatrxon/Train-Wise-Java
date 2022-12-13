package jsfcourse.entitlies;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the czlonkostwo database table.
 * 
 */
@Entity
@NamedQuery(name="Czlonkostwo.findAll", query="SELECT c FROM Czlonkostwo c")
public class Czlonkostwo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_czlonkostwa;

	@Temporal(TemporalType.DATE)
	private Date data_rozpoczecia;

	@Temporal(TemporalType.DATE)
	private Date data_zakonczenia;

	//bi-directional many-to-one association to Klient
	@ManyToOne
	@JoinColumn(name="ID_klienta")
	private Klient klient;

	//bi-directional many-to-one association to Karnet
	@ManyToOne
	@JoinColumn(name="ID_karnetu")
	private Karnet karnet;

	public Czlonkostwo() {
	}

	public int getID_czlonkostwa() {
		return this.ID_czlonkostwa;
	}

	public void setID_czlonkostwa(int ID_czlonkostwa) {
		this.ID_czlonkostwa = ID_czlonkostwa;
	}

	public Date getData_rozpoczecia() {
		return this.data_rozpoczecia;
	}

	public void setData_rozpoczecia(Date data_rozpoczecia) {
		this.data_rozpoczecia = data_rozpoczecia;
	}

	public Date getData_zakonczenia() {
		return this.data_zakonczenia;
	}

	public void setData_zakonczenia(Date data_zakonczenia) {
		this.data_zakonczenia = data_zakonczenia;
	}

	public Klient getKlient() {
		return this.klient;
	}

	public void setKlient(Klient klient) {
		this.klient = klient;
	}

	public Karnet getKarnet() {
		return this.karnet;
	}

	public void setKarnet(Karnet karnet) {
		this.karnet = karnet;
	}

}