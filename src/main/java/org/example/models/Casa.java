package org.example.models;

import java.util.Date;
import java.util.UUID;

public class Casa extends Imovel {
    private boolean mobiliada;

    public Casa(UUID id, String nome, String descricao, String tipo,
                Date anoDeConstrucao, boolean disponivel, boolean mobiliada, Proprietario proprietario) {
        super(
                id,
                nome,
                descricao,
                tipo,
                anoDeConstrucao,
                disponivel,
                proprietario
        );
        this.mobiliada = mobiliada;
    }

    public void setMobiliada(boolean mobiliada) {
        this.mobiliada = mobiliada;
    }

}
