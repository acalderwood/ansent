/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao;

import de.reichel.domain.model.Uvv;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author alastair
 */
public interface UvvDAO extends Serializable {

    public List<Uvv> getSelectableUvv(int idAnlagen);

    public Object[] getUvv(int idUvv);

    public List<Object[]> getUvvPunkte(int idUvv);

    public List<Object[]> getUvvMangel(int idUvv);
}
