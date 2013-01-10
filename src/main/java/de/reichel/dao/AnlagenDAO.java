/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao;

import de.reichel.bean.AnlageEdit;
import de.reichel.bean.AnlageNew;
import de.reichel.bean.AnlageSearch;
import de.reichel.domain.model.Anlagen;
import de.reichel.domain.model.AnlagenArt;
import de.reichel.domain.model.AnlagenHersteller;
import de.reichel.domain.model.Betreiber;
import de.reichel.domain.model.Kunden;
import de.reichel.domain.model.Standorte;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author alastair
 */
public interface AnlagenDAO extends Serializable {

    public List<Anlagen> getAllAnlagen();

    public Object[] getAnlagen(String interneNr);

    public List<Object[]> getAnlagen(AnlageSearch anlageSearch);
    
    public long getNumberAnlagen(AnlageSearch anlageSearch);

    public void addAnlagen(AnlageNew backingBean);

    public List<AnlagenArt> getSelectableArt();

    public List<AnlagenHersteller> getSelectableHersteller();

    public List<Standorte> getSelectableStandort();

    public List<Betreiber> getSelectableBetreiber();

    public List<Kunden> getSelectableKunden();

    public void updateAnlagen(AnlageEdit anlageEdit);

    public Object[] getAnlagen(int idAnlagen);

    public Standorte getStandort(int idAnlagen);

    public Betreiber getBetreiber(int idAnlagen);

    public Kunden getKunden(int idAnlagen);
}
