package com.example.nativo_ddm_sabado_letivo.domain.entities;


import com.example.nativo_ddm_sabado_letivo.domain.entities.common.Animal;

public class Vertebrado extends Animal {
    private String tipoColunaVertebral;
    private String grupo;

    public Vertebrado(String nome, int idade, String especie, String tipoColunaVertebral, String grupo) {
        super(nome, idade, especie);
        this.tipoColunaVertebral = tipoColunaVertebral;
        this.grupo = grupo;
    }

    public String getTipoColunaVertebral() {
        return tipoColunaVertebral;
    }

    public void setTipoColunaVertebral(String tipoColunaVertebral) {
        this.tipoColunaVertebral = tipoColunaVertebral;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
