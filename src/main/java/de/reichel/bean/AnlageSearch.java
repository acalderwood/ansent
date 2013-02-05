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
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author alastair
 */
@ManagedBean(name = "anlageSearch")
@ViewScoped
@Scope("view")
@Controller
public class AnlageSearch extends AnlageBean {

    private static final Log log = LogFactory.getLog(AnlageSearch.class);
    private HtmlOutputText idSingleAnlagen;
    private List<Integer> selectedArt;
    private List<Integer> selectedHersteller;
    private Object[] anlage;
    private String searchAnlagenInterneNr;
    private String searchAnlagenArt;
    private String searchAnlagenHersteller;
    private String searchAnlagenTyp;
    private String searchAnlagenFabrikationsNr;
    private String searchAnlagenBaujahr;
    private String searchAnlagenBemerkung;
    private String searchKundenFirmenName;
    private String searchKundenAnsprechPartner;
    private String searchKundenStrasseNr;
    private String searchKundenPlz;
    private String searchKundenOrt;
    private String searchKundenBemerkung;
    private String searchStandortName;
    private String searchStandortAnsprechPartner;
    private String searchStandortStrasseNr;
    private String searchStandortPlz;
    private String searchStandortOrt;
    private String searchStandortBemerkung;
    private String searchBetreiberName;
    private String searchBetreiberAnsprechPartner;
    private String searchBetreiberStrasseNr;
    private String searchBetreiberPlz;
    private String searchBetreiberOrt;
    private String searchBetreiberBemerkung;
    private List<Anlagen> anlagenResultList;

    public void searchAnlagen() {
        anlagenDAO.searchAnlagen(this);
    }

