package com.ConsigueVentas.TiendaAccesorios.GlobalException;

public class DuplicateResourceException extends RuntimeException{
    public DuplicateResourceException(String message) {
        super(message);
    }
}
