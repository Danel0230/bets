package dataAccess;

import java.util.Date;

import domain.Registered;

public class DiruaSartuParameter {
	public Registered u;
	public Date data;
	public String mota;

	public DiruaSartuParameter(Registered u, Date data, String mota) {
		this.u = u;
		this.data = data;
		this.mota = mota;
	}
}