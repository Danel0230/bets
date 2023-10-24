package dataAccess;

import domain.ApustuAnitza;

public class JarraitzaileakApustuEginParameter {
	public Double balioa;
	public Integer apustuBikoitzaGalarazi;
	public ApustuAnitza apustuAnitza;

	public JarraitzaileakApustuEginParameter(Double balioa, Integer apustuBikoitzaGalarazi, ApustuAnitza apustuAnitza) {
		this.balioa = balioa;
		this.apustuBikoitzaGalarazi = apustuBikoitzaGalarazi;
		this.apustuAnitza = apustuAnitza;
	}
}