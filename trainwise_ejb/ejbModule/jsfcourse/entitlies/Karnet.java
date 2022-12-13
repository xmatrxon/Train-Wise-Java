package jsfcourse.entitlies;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the karnet database table.
 * 
 */
@Entity
@NamedQuery(name="Karnet.findAll", query="SELECT k FROM Karnet k")
public class Karnet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_karnetu;

	private float cena_karnetu;

	private String nazwa_karnetu;

	//bi-directional many-to-one association to Czlonkostwo
	@OneToMany(mappedBy="karnet")
	private List<Czlonkostwo> czlonkostwos;

	public Karnet() {
	}

	public int getID_karnetu() {
		return this.ID_karnetu;
	}

	public void setID_karnetu(int ID_karnetu) {
		this.ID_karnetu = ID_karnetu;
	}

	public float getCena_karnetu() {
		return this.cena_karnetu;
	}

	public void setCena_karnetu(float cena_karnetu) {
		this.cena_karnetu = cena_karnetu;
	}

	public String getNazwa_karnetu() {
		return this.nazwa_karnetu;
	}

	public void setNazwa_karnetu(String nazwa_karnetu) {
		this.nazwa_karnetu = nazwa_karnetu;
	}

	public List<Czlonkostwo> getCzlonkostwos() {
		return this.czlonkostwos;
	}

	public void setCzlonkostwos(List<Czlonkostwo> czlonkostwos) {
		this.czlonkostwos = czlonkostwos;
	}

	public Czlonkostwo addCzlonkostwo(Czlonkostwo czlonkostwo) {
		getCzlonkostwos().add(czlonkostwo);
		czlonkostwo.setKarnet(this);

		return czlonkostwo;
	}

	public Czlonkostwo removeCzlonkostwo(Czlonkostwo czlonkostwo) {
		getCzlonkostwos().remove(czlonkostwo);
		czlonkostwo.setKarnet(null);

		return czlonkostwo;
	}

}