package br.com.rest.main;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
}
