/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao;

import de.reichel.bean.BetreiberEdit;
import de.reichel.bean.BetreiberNew;
import de.reichel.domain.model.Betreiber;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alastair Calderwood
 */
public interface BetreiberDAO extends Serializable  {

    public void updateBetreiber(BetreiberEdit backingBean);

    public void addBetreiber(BetreiberNew backingBean);

    public List<Betreiber> getAllBetreiber();

    public Betreiber getBetreiber(Integer idBetreiber);

    public void loadBetreiber(BetreiberEdit backingBean);
}
