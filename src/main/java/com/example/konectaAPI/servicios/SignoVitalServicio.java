package com.example.konectaAPI.servicios;

import com.example.konectaAPI.entidades.Afiliado;
import com.example.konectaAPI.entidades.Examen;
import com.example.konectaAPI.entidades.SignoVital;
import com.example.konectaAPI.repositorios.ExamenRepositorio;
import com.example.konectaAPI.repositorios.SignoVitalRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SignoVitalServicio {
    @Autowired
    SignoVitalRepositorio signoVitalRepositorio;

    public SignoVital registrarSignoVital(SignoVital signoVital) throws Exception{
        try{

            return this.signoVitalRepositorio.save(signoVital);

        }catch(Exception error){
            throw new Exception("error");
        }
    }
    public SignoVital consultarsignoVital(Integer idSignoVital)throws Exception {
        try {
            Optional<SignoVital> signoVitalBuscado = this.signoVitalRepositorio.findById(idSignoVital);
            if (signoVitalBuscado.isPresent()) {//lo encontre en la BD
                return signoVitalBuscado.get();

            } else {//no lo encontre
                throw new Exception("signo no encontrado");
            }

        } catch (Exception error) {
            throw new Exception("error en la consulta del signo");
        }
    }
    public List<SignoVital> buscarTodosLosSignos() throws Exception{
        try{
            List<SignoVital> listaConsultadaS=this.signoVitalRepositorio.findAll();
            return listaConsultadaS;
        }catch (Exception error){
            throw new Exception("error consultando signos vitales");
        }
    }
    public SignoVital EditarSignoVital(Integer id,SignoVital signoVital) throws Exception{
        try {
            Optional<SignoVital>signoVitalBuscado=this.signoVitalRepositorio.findById(id);
            if (signoVitalBuscado.isPresent()){

                SignoVital signoVitalExistente=signoVitalBuscado.get();
                signoVitalExistente.setNombre(signoVital.getNombre());
                signoVitalExistente.setUnidadMedida(signoVital.getUnidadMedida());
                SignoVital signoVitalModificado=this.signoVitalRepositorio.save(signoVitalExistente);
                return signoVitalModificado;
            }else{
                throw new Exception("no encontramos este afiliado");
            }
        }catch (Exception error){
            System.out.println(error.getMessage());

            throw new Exception("fallamos editando");

        }

    }

    //borrarAfiliado
    public Boolean retirarSignoVital (Integer id) throws Exception{
        try{
            Boolean signoVitalEncontrado=this.signoVitalRepositorio.existsById(id);
            if (signoVitalEncontrado){
                this.signoVitalRepositorio.deleteById(id);
                return true;
            }else{
                throw new Exception("SignoVital no encontrado");
            }

        }catch (Exception error){
            throw new Exception("Error borrando al SignoVital");
        }
    }
}
