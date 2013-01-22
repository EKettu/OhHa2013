/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elio;


public class Elio {
    
    private String suominimi;
    private String lattari;
    
    
    public Elio (String suominimi, String lattari) {
        this.suominimi = suominimi;
        this.lattari = lattari;
    }
    
    
    public String getSuominimi() {
        return suominimi;
    }
    
    public String getLattari() {
        return lattari;
    }
}
