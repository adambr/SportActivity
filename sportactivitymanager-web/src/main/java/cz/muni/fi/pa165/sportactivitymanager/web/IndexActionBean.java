/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivitymanager.web;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author Petr Jelinek
 */
@UrlBinding("/")
public class IndexActionBean extends BaseActionBean {
    
    @DefaultHandler
    public Resolution index() {
        return new ForwardResolution("/index.jsp");
    }
    
}
