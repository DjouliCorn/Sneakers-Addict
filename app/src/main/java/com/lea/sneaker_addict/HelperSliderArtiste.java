package com.lea.sneaker_addict;

import android.widget.Button;

public class HelperSliderArtiste {

    int image;
    String artisteName;
    Button buttonArtisteSlider;

    public HelperSliderArtiste(){
        this.image = image;
        this.artisteName = artisteName;
        this.buttonArtisteSlider = buttonArtisteSlider;
    }

    public int getImage(){
        return image;
    }

    public String getArtisteName(){
        return artisteName;
    }

    public Button getButtonArtisteSlider(){
        return buttonArtisteSlider;
    }
}
