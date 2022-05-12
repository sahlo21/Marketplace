package co.uniquindio.edu.co.Marketplace.model;

public class MeGusta {
String codVendedor;

public MeGusta(String codVendedor) {
	super();
	this.codVendedor = codVendedor;
}

public String getCodVendedor() {
	return codVendedor;
}

public void setCodVendedor(String codVendedor) {
	this.codVendedor = codVendedor;
}

@Override
public String toString() {
	return "MeGusta [codVendedor=" + codVendedor + "]";
}


}