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
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author alastair
 */
public interface AnlagenDAO {

    public void setEntityManager(EntityManager entityManager);

    public void updateAnlagen(AnlageEdit backingBean);

    public long getNumberAnlagen(AnlageSearch anlageSearch);

    public void addAnlagen(AnlageNew backingBean);

    public List<Anlagen> getAllAnlagen();

    public Object[] getAnlagen(String interneNr);

    public List<Object[]> getAnlagen(AnlageSearch backingBean);

    public List<AnlagenArt> getSelectableArt();

    public List<AnlagenHersteller> getSelectableHersteller();

    public List<Standorte> getSelectableStandort();

    public List<Betreiber> getSelectableBetreiber();

    public List<Kunden> getSelectableKunden();

    public Object[] getAnlagen(int idAnlagen);

    public int getIdStandort(int idAnlagen);

    public int getIdBetreiber(int idAnlagen);

    public int getIdKunden(int idAnlagen);

    public void loadAnlagen(AnlageEdit backingBean);

    public void searchAnlagen(AnlageSearch anlageSearch);

    public void loadAnlagen(AnlageSearch backingBean);

    public List<Anlagen> getAnlagebyStandort(AnlageSearch backingBean);	
}
