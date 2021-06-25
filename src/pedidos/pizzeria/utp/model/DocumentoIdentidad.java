/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.model;

/**
 *
 * @author wilderlizama
 */
public class DocumentoIdentidad {
    private int IdDocumentoIdentidad;
    private String numero;
    private TipoDocumentoIdentidad tipoDocumentoIdentidad;

    public DocumentoIdentidad() {
    }

    public DocumentoIdentidad(int IdDocumentoIdentidad, String numero, TipoDocumentoIdentidad tipoDocumentoIdentidad) {
        this.IdDocumentoIdentidad = IdDocumentoIdentidad;
        this.numero = numero;
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
    }

    public int getIdDocumentoIdentidad() {
        return IdDocumentoIdentidad;
    }

    public void setIdDocumentoIdentidad(int IdDocumentoIdentidad) {
        this.IdDocumentoIdentidad = IdDocumentoIdentidad;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoDocumentoIdentidad getTipoDocumentoIdentidad() {
        return tipoDocumentoIdentidad;
    }

    public void setTipoDocumentoIdentidad(TipoDocumentoIdentidad tipoDocumentoIdentidad) {
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
    }
    
    
    
    
}
