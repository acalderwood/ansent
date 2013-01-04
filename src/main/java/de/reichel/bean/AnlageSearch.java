/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.AnlagenDAO;
import de.reichel.domain.model.Anlagen;
import de.reichel.domain.model.AnlagenArt;
import de.reichel.domain.model.AnlagenHersteller;
import de.reichel.domain.model.Betreiber;
import de.reichel.domain.model.Kunden;
import de.reichel.domain.model.Standorte;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

/**
 *
 * @author alastair
 */
@ManagedBean(name = "anlageSearch")
@RequestScoped
@Controller
public class AnlageSearch extends AnlageBean {

    private static final Log log = LogFactory.getLog(AnlageSearch.class);
    @Inject
    private AnlagenDAO anlagenDAO;
    private HtmlOutputText idSingleAnlagen;
    private List<Integer> selectedArt;
    private List<Integer> selectedHersteller;
    private Object[] anlage;
    private List<Object[]> anlagen;
    private Standorte standorte;
    private Kunden kunden;
    private Betreiber betreiber;

    public List<Object[]> getSelectableAnlagen() {
        return anlagenDAO.getAnlagen(this);
    }

    public String searchEdit() {
        if (anlagenDAO.getNumberAnlagen(this) == 1L) {
            FacesContext context = FacesContext.getCurrentInstance();
            AnlageEdit anlageEdit = (AnlageEdit) context.getApplication().evaluateExpressionGet(context, "#{anlageEdit}", AnlageEdit.class);
            anlageEdit.hydrate(anlagenDAO.getAnlagen(this).get(0));
        }
        return "bearbeitenanlage";
    }

    public String searchView() {
        if (anlagenDAO.getNumberAnlagen(this) == 1L) {
            FacesContext context = FacesContext.getCurrentInstance();
            AnlageView anlageView = (AnlageView) context.getApplication().evaluateExpressionGet(context, "#{anlageView}", AnlageView.class);
            anlageView.hydrate(anlagenDAO.getAnlagen(this).get(0));
        }
        return "ubersichtanlage";
    }

    public String fetchEdit() {
        FacesContext context = FacesContext.getCurrentInstance();
        AnlageEdit anlageEdit = (AnlageEdit) context.getApplication().evaluateExpressionGet(context, "#{anlageEdit}", AnlageEdit.class);

        Object[] anlagenGraph = anlagenDAO.getAnlagen((String) idSingleAnlagen.getValue());
        anlageEdit.hydrate(anlagenGraph);
        return "bearbeitenanlage";
    }

    public String fetchView() {
        System.out.println("fetchView");
        FacesContext context = FacesContext.getCurrentInstance();
        AnlageView anlageView = (AnlageView) context.getApplication().evaluateExpressionGet(context, "#{anlageView}", AnlageView.class);
      System.out.println(idSingleAnlagen.getValue());
        
        Object[] anlagenGraph = anlagenDAO.getAnlagen((String) idSingleAnlagen.getValue());
        anlageView.hydrate(anlagenGraph);
        System.out.println(anlageView.getInterneNr());
        return "ubersichtanlage";
    }

    public List<Integer> getSelectedArt() {
        return selectedArt;
    }

    public void setSelectedArt(List<Integer> selectedArt) {
        this.selectedArt = selectedArt;
    }

    //@TODO select all option?
    public List<Integer> getSelectedHersteller() {
        return selectedHersteller;
    }

    public void setSelectedHersteller(List<Integer> selectedHersteller) {
        this.selectedHersteller = selectedHersteller;
    }

    public List<AnlagenArt> getSelectableArt() {
        return anlagenDAO.getSelectableArt();
    }

    public List<AnlagenHersteller> getSelectableHersteller() {
        return anlagenDAO.getSelectableHersteller();
    }

    public Object[] getAnlage() {
        return anlage;
    }

    public List<Object[]> getAllAnlagen() {
        return anlagenDAO.getAllAnlagen();
    }

    public void setAnlage(Object[] anlage) {
        this.anlage = anlage;
    }

    public HtmlOutputText getIdSingleAnlagen() {
        return idSingleAnlagen;
    }

    public void setIdSingleAnlagen(HtmlOutputText idSingleAnlagen) {
        System.out.println("Setting idSingleAnlagen=" + idSingleAnlagen.getValue());
        this.idSingleAnlagen = idSingleAnlagen;
    }

    public List<Standorte> getSelectableStandort() {
        return anlagenDAO.getSelectableStandort();
    }

    public List<Kunden> getSelectableKunden() {
        return anlagenDAO.getSelectableKunden();
    }

    public List<Betreiber> getSelectableBetreiber() {
        return anlagenDAO.getSelectableBetreiber();
    }

    public Standorte getStandorte() {
        return standorte;
    }

    public void setStandorte(Standorte standorte) {
        this.standorte = standorte;
    }

    public Kunden getKunden() {
        return kunden;
    }

    public void setKunden(Kunden kunden) {
        this.kunden = kunden;
    }

    public Betreiber getBetreiber() {
        return betreiber;
    }

    public void setBetreiber(Betreiber betreiber) {
        this.betreiber = betreiber;
    }

    public void validateBaujahr(FacesContext context, UIComponent validate, Object value) throws ValidatorException {
        if (value != null && !((String) value).equals("")) {
            try {
                Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                FacesMessage msg = new FacesMessage("Baujahr muß ein Jahr sein");
                throw new ValidatorException(msg);
            }
        }
    }


}
