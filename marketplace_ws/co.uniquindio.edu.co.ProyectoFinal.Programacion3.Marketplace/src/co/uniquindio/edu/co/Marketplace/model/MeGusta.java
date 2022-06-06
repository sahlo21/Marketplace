package co.uniquindio.edu.co.Marketplace.model;

import java.io.Serializable;

public class MeGusta implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	String codVendedor;

	public MeGusta() {

	}
/**
 * 
 * @param codVendedor
 */
	public MeGusta(String codVendedor) {
		super();
		this.codVendedor = codVendedor;
	}
/**
 * 
 * @return
 */
	public String getCodVendedor() {
		return codVendedor;
	}
/**
 * 
 * @param codVendedor
 */
	public void setCodVendedor(String codVendedor) {
		this.codVendedor = codVendedor;
	}

	@Override
	public String toString() {
		return "MeGusta [codVendedor=" + codVendedor + "]";
	}

}