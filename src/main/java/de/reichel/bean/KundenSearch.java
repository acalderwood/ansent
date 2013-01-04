/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.KundenDAO;
import de.reichel.domain.model.Kunden;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "kundenSearch")
@RequestScoped
@Controller
public class KundenSearch {

    private HtmlOutputText idSingleKunden;
    @Inject
    private KundenDAO kundenDAO;

    public List<Kunden> getSelectableKunden() {
        return kundenDAO.getSelectableKunden();
    }

    public String fetchEdit() {
        FacesContext context = FacesContext.getCurrentInstance();
        KundenEdit kundenEdit = (KundenEdit) context.getApplication().evaluateExpressionGet(context, "#{kundenEdit}", KundenEdit.class);

        Kunden kunden = kundenDAO.getKunden((Integer)idSingleKunden.getValue());
        kundenEdit.hydrate(kunden);
        return "bearbeitenkunde";
    }
    
    public String fetchView() {
        FacesContext context = FacesContext.getCurrentInstance();
        KundenView kundenView = (KundenView) context.getApplication().evaluateExpressionGet(context, "#{kundenView}", KundenView.class);

        Kunden kunden = kundenDAO.getKunden((Integer)idSingleKunden.getValue());
        kundenView.hydrate(kunden);
        return "ubersichtkunde";
    }
//
//    public HtmlOutputText getIdSingleKunden() {
//        return idSingleKunden;
//    }
//
//    public void setIdSingleKunden(HtmlOutputText idSingleKunden) {
//        System.out.println("Setting idSingleKunden=" + idSingleKunden.getValue());
//        this.idSingleKunden = idSingleKunden;
//    }
    
    public void processSelectedKundenForEdit(ValueChangeEvent event) {
        System.out.println("processSelectedKundenForEdit");
        FacesContext context = FacesContext.getCurrentInstance();
        KundenEdit kundenEdit = (KundenEdit) context.getApplication().evaluateExpressionGet(context, "#{kundenEdit}", KundenEdit.class);

        Kunden kunden = kundenDAO.getKunden((Integer)idSingleKunden.getValue());
        kundenEdit.hydrate(kunden);
    }
}
