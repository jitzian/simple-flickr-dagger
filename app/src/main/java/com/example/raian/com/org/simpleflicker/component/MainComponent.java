package com.example.raian.com.org.simpleflicker.component;

import com.example.raian.com.org.simpleflicker.MainActivity;
import com.example.raian.com.org.simpleflicker.module.RestModule;

import javax.inject.Inject;

import dagger.Component;

/**
 * Created by raian on 12/6/16.
 */

@Component(modules = {RestModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
