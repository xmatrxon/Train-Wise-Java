package jsf.course.enities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the klient database table.
 * 
 */
@Entity
@NamedQuery(name="Klient.findAll", query="SELECT k FROM Klient k")
public class Klient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_klienta")
	private int idKlienta;

	private byte aktywny;

	private String haslo;

	private String imie;

	private String login;

	private String nazwisko;

	@Column(name="nr_tel")
	private String nrTel;

	private String pesel;

	private String rola;

	//bi-directional many-to-one association to Czlonkostwo
	@OneToMany(mappedBy="klient")
	private List<Czlonkostwo> czlonkostwos;

	public Klient() {
	}

	public int getIdKlienta() {
		return this.idKlienta;
	}

	public void setIdKlienta(int idKlienta) {
		this.idKlienta = idKlienta;
	}

	public byte getAktywny() {
		return this.aktywny;
	}

	public void setAktywny(byte aktywny) {
		this.aktywny = aktywny;
	}

	public String getHaslo() {
		return this.haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getImie() {
		return this.imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNazwisko() {
		return this.nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getNrTel() {
		return this.nrTel;
	}

	public void setNrTel(String nrTel) {
		this.nrTel = nrTel;
	}

	public String getPesel() {
		return this.pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getRola() {
		return this.rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	public List<Czlonkostwo> getCzlonkostwos() {
		return this.czlonkostwos;
	}

	public void setCzlonkostwos(List<Czlonkostwo> czlonkostwos) {
		this.czlonkostwos = czlonkostwos;
	}

	public Czlonkostwo addCzlonkostwo(Czlonkostwo czlonkostwo) {
		getCzlonkostwos().add(czlonkostwo);
		czlonkostwo.setKlient(this);

		return czlonkostwo;
	}

	public Czlonkostwo removeCzlonkostwo(Czlonkostwo czlonkostwo) {
		getCzlonkostwos().remove(czlonkostwo);
		czlonkostwo.setKlient(null);

		return czlonkostwo;
	}

}