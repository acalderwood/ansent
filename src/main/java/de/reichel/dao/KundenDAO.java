/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao;

import de.reichel.bean.KundenEdit;
import de.reichel.bean.KundenNew;
import de.reichel.domain.model.Kunden;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Alastair Calderwood
 */
public interface KundenDAO extends Serializable {

    public void addKunden(KundenNew backingBean);

    public List<Kunden> getSelectableKunden();

    public Kunden getKunden(Integer kundenId);

    public void updateKunden(KundenEdit backingBean);

    public void loadKunden(KundenEdit backingBean);
}
