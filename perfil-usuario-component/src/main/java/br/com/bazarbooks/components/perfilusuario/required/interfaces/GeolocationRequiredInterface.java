package br.com.bazarbooks.components.perfilusuario.required.interfaces;

import br.com.bazarbooks.components.perfilusuario.internal.model.Address;

public interface GeolocationRequiredInterface {
    String getCoordinates(Address address);
}