package com.example.konectaAPI.servicios;

import com.example.konectaAPI.entidades.Afiliado;
import com.example.konectaAPI.repositorios.AfiliadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AfiliadoServicio {
    @Autowired
    AfiliadoRepositorio afiliadoRepositorio;
    //registrar afiliado
    public Afiliado registrarAfiliado(Afiliado afiliado) throws Exception{
        try{

           return this.afiliadoRepositorio.save(afiliado);

        }catch(Exception error){
            throw new Exception("error ");
        }
    }
    //consultar afiliado
    public Afiliado consultarAfiliado(Integer idAfiliado)throws Exception {
        try {
            Optional<Afiliado>afiliadoBuscado=this.afiliadoRepositorio.findById(idAfiliado);
            if (afiliadoBuscado.isPresent()){//lo encontre en la BD
                return afiliadoBuscado.get();

            }else {//no lo encontre
                throw new Exception("afiliado no encontrado");
            }

        }catch (Exception error){
            throw new Exception("error en la consulta del afiliado");
        }
    }

    //consultarAfiliados
    public List<Afiliado> buscarTodosLosAfiliados() throws Exception{
        try{
            List<Afiliado> listaConsultada=this.afiliadoRepositorio.findAll();
            return listaConsultada;
        }catch (Exception error){
            throw new Exception("error consultando afiliados");
        }
    }
    //modificarDatosAfiliados
    public Afiliado editarAfiliado(Integer id,Afiliado afiliado) throws Exception{
        try {
            Optional<Afiliado>afiliadoBuscado=this.afiliadoRepositorio.findById(id);
            if (afiliadoBuscado.isPresent()){
               //Afiliado afiliadoEditado=this.afiliadoRepositorio.save(afiliado);
              // return afiliadoEditado; paratodo
                Afiliado afiliadoExistente=afiliadoBuscado.get();
                afiliadoExistente.setCorreo(afiliado.getCorreo());
                afiliadoExistente.setTelefono(afiliado.getTelefono());
                Afiliado afiliadoModificado=this.afiliadoRepositorio.save(afiliadoExistente);
                return afiliadoModificado;
            }else{
                throw new Exception("no encontramos este afiliado");
            }
        }catch (Exception error){
            System.out.println(error.getMessage());

            throw new Exception("fallamos editando");

        }

    }

    //borrarAfiliado
    public Boolean retirarAfiliado (Integer id) throws Exception{
       try{
           Boolean afiliadoEncontrado=this.afiliadoRepositorio.existsById(id);
           if (afiliadoEncontrado){
               this.afiliadoRepositorio.deleteById(id);
               return true;
           }else{
               throw new Exception("Usuario no encontrado");
           }

       }catch (Exception error){
          throw new Exception("Error borrando al usuario");
       }
    }
}
