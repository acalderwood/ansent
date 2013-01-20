/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import de.reichel.bean.TeileBean;
import de.reichel.bean.TeileEdit;
import de.reichel.bean.TeileNew;
import de.reichel.dao.TeileDAO;
import de.reichel.domain.model.RepairTeile;
import de.reichel.domain.model.Teile;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alastair Calderwood
 */
@Repository
public class TeileDAOImpl implements TeileDAO {

    private static final Log log = LogFactory.getLog(TeileDAOImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = false)
    public void updateRepairTeile(TeileEdit backingBean) {
        Query query = entityManager.createQuery("from RepairTeile repairTeile where repairTeile.idRepairTeile = :idRepairTeile");
        query.setParameter("idRepairTeile", backingBean.getIdRepairTeile());
        RepairTeile repairTeile = (RepairTeile) query.getSingleResult();
        repairTeile.setAnzahl(backingBean.getAnzahl());
        repairTeile.setIdSub(backingBean.getIdSub());
        repairTeile.setIdTeile(backingBean.getIdTeile());
        repairTeile.setTeileEinheit(backingBean.getTeileEinheit());
        repairTeile.setTeileEk(backingBean.getTeileEk());
        repairTeile.setTeileName(backingBean.getTeileName());
        repairTeile.setTeilePreis(backingBean.getTeilePreis());
        repairTeile.setTeileRabatt(backingBean.getTeileRabatt());
        repairTeile.setTimestamp(Calendar.getInstance().getTime());
        entityManager.merge(repairTeile);
    }

    @Transactional(readOnly = false)
    public void addRepairTeile(TeileNew backingBean) {
        RepairTeile repairTeile = new RepairTeile();
        repairTeile.setAnzahl(backingBean.getAnzahl());
        repairTeile.setIdRepair(backingBean.getIdRepair());
        repairTeile.setIdSub(backingBean.getIdSub());
        repairTeile.setIdTeile(backingBean.getIdTeile());
        repairTeile.setTeileEinheit(backingBean.getTeileEinheit());
        repairTeile.setTeileEk(backingBean.getTeileEk());
        repairTeile.setTeileName(backingBean.getTeileName());
        repairTeile.setTeilePreis(backingBean.getTeilePreis());
        repairTeile.setTeileRabatt(backingBean.getTeileRabatt());
        repairTeile.setTimestamp(Calendar.getInstance().getTime());
        entityManager.persist(repairTeile);
        backingBean.setIdRepairTeile(repairTeile.getIdRepairTeile());
    }

    @Transactional(readOnly = false)
    public void removeRepairTeile(TeileEdit backingBean) {
        RepairTeile repairTeile = getRepairTeile(backingBean.getIdRepairTeile());
        entityManager.remove(repairTeile);
    }

    @Transactional(readOnly = true)
    public List<Teile> getAllTeile() {
        Query query = entityManager.createQuery("from Teile teile order by teile.bezeichnung");
        List<Teile> result = query.getResultList();
        return result;
    }

    @Transactional(readOnly = true)
    public Teile getTeile(Integer idTeile) {
        Query query = entityManager.createQuery("from Teile teile where teile.idTeile = :idTeile");
        query.setParameter("idTeile", idTeile);
        return (Teile) query.getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<RepairTeile> getAllRepairTeile() {
        Query query = entityManager.createQuery("from RepairTeile repairTeile order by repairTeile.getTeileName");
        List<RepairTeile> result = query.getResultList();
        return result;
    }

    @Transactional(readOnly = true)
    public RepairTeile getRepairTeile(Integer idRepairTeile) {
        Query query = entityManager.createQuery("from RepairTeile repairTeile where repairTeile.idRepairTeile = :idRepairTeile");
        query.setParameter("idRepairTeile", idRepairTeile);
        return (RepairTeile) query.getSingleResult();
    }
}
