package org.example;

import io.javalin.Javalin;
import org.example.controller.ImovelController;
import org.example.controller.ProprietarioController;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(
                config -> {
                    config.bundledPlugins.enableCors(cors -> cors.addRule(it -> it.anyHost()));
                }).start(7000);

        ProprietarioController proprietariocontroller = new ProprietarioController();
        ImovelController imovelcontroller = new ImovelController();

        app.post("/proprietario", proprietariocontroller::criarProprietario);

        app.post("/imovel", imovelcontroller::criarImovel);
    }
}