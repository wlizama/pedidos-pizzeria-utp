/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.model;

/**
 *
 * @author jonas
 */
public class Cliente extends Persona{
    
    private int idCliente;    

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(int idCliente, int IdPersona, String nombres, String apellidos, String telefono, TipoPersona tipoPersona, DocumentoIdentidad documentoIdentidad, Estado estado) {
        super(IdPersona, nombres, apellidos, telefono, tipoPersona, documentoIdentidad, estado);
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    
}
