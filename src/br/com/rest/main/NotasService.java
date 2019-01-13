package br.com.rest.main;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.dao.NotasDao;
import br.com.rest.entidade.Notas;

@Path("/notas")
public class NotasService {
	private NotasDao notasDao;
	@PostConstruct
	private void init() {
		notasDao = new NotasDao();
	}
	
	@GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Notas> listarNotas() {
        List<Notas> lista = null;
        try {
            lista = notasDao.listarNotas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
	 @POST
	    @Path("/add")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public String addNota(Notas nota) {
	        String msg = "";
	 
	        System.out.println(nota.getTitulo());
	 
	        try {
	            int idGerado = notasDao.addNota(nota);
	 
	            msg = "nota gerada = " + String.valueOf(idGerado);
	        } catch (Exception e) {
	            msg = "Erro ao add a nota!";
	            e.printStackTrace();
	        }
	 
	        return msg;
	    }
	@GET
    @Path("/get/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Notas buscarPorId(@PathParam("id") int idNota) {
        Notas nota = null;
        try {
            nota = notasDao.buscarNotaPorId(idNota);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return nota;
    }
	@PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String editarNota(Notas nota, @PathParam("id") int idNota) {
        String msg = "";
         
        System.out.println(nota.getTitulo());
         
        try {
            notasDao.editarNota(nota, idNota);
             
            msg = "Nota editada com sucesso!";
        } catch (Exception e) {
            msg = "Erro ao editar a nota!";
            e.printStackTrace();
        }
         
        return msg;
    }
     
    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String removerNota(@PathParam("id") int idNota) {
        String msg = "";
         
        try {
            notasDao.removerNota(idNota);
             
            msg = "Nota removida com sucesso!";
        } catch (Exception e) {
            msg = "Erro ao remover a nota!";
            e.printStackTrace();
        }
         
        return msg;
    }
}
