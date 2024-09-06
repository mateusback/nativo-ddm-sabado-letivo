package com.example.nativo_ddm_sabado_letivo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nativo_ddm_sabado_letivo.domain.entities.Vertebrado;
import com.example.nativo_ddm_sabado_letivo.infrastructure.data.sqlite.VertebradoDao;

import java.util.ArrayList;
import java.util.List;

public class VertebradoActivity extends AppCompatActivity {

    private VertebradoDao vertebradoDao;
    private EditText etNome, etIdade, etEspecie, etTipoColuna, etGrupo;
    private ListView listViewVertebrados;
    private ArrayAdapter<String> adapter;
    private List<Vertebrado> vertebradosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertebrado);

        vertebradoDao = new VertebradoDao(this);

        etNome = findViewById(R.id.etNome);
        etIdade = findViewById(R.id.etIdade);
        etEspecie = findViewById(R.id.etEspecie);
        etTipoColuna = findViewById(R.id.etTipoColuna);
        etGrupo = findViewById(R.id.etGrupo);
        listViewVertebrados = findViewById(R.id.listViewVertebrados);

        Button btnAdicionar = findViewById(R.id.btnAdicionar);
        Button btnAtualizar = findViewById(R.id.btnAtualizar);
        Button btnDeletar = findViewById(R.id.btnDeletar);

        carregarVertebrados();

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString();
                int idade = Integer.parseInt(etIdade.getText().toString());
                String especie = etEspecie.getText().toString();
                String tipoColuna = etTipoColuna.getText().toString();
                String grupo = etGrupo.getText().toString();

                Vertebrado novoVertebrado = new Vertebrado(nome, idade, especie, tipoColuna, grupo);
                vertebradoDao.adicionarVertebrado(novoVertebrado);
                Toast.makeText(VertebradoActivity.this, "Vertebrado adicionado!", Toast.LENGTH_SHORT).show();
                carregarVertebrados();
            }
        });

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (listViewVertebrados.getCheckedItemPosition() != ListView.INVALID_POSITION) {
                        Vertebrado vertebradoSelecionado = vertebradosList.get(listViewVertebrados.getCheckedItemPosition());
                        vertebradoSelecionado.setNome(etNome.getText().toString());
                        vertebradoSelecionado.setIdade(Integer.parseInt(etIdade.getText().toString()));
                        vertebradoSelecionado.setEspecie(etEspecie.getText().toString());
                        vertebradoSelecionado.setTipoColunaVertebral(etTipoColuna.getText().toString());
                        vertebradoSelecionado.setGrupo(etGrupo.getText().toString());

                        vertebradoDao.atualizarVertebrado(vertebradoSelecionado);
                        Toast.makeText(VertebradoActivity.this, "Vertebrado atualizado!", Toast.LENGTH_SHORT).show();
                        carregarVertebrados();
                    } else {
                        Toast.makeText(VertebradoActivity.this, "Selecione um vertebrado para atualizar!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(VertebradoActivity.this, "Erro ao atualizar vertebrado!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listViewVertebrados.getCheckedItemPosition() != ListView.INVALID_POSITION) {
                    Vertebrado vertebradoSelecionado = vertebradosList.get(listViewVertebrados.getCheckedItemPosition());
                    vertebradoDao.deletarVertebrado(vertebradoSelecionado.getId());
                    Toast.makeText(VertebradoActivity.this, "Vertebrado deletado!", Toast.LENGTH_SHORT).show();
                    carregarVertebrados();
                } else {
                    Toast.makeText(VertebradoActivity.this, "Selecione um vertebrado para deletar!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listViewVertebrados.setOnItemClickListener((parent, view, position, id) -> {
            Vertebrado vertebradoSelecionado = vertebradosList.get(position);
            etNome.setText(vertebradoSelecionado.getNome());
            etIdade.setText(String.valueOf(vertebradoSelecionado.getIdade()));
            etEspecie.setText(vertebradoSelecionado.getEspecie());
            etTipoColuna.setText(vertebradoSelecionado.getTipoColunaVertebral());
            etGrupo.setText(vertebradoSelecionado.getGrupo());
        });
    }

    private void carregarVertebrados() {
        vertebradosList = vertebradoDao.listarVertebrados();
        List<String> vertebradosDisplay = new ArrayList<>();
        for (Vertebrado v : vertebradosList) {
            vertebradosDisplay.add(v.getNome() + " - " + v.getEspecie() + " - " + v.getIdade());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, vertebradosDisplay);
        listViewVertebrados.setAdapter(adapter);
    }
}