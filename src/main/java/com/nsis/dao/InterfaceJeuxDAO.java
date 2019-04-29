package com.nsis.dao;

import com.nsis.bo.JeuxBO;
import com.nsis.exception.JeuxDAOException;

public interface InterfaceJeuxDAO {

	void insertJeux(JeuxBO jeux) throws JeuxDAOException;

}
