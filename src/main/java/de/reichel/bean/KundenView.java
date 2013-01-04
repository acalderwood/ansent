/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "kundenView")
@RequestScoped
@Controller
public class KundenView extends KundenBean {
}
