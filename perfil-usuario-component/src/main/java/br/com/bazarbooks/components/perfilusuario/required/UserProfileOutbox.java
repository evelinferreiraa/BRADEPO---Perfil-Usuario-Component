package br.com.bazarbooks.components.perfilusuario.required;

import br.com.bazarbooks.components.perfilusuario.internal.model.Address;
import br.com.bazarbooks.components.perfilusuario.required.interfaces.GeolocationRequiredInterface;
import br.com.bazarbooks.components.perfilusuario.required.interfaces.ImageUploaderRequiredInterface;
import io.github.lifveras.bredeco_pic_abstract.required.PortOutbox;

public class UserProfileOutbox extends PortOutbox {
    private GeolocationRequiredInterface geolocationProvider;
    private ImageUploaderRequiredInterface imageUploader;

    public UserProfileOutbox() {
        this.geolocationProvider = new GeolocationRequiredInterface() {
            @Override
            public String getCoordinates(Address address) {
                return "Lat: " + (address.getLatitude() != 0.0 ? address.getLatitude() : 10.0 + address.getNumber()) +
                        ", Lon: "
                        + (address.getLongitude() != 0.0 ? address.getLongitude() : -20.0 - address.getNumber());
            }
        };

        this.imageUploader = new ImageUploaderRequiredInterface() {
            @Override
            public String uploadProfileImage(byte[] imageData, String fileName) {
                return fileName + "_simulated.jpg";
            }
        };
    }

    public Object getInterface(String name) {
        switch (name) {
            case "geolocationProvider":
                return geolocationProvider;
            case "imageUploader":
                return imageUploader;
            default:
                throw new IllegalArgumentException("Interface requerida '" + name + "' não encontrada na Outbox.");
        }
    }

    @Override
    public void disconnect() {
        this.geolocationProvider = null;
        this.imageUploader = null;
    }
}