    public void setIdSingleAnlagen(HtmlOutputText idSingleAnlagen) {
        System.out.println("Setting idSingleAnlagen=" + idSingleAnlagen.getValue());
        this.idSingleAnlagen = idSingleAnlagen;
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

    public List<Anlagen> getSelectableAnlagen() {
        return anlagenDAO.getAllAnlagen();
    }

    public List<AnlagenArt> getSelectableArt() {
        return anlagenDAO.getSelectableArt();
    }

    public List<AnlagenHersteller> getSelectableHersteller() {
        return anlagenDAO.getSelectableHersteller();
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

    public Object[] getAnlage() {
        return anlage;
    }

    public void setAnlage(Object[] anlage) {
        this.anlage = anlage;
    }

    public HtmlOutputText getIdSingleAnlagen() {
        return idSingleAnlagen;
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
    
    public String load(){
         log.debug("Load AnlageSearch is called");
         anlagenDAO.loadAnlagen(this);
         return "index";
    }

    /**
     * @return the searchAnlagenInterneNr
     */
    public String getSearchAnlagenInterneNr() {
        return searchAnlagenInterneNr;
    }

    /**
     * @param searchAnlagenInterneNr the searchAnlagenInterneNr to set
     */
    public void setSearchAnlagenInterneNr(String searchAnlagenInterneNr) {
        this.searchAnlagenInterneNr = searchAnlagenInterneNr;
    }

    /**
     * @return the searchAnlagenArt
     */
    public String getSearchAnlagenArt() {
        return searchAnlagenArt;
    }

    /**
     * @param searchAnlagenArt the searchAnlagenArt to set
     */
    public void setSearchAnlagenArt(String searchAnlagenArt) {
        this.searchAnlagenArt = searchAnlagenArt;
    }

    /**
     * @return the searchAnlagenHersteller
     */
    public String getSearchAnlagenHersteller() {
        return searchAnlagenHersteller;
    }

    /**
     * @param searchAnlagenHersteller the searchAnlagenHersteller to set
     */
    public void setSearchAnlagenHersteller(String searchAnlagenHersteller) {
        this.searchAnlagenHersteller = searchAnlagenHersteller;
    }

    /**
     * @return the searchAnlagenTyp
     */
    public String getSearchAnlagenTyp() {
        return searchAnlagenTyp;
    }

    /**
     * @param searchAnlagenTyp the searchAnlagenTyp to set
     */
    public void setSearchAnlagenTyp(String searchAnlagenTyp) {
        this.searchAnlagenTyp = searchAnlagenTyp;
    }

    /**
     * @return the searchAnlagenFabrikationsNr
     */
    public String getSearchAnlagenFabrikationsNr() {
        return searchAnlagenFabrikationsNr;
    }

    /**
     * @param searchAnlagenFabrikationsNr the searchAnlagenFabrikationsNr to set
     */
    public void setSearchAnlagenFabrikationsNr(String searchAnlagenFabrikationsNr) {
        this.searchAnlagenFabrikationsNr = searchAnlagenFabrikationsNr;
    }

    /**
     * @return the searchAnlagenBaujahr
     */
    public String getSearchAnlagenBaujahr() {
        return searchAnlagenBaujahr;
    }

    /**
     * @param searchAnlagenBaujahr the searchAnlagenBaujahr to set
     */
    public void setSearchAnlagenBaujahr(String searchAnlagenBaujahr) {
        this.searchAnlagenBaujahr = searchAnlagenBaujahr;
    }

    /**
     * @return the searchAnlagenBemerkung
     */
    public String getSearchAnlagenBemerkung() {
        return searchAnlagenBemerkung;
    }

    /**
     * @param searchAnlagenBemerkung the searchAnlagenBemerkung to set
     */
    public void setSearchAnlagenBemerkung(String searchAnlagenBemerkung) {
        this.searchAnlagenBemerkung = searchAnlagenBemerkung;
    }

    /**
     * @return the searchKundenFirmenName
     */
    public String getSearchKundenFirmenName() {
        return searchKundenFirmenName;
    }

    /**
     * @param searchKundenFirmenName the searchKundenFirmenName to set
     */
    public void setSearchKundenFirmenName(String searchKundenFirmenName) {
        this.searchKundenFirmenName = searchKundenFirmenName;
    }

    /**
     * @return the searchKundenAnsprechPartner
     */
    public String getSearchKundenAnsprechPartner() {
        return searchKundenAnsprechPartner;
    }

    /**
     * @param searchKundenAnsprechPartner the searchKundenAnsprechPartner to set
     */
    public void setSearchKundenAnsprechPartner(String searchKundenAnsprechPartner) {
        this.searchKundenAnsprechPartner = searchKundenAnsprechPartner;
    }

    /**
     * @return the searchKundenStrasseNr
     */
    public String getSearchKundenStrasseNr() {
        return searchKundenStrasseNr;
    }

    /**
     * @param searchKundenStrasseNr the searchKundenStrasseNr to set
     */
    public void setSearchKundenStrasseNr(String searchKundenStrasseNr) {
        this.searchKundenStrasseNr = searchKundenStrasseNr;
    }

    /**
     * @return the searchKundenPlz
     */
    public String getSearchKundenPlz() {
        return searchKundenPlz;
    }

    /**
     * @param searchKundenPlz the searchKundenPlz to set
     */
    public void setSearchKundenPlz(String searchKundenPlz) {
        this.searchKundenPlz = searchKundenPlz;
    }

    /**
     * @return the searchKundenOrt
     */
    public String getSearchKundenOrt() {
        return searchKundenOrt;
    }

    /**
     * @param searchKundenOrt the searchKundenOrt to set
     */
    public void setSearchKundenOrt(String searchKundenOrt) {
        this.searchKundenOrt = searchKundenOrt;
    }

    /**
     * @return the searchKundenBemerkung
     */
    public String getSearchKundenBemerkung() {
        return searchKundenBemerkung;
    }

    /**
     * @param searchKundenBemerkung the searchKundenBemerkung to set
     */
    public void setSearchKundenBemerkung(String searchKundenBemerkung) {
        this.searchKundenBemerkung = searchKundenBemerkung;
    }

    /**
     * @return the searchStandortName
     */
    public String getSearchStandortName() {
        return searchStandortName;
    }

    /**
     * @param searchStandortName the searchStandortName to set
     */
    public void setSearchStandortName(String searchStandortName) {
        this.searchStandortName = searchStandortName;
    }

    /**
     * @return the searchStandortAnsprechPartner
     */
    public String getSearchStandortAnsprechPartner() {
        return searchStandortAnsprechPartner;
    }

    /**
     * @param searchStandortAnsprechPartner the searchStandortAnsprechPartner to
     * set
     */
    public void setSearchStandortAnsprechPartner(String searchStandortAnsprechPartner) {
        this.searchStandortAnsprechPartner = searchStandortAnsprechPartner;
    }

    /**
     * @return the searchStandortStrasseNr
     */
    public String getSearchStandortStrasseNr() {
        return searchStandortStrasseNr;
    }

    /**
     * @param searchStandortStrasseNr the searchStandortStrasseNr to set
     */
    public void setSearchStandortStrasseNr(String searchStandortStrasseNr) {
        this.searchStandortStrasseNr = searchStandortStrasseNr;
    }

    /**
     * @return the searchStandortPlz
     */
    public String getSearchStandortPlz() {
        return searchStandortPlz;
    }

    /**
     * @param searchStandortPlz the searchStandortPlz to set
     */
    public void setSearchStandortPlz(String searchStandortPlz) {
        this.searchStandortPlz = searchStandortPlz;
    }

    /**
     * @return the searchStandortOrt
     */
    public String getSearchStandortOrt() {
        return searchStandortOrt;
    }

    /**
     * @param searchStandortOrt the searchStandortOrt to set
     */
    public void setSearchStandortOrt(String searchStandortOrt) {
        this.searchStandortOrt = searchStandortOrt;
    }

    /**
     * @return the searchStandortBemerkung
     */
    public String getSearchStandortBemerkung() {
        return searchStandortBemerkung;
    }

    /**
     * @param searchStandortBemerkung the searchStandortBemerkung to set
     */
    public void setSearchStandortBemerkung(String searchStandortBemerkung) {
        this.searchStandortBemerkung = searchStandortBemerkung;
    }

    /**
     * @return the searchBetreiberName
     */
    public String getSearchBetreiberName() {
        return searchBetreiberName;
    }

    /**
     * @param searchBetreiberName the searchBetreiberName to set
     */
    public void setSearchBetreiberName(String searchBetreiberName) {
        this.searchBetreiberName = searchBetreiberName;
    }

    /**
     * @return the searchBetreiberAnsprechPartner
     */
    public String getSearchBetreiberAnsprechPartner() {
        return searchBetreiberAnsprechPartner;
    }

    /**
     * @param searchBetreiberAnsprechPartner the searchBetreiberAnsprechPartner
     * to set
     */
    public void setSearchBetreiberAnsprechPartner(String searchBetreiberAnsprechPartner) {
        this.searchBetreiberAnsprechPartner = searchBetreiberAnsprechPartner;
    }

    /**
     * @return the searchBetreiberStrasseNr
     */
    public String getSearchBetreiberStrasseNr() {
        return searchBetreiberStrasseNr;
    }

    /**
     * @param searchBetreiberStrasseNr the searchBetreiberStrasseNr to set
     */
    public void setSearchBetreiberStrasseNr(String searchBetreiberStrasseNr) {
        this.searchBetreiberStrasseNr = searchBetreiberStrasseNr;
    }

    /**
     * @return the searchBetreiberPlz
     */
    public String getSearchBetreiberPlz() {
        return searchBetreiberPlz;
    }

    /**
     * @param searchBetreiberPlz the searchBetreiberPlz to set
     */
    public void setSearchBetreiberPlz(String searchBetreiberPlz) {
        this.searchBetreiberPlz = searchBetreiberPlz;
    }

    /**
     * @return the searchBetreiberOrt
     */
    public String getSearchBetreiberOrt() {
        return searchBetreiberOrt;
    }

    /**
     * @param searchBetreiberOrt the searchBetreiberOrt to set
     */
    public void setSearchBetreiberOrt(String searchBetreiberOrt) {
        this.searchBetreiberOrt = searchBetreiberOrt;
    }

    /**
     * @return the searchBetreiberBemerkung
     */
    public String getSearchBetreiberBemerkung() {
        return searchBetreiberBemerkung;
    }

    /**
     * @param searchBetreiberBemerkung the searchBetreiberBemerkung to set
     */
    public void setSearchBetreiberBemerkung(String searchBetreiberBemerkung) {
        this.searchBetreiberBemerkung = searchBetreiberBemerkung;
    }

    /**
     * @return the anlagenResultList
     */
    public List<Anlagen> getAnlagenResultList() {
        return anlagenResultList;
    }

    /**
     * @param anlagenResultList the anlagenResultList to set
     */
    public void setAnlagenResultList(List<Anlagen> anlagenResultList) {
        this.anlagenResultList = anlagenResultList;
    }
    public List<Anlagen> getAnlagebyStandort() {
        return anlagenDAO.getAnlagebyStandort(this);
    }
    
}
