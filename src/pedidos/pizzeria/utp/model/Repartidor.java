/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.model;

/**
 *
 * @author Percy
 */
public class Repartidor extends Persona{
    private int IdRepartidor;
    
    public Repartidor(){
        
    }

    public Repartidor(int IdRepartidor) {
        this.IdRepartidor = IdRepartidor;
    }

    public Repartidor(int IdRepartidor, int IdPersona, String nombres, String apellidos, String telefono, TipoPersona tipoPersona, DocumentoIdentidad documentoIdentidad, Estado estado) {
        super(IdPersona, nombres, apellidos, telefono, tipoPersona, documentoIdentidad, estado);
        this.IdRepartidor = IdRepartidor;
    }

    public int getIdRepartidor() {
        return IdRepartidor;
    }

    public void setIdRepartidor(int IdRepartidor) {
        this.IdRepartidor = IdRepartidor;
    }

    
    
    
    
}

