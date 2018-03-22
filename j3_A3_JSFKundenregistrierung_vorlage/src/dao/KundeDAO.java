package dao;

import model.Kunde;

public interface KundeDAO {

	// TODO Dummy
	Kunde findKunde(String usr, String pwd);

	/**
	 * 
	 * @param newKunde
	 * @return
	 */
	boolean storeNewKunde(Kunde newKunde);

}