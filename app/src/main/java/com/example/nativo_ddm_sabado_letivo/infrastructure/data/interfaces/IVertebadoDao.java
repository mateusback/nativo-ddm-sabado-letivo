package com.example.nativo_ddm_sabado_letivo.infrastructure.data.interfaces;


import com.example.nativo_ddm_sabado_letivo.domain.entities.Vertebrado;

import java.util.List;

public interface IVertebadoDao {
    void adicionarVertebrado(Vertebrado vertebrado);
    List<Vertebrado> listarVertebrados();
    void atualizarVertebrado(Vertebrado vertebrado);
    void deletarVertebrado(int id);
}
